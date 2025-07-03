package com.gerenciador.gerenciador.de.tarefas.repository;

import com.gerenciador.gerenciador.de.tarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositório para gerenciar as operações de persistência das tarefas.
 * Extende JpaRepository para fornecer métodos CRUD e consultas personalizadas.
 */

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByUsuarioId(Long usuarioId);
}
