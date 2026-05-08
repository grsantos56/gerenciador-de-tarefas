package br.com.todolist.todolist.controller;

import br.com.todolist.todolist.controller.docs.TarefaControllerDocs;
import br.com.todolist.todolist.dto.request.TarefaRequestDTO;
import br.com.todolist.todolist.dto.response.TarefaResponseDTO;
import br.com.todolist.todolist.service.TarefaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tarefas")
public class TarefaController implements TarefaControllerDocs {

    private final TarefaService tarefaService;


    @Override
    @PostMapping
    public ResponseEntity<TarefaResponseDTO> criarTarefa(@RequestBody @Valid TarefaRequestDTO dto) {
        TarefaResponseDTO responseDTO = tarefaService.criarTarefa(dto);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> atualizarTarefa(Long id, @RequestBody @Valid TarefaRequestDTO dto) {
        TarefaResponseDTO responseDTO = tarefaService.atualizarTarefa(id, dto);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarTarefa(Long id) {
        tarefaService.excluirTarefa(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> buscarPorId(Long id) {
        TarefaResponseDTO responseDTO = tarefaService.listarTarefaPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> listarTodas() {
        List<TarefaResponseDTO> responseDTOs = tarefaService.listarTarefas();
        return ResponseEntity.ok(responseDTOs);
    }
}
