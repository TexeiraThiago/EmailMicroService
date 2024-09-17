package com.ms.email.services;

import com.ms.email.domain.enums.StatusEmail;
import com.ms.email.domain.models.EmailModel;
import com.ms.email.domain.repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.LocalDateTime;

@Service
public class EmailService {

    final EmailRepository emailRepository;

    final JavaMailSender emailSeder;

    final MailProperties mailProperties;

    public EmailService(EmailRepository emailRepository, JavaMailSender emailSeder, MailProperties mailProperties) {
        this.emailRepository = emailRepository;
        this.emailSeder = emailSeder;
        this.mailProperties = mailProperties;
    }

    @Value(value = "${spring.mail.username}")
    private String emailFrom;


    @Transactional
    public EmailModel sendEmail(EmailModel emailModel) {
        try {
            emailModel.setSendDataEmail(LocalDateTime.now());
            emailModel.setEmailFrom(emailFrom);


            MimeMessage mimeMailMessage = emailSeder.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMailMessage, true);
            helper.setTo(emailModel.getEmailTo());
            helper.setSubject(emailModel.getSubject());
            helper.setText(emailModel.getText());

            FileSystemResource file = new FileSystemResource(new File("/home/thiagoteixeira/Pictures/JAVA-Leasons-apostube.png"));
            helper.addAttachment(file.getFilename(), file);

            emailSeder.send(mimeMailMessage);

            emailModel.setStatusEmail(StatusEmail.SENT);
        }catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        } catch (MessagingException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
            throw new RuntimeException(e);
        } finally {
            return emailRepository.save(emailModel);
        }
    }

}
