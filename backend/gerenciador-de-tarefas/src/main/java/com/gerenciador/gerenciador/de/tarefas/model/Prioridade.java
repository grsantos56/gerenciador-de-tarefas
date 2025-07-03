package com.gerenciador.gerenciador.de.tarefas.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enumeração que representa os níveis de prioridade de uma tarefa.
 * As prioridades podem ser BAIXA, MEDIA ou ALTA.
 *
 * BAIXA: Indica que a tarefa não é urgente e pode ser realizada posteriormente.
 * MEDIA: Indica que a tarefa tem uma importância moderada e deve ser realizada em breve.
 * ALTA: Indica que a tarefa é urgente e deve ser realizada o mais rápido possível.
 */

@Schema(
        description = "Níveis de prioridade de uma tarefa: BAIXA, MEDIA ou ALTA"
)
public enum Prioridade {
    BAIXA,
    MEDIA,
    ALTA
}