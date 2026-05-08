package com.animales.frontend.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MascotaDtoTest {

    @Test
    void gettersYSettersFuncionan() {
        MascotaDto dto = new MascotaDto();

        dto.setId(1L);
        dto.setNombre("Luna");
        dto.setEspecie("Perro");
        dto.setRaza("Mestiza");
        dto.setEdad(2);
        dto.setUbicacion("Santiago");
        dto.setGenero("Hembra");
        dto.setFotoUrl("foto.jpg");
        dto.setEstadoAdopcion("Disponible");

        assertEquals(1L, dto.getId());
        assertEquals("Luna", dto.getNombre());
        assertEquals("Perro", dto.getEspecie());
        assertEquals("Mestiza", dto.getRaza());
        assertEquals(2, dto.getEdad());
        assertEquals("Santiago", dto.getUbicacion());
        assertEquals("Hembra", dto.getGenero());
        assertEquals("foto.jpg", dto.getFotoUrl());
        assertEquals("Disponible", dto.getEstadoAdopcion());
    }
}