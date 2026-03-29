package com.hirata.service;

import com.hirata.model.*;
import com.hirata.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlotaService {

    @Autowired
    private CamionRepository camionRepository;

    @Autowired
    private RegistroKilometrajeRepository registroKilometrajeRepository;

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    public void registrarKilometraje(Camion camion, Conductor conductor, Integer kilometros) {
        RegistroKilometraje registro = new RegistroKilometraje();
        registro.setCamion(camion);
        registro.setConductor(conductor);
        registro.setKilometrosRecorridos(kilometros);
        registroKilometrajeRepository.save(registro);

        camion.setKilometrajeAcumulado(camion.getKilometrajeAcumulado() + kilometros);
        camion.setKilometrajeDesdeMantenimiento(camion.getKilometrajeDesdeMantenimiento() + kilometros);
        camionRepository.save(camion);

        if (camion.getKilometrajeDesdeMantenimiento() >= 5000) {
            boolean existe = alertaRepository.findByCamionAndActiva(camion, true).isPresent();
            if (!existe) {
                Alerta alerta = new Alerta();
                alerta.setCamion(camion);
                alerta.setMensaje("El camión " + camion.getPatente() + " requiere mantenimiento preventivo.");
                alertaRepository.save(alerta);
            }
        }
    }

    public void registrarMantenimiento(Camion camion, String tipo, String descripcion) {
        Mantenimiento mantenimiento = new Mantenimiento();
        mantenimiento.setCamion(camion);
        mantenimiento.setTipo(tipo);
        mantenimiento.setDescripcion(descripcion);
        mantenimientoRepository.save(mantenimiento);

        camion.setKilometrajeDesdeMantenimiento(0);
        camionRepository.save(camion);

        alertaRepository.findByCamionAndActiva(camion, true).ifPresent(alerta -> {
            alerta.setActiva(false);
            alertaRepository.save(alerta);
        });
    }
}