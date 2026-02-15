package com.albin.parkgest.controller;

import com.albin.parkgest.dto.vagas.VagasRegisterDTO;
import com.albin.parkgest.dto.valorhora.ValorHoraRegisterDTO;
import com.albin.parkgest.dto.valorhora.ValorHoraResponseDTO;
import com.albin.parkgest.service.ValorHoraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/valoreshora")
@CrossOrigin(origins = "http://localhost:3000")
public class ValoresHoraController {
    @Autowired
    private ValorHoraService valoresHoraService;

    @GetMapping("/")
    public ResponseEntity<List<ValorHoraResponseDTO>> valoresHora(){
        List<ValorHoraResponseDTO> valoresHora = valoresHoraService.retornaValoresHora();
        return ResponseEntity.ok(valoresHora);
    }

    @PostMapping("/add")
    public ResponseEntity<ValorHoraResponseDTO> novoValorHora(@Valid @RequestBody ValorHoraRegisterDTO dto){
        ValorHoraResponseDTO criaValorHora = valoresHoraService.cadastraValorHora(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(criaValorHora);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ValorHoraResponseDTO> editaValorHora(@PathVariable Long id, @Valid @RequestBody ValorHoraRegisterDTO dto){
        ValorHoraResponseDTO atualizado = valoresHoraService.editaValoresHora(id, dto);
        return ResponseEntity.ok(atualizado);
    }
}
