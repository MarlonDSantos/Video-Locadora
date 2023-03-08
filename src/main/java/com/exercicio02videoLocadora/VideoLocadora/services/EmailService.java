package com.exercicio02videoLocadora.VideoLocadora.services;

import com.exercicio02videoLocadora.VideoLocadora.enums.StatusEmail;
import com.exercicio02videoLocadora.VideoLocadora.models.EmailModel;
import com.exercicio02videoLocadora.VideoLocadora.repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.time.LocalDateTime;

@Service
public class EmailService {

    @Autowired
    EmailRepository emailRepository;
    @Autowired
    private JavaMailSender emailRemetente;

    public EmailModel sendEmail(EmailModel emailModel){

        emailModel.setDataEnvioEmail(LocalDateTime.now());

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailDe());
            message.setTo(emailModel.getEmailPara());
            message.setSubject(emailModel.getTitulo());
            message.setText(emailModel.getText());

            emailRemetente.send(message);

            emailModel.setStatusEmail(StatusEmail.SENT);
        }catch (MailException e) {
            emailModel.setStatusEmail(StatusEmail.ERROR);
        }finally{
            return emailRepository.save(emailModel);
        }
    }

}
