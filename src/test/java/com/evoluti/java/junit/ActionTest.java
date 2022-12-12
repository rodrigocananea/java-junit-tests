/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evoluti.java.junit;

import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Rodrigo Cananea <rodrigoaguiar35@gmail.com>
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ActionTest {

    private String CLIENT_ID;

    @BeforeEach
    @DisplayName("Inicializando configuração")
    @Order(1)
    public void configuraTeste() {
        String txt = getEnvParam("OS");
        System.out.println(txt);

        CLIENT_ID = getEnvParam("CLIENT_ID");
    }

    @Test
    @DisplayName("Testar variaveis de ambiente")
    @Order(2)
    void testaConfig() {
        String windowsNt = getEnvParam("OS");
        System.out.println("CLIENT_ID:");
        System.out.println(CLIENT_ID);
        assertEquals("Windows_NT", windowsNt);
    }

    public String getEnvParam(final String param) {
        Map<String, String> env = System.getenv();
        Optional<Map.Entry<String, String>> optParam = env.entrySet().stream()
                .filter(e -> e.getKey().equals(param))
                .findFirst();
        return optParam.map(Map.Entry::getValue).orElse(null);
    }
}
