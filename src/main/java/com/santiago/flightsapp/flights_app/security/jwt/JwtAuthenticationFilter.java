package com.santiago.flightsapp.flights_app.security.jwt;

import static com.santiago.flightsapp.flights_app.security.jwt.JwtConstants.*;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.santiago.flightsapp.flights_app.entities.User;
import com.santiago.flightsapp.flights_app.repositories.UserRepository;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    // Metodo que obtiene los datos del usuario y crea la autenticacion
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        // Inicializa los valores
        User user = null;
        String username = null;
        String password = null;

        try {
            // Obtiene los valores enviados en la request
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            username = user.getUsername();
            password = user.getPassword();
        } catch (StreamReadException e) {
            e.printStackTrace();
        } catch (DatabindException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Crea un objeto de tipo UsernamePasswordAuthenticationToken con los valores de
        // username y password
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
                password);

        // Llama al authenticationManager para que intente autenticar al usuario
        // usando el token anterior. Si las credenciales son correctas, devuelve un
        // objeto Authentication válido;
        // si no lo son, lanzará una excepción de tipo AuthenticationException.
        return authenticationManager.authenticate(authenticationToken);
    }

    // Metodo que se ejecuta si la autentiacion fue correcta
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        // Obtengo los valores del usuario autenticado
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authResult
                .getPrincipal();
        String username = user.getUsername();

         User userEntity = userRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado después de autenticar"));

        // Firma el token
        String token = Jwts.builder()
                .subject(username) // Agrega el usuario
                .expiration(new Date(System.currentTimeMillis() + 3600000)) // Agrega la fecha de expiracion (la fecha
                .claim("id", userEntity.getId())                                                            // actual mas una hora)
                .issuedAt(new Date()) // Fecha de creacion
                .signWith(SECRET_KEY) // Firma con la secret key
                .compact();
        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);
        response.setContentType(CONTENT_TYPE);

        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("username", username);
        body.put("message", "inicio de sesion exitoso");

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(200);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        Map<String, String> body = new HashMap<>();
        body.put("message", "Error en el inicio de sesion. Username o password incorrectos");
        body.put("error", failed.getMessage());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setStatus(401);
        response.setContentType(CONTENT_TYPE);
    }

}
