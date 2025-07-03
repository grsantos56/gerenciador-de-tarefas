package com.gerenciador.gerenciador.de.tarefas.service;

import com.gerenciador.gerenciador.de.tarefas.dto.TarefaDTO;
import com.gerenciador.gerenciador.de.tarefas.model.Tarefa;
import com.gerenciador.gerenciador.de.tarefas.model.Usuario;
import com.gerenciador.gerenciador.de.tarefas.repository.TarefaRepository;
import com.gerenciador.gerenciador.de.tarefas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {
    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Cria uma nova tarefa a partir do DTO fornecido.
     * Verifica se a data de início é anterior à data de fim e se o usuário existe.
     *
     * @param dto O DTO contendo os dados da tarefa a ser criada.
     * @return A tarefa criada.
     */

    public Tarefa criarTarefa(TarefaDTO dto) {
        if (dto.getInicio().isAfter(dto.getFim())) {
            throw new IllegalArgumentException("A data de início não pode ser posterior à data de fim.");
        }

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Tarefa tarefa = new Tarefa();
        tarefa.setNome(dto.getNome());
        tarefa.setStatus(dto.getStatus());
        tarefa.setInicio(dto.getInicio());
        tarefa.setFim(dto.getFim());
        tarefa.setPrioridade(dto.getPrioridade());
        tarefa.setUsuario(usuario);

        return tarefaRepository.save(tarefa);
    }

    /**
     * Lista todas as tarefas associadas a um usuário específico.
     *
     * @param usuarioId O ID do usuário cujas tarefas serão listadas.
     * @return Uma lista de tarefas associadas ao usuário.
     */

    public List<Tarefa> listarTarefasPorUsuario(Long usuarioId) {
        return tarefaRepository.findByUsuarioId(usuarioId);
    }

    /**
     * Atualiza uma tarefa existente com os dados fornecidos no DTO.
     * Verifica se a data de início é anterior à data de fim.
     *
     * @param id  O ID da tarefa a ser atualizada.
     * @param dto O DTO contendo os novos dados da tarefa.
     * @return A tarefa atualizada.
     */

    public Tarefa atualizarTarefa(Long id, TarefaDTO dto) {
        if (dto.getInicio().isAfter(dto.getFim())) {
            throw new IllegalArgumentException("A data de início não pode ser posterior à data de fim.");
        }

        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        tarefa.setNome(dto.getNome());
        tarefa.setStatus(dto.getStatus());
        tarefa.setInicio(dto.getInicio());
        tarefa.setFim(dto.getFim());
        tarefa.setPrioridade(dto.getPrioridade());

        return tarefaRepository.save(tarefa);
    }

    /**
     * Exclui uma tarefa pelo ID fornecido.
     *
     * @param id O ID da tarefa a ser excluída.
     */

    public void excluirTarefa(Long id) {
        tarefaRepository.deleteById(id);
    }
}
