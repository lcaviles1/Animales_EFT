package com.animales.frontend.controller;

import com.animales.frontend.model.MascotaDto;
import com.animales.frontend.service.MascotaFrontendService;
import org.junit.jupiter.api.Test;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FrontendControllerTest {

    @Test
    void inicioSinFiltrosUsaListarMascotasYRetornaIndex() {
        MascotaFrontendService service = mock(MascotaFrontendService.class);
        FrontendController controller = new FrontendController(service);
        Model model = new ExtendedModelMap();

        when(service.listarMascotas()).thenReturn(List.of(crearDto("Luna")));

        String vista = controller.inicio(null, null, null, null, model);

        assertEquals("index", vista);
        assertEquals(1, ((List<?>) model.getAttribute("mascotas")).size());
        verify(service).listarMascotas();
        verify(service, never()).buscarMascotas(any(), any(), any(), any());
    }

    @Test
    void inicioConFiltrosUsaBuscarMascotasYRetornaIndex() {
        MascotaFrontendService service = mock(MascotaFrontendService.class);
        FrontendController controller = new FrontendController(service);
        Model model = new ExtendedModelMap();

        when(service.buscarMascotas("Perro", 2, "Santiago", "Hembra"))
                .thenReturn(List.of(crearDto("Nube")));

        String vista = controller.inicio("Perro", 2, "Santiago", "Hembra", model);

        assertEquals("index", vista);
        assertEquals("Perro", model.getAttribute("especie"));
        assertEquals(2, model.getAttribute("edad"));
        assertEquals("Santiago", model.getAttribute("ubicacion"));
        assertEquals("Hembra", model.getAttribute("genero"));
        verify(service).buscarMascotas("Perro", 2, "Santiago", "Hembra");
    }

    @Test
    void loginRetornaVistaLogin() {
        MascotaFrontendService service = mock(MascotaFrontendService.class);
        FrontendController controller = new FrontendController(service);

        assertEquals("login", controller.login());
    }

    @Test
    void adminRetornaVistaAdmin() {
        MascotaFrontendService service = mock(MascotaFrontendService.class);
        FrontendController controller = new FrontendController(service);

        assertEquals("admin", controller.admin());
    }

    @Test
    void agregarRetornaVistaAgregar() {
        MascotaFrontendService service = mock(MascotaFrontendService.class);
        FrontendController controller = new FrontendController(service);

        assertEquals("agregar", controller.agregar());
    }

    @Test
    void editarRetornaVistaEditar() {
        MascotaFrontendService service = mock(MascotaFrontendService.class);
        FrontendController controller = new FrontendController(service);

        assertEquals("editar", controller.editar());
    }

    @Test
    void eliminarRetornaVistaEliminar() {
        MascotaFrontendService service = mock(MascotaFrontendService.class);
        FrontendController controller = new FrontendController(service);

        assertEquals("eliminar", controller.eliminar());
    }

    private MascotaDto crearDto(String nombre) {
        MascotaDto dto = new MascotaDto();
        dto.setNombre(nombre);
        return dto;
    }
}