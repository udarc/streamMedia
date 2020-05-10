package com.streammedia.controller;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.streammedia.entity.*;
import com.streammedia.perisistence.GenericDao;
import com.streammedia.utility.AWSS3UploadUtil;
import com.streammedia.utility.JavaHelperMethods;
import com.streammedia.utility.PropertiesLoader;
import lombok.extern.log4j.Log4j2;
import org.hibernate.query.procedure.ProcedureParameter;

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
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * The type Book add.
 * https://dzone.com/tutorials/java/hibernate/hibernate-example/hibernate-mapping-many-to-many-using-annotations-1.html
 * http://websystique.com/hibernate/hibernate-many-to-many-bidirectional-annotation-example/
 * Gets form data and send them to dao for persisting them
 * @author Jeanne
 * @version 1.0
 * @since 05-05-2020
 */
@WebServlet(
        urlPatterns = {"/book-new"}
)
@Log4j2
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10 * 10 * 10,      // 1GB
        maxRequestSize = 1024 * 1024 * 5 * 5 * 5   // 100MB
)
public class BookAdd extends HttpServlet implements PropertiesLoader {
    private GenericDao bookDao;
    private GenericDao userDao;
    private GenericDao catDao;
    @Override
    public void init() throws ServletException {
        bookDao = new GenericDao(Book.class);
        userDao = new GenericDao(User.class);
        catDao = new GenericDao(BkCategory.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<BkCategory> categoryList = catDao.getAll();
        req.setAttribute("categories",categoryList );
        log.debug(categoryList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/book/bookAddEdit.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) userDao.getByPropertyEqual("username", req.getRemoteUser()).get(0);
        Book newBook = new Book();
        Set<BkCategory> categoryList = new HashSet<>();
        Set<Book> books = new HashSet<Book>();
        String[] categoryIds = req.getParameterValues("category");

        try {
            Properties properties = loadProperties("/aws.properties");
            retrieveCategories(categoryList, categoryIds);
            newBook.setUser(user);
            newBook.setTitle(req.getParameter("title"));
            newBook.setAuthor(req.getParameter("author"));
            newBook.setISBN(req.getParameter("isbn"));
            newBook.setPublisher(req.getParameter("publisher"));
            newBook.setEdition(req.getParameter("edition"));
            newBook.setPageNumber(Integer.parseInt(req.getParameter("page_number")));
            String pubDate = req.getParameter("pub_date").trim();
            if( pubDate == null || pubDate.length() <= 0 ) {
                log.debug(newBook.getPublicationDate());
                newBook.setPublicationDate(LocalDateTime.now());
            }
            else {
                newBook.setPublicationDate(LocalDateTime.parse(pubDate));
            }
            newBook.setSummary(req.getParameter("summary"));
            for (BkCategory category : categoryList) {
                newBook.getCategories().add(category);
            }
            books.add(newBook);
            Part part = req.getPart("cover");
            if (part.getSubmittedFileName().isEmpty()) {
                newBook.setCover("media/book.png");
            } else {
                String fileName = part.getSubmittedFileName().toLowerCase();
                if (fileName.endsWith(".jpg") || fileName.endsWith(".png") || fileName.endsWith("jpeg")) {
                    String accessKeyId = properties.getProperty("aws.access.key.id");
                    String secretAccessKey = properties.getProperty("aws.secret.access.key");
                    String region = properties.getProperty("aws.region");
                    String bucketName = properties.getProperty("aws.bucket.name");
                    String subdirectory = "media/" + Book.class.getSimpleName().toLowerCase();
                    String fileObjKeyName = subdirectory + "/" + newBook.getTitle().toLowerCase().trim()
                            .replace(" ", "_") + "_" + newBook.getAuthor().trim()
                            .replace(" ", "_") + fileName.substring(
                            fileName.lastIndexOf("."));
                    String fileToUpload = JavaHelperMethods.saveFileName(System.getProperty("java.io.tmpdir"), part);

                    AWSS3UploadUtil awsS3 = new AWSS3UploadUtil();
                    String fileUrl = awsS3.uploadToAWSS3(part, accessKeyId, secretAccessKey, region, bucketName, fileObjKeyName, fileToUpload);
                    newBook.setCover(fileUrl);
                    Files.deleteIfExists(Paths.get(fileToUpload));

                } else {
                    String errorMessage = "Cover not saved!Unsupported file extension! " +
                            "<br/>Please only upload JPG, JPEG or PNG files";
                    req.getSession().setAttribute("unsupportedExtension",errorMessage);
                }
            }
            if(!newBook.equals(null)){
                bookDao.insert(newBook);
                String successMessage = "Successfully added a book!";
                req.getSession().setAttribute("addBookSuccess",successMessage);
                resp.sendRedirect("books");
            } else {
                req.getSession().setAttribute("bookErrorMessage","Failed to add a book!");
                req.getRequestDispatcher("/book/bookAddEdit.jsp").forward(req, resp);
            }
        }  catch (NumberFormatException numberFormatException) {
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
    }

    private void retrieveCategories(Set<BkCategory> categorySet, String[] catIds) {
        for (String id: catIds ) {
            categorySet.add((BkCategory) catDao.getById(Integer.parseInt(id)));
        }
    }
}