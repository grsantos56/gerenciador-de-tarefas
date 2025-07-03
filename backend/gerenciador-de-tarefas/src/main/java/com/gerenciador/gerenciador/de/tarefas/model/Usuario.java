package com.gerenciador.gerenciador.de.tarefas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Classe que representa um usuário do sistema.
 * Implementa a interface UserDetails para integração com o Spring Security.
 */

@Entity
@Table(name = "tb_usuario")
@Schema(name = "Usuario", description = "Representa um usuário do sistema")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único do usuário (gerado automaticamente)", example = "101", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome completo do usuário", example = "Maria Santos")
    private String nomeCompleto;

    @Column(unique = true)
    @Schema(description = "Login do usuário (deve ser único e usado para autenticação)", example = "maria.s@email.com")
    private String login;

    @JsonIgnore
    @Schema(description = "Senha da conta do usuário (não é retornada na resposta da API)", hidden = true) 
    private String senha;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @JsonIgnore
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    @JsonIgnore
    @Schema(hidden = true)
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    @JsonIgnore
    @Schema(hidden = true)
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    @JsonIgnore
    @Schema(hidden = true)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    @Schema(hidden = true)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    @Schema(hidden = true)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    @Schema(hidden = true)
    public boolean isEnabled() {
        return true;
    }
}