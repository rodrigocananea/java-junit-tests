package com.evoluti.java.junit;

import org.junit.platform.commons.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class EnvUtil {

    private static Properties properties = new Properties();

    public static void init() throws Exception {
        Path file = Paths.get(".env");

        List<String> linhas = Files.readAllLines(file, StandardCharsets.UTF_8);

        linhas.stream().filter(linha -> linha != null && !linha.equals("")).forEach(linha -> {
            String[] colunas = linha.split("=", 2);
            String parametro = colunas[0].trim();
            String valor = colunas[1].trim();

            properties.put(parametro, valor);
        });
    }

    public static String getProperty(String name) {
        return getProperty(name, null);
    }

    public static String getProperty(String name, String defaultValue) {
        return properties.getProperty(name, defaultValue);
    }
}
