package com.exercicio02videoLocadora.VideoLocadora.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EmailDto {
    @NotBlank
    private String PropRef;
    @NotBlank
    @Email
    private String emailDe;
    @NotBlank
    @Email
    private String emailPara;
    @NotBlank
    private String titulo;
    @NotBlank
    private String text;
}
