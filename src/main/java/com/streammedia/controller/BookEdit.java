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
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * The type Book edit.
 * Responsible for getting form data to update User's recor
 *
 * @author Jeanne
 * @version 1.0
 * @since 05 -05-2020
 */
@WebServlet(
        urlPatterns = {"/book-edit"}
)
@Log4j2
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10 * 10 * 10,      // 1GB
        maxRequestSize = 1024 * 1024 * 5 * 5 * 5   // 100MB
)
public class BookEdit extends HttpServlet implements PropertiesLoader {
    private GenericDao bookDao;
    private GenericDao userDao;
    private GenericDao catDao;

    /**
     * Init.
     * Responsible to create an instance of dao.
     */
    @Override
    public void init() {
        bookDao = new GenericDao(Book.class);
        userDao = new GenericDao(User.class);
        catDao = new GenericDao(BkCategory.class);
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
        int id = Integer.parseInt(req.getParameter("uid"));
        Book book = (Book) bookDao.getById(id);
        List<BkCategory> categoryList = catDao.getAll();
        Set<BkCategory> currentCategories = book.getCategories();
        req.setAttribute("categories", categoryList);
        req.setAttribute("book", book);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/book/bookAddEdit.jsp");
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
        if (req.isUserInRole("admin")) {
            try {
                Properties properties = loadProperties("/aws.properties");
                User user = (User) userDao.getByPropertyEqual("username", req.getRemoteUser()).get(0);
                Book bookToEdit = (Book) bookDao.getById(Integer.parseInt(req.getParameter("uid")));
                bookToEdit.setTitle(req.getParameter("title"));
                bookToEdit.setPublisher(req.getParameter("publisher"));
                bookToEdit.setISBN(req.getParameter("isbn"));
                bookToEdit.setPublicationDate(LocalDateTime.parse(req.getParameter("pub_date").trim()));
                bookToEdit.setAuthor(req.getParameter("author"));
                bookToEdit.setEdition(req.getParameter("edition"));
                bookToEdit.setPageNumber(Integer.parseInt(req.getParameter("page_number")));
                bookToEdit.setSummary(req.getParameter("summary"));
                Set<BkCategory> categoryList = bookToEdit.getCategories();
                String[] categoryIds = req.getParameterValues("category");
                if (categoryIds != null) {
                    categoryList.clear();
                    retrieveCategories(categoryList, categoryIds);
                } else {
                    for (BkCategory cat : bookToEdit.getCategories()) {
                        bookToEdit.addCategory(cat);
                    }
                }
                Part coverPart = req.getPart("cover");
                if (!coverPart.getSubmittedFileName().isEmpty()) {
                    String bookCoverFileName = coverPart.getSubmittedFileName().toLowerCase();
                    if (bookCoverFileName.endsWith(".jpg") || bookCoverFileName.endsWith(".png") || bookCoverFileName.endsWith("jpeg")) {
                        String accessKeyId = properties.getProperty("aws.access.key.id");
                        String secretAccessKey = properties.getProperty("aws.secret.access.key");
                        String region = properties.getProperty("aws.region");
                        String bucketName = properties.getProperty("aws.bucket.name");
                        String subdirectory = "media/" + Book.class.getSimpleName().toLowerCase();
                        String fileObjKeyName = subdirectory + "/" + bookToEdit.getTitle().toLowerCase().trim()
                                .replace(" ", "_") + "_" + bookToEdit.getAuthor().trim()
                                .replace(" ", "_") + bookCoverFileName.substring(
                                bookCoverFileName.lastIndexOf("."));
                        String fileToUpload = JavaHelperMethods.saveFileName(System.getProperty("java.io.tmpdir"), coverPart);

                        AWSS3UploadUtil awsS3 = new AWSS3UploadUtil();
                        String fileUrl = awsS3.uploadToAWSS3(coverPart, accessKeyId, secretAccessKey, region, bucketName, fileObjKeyName, fileToUpload);
                        bookToEdit.setCover(fileUrl);
                        Files.deleteIfExists(Paths.get(fileToUpload));
                    }
                }
                log.debug(bookToEdit);
                bookDao.saveOrUpdate(bookToEdit);
                String successMessage = "Successfully added a book!";
                req.getSession().setAttribute("editBookSuccess", successMessage);
                resp.sendRedirect("book-details?uid=" + bookToEdit.getBookId());
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
            } catch (Exception exception) {
                log.error(exception);
            }
        } else {
            req.getSession().setAttribute("bookErrorMessage","Failed to update a book!");
            req.getRequestDispatcher("/book/bookAddEdit.jsp").forward(req, resp);
        }
    }

    private void retrieveCategories(Set<BkCategory> categoryList, String[] categoryIds) {
        for (String id : categoryIds) {
            log.debug(id);
            categoryList.add((BkCategory) catDao.getById(Integer.parseInt(id)));

        }
    }
}


