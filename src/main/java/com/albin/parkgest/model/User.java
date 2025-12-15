package com.albin.parkgest.model;

import jakarta.persistence.*; // @entity, @table, @id ....
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "password_hash", nullable = false, columnDefinition = "TEXT")
    private String passwordHash;

    @Column(name = "created_by")
    private Long createBy;

    @Column(name = "created_at")
    private LocalDateTime createAt;

    @Column(name = "updated_by")
    private Long updateBy;

    @Column(name = "updated_at")
    private LocalDateTime updateAt;

}