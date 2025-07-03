package com.gerenciador.gerenciador.de.tarefas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * Classe que representa a requisição de login de um usuário.
 * Contém os campos necessários para autenticação, como login e senha.
 */

@Schema(name = "LoginRequest", description = "Objeto de requisição para autenticação de usuário")
public class LoginRequest {

    @Schema(
            description = "O login do usuário (geralmente um endereço de e-mail)",
            example = "usuario@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "O login não pode estar em branco")
    private String login;

    @Schema(
            description = "A senha do usuário",
            example = "minhasenha123",
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "string",
            format = "password"
    )
    @NotBlank(message = "A senha não pode estar em branco")
    private String senha;

    // Getters e Setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}