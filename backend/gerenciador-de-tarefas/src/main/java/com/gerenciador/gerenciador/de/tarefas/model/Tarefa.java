package com.gerenciador.gerenciador.de.tarefas.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Classe que representa uma tarefa no sistema.
 * Contém informações como nome, status, datas de início e fim,
 * prioridade e o usuário ao qual a tarefa pertence.
 */

@Entity
@Table(name = "tb_tarefa")
@Schema(name = "Tarefa", description = "Representa uma tarefa no sistema")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID único da tarefa (gerado automaticamente)", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome ou descrição da tarefa", example = "Finalizar relatório de vendas")
    private String nome;

    @Schema(description = "Status atual da tarefa", example = "Em execução")
    private String status;

    @Schema(description = "Data e hora de início da tarefa", example = "2025-07-01T10:00:00", type = "string", format = "date-time")
    private LocalDateTime inicio;

    @Schema(description = "Data e hora de fim da tarefa", example = "2025-07-05T17:00:00", type = "string", format = "date-time")
    private LocalDateTime fim;

    @Enumerated(EnumType.STRING)
    @Schema(description = "Nível de prioridade da tarefa", implementation = Prioridade.class, example = "ALTA")
    private Prioridade prioridade;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @Schema(description = "Dados do usuário ao qual a tarefa pertence")
    private Usuario usuario;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }
}