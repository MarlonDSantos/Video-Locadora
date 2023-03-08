package com.exercicio02videoLocadora.VideoLocadora.models;

import com.exercicio02videoLocadora.VideoLocadora.enums.StatusEmail;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "TB_EMAIL_LOCADORA")
public class EmailModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long emailId;

    private String PropRef;

    private String emailDe;

    private String emailPara;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String text;

    private LocalDateTime dataEnvioEmail;

    private StatusEmail statusEmail;
}
