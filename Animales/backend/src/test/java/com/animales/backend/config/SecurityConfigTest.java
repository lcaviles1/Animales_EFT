package com.animales.backend.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SecurityConfigTest {

    @Test
    void passwordEncoderSeCreaCorrectamente() {
        SecurityConfig securityConfig = new SecurityConfig();

        PasswordEncoder encoder = securityConfig.passwordEncoder();

        assertNotNull(encoder);
        assertTrue(encoder.matches("Admin123", encoder.encode("Admin123")));
    }

    @Test
    void userDetailsServiceCargaTresUsuarios() {
        SecurityConfig securityConfig = new SecurityConfig();
        PasswordEncoder encoder = securityConfig.passwordEncoder();

        UserDetailsService uds = securityConfig.userDetailsService(encoder);

        UserDetails admin = uds.loadUserByUsername("admin");
        UserDetails gestor = uds.loadUserByUsername("gestor");
        UserDetails voluntario = uds.loadUserByUsername("voluntario");

        assertEquals("admin", admin.getUsername());
        assertEquals("gestor", gestor.getUsername());
        assertEquals("voluntario", voluntario.getUsername());

        assertTrue(encoder.matches("Admin123", admin.getPassword()));
        assertTrue(encoder.matches("Gestor123", gestor.getPassword()));
        assertTrue(encoder.matches("Voluntario123", voluntario.getPassword()));
    }

    @Test
    void authenticationManagerSeObtieneDesdeConfiguration() throws Exception {
        SecurityConfig securityConfig = new SecurityConfig();
        AuthenticationConfiguration configuration = mock(AuthenticationConfiguration.class);
        AuthenticationManager manager = mock(AuthenticationManager.class);

        when(configuration.getAuthenticationManager()).thenReturn(manager);

        AuthenticationManager resultado = securityConfig.authenticationManager(configuration);

        assertSame(manager, resultado);
    }
}