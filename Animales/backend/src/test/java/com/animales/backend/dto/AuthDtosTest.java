package com.animales.backend.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthDtosTest {

    @Test
    void authRequestGuardaValoresCorrectamente() {
        AuthRequest request = new AuthRequest("admin", "Admin123");

        assertEquals("admin", request.username());
        assertEquals("Admin123", request.password());
    }

    @Test
    void authResponseGuardaTokenCorrectamente() {
        AuthResponse response = new AuthResponse("token-demo");

        assertEquals("token-demo", response.token());
    }
}