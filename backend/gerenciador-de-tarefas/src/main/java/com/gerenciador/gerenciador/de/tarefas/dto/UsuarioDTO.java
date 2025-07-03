package com.gerenciador.gerenciador.de.tarefas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Classe DTO para representar os dados necessários para registrar um novo usuário.
 * Utilizada na camada de apresentação para receber os dados de entrada do usuário.
 */

@Schema(name = "UsuarioDTO", description = "Objeto de requisição para registrar um novo usuário")
public class UsuarioDTO {

    @Schema(
            description = "Nome completo do usuário",
            example = "João da Silva",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "O nome completo não pode estar em branco")
    private String nomeCompleto;

    @Schema(
            description = "Login do usuário (geralmente um endereço de e-mail)",
            example = "joao.silva@email.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "O login não pode estar em branco")
    private String login;

    @Schema(
            description = "Senha da conta do usuário",
            example = "minhaSenhaSegura123",
            requiredMode = Schema.RequiredMode.REQUIRED,
            type = "string",
            format = "password",
            minLength = 6
    )
    @NotBlank(message = "A senha não pode estar em branco")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    // Getters e Setters
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

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