package com.streammedia.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import com.streammedia.utility.AWSS3UploadUtil;
import com.streammedia.utility.JavaHelperMethods;
import com.streammedia.utility.PropertiesLoader;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.jni.ProcErrorCallback;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.*;
import java.util.*;

/**
 * The type Film add.
 * https://dzone.com/tutorials/java/hibernate/hibernate-example/hibernate-mapping-many-to-many-using-annotations-1.html
 * http://websystique.com/hibernate/hibernate-many-to-many-bidirectional-annotation-example/
 */
@WebServlet(
        urlPatterns = {"/film-new"}
)
@Log4j2
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10 * 10 * 10,      // 1GB
        maxRequestSize = 1024 * 1024 * 5 * 5 * 5   // 100MB
)
public class FilmAdd extends HttpServlet implements PropertiesLoader {
    private GenericDao filmDao;
    private GenericDao userDao;
    private GenericDao crewDao;
    private GenericDao genreDao;

    /**
     * Init.
     * Responsible to create an instance of dao.
     *
     * @throws ServletException the servlet exception
     */
    @Override
    public void init() throws ServletException {
        filmDao = new GenericDao(Film.class);
        userDao = new GenericDao(User.class);
        crewDao = new GenericDao(Crew.class);
        genreDao = new GenericDao(Genre.class);
    }

