package com.gerenciador.gerenciador.de.tarefas.dto;

import com.gerenciador.gerenciador.de.tarefas.model.Prioridade;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * Classe que representa uma tarefa no sistema de gerenciamento de tarefas.
 * Contém informações como nome, status, datas de início e fim, prioridade e ID do usuário associado.
 */

@Schema(name = "TarefaDTO", description = "Objeto para criar ou atualizar uma tarefa")
public class TarefaDTO {

    @Schema(description = "Nome ou descrição da tarefa", example = "Comprar mantimentos para a semana", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "O nome da tarefa não pode estar em branco")
    private String nome;

    @Schema(description = "Status atual da tarefa", example = "Pendente", allowableValues = {"Pendente", "Em execução", "Concluído"})
    @NotBlank(message = "O status da tarefa não pode estar em branco")
    private String status;

    @Schema(description = "Data e hora de início da tarefa", example = "2025-07-01T09:00:00", type = "string", format = "date-time")
    @NotNull(message = "A data de início não pode ser nula")
    private LocalDateTime inicio;

    @Schema(description = "Data e hora de fim da tarefa", example = "2025-07-05T18:00:00", type = "string", format = "date-time")
    @NotNull(message = "A data de fim não pode ser nula")
    private LocalDateTime fim;

    @Schema(description = "Nível de prioridade da tarefa", example = "MEDIA", implementation = Prioridade.class)
    @NotNull(message = "A prioridade não pode ser nula")
    private Prioridade prioridade;

    @Schema(description = "ID do usuário ao qual a tarefa pertence", example = "101", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "O ID do usuário não pode ser nulo")
    private Long usuarioId;

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}