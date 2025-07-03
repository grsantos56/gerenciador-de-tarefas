package com.gerenciador.gerenciador.de.tarefas.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * Filtro de autenticação JWT que intercepta requisições HTTP para verificar o token JWT.
 * Se o token for válido, configura o contexto de segurança com as informações do usuário.
 */

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // Rotas que não precisam passar pelo filtro JWT
    private static final List<String> EXCLUDED_PATHS = List.of(
            "/auth", "/auth/",
            "/auth/login", "/auth/register",
            "/v3/api-docs", "/v3/api-docs/",
            "/v3/api-docs/swagger-config",
            "/swagger-ui.html",
            "/swagger-ui/index.html",
            "/swagger-ui",
            "/swagger-ui/"
    );

    /**
     * Método que executa o filtro de autenticação.
     * Verifica se o token JWT está presente e é válido, e configura o contexto de segurança.
     *
     * @param request  A requisição HTTP recebida.
     * @param response A resposta HTTP a ser enviada.
     * @param filterChain A cadeia de filtros a serem executados.
     * @throws IOException Se ocorrer um erro de entrada/saída.
     * @throws ServletException Se ocorrer um erro no servlet.
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws IOException, ServletException {
        String path = request.getServletPath();

        if (EXCLUDED_PATHS.stream().anyMatch(path::startsWith)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            username = jwtUtil.getUsernameFromToken(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validarToken(jwt)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
