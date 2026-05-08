package com.animales.frontend.service;

import com.animales.frontend.model.MascotaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class MascotaFrontendServiceTest {

    private RestTemplate restTemplate;
    private MascotaFrontendService service;

    @BeforeEach
    void setUp() {
        restTemplate = mock(RestTemplate.class);
        service = new MascotaFrontendService(restTemplate);
        ReflectionTestUtils.setField(service, "backendApiBaseUrl", "http://localhost:8080");
    }

    @Test
    void listarMascotasRetornaListaCuandoHayRespuesta() {
        MascotaDto dto1 = crearDto("Luna", "Perro");
        MascotaDto dto2 = crearDto("Simba", "Gato");

        when(restTemplate.getForObject(
                eq("http://localhost:8080/api/public/mascotas"),
                eq(MascotaDto[].class)
        )).thenReturn(new MascotaDto[]{dto1, dto2});

        List<MascotaDto> resultado = service.listarMascotas();

        assertEquals(2, resultado.size());
        assertEquals("Luna", resultado.get(0).getNombre());
        assertEquals("Simba", resultado.get(1).getNombre());
    }

    @Test
    void listarMascotasRetornaListaVaciaCuandoRespuestaEsNull() {
        when(restTemplate.getForObject(
                eq("http://localhost:8080/api/public/mascotas"),
                eq(MascotaDto[].class)
        )).thenReturn(null);

        List<MascotaDto> resultado = service.listarMascotas();

        assertEquals(0, resultado.size());
    }

    @Test
    void buscarMascotasConFiltrosConstruyeUrlYRetornaCoincidencias() {
        MascotaDto dto = crearDto("Nube", "Perro");

        String urlEsperada = "http://localhost:8080/api/public/mascotas/buscar?especie=Perro&edad=1&ubicacion=Maipu&genero=Hembra";

        when(restTemplate.getForObject(
                eq(urlEsperada),
                eq(MascotaDto[].class)
        )).thenReturn(new MascotaDto[]{dto});

        List<MascotaDto> resultado = service.buscarMascotas("Perro", 1, "Maipu", "Hembra");

        assertEquals(1, resultado.size());
        assertEquals("Nube", resultado.get(0).getNombre());
    }

    @Test
    void buscarMascotasSinFiltrosRetornaListaVaciaSiRespuestaEsNull() {
        String urlEsperada = "http://localhost:8080/api/public/mascotas/buscar";

        when(restTemplate.getForObject(
                eq(urlEsperada),
                eq(MascotaDto[].class)
        )).thenReturn(null);

        List<MascotaDto> resultado = service.buscarMascotas("", null, "", "");

        assertEquals(0, resultado.size());
    }

    private MascotaDto crearDto(String nombre, String especie) {
        MascotaDto dto = new MascotaDto();
        dto.setNombre(nombre);
        dto.setEspecie(especie);
        return dto;
    }
}