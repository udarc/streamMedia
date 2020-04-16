package com.streammedia.utility;

import javax.mail.*;
import javax.mail.internet.*;
import lombok.extern.log4j.Log4j2;
import java.util.Properties;


/**
 * The type Java mail utility.
 * @author Jeanne
 * @version 1.0
 * @since 2020-02-21
 */
@Log4j2
public  class JavaMailUtility implements PropertiesLoader {
    private Properties props;
        public  JavaMailUtility(){
            try {
                props = loadProperties("/contact.properties");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    /**
     * Send as message.
     *
     * @param toEmail the to email
     * @param title   the title
     * @param content the content
     * @throws MessagingException the messaging exception
     */
    public  void sendAsHtml(String fromEmail,String toEmail, String title, String content)
            throws MessagingException {
       log.info("Sending email to " + toEmail);

        Session session = createSession();

        //create message using session
        MimeMessage message = new MimeMessage(session);
        prepareEmailMessage(message,fromEmail, toEmail, title, content);

        //sending message
        Transport.send(message,toEmail,props.getProperty("mail.password"));
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
    private static void prepareEmailMessage(MimeMessage message,String fromEmail, String toEmail, String title, String content) throws MessagingException {
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
    private  Session createSession() {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth",props.getProperty("mail.auth"));//Outgoing server requires authentication
        properties.put("mail.smtp.starttls.enable", props.getProperty("mail.starttls"));//TLS must be activated
        properties.put("mail.smtp.host", props.getProperty("mail.host")); //Outgoing server (SMTP) - change it to your SMTP server
        properties.put("mail.smtp.port", props.getProperty("mail.port"));//Outgoing port

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(props.getProperty("mail.username"), props.getProperty("mail.password"));
            }
        });
        return session;
    }

}
