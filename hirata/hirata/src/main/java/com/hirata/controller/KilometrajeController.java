package com.hirata.controller;

import com.hirata.model.*;
import com.hirata.repository.*;
import com.hirata.service.FlotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class KilometrajeController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ConductorRepository conductorRepository;
    @Autowired
    private CamionRepository camionRepository;
    @Autowired
    private FlotaService flotaService;

    @GetMapping("/kilometraje")
    public String formularioKilometraje(Model model, Authentication authentication) {
        Usuario usuario = usuarioRepository.findByUsername(authentication.getName()).orElseThrow();
        model.addAttribute("usuario", usuario);

        if (usuario.getRol() == Rol.CONDUCTOR) {
            Conductor conductor = conductorRepository.findByUsuario(usuario).orElseThrow();
            model.addAttribute("camiones", camionRepository.findByConductor(conductor));
        } else {
            model.addAttribute("camiones", camionRepository.findAll());
        }

        return "kilometraje";
    }

    @PostMapping("/kilometraje")
    public String registrarKilometraje(@RequestParam Long camionId,
                                       @RequestParam Integer kilometros,
                                       Authentication authentication) {

        Usuario usuario = usuarioRepository.findByUsername(authentication.getName()).orElseThrow();
        Camion camion = camionRepository.findById(camionId).orElseThrow();

        Conductor conductor;
        if (usuario.getRol() == Rol.CONDUCTOR) {
            conductor = conductorRepository.findByUsuario(usuario).orElseThrow();
            if (camion.getConductor() == null || !camion.getConductor().getId().equals(conductor.getId())) {
                return "redirect:/kilometraje?error";
            }
        } else {
            conductor = camion.getConductor();
        }

        flotaService.registrarKilometraje(camion, conductor, kilometros);
        return "redirect:/camiones";
    }
}