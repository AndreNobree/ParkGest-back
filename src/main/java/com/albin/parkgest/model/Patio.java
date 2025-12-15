package com.albin.parkgest.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "patio")
@Getter
@Setter
public class Patio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vaga_id", nullable = false)
    private Vagas vaga;

    @Column(name = "modelo_cor")
    private String modeloCor;

    @Column(nullable = false)
    private String placa;

    private String tipo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Clientes cliente;

    @Column(name = "hora_entrada", nullable = false)
    private LocalDateTime horaEntrada;

    @ManyToOne
    @JoinColumn(name = "valores_hora_id")
    private ValorHora valorHora;

    private String acao;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