    /**
     * Do get.
     *
     * @param req  the req
     * @param resp the resp
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Crew> crewList = crewDao.getAll();
        req.setAttribute("crews", crewList);
        List<Genre> genreList = genreDao.getAll();
        req.setAttribute("genres", genreList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/film/filmAddEdit.jsp");
        dispatcher.forward(req, resp);
    }

    /**
     * Do post.
     *
     * @param req  the req
     * @param resp the resp
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.isUserInRole("admin")){
        User user = (User) userDao.getByPropertyEqual("username", req.getRemoteUser()).get(0);
        Film newFilm = new Film();
        Set<Crew> crewList = new HashSet<>();
        Set<Genre> genreList = new HashSet<>();
        Set<Film> films = new HashSet<Film>();
        try {
            Properties properties = loadProperties("/aws.properties");
            String[] genreIds = req.getParameterValues("genre");
            String[] crewIds = req.getParameterValues("crew");
            retrieveGenres(genreList, genreIds);
            retrieveCrews(crewList, crewIds);
            newFilm.setUser(user);
            log.debug("Genres Size: " + genreList.size());
            log.debug("Crew Size " + crewList.size());
            newFilm.setTitle(req.getParameter("title"));
            newFilm.setDirector(req.getParameter("director"));
            log.debug(newFilm.getDirector());
            // TO DO create duration format
            newFilm.setDuration(LocalTime.parse(req.getParameter("duration")));
            log.debug(newFilm.getDuration());
            String pubDate = req.getParameter("pub_date").trim();
            if (pubDate == null || pubDate.length() <= 0) {
                log.debug(newFilm.getPublicationDate());
                newFilm.setPublicationDate(LocalDateTime.now());
            } else {
                newFilm.setPublicationDate(LocalDateTime.parse(pubDate));
            }
            newFilm.setEpisode(req.getParameter("episode"));
            newFilm.setLink(req.getParameter("link"));
            newFilm.setSummary(req.getParameter("summary"));
            log.debug("Title of th  Film: " + newFilm.getTitle());
            for (Genre genre : genreList) {
                newFilm.getGenres().add(genre);
            }
            for (Crew crew : crewList) {
                newFilm.getCrews().add(crew);
            }
            films.add(newFilm);
            Part part = req.getPart("cover");
            if (part.getSubmittedFileName().isEmpty()) {
                newFilm.setCover("media/trailer1.jpg");
            } else {
                String fileName = part.getSubmittedFileName().toLowerCase();
                if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith("jpeg")) {
                    String accessKeyId = properties.getProperty("aws.access.key.id");
                    String secretAccessKey = properties.getProperty("aws.secret.access.key");
                    String region = properties.getProperty("aws.region");
                    String bucketName = properties.getProperty("aws.bucket.name");
                    String subdirectory = "media/" + Film.class.getSimpleName().toLowerCase();
                    String fileObjKeyName = subdirectory + "/covers/" + newFilm.getTitle().toLowerCase().trim()
                            .replace(" ", "_") + "_" + newFilm.getDirector().trim()
                            .replace(" ", "_") + fileName.substring(
                            fileName.lastIndexOf("."));
                    String fileToUpload = JavaHelperMethods.saveFileName(System.getProperty("java.io.tmpdir"), part);

                    AWSS3UploadUtil awsS3 = new AWSS3UploadUtil();
                    String fileUrl = awsS3.uploadToAWSS3(part, accessKeyId, secretAccessKey, region, bucketName, fileObjKeyName, fileToUpload);
                    newFilm.setCover(fileUrl);
                    Files.deleteIfExists(Paths.get(fileToUpload));

                } else {
                    String errorMessage = "Cover not saved!Unsupported file extension! " +
                            "<br/>Please only upload JPG, JPEG or PNG files";
                    req.getSession().setAttribute("unsupportedExtension",errorMessage);
                }
            }
            Part videoPart = req.getPart("video");
            if (videoPart.getSubmittedFileName().isEmpty()) {
                newFilm.setVideo("media/trailer.mp4");
            } else {
                String fileName = videoPart.getSubmittedFileName().toLowerCase();
                if (fileName.endsWith(".mp4")) {
                    String accessKeyId = properties.getProperty("aws.access.key.id");
                    String secretAccessKey = properties.getProperty("aws.secret.access.key");
                    String region = properties.getProperty("aws.region");
                    String bucketName = properties.getProperty("aws.bucket.name");
                    String subdirectory = "media/" + Film.class.getSimpleName().toLowerCase();
                    String fileObjKeyName = subdirectory + "/videos/" + newFilm.getTitle().toLowerCase().trim()
                            .replace(" ", "_") + "_" + newFilm.getDirector().trim()
                            .replace(" ", "_") + fileName.substring(
                            fileName.lastIndexOf("."));
                    String fileToUpload = JavaHelperMethods.saveFileName(System.getProperty("java.io.tmpdir"), videoPart);

                    AWSS3UploadUtil awsS3 = new AWSS3UploadUtil();
                    String fileUrl = awsS3.uploadToAWSS3(videoPart, accessKeyId, secretAccessKey, region, bucketName, fileObjKeyName, fileToUpload);
                    newFilm.setVideo(fileUrl);
                    Files.deleteIfExists(Paths.get(fileToUpload));

                } else {
                    String errorMessage = "Video not saved!Unsupported file extension! " +
                            "<br/>Please only upload mp4 files";
                    req.getSession().setAttribute("unsupportedVideoExtension",errorMessage);
                }
            }

            log.debug("Servlet Film " + newFilm.getDuration());

                filmDao.insert(newFilm);
                String successMessage = "Successfully added " + Film.class.getSimpleName();
                req.getSession().setAttribute("filmAddSuccessMessage", successMessage);
                resp.sendRedirect("films");

        } catch (NumberFormatException numberFormatException) {
            log.error(numberFormatException);
        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            log.error(e);
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            log.error(e);
        } catch (NullPointerException npe) {
            log.error("Object Does not Exists", npe);
        }catch (Exception exception) {
            log.error(exception);
        }
        } else {
            req.getSession().setAttribute("filmErrorMessage", "Failed to add " + Film.class.getSimpleName());
            req.getRequestDispatcher("/film/filmAddEdit.jsp").forward(req, resp);
        }
    }
    private void retrieveGenres(Set<Genre> genreList, String[] genreIds) {
        for (String id : genreIds) {
            genreList.add((Genre) genreDao.getById(Integer.parseInt(id)));
        }
    }

    private void retrieveCrews(Set<Crew> crewList, String[] crewIds) {
        for (String id : crewIds) {
            crewList.add((Crew) crewDao.getById(Integer.parseInt(id)));
        }
    }
}
