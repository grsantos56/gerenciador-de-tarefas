package com.gerenciador.gerenciador.de.tarefas.controller;

import com.gerenciador.gerenciador.de.tarefas.dto.TarefaDTO;
import com.gerenciador.gerenciador.de.tarefas.model.Tarefa;
import com.gerenciador.gerenciador.de.tarefas.service.TarefaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável por gerenciar as tarefas do sistema.
 * Permite criar, listar, atualizar e excluir tarefas associadas a usuários.
 */

@RestController
@RequestMapping("/tarefas")
@Tag(name = "Tarefas", description = "Endpoints para gerenciamento de tarefas (requer autenticação JWT)")
@SecurityRequirement(name = "bearerAuth")
public class TarefaController {
        @Autowired
        private TarefaService tarefaService;

        /**
         * Cria uma nova tarefa associada a um usuário.
         *
         * @param dto Dados da tarefa a ser criada.
         * @return A tarefa criada.
         */

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        @Operation(
                summary = "Criar uma nova tarefa",
                description = "Cria e adiciona uma nova tarefa para um usuário."
        )
        @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = Tarefa.class)))
        @ApiResponse(responseCode = "400", description = "Dados da tarefa inválidos")
        @ApiResponse(responseCode = "401", description = "Não autorizado - Token JWT ausente ou inválido")
        public Tarefa criar(@RequestBody @Valid TarefaDTO dto) {
                return tarefaService.criarTarefa(dto);
        }

        /**
         * Lista todas as tarefas do sistema.
         *
         * @return Lista de todas as tarefas.
         */

        @GetMapping("/usuario/{usuarioId}")
        @Operation(
                summary = "Listar tarefas por usuário",
                description = "Busca e retorna todas as tarefas associadas a um usuário específico."
        )
        @ApiResponse(responseCode = "200", description = "Tarefas listadas com sucesso",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        array = @ArraySchema(schema = @Schema(implementation = Tarefa.class))))
        @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
        @ApiResponse(responseCode = "401", description = "Não autorizado - Token JWT ausente ou inválido")
        public List<Tarefa> listarPorUsuario(@PathVariable @Parameter(description = "ID do usuário para buscar as tarefas") Long usuarioId) {
                return tarefaService.listarTarefasPorUsuario(usuarioId);
        }

        /**
         * Atualiza uma tarefa existente pelo seu ID.
         *
         * @param id  ID da tarefa a ser atualizada.
         * @param dto Dados atualizados da tarefa.
         * @return A tarefa atualizada.
         */

        @PutMapping("/{id}")
        @Operation(
                summary = "Atualizar uma tarefa",
                description = "Atualiza os detalhes de uma tarefa existente pelo seu ID."
        )
        @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = Tarefa.class)))
        @ApiResponse(responseCode = "400", description = "Dados da tarefa inválidos")
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
        @ApiResponse(responseCode = "401", description = "Não autorizado - Token JWT ausente ou inválido")
        public Tarefa atualizar(@PathVariable @Parameter(description = "ID da tarefa a ser atualizada") Long id, @RequestBody @Valid TarefaDTO dto) { 
                return tarefaService.atualizarTarefa(id, dto);
        }

        /**
         * Exclui uma tarefa do sistema pelo seu ID.
         *
         * @param id ID da tarefa a ser excluída.
         */

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT) 
        @Operation(
                summary = "Excluir uma tarefa",
                description = "Exclui uma tarefa do sistema pelo seu ID."
        )
        @ApiResponse(responseCode = "204", description = "Tarefa excluída com sucesso")
        @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
        @ApiResponse(responseCode = "401", description = "Não autorizado - Token JWT ausente ou inválido")
        public void excluir(@PathVariable @Parameter(description = "ID da tarefa a ser excluída") Long id) {
                tarefaService.excluirTarefa(id);
        }
}