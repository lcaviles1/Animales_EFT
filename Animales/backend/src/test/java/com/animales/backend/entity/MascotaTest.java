package com.animales.backend.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MascotaTest {

    @Test
    void constructorYGettersFuncionan() {
        Mascota mascota = new Mascota(
                1L,
                "Luna",
                "Perro",
                "Mestiza",
                2,
                "Santiago",
                "Hembra",
                "foto.jpg",
                "Disponible"
        );

        assertEquals(1L, mascota.getId());
        assertEquals("Luna", mascota.getNombre());
        assertEquals("Perro", mascota.getEspecie());
        assertEquals("Mestiza", mascota.getRaza());
        assertEquals(2, mascota.getEdad());
        assertEquals("Santiago", mascota.getUbicacion());
        assertEquals("Hembra", mascota.getGenero());
        assertEquals("foto.jpg", mascota.getFotoUrl());
        assertEquals("Disponible", mascota.getEstadoAdopcion());
    }

    @Test
    void settersFuncionan() {
        Mascota mascota = new Mascota();

        mascota.setId(2L);
        mascota.setNombre("Simba");
        mascota.setEspecie("Gato");
        mascota.setRaza("Naranja");
        mascota.setEdad(3);
        mascota.setUbicacion("Providencia");
        mascota.setGenero("Macho");
        mascota.setFotoUrl("foto2.jpg");
        mascota.setEstadoAdopcion("Adoptado");

        assertEquals(2L, mascota.getId());
        assertEquals("Simba", mascota.getNombre());
        assertEquals("Gato", mascota.getEspecie());
        assertEquals("Naranja", mascota.getRaza());
        assertEquals(3, mascota.getEdad());
        assertEquals("Providencia", mascota.getUbicacion());
        assertEquals("Macho", mascota.getGenero());
        assertEquals("foto2.jpg", mascota.getFotoUrl());
        assertEquals("Adoptado", mascota.getEstadoAdopcion());
    }
}