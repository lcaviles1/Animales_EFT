package com.animales.backend.controller;

import com.animales.backend.entity.Mascota;
import com.animales.backend.service.MascotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MascotaController {

    private final MascotaService mascotaService;

    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @GetMapping("/public/mascotas")
    public ResponseEntity<List<Mascota>> listarMascotas() {
        return ResponseEntity.ok(mascotaService.listarTodas());
    }

    @GetMapping("/public/mascotas/buscar")
    public ResponseEntity<List<Mascota>> buscarMascotas(
            @RequestParam(required = false) String especie,
            @RequestParam(required = false) Integer edad,
            @RequestParam(required = false) String ubicacion,
            @RequestParam(required = false) String genero
    ) {
        return ResponseEntity.ok(mascotaService.buscar(especie, edad, ubicacion, genero));
    }

    @PostMapping("/private/mascotas")
    public ResponseEntity<Mascota> crearMascota(@RequestBody Mascota mascota) {
        return ResponseEntity.ok(mascotaService.guardar(mascota));
    }

    @PutMapping("/private/mascotas/{id}")
    public ResponseEntity<Mascota> actualizarMascota(@PathVariable Long id, @RequestBody Mascota mascota) {
        return ResponseEntity.ok(mascotaService.actualizar(id, mascota));
    }

    @DeleteMapping("/private/mascotas/{id}")
    public ResponseEntity<String> eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminar(id);
        return ResponseEntity.ok("Mascota eliminada correctamente");
    }
}