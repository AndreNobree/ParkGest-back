package com.albin.parkgest.controller;

import com.albin.parkgest.dto.vagas.VagasRegisterDTO;
import com.albin.parkgest.dto.vagas.VagasResponseDTO;
import com.albin.parkgest.service.VagasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vagas")
@CrossOrigin(origins = "http://localhost:3000")
public class VagasController {

    @Autowired
    private VagasService vagasService;

    @PostMapping("/register")
    public ResponseEntity<VagasResponseDTO> novaVaga(@Valid @RequestBody VagasRegisterDTO dto){
        VagasResponseDTO criaVaga = vagasService.cadastraVaga(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(criaVaga);
    }

    @GetMapping("/livres")
    public ResponseEntity<List<VagasResponseDTO>> controle(){
        List<VagasResponseDTO> controle = vagasService.vagasRestantes();
        return ResponseEntity.ok(controle);
    }

    @GetMapping("/all")
    public ResponseEntity<List<VagasResponseDTO>> vagas(){
        List<VagasResponseDTO> vagas = vagasService.vagas();
        return ResponseEntity.ok(vagas);
    }


}
