package br.com.todolist.todolist.service;

import br.com.todolist.todolist.dto.request.TarefaRequestDTO;
import br.com.todolist.todolist.dto.response.TarefaResponseDTO;
import br.com.todolist.todolist.mapper.TarefaMapper;
import br.com.todolist.todolist.model.Tarefa;
import br.com.todolist.todolist.repository.TarefaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço para gerenciar as operações relacionadas às tarefas, utilizando o TarefaRepository para acessar os dados.
 * Esta classe pode incluir métodos para criar, atualizar, excluir e buscar tarefas, além de aplicar regras de negócio.
 */

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public TarefaResponseDTO criarTarefa(TarefaRequestDTO dto) {
        Tarefa tarefa = TarefaMapper.toEntity(dto);
        tarefa = tarefaRepository.save(tarefa);
        return TarefaMapper.toDTO(tarefa);
    }

    public List<TarefaResponseDTO> listarTarefas() {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        return tarefas.stream()
                .map(TarefaMapper::toDTO)
                .toList();
    }

    public TarefaResponseDTO listarTarefaPorId(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        return TarefaMapper.toDTO(tarefa);
    }

    public TarefaResponseDTO atualizarTarefa(Long id, TarefaRequestDTO dto) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        TarefaMapper.updateEntity(tarefa, dto);
        tarefa = tarefaRepository.save(tarefa);
        return TarefaMapper.toDTO(tarefa);
    }

    public void excluirTarefa(Long id) {
        Tarefa tarefa = tarefaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        tarefaRepository.delete(tarefa);
    }

}
