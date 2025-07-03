package com.gerenciador.gerenciador.de.tarefas.exception;

/**
 * Exceção lançada quando uma tarefa é considerada inválida.
 * Pode ocorrer devido a campos obrigatórios não preenchidos ou valores inválidos.
 */

public class TarefaInvalidaException extends RuntimeException {
    /**
     * Construtor que recebe uma mensagem de erro.
     *
     * @param msg Mensagem de erro a ser exibida.
     */
    public TarefaInvalidaException(String msg) {
        super(msg);
    }
}
