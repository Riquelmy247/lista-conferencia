package com.conferencia.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pessoas")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer pagou = 0;

    @Column(nullable = false)
    private Integer entrou = 0;
} 