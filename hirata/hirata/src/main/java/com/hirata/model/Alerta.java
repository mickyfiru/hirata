package com.hirata.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "alertas")
@Getter
@Setter
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;
    private LocalDateTime fecha = LocalDateTime.now();
    private Boolean activa = true;

    @ManyToOne
    @JoinColumn(name = "camion_id")
    private Camion camion;
}