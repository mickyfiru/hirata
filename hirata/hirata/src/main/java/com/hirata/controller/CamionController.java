package com.hirata.controller;

import com.hirata.model.Camion;
import com.hirata.repository.CamionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CamionController {

    @Autowired
    private CamionRepository camionRepository;

    @GetMapping("/camiones")
    public String listarCamiones(Model model) {
        model.addAttribute("camiones", camionRepository.findAll());
        return "camiones";
    }

    @GetMapping("/camiones/nuevo")
    public String formularioNuevoCamion(Model model) {
        model.addAttribute("camion", new Camion());
        return "nuevo-camion";
    }

    @PostMapping("/camiones/nuevo")
    public String guardarCamion(@ModelAttribute Camion camion) {
        camionRepository.save(camion);
        return "redirect:/camiones";
    }
}
