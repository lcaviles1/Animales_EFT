package com.animales.backend.service;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secret", "mi-clave-super-secreta-jwt-12345678901234567890");
        ReflectionTestUtils.setField(jwtService, "jwtExpiration", 3600000L);
    }

    @Test
    void generateTokenYExtractUsernameFuncionan() {
        UserDetails user = User.withUsername("admin")
                .password("123")
                .roles("ADMIN")
                .build();

        String token = jwtService.generateToken(user);
        String username = jwtService.extractUsername(token);

        assertNotNull(token);
        assertEquals("admin", username);
    }

    @Test
    void extractClaimDevuelveExpiration() {
        UserDetails user = User.withUsername("gestor")
                .password("123")
                .roles("GESTOR")
                .build();

        String token = jwtService.generateToken(user);
        Date expiration = jwtService.extractClaim(token, Claims::getExpiration);

        assertNotNull(expiration);
    }

    @Test
    void isTokenValidRetornaTrueParaUsuarioCorrecto() {
        UserDetails user = User.withUsername("voluntario")
                .password("123")
                .roles("VOLUNTARIO")
                .build();

        String token = jwtService.generateToken(user);

        assertTrue(jwtService.isTokenValid(token, user));
    }

    @Test
    void isTokenValidRetornaFalseParaUsuarioDistinto() {
        UserDetails userToken = User.withUsername("admin")
                .password("123")
                .roles("ADMIN")
                .build();

        UserDetails otroUser = User.withUsername("gestor")
                .password("123")
                .roles("GESTOR")
                .build();

        String token = jwtService.generateToken(userToken);

        assertFalse(jwtService.isTokenValid(token, otroUser));
    }
}