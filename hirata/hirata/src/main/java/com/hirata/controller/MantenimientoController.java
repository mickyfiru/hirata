package com.hirata.controller;

import com.hirata.model.Camion;
import com.hirata.repository.CamionRepository;
import com.hirata.repository.MantenimientoRepository;
import com.hirata.service.FlotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MantenimientoController {

    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    @Autowired
    private CamionRepository camionRepository;

    @Autowired
    private FlotaService flotaService;

    @GetMapping("/mantenimientos")
    public String listarMantenimientos(Model model) {
        model.addAttribute("mantenimientos", mantenimientoRepository.findAll());
        return "mantenimientos";
    }

    @GetMapping("/mantenimientos/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("camiones", camionRepository.findAll());
        return "nuevo-mantenimiento";
    }

    @PostMapping("/mantenimientos/nuevo")
    public String guardar(@RequestParam Long camionId,
                          @RequestParam String tipo,
                          @RequestParam String descripcion) {
        Camion camion = camionRepository.findById(camionId).orElseThrow();
        flotaService.registrarMantenimiento(camion, tipo, descripcion);
        return "redirect:/mantenimientos";
    }
}