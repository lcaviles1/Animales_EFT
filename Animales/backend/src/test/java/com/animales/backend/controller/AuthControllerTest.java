package com.animales.backend.controller;

import com.animales.backend.dto.AuthRequest;
import com.animales.backend.dto.AuthResponse;
import com.animales.backend.service.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthController authController;

    @Test
    void loginRetornaToken() {
        AuthRequest request = new AuthRequest("admin", "Admin123");

        UserDetails userDetails = User.withUsername("admin")
                .password("Admin123")
                .roles("ADMIN")
                .build();

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken("admin", "Admin123"));

        when(userDetailsService.loadUserByUsername("admin")).thenReturn(userDetails);
        when(jwtService.generateToken(userDetails)).thenReturn("token-demo");

        ResponseEntity<AuthResponse> response = authController.login(request);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("token-demo", response.getBody().token());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userDetailsService).loadUserByUsername("admin");
        verify(jwtService).generateToken(userDetails);
    }
}