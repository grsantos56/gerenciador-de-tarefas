package br.com.todolist.todolist.controller.docs;

import br.com.todolist.todolist.dto.request.TarefaRequestDTO;
import br.com.todolist.todolist.dto.response.TarefaResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TarefaControllerDocs {

     @Operation(summary = "Criar Tarefa",
             description = "Cria uma nova tarefa com os detalhes fornecidos.",
             tags = {"Tarefa"},
             responses = {
                     @ApiResponse(description = "Tarefa criada com sucesso", responseCode = "200", content = {
                             @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TarefaResponseDTO.class))
                     }),
                     @ApiResponse(description = "Requisição inválida", responseCode = "400", content = @Content),
                     @ApiResponse(description = "Não autorizado", responseCode = "401", content = @Content),
                     @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
             }
     )
     public ResponseEntity<TarefaResponseDTO> criarTarefa(@RequestBody @Valid TarefaRequestDTO dto);

     @Operation(summary = "Atualizar Tarefa",
             description = "Atualiza os detalhes de uma tarefa existente com base no ID fornecido.",
             tags = {"Tarefa"},
             responses = {
                     @ApiResponse(description = "Tarefa atualizada com sucesso", responseCode = "200", content = {
                             @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TarefaResponseDTO.class))
                     }),
                     @ApiResponse(description = "Tarefa não encontrada", responseCode = "404", content = @Content),
                     @ApiResponse(description = "Requisição inválida", responseCode = "400", content = @Content),
                     @ApiResponse(description = "Não autorizado", responseCode = "401", content = @Content),
                     @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
             }
     )
     public ResponseEntity<TarefaResponseDTO> atualizarTarefa(Long id, @RequestBody @Valid TarefaRequestDTO dto);

     @Operation(summary = "Deletar Tarefa",
             description = "Exclui uma tarefa existente com base no ID fornecido.",
             tags = {"Tarefa"},
             responses = {
                     @ApiResponse(description = "Tarefa deletada com sucesso", responseCode = "204", content = @Content),
                     @ApiResponse(description = "Tarefa não encontrada", responseCode = "404", content = @Content),
                     @ApiResponse(description = "Não autorizado", responseCode = "401", content = @Content),
                     @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
             }
     )
     public ResponseEntity<?> deletarTarefa(Long id);

     @Operation(summary = "Buscar Tarefa por ID",
             description = "Recupera os detalhes de uma tarefa específica com base no ID fornecido.",
             tags = {"Tarefa"},
             responses = {
                     @ApiResponse(description = "Tarefa encontrada com sucesso", responseCode = "200", content = {
                             @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TarefaResponseDTO.class))
                     }),
                     @ApiResponse(description = "Tarefa não encontrada", responseCode = "404", content = @Content),
                     @ApiResponse(description = "Não autorizado", responseCode = "401", content = @Content),
                     @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
             }
     )
     public ResponseEntity<TarefaResponseDTO> buscarPorId(Long id);

     @Operation(summary = "Listar Todas as Tarefas",
            description = "Recupera uma lista de todas as tarefas existentes.",
            tags = {"Tarefa"},
            responses = {
                    @ApiResponse(description = "Lista de tarefas recuperada com sucesso", responseCode = "200", content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = TarefaResponseDTO.class)))
                    }),
                    @ApiResponse(description = "Não autorizado", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Erro interno do servidor", responseCode = "500", content = @Content)
            }
     )
     public ResponseEntity<List<TarefaResponseDTO>> listarTodas();

}
