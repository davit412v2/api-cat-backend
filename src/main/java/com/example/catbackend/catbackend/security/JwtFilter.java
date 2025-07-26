package com.example.catbackend.catbackend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * Filtro que intercepta cada solicitud HTTP para validar el token JWT.
 * Si el token es válido, se configura la autenticación en el contexto de
 * seguridad de Spring.
 */
public class JwtFilter extends OncePerRequestFilter {

    private final JwtSecurity jwtSecurity;

    public JwtFilter(JwtSecurity jwtSecurity) {
        this.jwtSecurity = jwtSecurity;
    }

    /**
     * Filtra cada solicitud entrante verificando si hay un token JWT válido en la
     * cabecera Authorization.
     *
     * @param request     solicitud HTTP entrante
     * @param response    respuesta HTTP
     * @param filterChain cadena de filtros que deben ejecutarse
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtSecurity.extractUsername(token);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtSecurity.validateToken(token)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        username, null, Collections.emptyList());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}