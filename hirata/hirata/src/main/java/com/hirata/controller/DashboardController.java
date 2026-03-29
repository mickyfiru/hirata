package com.hirata.controller;

import com.hirata.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @Autowired
    private CamionRepository camionRepository;
    @Autowired
    private ConductorRepository conductorRepository;
    @Autowired
    private MantenimientoRepository mantenimientoRepository;
    @Autowired
    private AlertaRepository alertaRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("camiones", camionRepository.count());
        model.addAttribute("conductores", conductorRepository.count());
        model.addAttribute("mantenimientos", mantenimientoRepository.count());
        model.addAttribute("alertas", alertaRepository.findByActivaTrue().size());
        return "dashboard";
    }
}
