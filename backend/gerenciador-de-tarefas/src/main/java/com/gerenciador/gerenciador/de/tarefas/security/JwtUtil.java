package com.gerenciador.gerenciador.de.tarefas.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Classe utilitária para gerar e validar tokens JWT.
 * Utiliza a biblioteca io.jsonwebtoken para manipulação de tokens.
 */

@Component
public class JwtUtil {
    private final SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long expiracao = 86400000; // 1 dia

    /**
     * Gera um token JWT para o usuário autenticado.
     *
     * @param authentication A autenticação do usuário, contendo detalhes como nome de usuário.
     * @return O token JWT gerado.
     */

    public String gerarToken(Authentication authentication) {
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiracao))
                .signWith(secretKey)
                .compact();
    }

    /**
     * Extrai o nome de usuário do token JWT.
     *
     * @param token O token JWT do qual extrair o nome de usuário.
     * @return O nome de usuário contido no token.
     */

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Valida se o token JWT é válido.
     *
     * @param token O token JWT a ser validado.
     * @return true se o token for válido, false caso contrário.
     */
    
    public boolean validarToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}

