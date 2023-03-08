package com.exercicio02videoLocadora.VideoLocadora.controllers;

import com.exercicio02videoLocadora.VideoLocadora.dtos.EmailDto;
import com.exercicio02videoLocadora.VideoLocadora.models.EmailModel;
import com.exercicio02videoLocadora.VideoLocadora.services.EmailService;
import jakarta.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping("/enviando-email-locadora")
    public ResponseEntity<EmailModel> enviandoEmail(@RequestBody @Valid EmailDto emailDto){

        EmailModel emailModel = new EmailModel();

        BeanUtils.copyProperties(emailDto, emailModel);

        emailService.sendEmail(emailModel);

        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }
}
