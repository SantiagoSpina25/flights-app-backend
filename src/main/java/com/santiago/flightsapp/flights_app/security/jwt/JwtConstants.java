package com.santiago.flightsapp.flights_app.security.jwt;

import java.security.Key;

import io.jsonwebtoken.Jwts;

public class JwtConstants {
    // Llave secreta para firmar el token
    public static final Key SECRET_KEY = Jwts.SIG.HS256.key().build();
    // Bearer es un esquema de autenticación
    public static final String PREFIX_TOKEN = "Bearer ";
    // Nombre del header
    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String CONTENT_TYPE = "application/json";
}
