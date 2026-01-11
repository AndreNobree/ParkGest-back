package com.albin.parkgest.repository;

import com.albin.parkgest.model.Patio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatioRepository extends JpaRepository<Patio, Long> {

    List<Patio> findByAcao(String acao); //buscar por vagas ocupadas para retornar na home
}
