package br.com.todolist.todolist.dto.request;

import br.com.todolist.todolist.model.enums.Prioridade;
import br.com.todolist.todolist.model.enums.StatusTarefa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefaRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 100, message = "O título deve conter no máximo 100 caracteres")
    private String titulo;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Prioridade prioridade;
    private StatusTarefa statusTarefa;

}
