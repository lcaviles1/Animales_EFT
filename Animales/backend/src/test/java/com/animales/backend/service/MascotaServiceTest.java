package com.animales.backend.service;

import com.animales.backend.entity.Mascota;
import com.animales.backend.repository.MascotaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MascotaServiceTest {

    @Mock
    private MascotaRepository mascotaRepository;

    @InjectMocks
    private MascotaService mascotaService;

    @Test
    void listarTodasRetornaLista() {
        List<Mascota> mascotas = List.of(
                crearMascota(1L, "Luna", "Perro", 2, "Santiago", "Hembra"),
                crearMascota(2L, "Simba", "Gato", 3, "Providencia", "Macho")
        );

        when(mascotaRepository.findAll()).thenReturn(mascotas);

        List<Mascota> resultado = mascotaService.listarTodas();

        assertEquals(2, resultado.size());
        verify(mascotaRepository).findAll();
    }

    @Test
    void buscarSinFiltrosRetornaTodo() {
        List<Mascota> mascotas = List.of(
                crearMascota(1L, "Luna", "Perro", 2, "Santiago", "Hembra"),
                crearMascota(2L, "Simba", "Gato", 3, "Providencia", "Macho")
        );

        when(mascotaRepository.findAll()).thenReturn(mascotas);

        List<Mascota> resultado = mascotaService.buscar("", null, "", "");

        assertEquals(2, resultado.size());
    }

    @Test
    void buscarConFiltrosRetornaCoincidencias() {
        List<Mascota> mascotas = List.of(
                crearMascota(1L, "Luna", "Perro", 2, "Santiago", "Hembra"),
                crearMascota(2L, "Simba", "Gato", 3, "Providencia", "Macho"),
                crearMascota(3L, "Nube", "Perro", 1, "Maipu", "Hembra")
        );

        when(mascotaRepository.findAll()).thenReturn(mascotas);

        List<Mascota> resultado = mascotaService.buscar("Perro", 1, "Maipu", "Hembra");

        assertEquals(1, resultado.size());
        assertEquals("Nube", resultado.get(0).getNombre());
    }

    @Test
    void guardarRetornaMascotaGuardada() {
        Mascota mascota = crearMascota(1L, "Luna", "Perro", 2, "Santiago", "Hembra");

        when(mascotaRepository.save(mascota)).thenReturn(mascota);

        Mascota resultado = mascotaService.guardar(mascota);

        assertEquals("Luna", resultado.getNombre());
        verify(mascotaRepository).save(mascota);
    }

    @Test
    void actualizarModificaYGuardaMascota() {
        Mascota existente = crearMascota(1L, "Luna", "Perro", 2, "Santiago", "Hembra");
        Mascota nuevaMascota = crearMascota(1L, "Luna Editada", "Perro", 4, "Maipu", "Hembra");

        when(mascotaRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(mascotaRepository.save(any(Mascota.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Mascota resultado = mascotaService.actualizar(1L, nuevaMascota);

        assertEquals("Luna Editada", resultado.getNombre());
        assertEquals(4, resultado.getEdad());
        assertEquals("Maipu", resultado.getUbicacion());
        verify(mascotaRepository).findById(1L);
        verify(mascotaRepository).save(existente);
    }

    @Test
    void actualizarLanzaErrorSiNoExisteMascota() {
        Mascota nuevaMascota = crearMascota(1L, "Luna", "Perro", 2, "Santiago", "Hembra");

        when(mascotaRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> mascotaService.actualizar(1L, nuevaMascota));

        assertEquals("Mascota no encontrada", ex.getMessage());
    }

    @Test
    void eliminarInvocaRepositorio() {
        mascotaService.eliminar(1L);
        verify(mascotaRepository).deleteById(1L);
    }

    private Mascota crearMascota(Long id, String nombre, String especie, Integer edad, String ubicacion, String genero) {
        return new Mascota(id, nombre, especie, "Mestiza", edad, ubicacion, genero, "foto.jpg", "Disponible");
    }
}