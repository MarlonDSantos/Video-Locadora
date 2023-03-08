package com.exercicio02videoLocadora.VideoLocadora.repositories;

import com.exercicio02videoLocadora.VideoLocadora.models.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {

}
