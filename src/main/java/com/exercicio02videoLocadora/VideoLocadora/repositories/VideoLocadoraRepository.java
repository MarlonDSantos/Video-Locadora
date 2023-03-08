package com.exercicio02videoLocadora.VideoLocadora.repositories;

import com.exercicio02videoLocadora.VideoLocadora.models.VideoLocadoraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface VideoLocadoraRepository extends JpaRepository<VideoLocadoraModel, UUID> {

    boolean existsByNomeFilme(String nomeFilme);

    boolean existsByCpfCliente(String cpfCliente);

    boolean existsByEmailCliente(String emailCliente);
}
