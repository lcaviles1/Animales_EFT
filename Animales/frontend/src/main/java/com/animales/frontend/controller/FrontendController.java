package com.animales.frontend.controller;

import com.animales.frontend.model.MascotaDto;
import com.animales.frontend.service.MascotaFrontendService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FrontendController {

    private final MascotaFrontendService mascotaFrontendService;

    public FrontendController(MascotaFrontendService mascotaFrontendService) {
        this.mascotaFrontendService = mascotaFrontendService;
    }

    @GetMapping("/")
    public String inicio(String especie, Integer edad, String ubicacion, String genero, Model model) {
        List<MascotaDto> mascotas;

        boolean hayFiltros = (especie != null && !especie.isBlank())
                || edad != null
                || (ubicacion != null && !ubicacion.isBlank())
                || (genero != null && !genero.isBlank());

        if (hayFiltros) {
            mascotas = mascotaFrontendService.buscarMascotas(especie, edad, ubicacion, genero);
        } else {
            mascotas = mascotaFrontendService.listarMascotas();
        }

        model.addAttribute("mascotas", mascotas);
        model.addAttribute("especie", especie);
        model.addAttribute("edad", edad);
        model.addAttribute("ubicacion", ubicacion);
        model.addAttribute("genero", genero);

        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/admin/agregar")
    public String agregar() {
        return "agregar";
    }

    @GetMapping("/admin/editar")
    public String editar() {
        return "editar";
    }

    @GetMapping("/admin/eliminar")
    public String eliminar() {
        return "eliminar";
    }
}