package com.herokuapp.trademateapi.demo;

import com.sun.istack.ByteArrayDataSource;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.List;
import java.util.Properties;

public class EmailSender {
    private final String password = "xxx";
    private final String host = "smtp.gmail.com";
    private final String from = "andreydem42@gmail.com";
    private final String protocol = "smtp";

    public void send(EmailMessage message) {
        Session session = createSession();
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(message.to));
            mimeMessage.setSubject(message.subject);
            mimeMessage.setText(message.text);
            Transport transport = session.getTransport(protocol);
            transport.connect(host, from, password);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendPhotos(List<byte[]> bytes, String to, String reportName) {
        Session session = createSession();
        try {
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(from));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(reportName);
            Multipart multipart = new MimeMultipart();
            int k = 0;
            for (byte[] image : bytes) {
                k++;
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                DataSource byteArraySource = new ByteArrayDataSource(image, "application/octet-stream");
                mimeBodyPart.setFileName(k + ".png");
                mimeBodyPart.setDataHandler(new DataHandler(byteArraySource));
                multipart.addBodyPart(mimeBodyPart);
            }
            mimeMessage.setContent(multipart);
            Transport transport = session.getTransport(protocol);
            transport.connect(host, from, password);
            transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private Session createSession() {
        String port = "587";
        Properties props = System.getProperties();
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.ssl.trust", "*");
        return Session.getDefaultInstance(props);
    }
}
