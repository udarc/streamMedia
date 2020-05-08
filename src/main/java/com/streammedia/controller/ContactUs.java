package com.streammedia.controller;

import com.streammedia.utility.JavaMailUtility;
import com.streammedia.utility.PropertiesLoader;
import lombok.extern.log4j.Log4j2;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;

/**
 * The type Contact us.
 * Responsible for getting form data to send to sender email address.
 * @author Jeanne
 * @version 1.0
 * @since 2020-02-12
 */
@Log4j2
@WebServlet(
        name="Contactus",
        urlPatterns = {"/contact-us"}
)
public class ContactUs extends HttpServlet implements PropertiesLoader {
    private  Properties properties;
    private  String sendEmail;
    private String authPass;
    private JavaMailUtility contactUtility= new JavaMailUtility();
//TO DO Message not getting in email

    /**
     *  Handles HTTP GET requests.
     *
     *@param  request               Description of the Parameter
     *@param  response              Description of the Parameter
     *@exception ServletException  if there is a Servlet failure
     *@exception IOException       if there is an IO failure
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url ="/contact.jsp";
        RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(url);
        dispatcher.forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            String name = req.getParameter("firstName").trim() + " " + req.getParameter("lastName").trim();
            String email =  req.getParameter("email").trim();
            String subject = req.getParameter("subject").trim();
            String content = req.getParameter("message").trim();
            String amessage = "\nName: " + name + "\nEmail: " + email + "\nMessage:" + content ;

        if (!(name.equals(null) && !email.equals(null) && !subject.equals(null) && !amessage.equals(null))) {
            log.info("Preparing to send a message: " + amessage);

            try {
                properties = loadProperties("/contact.properties");
                sendEmail = properties.getProperty("mail.username");
                authPass = properties.getProperty("mail.password");
                log.debug("Username" + sendEmail);
                contactUtility.sendAsHtml(email,sendEmail,
                        subject,
                        amessage);
            } catch (MessagingException e) {
                log.info("Sending Mail error" + e);
            }catch (Exception ex) {
            log.debug("Error loading properties" + ex);
        }
            String successMessage = "Thank you for contacting Stream Media. We will get back to within 24 hours!";
            req.getSession().setAttribute("successMessage",successMessage);
            resp.sendRedirect(req.getContextPath());
        }

         else {
            req.getSession().setAttribute("contactError", "Failed to send your message! Try again");
            req.getRequestDispatcher("/contact.jsp").forward(req,resp);
        }
    }
}
