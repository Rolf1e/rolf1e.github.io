package fr.ulco.minijournal.controllers;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuthUtils {

    public static final String basicPayload = Base64.getEncoder()
            .encodeToString("admin:admin".getBytes(StandardCharsets.UTF_8));
}
