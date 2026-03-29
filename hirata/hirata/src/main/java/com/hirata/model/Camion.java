package com.hirata.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "camiones")
@Getter
@Setter
public class Camion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String patente;

    private String marca;
    private String modelo;
    private Integer anio;
    private Integer kilometrajeAcumulado = 0;
    private Integer kilometrajeDesdeMantenimiento = 0;

    @ManyToOne
    @JoinColumn(name = "conductor_id")
    private Conductor conductor;
}