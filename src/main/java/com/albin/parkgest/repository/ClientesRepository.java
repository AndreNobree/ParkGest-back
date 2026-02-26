package com.albin.parkgest.repository;

import com.albin.parkgest.dto.clientes.ClientesPlanoFidelidadeDTO;
import com.albin.parkgest.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> {
    boolean existsByNome(String nome);
    boolean existsByTelefone(String telefone);

    @Query("""
       SELECT new com.albin.parkgest.dto.clientes.ClientesPlanoFidelidadeDTO(
            c.id,
            c.nome,
            c.telefone,
            p.metodo,
            p.valor
       )
       FROM Clientes c
       LEFT JOIN c.planos p
       """)
    List<ClientesPlanoFidelidadeDTO> listarClientesComPlano();

}
