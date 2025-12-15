package com.albin.parkgest.model;

import jakarta.persistence.*; // @entity, @table, @id ....
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String telefone;
    private String email;

    @Column(name = "created_by")
    private Long createBy;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "updated_by")
    private Long updateBy;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;
}
