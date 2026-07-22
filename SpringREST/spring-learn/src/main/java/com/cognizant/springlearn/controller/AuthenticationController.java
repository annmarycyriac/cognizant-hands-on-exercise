package com.cognizant.springlearn.controller;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);
    private static final String SECRET = "secretkeysecretkeysecretkeysecretkey"; // 36 bytes

    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
        LOGGER.info("START authenticate");
        LOGGER.debug("Authorization Header: {}", authHeader);

        String user = getUser(authHeader);
        String token = generateJwt(user);

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        LOGGER.info("END authenticate");
        return response;
    }

    private String getUser(String authHeader) {
        LOGGER.info("START getUser");
        String encodedCredentials = authHeader.replace("Basic ", "").trim();
        byte[] decodedBytes = Base64.getDecoder().decode(encodedCredentials);
        String credentials = new String(decodedBytes, StandardCharsets.UTF_8);
        String user = credentials.split(":")[0];
        LOGGER.debug("Decoded user from Basic Auth: {}", user);
        LOGGER.info("END getUser");
        return user;
    }

    private String generateJwt(String user) {
        LOGGER.info("START generateJwt");
        Key key = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
        JwtBuilder builder = Jwts.builder();
        builder.setSubject(user);
        builder.setIssuedAt(new Date());
        // Expire in 20 minutes (1200000 ms)
        builder.setExpiration(new Date(System.currentTimeMillis() + 1200000));
        builder.signWith(key, SignatureAlgorithm.HS256);
        String token = builder.compact();
        LOGGER.debug("Generated Token: {}", token);
        LOGGER.info("END generateJwt");
        return token;
    }
}
