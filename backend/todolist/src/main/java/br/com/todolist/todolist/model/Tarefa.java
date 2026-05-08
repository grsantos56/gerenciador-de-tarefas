package br.com.todolist.todolist.model;

import br.com.todolist.todolist.model.enums.Prioridade;
import br.com.todolist.todolist.model.enums.StatusTarefa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Classe que representa a entidade Tarefa, mapeada para a tabela "tarefa" no banco de dados.
 * Contém os atributos e anotações necessárias para o mapeamento JPA.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tarefa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", length = 100)
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "status_tarefa")
    @Enumerated(EnumType.STRING)
    private StatusTarefa statusTarefa;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "inicio_tarefa")
    private LocalDateTime inicioTarefa;

    @Column(name = "fim_tarefa")
    private LocalDateTime fimTarefa;
}
