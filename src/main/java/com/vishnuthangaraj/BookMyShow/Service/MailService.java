package com.vishnuthangaraj.BookMyShow.Service;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Objects;

@Service
public class MailService {

    @Autowired
    private JavaMailSender javaMailSender;

    // Send mail with Attachment
    public void generateMailWithAttachment(String receiver, String subject, String messageBody, String filePath){
        MimeMessage message = javaMailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(messageBody);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            helper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
            javaMailSender.send(message);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }

    // Send mail without Attachment
    public void generateMailWithoutAttachment(String receiver, String subject, String messageBody){
        MimeMessage message = javaMailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(receiver);
            helper.setSubject(subject);
            helper.setText(messageBody);
            javaMailSender.send(message);
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
