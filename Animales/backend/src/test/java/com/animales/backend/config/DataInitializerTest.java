package com.animales.backend.config;

import com.animales.backend.repository.MascotaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.CommandLineRunner;

import static org.mockito.Mockito.*;

class DataInitializerTest {

    @Test
    void initDataGuardaTresMascotasCuandoBaseEstaVacia() throws Exception {
        MascotaRepository mascotaRepository = mock(MascotaRepository.class);
        when(mascotaRepository.count()).thenReturn(0L);

        DataInitializer dataInitializer = new DataInitializer();
        CommandLineRunner runner = dataInitializer.initData(mascotaRepository);

        runner.run();

        verify(mascotaRepository, times(3)).save(any());
    }

    @Test
    void initDataNoGuardaNadaCuandoYaHayRegistros() throws Exception {
        MascotaRepository mascotaRepository = mock(MascotaRepository.class);
        when(mascotaRepository.count()).thenReturn(2L);

        DataInitializer dataInitializer = new DataInitializer();
        CommandLineRunner runner = dataInitializer.initData(mascotaRepository);

        runner.run();

        verify(mascotaRepository, never()).save(any());
    }
}