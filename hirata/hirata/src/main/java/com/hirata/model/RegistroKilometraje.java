package com.hirata.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "registros_kilometraje")
@Getter
@Setter
public class RegistroKilometraje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha = LocalDateTime.now();
    private Integer kilometrosRecorridos;

    @ManyToOne
    @JoinColumn(name = "camion_id")
    private Camion camion;

    @ManyToOne
    @JoinColumn(name = "conductor_id")
    private Conductor conductor;
}