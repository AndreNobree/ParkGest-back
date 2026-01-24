package com.albin.parkgest.repository;

import com.albin.parkgest.model.Patio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PatioRepository extends JpaRepository<Patio, Long> {

    boolean existsByVaga_Id(Long vagaId);
}
