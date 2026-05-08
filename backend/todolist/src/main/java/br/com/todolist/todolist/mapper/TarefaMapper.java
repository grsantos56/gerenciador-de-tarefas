package br.com.todolist.todolist.mapper;

import br.com.todolist.todolist.dto.request.TarefaRequestDTO;
import br.com.todolist.todolist.dto.response.TarefaResponseDTO;
import br.com.todolist.todolist.model.Tarefa;

import java.time.LocalDateTime;

/**
 * Mapper para converter entre Tarefa, TarefaRequestDTO e TarefaResponseDTO.
 *
 * Este mapper é responsável por transformar os dados de entrada (TarefaRequestDTO) em entidades de domínio (Tarefa)
 * e também por converter as entidades de domínio em objetos de resposta (TarefaResponseDTO) para serem retornados
 * pelas APIs.
 */

public class TarefaMapper {

    // Método para converter TarefaRequestDTO em Tarefa

    public static Tarefa toEntity(TarefaRequestDTO dto) {
        Tarefa tarefa = new Tarefa();

        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setStatusTarefa(dto.getStatusTarefa());
        tarefa.setPrioridade(dto.getPrioridade());
        tarefa.setInicioTarefa(dto.getDataInicio());
        tarefa.setFimTarefa(dto.getDataFim());

        tarefa.setDataCriacao(LocalDateTime.now());
        return tarefa;
    }

    // Método para converter Tarefa em TarefaResponseDTO

    public static TarefaResponseDTO toDTO(Tarefa tarefa){
        TarefaResponseDTO dto = new TarefaResponseDTO();

        dto.setId(tarefa.getId());
        dto.setTitulo(tarefa.getTitulo());
        dto.setDescricao(tarefa.getDescricao());
        dto.setDataCriacao(tarefa.getDataCriacao());
        dto.setInicioTarefa(tarefa.getInicioTarefa());
        dto.setFimTarefa(tarefa.getFimTarefa());
        dto.setStatusTarefa(tarefa.getStatusTarefa());
        dto.setPrioridade(tarefa.getPrioridade());

        return dto;
    }

    // Método para atualizar uma entidade Tarefa existente com os dados de TarefaRequestDTO

    public static void updateEntity(Tarefa tarefa, TarefaRequestDTO dto) {
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setStatusTarefa(dto.getStatusTarefa());
        tarefa.setPrioridade(dto.getPrioridade());
        tarefa.setInicioTarefa(dto.getDataInicio());
        tarefa.setFimTarefa(dto.getDataFim());
    }

}
