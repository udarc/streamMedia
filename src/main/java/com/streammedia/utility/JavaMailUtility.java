package com.streammedia.utility;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

import lombok.extern.log4j.Log4j2;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.logging.log4j.core.*;

/**
 * The type Java mail utility.
 * @author Jeanne
 * @version 1.0
 * @since 2020-02-21
 */
@Log4j2
public  class JavaMailUtility implements PropertiesLoader {

    /**
     * Send as message.
     *
     * @param toEmail the to email
     * @param title   the title
     * @param content the content
     * @throws MessagingException the messaging exception
     */
    public static void sendAsHtml(String fromEmail,String password,String toEmail, String title, String content)
            throws MessagingException {
       log.info("Sending email to " + toEmail);

        Session session = createSession(toEmail,password);

        //create message using session
        MimeMessage message = new MimeMessage(session);
        prepareEmailMessage(message,password,fromEmail, toEmail, title, content);

        //sending message
        Transport.send(message,toEmail,password);
        log.info("Done");
    }

    /**
     * Prepare email message.
     *
     * @param message the message
     * @param toEmail the to email
     * @param title   the title
     * @param content the content
     * @throws MessagingException the messaging exception
     */
    private static void prepareEmailMessage(MimeMessage message,String fromEmail,String password, String toEmail, String title, String content) throws MessagingException {
        message.setText(content);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(title);
    }


    /**
     * Create session session.
     *
     * @return the session
     */
    private static Session createSession(String username,String senderPassword) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");//Outgoing server requires authentication
        properties.put("mail.smtp.starttls.enable", "true");//TLS must be activated
        properties.put("mail.smtp.host", "smtp.gmail.com"); //Outgoing server (SMTP) - change it to your SMTP server
        properties.put("mail.smtp.port", "587");//Outgoing port

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, senderPassword);
            }
        });
        return session;
    }

}
