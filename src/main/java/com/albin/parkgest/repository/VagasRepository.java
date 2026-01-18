package com.albin.parkgest.repository;

import com.albin.parkgest.model.Vagas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VagasRepository extends JpaRepository<Vagas, Long> {

    List<Vagas> findByAcao(String acao);

    boolean existsByVaga(String vaga);
}
