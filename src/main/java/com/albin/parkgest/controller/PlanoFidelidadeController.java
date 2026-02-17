package com.albin.parkgest.controller;

import com.albin.parkgest.dto.planoFidelidade.PlanoFidelidadeRegisterDTO;
import com.albin.parkgest.dto.planoFidelidade.PlanoFidelidadeResponseDTO;
import com.albin.parkgest.service.PlanoFidelidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plano")
@CrossOrigin(origins = "http://localhost:3000")
public class PlanoFidelidadeController {
    @Autowired
    private PlanoFidelidadeService planoFidelidadeService;

    @GetMapping("/")
    public ResponseEntity<List<PlanoFidelidadeResponseDTO>> planoFidelidade(){
        List<PlanoFidelidadeResponseDTO> planoFidelidade = planoFidelidadeService.retornaPlanoFidelidade();
        return ResponseEntity.ok(planoFidelidade);
    }

    @PostMapping("/register")
    public ResponseEntity<PlanoFidelidadeResponseDTO> adicionaPlano(@Valid @RequestBody PlanoFidelidadeRegisterDTO dto){
        PlanoFidelidadeResponseDTO criaPlano = planoFidelidadeService.adicionaPlano(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(criaPlano);
    }
}
