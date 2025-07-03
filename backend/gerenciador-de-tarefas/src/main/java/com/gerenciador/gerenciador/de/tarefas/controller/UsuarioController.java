package com.gerenciador.gerenciador.de.tarefas.controller;

import com.gerenciador.gerenciador.de.tarefas.dto.UsuarioDTO;
import com.gerenciador.gerenciador.de.tarefas.model.Usuario;
import com.gerenciador.gerenciador.de.tarefas.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável por gerenciar usuários do sistema.
 * Permite registrar novos usuários e listar todos os usuários cadastrados.
 */

@RestController
@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários (requer autenticação JWT)")
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {
        @Autowired
        private UsuarioService usuarioService;

        /**
         * Registra um novo usuário no sistema.
         *
         * @param dto Dados do usuário a ser registrado.
         * @return O usuário registrado.
         */

        @PostMapping
        @Operation(
                summary = "Registrar um novo usuário",
                description = "Cria um novo usuário com nome, login e senha."

        )
        @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Usuario.class)))
        @ApiResponse(responseCode = "400", description = "Requisição inválida ou usuário já existe")
        public Usuario registrar(@RequestBody UsuarioDTO dto) {
                return usuarioService.registrarUsuario(dto);
         }

        /**
         * Lista todos os usuários cadastrados no sistema.
         * @return Lista de usuários.
         */

        @GetMapping
        @Operation(
                summary = "Listar todos os usuários",
                description = "Retorna uma lista de todos os usuários cadastrados no sistema. Requer autenticação com um token JWT válido."
        )
        @ApiResponse(responseCode = "200", description = "Lista de usuários recuperada com sucesso",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                array = @ArraySchema(schema = @Schema(implementation = Usuario.class))))
        @ApiResponse(responseCode = "401", description = "Não autorizado - Token JWT ausente ou inválido")
        @ApiResponse(responseCode = "403", description = "Proibido - Acesso negado")
        public List<Usuario> listarTodos() {
                return usuarioService.listarUsuarios();
        }

        /**
         * Busca um usuário específico pelo seu ID.
         *
         * @param id ID do usuário a ser buscado.
         * @return O usuário encontrado.
         */

        @GetMapping("/{id}")
        @Operation(
                summary = "Buscar usuário por ID",
                description = "Retorna os dados de um usuário específico pelo seu ID."
        )
        @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Usuario.class)))
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
        public Usuario buscarPorId(@PathVariable Long id) {
                return usuarioService.buscarUsuarioPorId(id);
        }
}