package com.albin.parkgest.repository;

import com.albin.parkgest.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {
    boolean existsByNome(String nome);
    boolean existsByTelefone(String telefone);

}
