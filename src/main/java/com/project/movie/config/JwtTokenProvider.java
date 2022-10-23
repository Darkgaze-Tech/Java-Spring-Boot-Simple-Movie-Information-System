package com.project.movie.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Function;

@Log4j2
@Component
public class JwtTokenProvider {

    private String key = "MIICXgIBAAKBgQCiLU3sYGTb9tZympsw2TmuiI2/sXXxwXRr/bnkmF49sTVTIuqsieBybneA+txODbFcpzLkqTyzHzwyBqLvbudpTQoL7KytDZY7TWWn77FHH6bN2eY0hY1XFmkbAwa17foLa+Nqv7M8wSBmBOsha6TEcBmxwSuuzRQoapX+T6THSwIDAQABAoGBAKBTc2aMQQln88bmayeWiDywCJoKgRQX8NVVxzFjJD+O8a2XpjIeOAJIjOG4npSXWtDDBkAhwr090s+N2gQkOqY+oyegGO1AMW3rHrKSjIc6S6xD6c1x342YdKNbxBpdQoI5jBr3/A++Bx0kaKLrJfBaYfTUQPRUhowJdprfZUWxAkEA11lbNYy/cm8c5xoLPiiYbnUSRjFta1v70TWSoXwfS77ua5JSTqRxQR/GTbfalR6hTWy4xcOlUAIp04zoVgaS+QJBAMDKbpDZsVAZb1v76G1GZ4Sv9lmeNeTNkZ/LpnDZzKNzzfCPyt+gZqWgjjihKywCui8b8ZSZQ4wAsIhBULcEuWMCQFcYssUDueKEggIODIIFVTFHdC6jiwei2kQREM7zLo0qGa+0LEnWRF/8g+2m8GbjToXL9Sc3K8MiPNxs3wL8AbECQQCjqvdEh1sNahps16XRJyT4sz2LhVGxhYcsRoJtaald1hEuOZR8VrrtvykeEE0LVuFi3vEvOxHqCCfV9MUmKp+LAkEAthDJb4gyRhnEpiSJaYJYuZ52Eg/TfUfIttvU1BEL76idwmI7x7klOeBHxl/MdZChI3rp3Y6assCGyXA0v2gLMA==";

    private SecretKey secretKey;

    @PostConstruct
    public void setUpSecretKey() {
        try {
            secretKey = Keys.hmacShaKeyFor(key.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("Error generating JWT Secret Key : {}", e.getMessage());
            throw new RuntimeException("Error generating JWT Secret Key", e);
        }
    }

    public String generateToken(Authentication authentication, String role) {
        String token = null;
        try {
            LocalDate now = LocalDate.now();
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Claims clms = Jwts.claims().setSubject(authentication.getName());
            clms.put("role", new ObjectMapper().writeValueAsString(role));
            ObjectMapper mapper = new ObjectMapper();
            return Jwts.builder()
                    .setId(UUID.randomUUID().toString())
                    .setClaims(clms)
                    .setSubject(authentication.getName())
                    .setIssuer(authentication.getName())
                    .setIssuedAt(Date.from(Instant.now()))
                    .setExpiration(Date.from(now.plusDays(1).atStartOfDay(defaultZoneId).toInstant()))
                    .signWith(secretKey)
                    .compact();
        } catch (Exception e) {
            log.error("error get token" + e.getMessage());
        }
        return token;
    }

    public boolean validateToken(String token, HttpServletRequest request) {
        try {
            log.info("Validating token...");
            String role = getRole(token).replace("\"", "");
            log.info("Current role: " + role);
            log.info("Current request method: " + request.getMethod());
            log.info("Current reqeust URI: " + request.getRequestURI());

            if (!role.equals("admin") && request.getRequestURI().contains("/api/v1/genre") && request.getMethod().equals("POST")){
                return false;
            } 
            if (!role.equals("admin") && request.getRequestURI().contains("/api/v1/movie") && request.getMethod().equals("POST")){
                return false;
            }
            if (!role.equals("admin") && request.getRequestURI().contains("/api/v1/genre") && request.getMethod().equals("DELETE")){
                return false;
            } 
            if (!role.equals("admin") && request.getRequestURI().contains("/api/v1/movie") && request.getMethod().equals("DELETE")){
                return false;
            }
            if (role.equals("admin") && request.getRequestURI().contains("/api/v1/review") && request.getMethod().equals("POST")){
                return false;
            }
            if (role.equals("admin") && request.getRequestURI().contains("/api/v1/review") && request.getMethod().equals("DELETE")){
                return false;
            }
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid Jwt Signature: {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid Jwt Token: {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired Jwt Signature: {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported Jwt Signature: {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("Jwt claim string is empty: {}", ex.getMessage());
        }
        return false;
    }


    public String getUsername(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return claims.getSubject();
    }

    public String getRole(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (String) claims.get("role");
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(key.getBytes()).parseClaimsJws(token).getBody();
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private String getDataFromToken(String token) {
        return getClaimFromToken(token, claims -> (String) claims.get("data"));
    }

    private HashMap<String, Object> additionalInfo() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", 1);
        return data;
    }
}