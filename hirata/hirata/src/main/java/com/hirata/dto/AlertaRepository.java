package com.hirata.repository;

import com.hirata.model.Alerta;
import com.hirata.model.Camion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlertaRepository extends JpaRepository<Alerta, Long> {

    Optional<Alerta> findByCamionAndActiva(Camion camion, Boolean activa);

    List<Alerta> findByActivaTrue();

}