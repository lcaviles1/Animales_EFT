package com.animales.backend.service;

import com.animales.backend.entity.Mascota;
import com.animales.backend.repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MascotaService {

    private final MascotaRepository mascotaRepository;

    public MascotaService(MascotaRepository mascotaRepository) {
        this.mascotaRepository = mascotaRepository;
    }

    public List<Mascota> listarTodas() {
        return mascotaRepository.findAll();
    }

    public List<Mascota> buscar(String especie, Integer edad, String ubicacion, String genero) {
        return mascotaRepository.findAll().stream()
                .filter(m -> especie == null || especie.isBlank() || m.getEspecie().equalsIgnoreCase(especie))
                .filter(m -> edad == null || m.getEdad().equals(edad))
                .filter(m -> ubicacion == null || ubicacion.isBlank() || m.getUbicacion().equalsIgnoreCase(ubicacion))
                .filter(m -> genero == null || genero.isBlank() || m.getGenero().equalsIgnoreCase(genero))
                .toList();
    }

    public Mascota guardar(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public Mascota actualizar(Long id, Mascota nuevaMascota) {
        Mascota existente = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        existente.setNombre(nuevaMascota.getNombre());
        existente.setEspecie(nuevaMascota.getEspecie());
        existente.setRaza(nuevaMascota.getRaza());
        existente.setEdad(nuevaMascota.getEdad());
        existente.setUbicacion(nuevaMascota.getUbicacion());
        existente.setGenero(nuevaMascota.getGenero());
        existente.setFotoUrl(nuevaMascota.getFotoUrl());
        existente.setEstadoAdopcion(nuevaMascota.getEstadoAdopcion());

        return mascotaRepository.save(existente);
    }

    public void eliminar(Long id) {
        mascotaRepository.deleteById(id);
    }
}
