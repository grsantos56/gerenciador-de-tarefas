package com.gerenciador.gerenciador.de.tarefas.repository;

import com.gerenciador.gerenciador.de.tarefas.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositório para gerenciar as operações de persistência dos usuários.
 * Extende JpaRepository para fornecer métodos CRUD e consultas personalizadas.
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);
}

