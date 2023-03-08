package com.exercicio02videoLocadora.VideoLocadora.services;

import com.exercicio02videoLocadora.VideoLocadora.models.VideoLocadoraModel;
import com.exercicio02videoLocadora.VideoLocadora.repositories.VideoLocadoraRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VideoLocadoraService {

    final VideoLocadoraRepository videoLocadoraRepository;

    public VideoLocadoraService(VideoLocadoraRepository videoLocadoraRepository) {
        this.videoLocadoraRepository = videoLocadoraRepository;
    }
    @Transactional
    public VideoLocadoraModel save(VideoLocadoraModel videoLocadoraModel) {
        return videoLocadoraRepository.save(videoLocadoraModel);
    }

    public boolean existsByNomeFilme(String nomeFilme) {
        return videoLocadoraRepository.existsByNomeFilme(nomeFilme);
    }

    public boolean existsByCpfCliente(String cpfCliente) {
        return videoLocadoraRepository.existsByCpfCliente(cpfCliente);
    }

    public boolean existsByEmailCliente(String emailCliente) {
        return videoLocadoraRepository.existsByEmailCliente(emailCliente);
    }

    public List<VideoLocadoraModel> findAll() {
        return videoLocadoraRepository.findAll();
    }

    public Optional<VideoLocadoraModel> findById(UUID id) {
        return videoLocadoraRepository.findById(id);
    }
    @Transactional
    public void delete(VideoLocadoraModel videoLocadoraModel) {
        videoLocadoraRepository.delete(videoLocadoraModel);
    }
}
