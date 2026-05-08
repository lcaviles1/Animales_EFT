package com.animales.frontend.service;

import com.animales.frontend.model.MascotaDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.Arrays;
import java.util.List;

@Service
@SuppressFBWarnings(
    value = "EI_EXPOSE_REP2",
    justification = "RestTemplate is a Spring-managed dependency injected intentionally and reused as service collaborator."
)
public class MascotaFrontendService {

    private final RestTemplate restTemplate;

    @Value("${backend.api.base-url}")
    private String backendApiBaseUrl;

    public MascotaFrontendService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<MascotaDto> listarMascotas() {
        String url = backendApiBaseUrl + "/api/public/mascotas";
        MascotaDto[] respuesta = restTemplate.getForObject(url, MascotaDto[].class);
        return respuesta != null ? Arrays.asList(respuesta) : List.of();
    }

    public List<MascotaDto> buscarMascotas(String especie, Integer edad, String ubicacion, String genero) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromUriString(backendApiBaseUrl + "/api/public/mascotas/buscar");

        if (especie != null && !especie.isBlank()) {
            builder.queryParam("especie", especie);
        }
        if (edad != null) {
            builder.queryParam("edad", edad);
        }
        if (ubicacion != null && !ubicacion.isBlank()) {
            builder.queryParam("ubicacion", ubicacion);
        }
        if (genero != null && !genero.isBlank()) {
            builder.queryParam("genero", genero);
        }

        String url = builder.toUriString();

        MascotaDto[] respuesta = restTemplate.getForObject(url, MascotaDto[].class);
        return respuesta != null ? Arrays.asList(respuesta) : List.of();
    }
}