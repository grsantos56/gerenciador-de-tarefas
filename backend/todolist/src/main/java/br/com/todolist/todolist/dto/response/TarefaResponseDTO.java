package br.com.todolist.todolist.dto.response;

import br.com.todolist.todolist.model.enums.Prioridade;
import br.com.todolist.todolist.model.enums.StatusTarefa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefaResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime inicioTarefa;
    private LocalDateTime fimTarefa;
    private StatusTarefa statusTarefa;
    private Prioridade prioridade;

}
