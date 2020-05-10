package com.streammedia.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import com.streammedia.utility.AWSS3UploadUtil;
import com.streammedia.utility.JavaHelperMethods;
import com.streammedia.utility.PropertiesLoader;
import lombok.extern.log4j.Log4j2;

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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * The type Film edit.
 * Responsible for getting form data to update Film's record.
 *
 * @author Jeanne
 * @version 1.0
 * @since 05-05-2020
 */
@WebServlet(
        urlPatterns = {"/film-edit"}
)
@Log4j2
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10 * 10 * 10,      // 1GB
        maxRequestSize = 1024 * 1024 * 5 * 5 * 10   // 100MB
)
public class FilmEdit extends HttpServlet implements PropertiesLoader {
    private GenericDao filmDao;
    private GenericDao userDao;
    private GenericDao crewDao;
    private GenericDao genreDao;

    @Override
    public void init() throws ServletException {
        filmDao = new GenericDao(Film.class);
        userDao = new GenericDao(User.class);
        crewDao = new GenericDao(Crew.class);
        genreDao = new GenericDao(Genre.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.isUserInRole("admin")) {
            int id = Integer.parseInt(req.getParameter("uid"));
            Film film = (Film) filmDao.getById(id);
            List<Genre> genreList = genreDao.getAll();
            List<Genre> crewList = crewDao.getAll();
            req.setAttribute("genres", genreList);
            req.setAttribute("crews", crewList);
            req.setAttribute("film", film);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/film/filmAddEdit.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Properties properties = loadProperties("/aws.properties");
            if (req.isUserInRole("admin")) {
                User user = (User) userDao.getByPropertyEqual("username", req.getRemoteUser()).get(0);
                Film filmToEdit = (Film) filmDao.getById(Integer.parseInt(req.getParameter("uid")));
                filmToEdit.setTitle(req.getParameter("title"));
                filmToEdit.setDirector(req.getParameter("director"));
                filmToEdit.setDuration(LocalTime.parse(req.getParameter("duration")));
                filmToEdit.setCover(req.getParameter("cover"));
                filmToEdit.setPublicationDate(LocalDateTime.parse(req.getParameter("pub_date").trim()));
                filmToEdit.setVideo(req.getParameter("video"));
                filmToEdit.setEpisode(req.getParameter("episode"));
                filmToEdit.setLink(req.getParameter("link"));
                filmToEdit.setSummary(req.getParameter("summary"));
                Set<Genre> genreList = filmToEdit.getGenres();
                Set<Crew> crewList = filmToEdit.getCrews();
                String[] genreIds = req.getParameterValues("genre");
                String[] crewIds = req.getParameterValues("crew");
                if (genreIds != null && crewIds != null) {
                    genreList.clear();
                    crewList.clear();
                    retrieveCrews(crewList, crewIds);
                    retrieveGenres(genreList, genreIds);
                } else if (genreIds == null && crewIds != null) {
                    crewList.clear();
                    retrieveCrews(crewList, crewIds);
                    for (Genre genre : genreList) {
                        filmToEdit.addGenre(genre);
                    }
                } else if (genreIds != null && crewIds == null) {
                    genreList.clear();
                    retrieveGenres(genreList, genreIds);
                    for (Crew crew : crewList) {
                        filmToEdit.addCrew(crew);
                    }
                } else {
                    for (Crew crew : crewList) {
                        filmToEdit.addCrew(crew);
                    }
                    for (Genre genre : genreList) {
                        filmToEdit.addGenre(genre);
                    }
                }
                Part part = req.getPart("cover");
                if (!part.getSubmittedFileName().isEmpty()) {
                    String fileName = part.getSubmittedFileName().toLowerCase();
                    if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith("jpeg")) {
                        String accessKeyId = properties.getProperty("aws.access.key.id");
                        String secretAccessKey = properties.getProperty("aws.secret.access.key");
                        String region = properties.getProperty("aws.region");
                        String bucketName = properties.getProperty("aws.bucket.name");
                        String subdirectory = "media/" + Film.class.getSimpleName().toLowerCase();
                        String fileObjKeyName = subdirectory + "/covers/" + filmToEdit.getTitle().toLowerCase().trim()
                                .replace(" ", "_") + "_" + filmToEdit.getDirector().trim()
                                .replace(" ", "_") + fileName.substring(
                                fileName.lastIndexOf("."));
                        String fileToUpload = JavaHelperMethods.saveFileName(System.getProperty("java.io.tmpdir"), part);

                        AWSS3UploadUtil awsS3 = new AWSS3UploadUtil();
                        String fileUrl = awsS3.uploadToAWSS3(part, accessKeyId, secretAccessKey, region, bucketName, fileObjKeyName, fileToUpload);
                        filmToEdit.setCover(fileUrl);
                        Files.deleteIfExists(Paths.get(fileToUpload));

                    } else {
                        String errorMessage = "Cover not saved!Unsupported file extension! " +
                                "<br/>Please only upload JPG, JPEG or PNG files";
                        req.getSession().setAttribute("unsupportedExtension",errorMessage);
                    }
                }
                Part videoPart = req.getPart("video");
                if (!videoPart.getSubmittedFileName().isEmpty()) {
                    String fileName = videoPart.getSubmittedFileName().toLowerCase();
                    if (fileName.endsWith(".mp4")) {
                        String accessKeyId = properties.getProperty("aws.access.key.id");
                        String secretAccessKey = properties.getProperty("aws.secret.access.key");
                        String region = properties.getProperty("aws.region");
                        String bucketName = properties.getProperty("aws.bucket.name");
                        String subdirectory = "media/" + Film.class.getSimpleName().toLowerCase();
                        String fileObjKeyName = subdirectory + "/videos/" + filmToEdit.getTitle().toLowerCase().trim()
                                .replace(" ", "_") + "_" + filmToEdit.getDirector().trim()
                                .replace(" ", "_") + fileName.substring(
                                fileName.lastIndexOf("."));
                        String fileToUpload = JavaHelperMethods.saveFileName(System.getProperty("java.io.tmpdir"), videoPart);

                        AWSS3UploadUtil awsS3 = new AWSS3UploadUtil();
                        String fileUrl = awsS3.uploadToAWSS3(videoPart, accessKeyId, secretAccessKey, region, bucketName, fileObjKeyName, fileToUpload);
                        filmToEdit.setVideo(fileUrl);
                        Files.deleteIfExists(Paths.get(fileToUpload));

                    } else {
                        String errorMessage = "Video not saved!Unsupported file extension! " +
                                "<br/>Please only upload mp4 files";
                        req.getSession().setAttribute("unsupportedVideoExtension",errorMessage);
                    }
                }

                log.error(filmToEdit);
                filmDao.saveOrUpdate(filmToEdit);
                String successMessage = "Successfully updated " + Film.class.getSimpleName();
                req.getSession().setAttribute("filmEditSuccessMessage", successMessage);
                resp.sendRedirect("film-details?uid=" + filmToEdit.getFilmId());
            } else {
                req.getSession().setAttribute("filmErrorMessage", "Failed to update " + Film.class.getSimpleName());
                req.getRequestDispatcher("/film/filmAddEdit.jsp").forward(req, resp);
            }
        } catch (NumberFormatException numberFormatException) {
            log.error(numberFormatException);
        } catch (
                AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it, so it returned an error response.
            log.error(e);
        } catch (
                SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            log.error(e);
        } catch (NullPointerException npe) {
            log.error("Object Does not Exists", npe);
        } catch (Exception exception) {
            log.error(exception);
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
