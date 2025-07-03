package com.gerenciador.gerenciador.de.tarefas.controller;

import com.gerenciador.gerenciador.de.tarefas.dto.LoginRequest;
import com.gerenciador.gerenciador.de.tarefas.dto.UsuarioDTO;
import com.gerenciador.gerenciador.de.tarefas.model.Usuario;
import com.gerenciador.gerenciador.de.tarefas.security.JwtUtil;
import com.gerenciador.gerenciador.de.tarefas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;

/**
 * Controlador responsável por gerenciar autenticação de usuários.
 * Permite registrar novos usuários e realizar login com autenticação JWT.
 */

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints para registro e login de usuários")
public class AuthController {
        /**
         * Serviço de usuário para registrar novos usuários e autenticar.
         */
        @Autowired
        private UsuarioService usuarioService;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtUtil jwtUtil;

        /**
         * Registra um novo usuário no sistema.
         *
         * @param dto Dados do usuário a ser registrado.
         * @return O usuário registrado.
         */

        @PostMapping("/register")
        @Operation(summary = "Registrar novo usuário",
                description = "Cria uma nova conta de usuário no sistema")
        @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso",
                content = @Content(schema = @Schema(implementation = Usuario.class)))
        @ApiResponse(responseCode = "400", description = "Dados inválidos ou usuário já existe")
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
        public Usuario registrar(
                @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "Dados do novo usuário",
                        required = true,
                        content = @Content(schema = @Schema(implementation = UsuarioDTO.class)))
                @RequestBody UsuarioDTO dto) {
                return usuarioService.registrarUsuario(dto);
        }

        /**
         * Realiza o login de um usuário e retorna um token JWT.
         *
         * @param request Dados de login contendo login e senha.
         * @return Um mapa contendo o token JWT e informações do usuário autenticado.
         */

        @PostMapping("/login")
        @Operation(summary = "Autenticar usuário",
                description = "Realiza login e retorna token JWT")
        @ApiResponse(responseCode = "200", description = "Autenticação bem-sucedida",
                content = @Content(schema = @Schema(implementation = Map.class,
                        example = "{\"token\": \"string\", \"id\": 1, \"nome\": \"Nome Usuário\"}")))
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
        @ApiResponse(responseCode = "400", description = "Dados de login inválidos")
        public Map<String, Object> login(
                @io.swagger.v3.oas.annotations.parameters.RequestBody(
                        description = "Credenciais de login",
                        required = true,
                        content = @Content(schema = @Schema(implementation = LoginRequest.class)))
                @RequestBody LoginRequest request) {
                System.out.println("Tentando autenticar: " + request.getLogin());

                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getLogin(), request.getSenha())
                );

                String token = jwtUtil.gerarToken(authentication);
                Usuario usuario = (Usuario) authentication.getPrincipal();

                return Map.of(
                        "token", token,
                        "id", usuario.getId(),
                        "nome", usuario.getNomeCompleto()
                );
        }
}