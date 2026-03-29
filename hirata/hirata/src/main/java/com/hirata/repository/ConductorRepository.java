package com.hirata.repository;

import com.hirata.model.Conductor;
import com.hirata.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConductorRepository extends JpaRepository<Conductor, Long> {

    Optional<Conductor> findByUsuario(Usuario usuario);

}