package com.animales.backend.controller;

import com.animales.backend.entity.Mascota;
import com.animales.backend.service.MascotaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MascotaControllerTest {

    @Mock
    private MascotaService mascotaService;

    @InjectMocks
    private MascotaController mascotaController;

    @Test
    void listarMascotasRetornaOk() {
        List<Mascota> mascotas = List.of(
                new Mascota(1L, "Luna", "Perro", "Mestiza", 2, "Santiago", "Hembra", "foto.jpg", "Disponible")
        );

        when(mascotaService.listarTodas()).thenReturn(mascotas);

        ResponseEntity<List<Mascota>> response = mascotaController.listarMascotas();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void buscarMascotasRetornaOk() {
        List<Mascota> mascotas = List.of(
                new Mascota(1L, "Nube", "Perro", "Poodle", 1, "Maipu", "Hembra", "foto.jpg", "Disponible")
        );

        when(mascotaService.buscar("Perro", 1, "Maipu", "Hembra")).thenReturn(mascotas);

        ResponseEntity<List<Mascota>> response =
                mascotaController.buscarMascotas("Perro", 1, "Maipu", "Hembra");

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Nube", response.getBody().get(0).getNombre());
    }

    @Test
    void crearMascotaRetornaOk() {
        Mascota mascota = new Mascota(1L, "Luna", "Perro", "Mestiza", 2, "Santiago", "Hembra", "foto.jpg", "Disponible");

        when(mascotaService.guardar(mascota)).thenReturn(mascota);

        ResponseEntity<Mascota> response = mascotaController.crearMascota(mascota);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Luna", response.getBody().getNombre());
    }

    @Test
    void actualizarMascotaRetornaOk() {
        Mascota mascota = new Mascota(1L, "Luna Editada", "Perro", "Mestiza", 4, "Maipu", "Hembra", "foto.jpg", "Disponible");

        when(mascotaService.actualizar(1L, mascota)).thenReturn(mascota);

        ResponseEntity<Mascota> response = mascotaController.actualizarMascota(1L, mascota);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(4, response.getBody().getEdad());
    }

    @Test
    void eliminarMascotaRetornaMensajeCorrecto() {
        ResponseEntity<String> response = mascotaController.eliminarMascota(1L);

        verify(mascotaService).eliminar(1L);
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Mascota eliminada correctamente", response.getBody());
    }
}
