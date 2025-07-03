package com.gerenciador.gerenciador.de.tarefas.exception;

/**
 * Exceção lançada quando uma tarefa não é encontrada.
 * Pode ocorrer quando se tenta acessar uma tarefa que não existe no sistema.
 */

public class TarefaNaoEncontradaException extends RuntimeException {
    /**
     * Construtor que recebe uma mensagem de erro.
     *
     * @param msg Mensagem de erro a ser exibida.
     */
    public TarefaNaoEncontradaException(String msg) {
        super(msg);
    }
}