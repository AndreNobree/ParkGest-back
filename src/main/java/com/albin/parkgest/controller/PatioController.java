package com.albin.parkgest.controller;


import com.albin.parkgest.dto.patio.PatioRegisterDTO;
import com.albin.parkgest.dto.patio.PatioResponseDTO;
import com.albin.parkgest.service.PatioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patio")
@CrossOrigin(origins = "http://localhost:3000")
public class PatioController {
    @Autowired
    private PatioService patioService;

    @GetMapping("/")
    public ResponseEntity<List<PatioResponseDTO>> patio(){
        List<PatioResponseDTO> patio = patioService.vagasOcupadas();
        return ResponseEntity.ok(patio);
    }

    @PostMapping("/add")
    public ResponseEntity<PatioResponseDTO> addPatio(@Valid @RequestBody PatioRegisterDTO dto){
        PatioResponseDTO addPatio = patioService.adicionaPatio(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(addPatio);
    }
}
