/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evoluti.java.junit;

import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

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
    public void configuraTeste() throws Exception {
        EnvUtil.init();
        CLIENT_ID = EnvUtil.getProperty("CLIENT_ID");
    }

    @Test
    @DisplayName("Exibir variaveis")
    @Order(2)
    void exibeConfig() {
        System.out.println(CLIENT_ID);
    }


    @Test
    @DisplayName("Testar variaveis de ambiente")
    @Order(3)
    void testaConfig() {
        CLIENT_ID = EnvUtil.getProperty("CLIENT_ID");
        assertTrue(CLIENT_ID != null);
    }

    public String getEnvParam(final String param) {
        Map<String, String> env = System.getenv();
        Optional<Map.Entry<String, String>> optParam = env.entrySet().stream()
                .filter(e -> e.getKey().equals(param))
                .findFirst();
        return optParam.map(Map.Entry::getValue).orElse(null);
    }
}
