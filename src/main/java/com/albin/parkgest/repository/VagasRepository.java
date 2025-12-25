package com.albin.parkgest.repository;

import com.albin.parkgest.model.Vagas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagasRepository extends JpaRepository<Vagas, Long> {

    boolean existsByVaga(String vaga);
}
