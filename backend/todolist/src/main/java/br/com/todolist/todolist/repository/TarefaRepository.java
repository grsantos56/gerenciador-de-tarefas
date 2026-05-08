package br.com.todolist.todolist.repository;

import br.com.todolist.todolist.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interface de repositório para a entidade Tarefa, estendendo JpaRepository para fornecer operações CRUD.
 * Inclui métodos personalizados para buscar tarefas por status e prioridade.
 */

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
