package com.streammedia.utility;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import lombok.extern.log4j.Log4j2;


import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import org.apache.logging.log4j.core.*;

/**
 * The type Java mail utility.
 */
@Log4j2
public class JavaMailUtility {

//    private final Logger logger = (Logger) LogManager.getLogger(this.getClass());

    private static final String receiverEmail = "some@gmail.com";
    private  static final String senderPassword = "Password";

    /**
     * Send as html.
     *
     * @param toEmail the to email
     * @param title   the title
     * @param content the content
     * @throws MessagingException the messaging exception
     */
    public static void sendAsHtml(String toEmail, String title, String content)
            throws MessagingException {
       log.info("Sending email to " + toEmail);

        Session session = createSession();

        //create message using session
        MimeMessage message = new MimeMessage(session);
        prepareEmailMessage(message, toEmail, title, content);

        //sending message
        Transport.send(message);
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
    private static void prepareEmailMessage(MimeMessage message, String toEmail, String title, String content) throws MessagingException {
        message.setText(content);
        message.setFrom(new InternetAddress(receiverEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(title);
    }


    /**
     * Create session session.
     *
     * @return the session
     */
    private static Session createSession() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");//Outgoing server requires authentication
        properties.put("mail.smtp.starttls.enable", "true");//TLS must be activated
        properties.put("mail.smtp.host", "smtp.1and1.com"); //Outgoing server (SMTP) - change it to your SMTP server
        properties.put("mail.smtp.port", "587");//Outgoing port

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(receiverEmail, senderPassword);
            }
        });
        return session;
    }

}
