package com.hirata.repository;

import com.hirata.model.Camion;
import com.hirata.model.Conductor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CamionRepository extends JpaRepository<Camion, Long> {

    Optional<Camion> findByPatente(String patente);

    List<Camion> findByConductor(Conductor conductor);

}