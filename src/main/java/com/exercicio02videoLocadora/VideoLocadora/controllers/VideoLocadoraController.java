package com.exercicio02videoLocadora.VideoLocadora.controllers;

import com.exercicio02videoLocadora.VideoLocadora.dtos.VideoLocadoraDto;
import com.exercicio02videoLocadora.VideoLocadora.models.VideoLocadoraModel;
import com.exercicio02videoLocadora.VideoLocadora.services.VideoLocadoraService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/video-locadora")
public class VideoLocadoraController {

    final VideoLocadoraService videoLocadoraService;

    public VideoLocadoraController(VideoLocadoraService videoLocadoraService) {
        this.videoLocadoraService = videoLocadoraService;
    }
    @PostMapping
    public ResponseEntity<Object> saveVideoLocadora(@RequestBody @Valid VideoLocadoraDto videoLocadoraDto){

        if(videoLocadoraService.existsByNomeFilme(videoLocadoraDto.getNomeFilme())){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Filme já cadastrado!");
        }
        if(videoLocadoraService.existsByCpfCliente(videoLocadoraDto.getCpfCliente())){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Cliente já cadastrado com esse CPF!");
        }
        if (videoLocadoraService.existsByEmailCliente(videoLocadoraDto.getEmailCliente())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Email já cadastrado com outro cliente!");
        }

        var videoLocadoraModel = new VideoLocadoraModel();
        BeanUtils.copyProperties(videoLocadoraDto, videoLocadoraModel);
        videoLocadoraModel.setDataRegistro(LocalDateTime.now(ZoneId.of("UCT")));
        return ResponseEntity.status(HttpStatus.CREATED).body(videoLocadoraService.save(videoLocadoraModel));
    }
    @GetMapping
    public ResponseEntity<List<VideoLocadoraModel>> getAllVideoLocadora(){
        return ResponseEntity.status(HttpStatus.OK).body(videoLocadoraService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneVideoLocadora(@PathVariable (value = "id") UUID id){

        Optional<VideoLocadoraModel> videoLocadoraModelOptional = videoLocadoraService.findById(id);

        if (videoLocadoraModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme Não Encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(videoLocadoraModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteVideoLocadora(@PathVariable(value = "id") UUID id){
        Optional<VideoLocadoraModel> videoLocadoraModelOptional = videoLocadoraService.findById(id);

        if (!videoLocadoraModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme Não Encontrado!");
        }
        videoLocadoraService.delete(videoLocadoraModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Filme deletado com sucesso!");
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Object> updateVideoLocadora(@PathVariable(value = "id") UUID id,
                                                       @RequestBody @Valid VideoLocadoraDto videoLocadoraDto){
        Optional<VideoLocadoraModel> videoLocadoraModelOptional = videoLocadoraService.findById(id);

        if (!videoLocadoraModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Filme não encontrado!");
        }

        var videoLocadoraModel = videoLocadoraModelOptional.get();

        BeanUtils.copyProperties(videoLocadoraDto, videoLocadoraModel);
        videoLocadoraModel.setId(videoLocadoraModelOptional.get().getId());
        videoLocadoraModel.setDataRegistro(videoLocadoraModelOptional.get().getDataRegistro());

        return ResponseEntity.status(HttpStatus.OK).body(videoLocadoraService.save(videoLocadoraModel));
    }
}
