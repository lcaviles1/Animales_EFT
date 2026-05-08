package com.animales.frontend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class FrontendContextCoverageTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        assertNotNull(applicationContext);
    }

    @Test
    void passwordEncoderBeanExiste() {
        PasswordEncoder passwordEncoder = applicationContext.getBean(PasswordEncoder.class);
        assertNotNull(passwordEncoder);
    }

    @Test
    void restTemplateBeanExiste() {
        RestTemplate restTemplate = applicationContext.getBean(RestTemplate.class);
        assertNotNull(restTemplate);
    }
}