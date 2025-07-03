package com.gerenciador.gerenciador.de.tarefas.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Classe que representa a resposta de login de um usuário.
 * Contém o token JWT gerado após a autenticação bem-sucedida.
 */

@Schema(name = "LoginResponse", description = "Objeto de resposta com o token JWT após o login bem-sucedido")
public class LoginResponse {

    @Schema(
            description = "Token de autenticação JWT (JSON Web Token)",
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c3VhcmlvQGVtYWlsLmNvbSIsImlhdCI6MTUxNjIzOTAyMn0.sVb_eMv_oN4yYgL1f_fJ9kG6J3QJ8qB0Qy4X3Z5X6wY"
    )
    private String token;

    // Construtor
    public LoginResponse(String token) {
        this.token = token;
    }

    // Getters e Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}