package com.hirata.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "mantenimientos")
@Getter
@Setter
public class Mantenimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha = LocalDateTime.now();
    private String tipo;

    @Column(length = 500)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "camion_id")
    private Camion camion;
}