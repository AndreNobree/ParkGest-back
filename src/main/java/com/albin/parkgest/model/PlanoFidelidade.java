package com.albin.parkgest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "plano_fidelidade")
@Getter
@Setter
public class PlanoFidelidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Clientes cliente;

    @Column(name = "valor", nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private String metodo;

    @Column(name = "created_by")
    private Long createBy;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "updated_by")
    private Long updateBy;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;
}
