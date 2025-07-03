package com.gerenciador.gerenciador.de.tarefas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Classe para tratar exceções globais na aplicação.
 * Captura exceções específicas e retorna respostas HTTP apropriadas.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Trata a exceção TarefaInvalidaException e retorna um status HTTP 400 (Bad Request).
     *
     * @param ex A exceção capturada.
     * @return ResponseEntity com a mensagem de erro e status 400.
     */
    
    @ExceptionHandler(TarefaInvalidaException.class)
    public ResponseEntity<String> handleTarefaInvalida(TarefaInvalidaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    /**
     * Trata a exceção TarefaNaoEncontradaException e retorna um status HTTP 404 (Not Found).
     *
     * @param ex A exceção capturada.
     * @return ResponseEntity com a mensagem de erro e status 404.
     */

    @ExceptionHandler(TarefaNaoEncontradaException.class)
    public ResponseEntity<String> handleTarefaNaoEncontrada(TarefaNaoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}