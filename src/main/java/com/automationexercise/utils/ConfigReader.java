package com.automationexercise.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;


public final class ConfigReader {

    private static final Properties DEFAULTS = new Properties();
    private static final Properties LOCAL = new Properties();

    static {
        try (InputStream in = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (in == null) {
                throw new IllegalStateException("config.properties not found on classpath");
            }
            DEFAULTS.load(in);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load config.properties", e);
        }

        Path local = Paths.get("config.local.properties");
        if (Files.exists(local)) {
            try (InputStream in = Files.newInputStream(local)) {
                LOCAL.load(in);
            } catch (IOException e) {
                throw new IllegalStateException("Unable to load config.local.properties", e);
            }
        }
    }

    private ConfigReader() {
    }

    public static String get(String key) {
        String value = System.getProperty(key);
        if (isSet(value)) {
            return value.trim();
        }
        value = System.getenv(key.toUpperCase().replace('.', '_'));
        if (isSet(value)) {
            return value.trim();
        }
        value = LOCAL.getProperty(key);
        if (isSet(value)) {
            return value.trim();
        }
        return DEFAULTS.getProperty(key, "").trim();
    }

    public static String getRequired(String key) {
        String value = get(key);
        if (value.isEmpty()) {
            throw new IllegalStateException("Missing config value '" + key + "'. Create config.local.properties "
                    + "(copy config.local.properties.example) or set env var "
                    + key.toUpperCase().replace('.', '_') + ".");
        }
        return value;
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    private static boolean isSet(String v) {
        return v != null && !v.isBlank() && !v.startsWith("${");
    }
}
