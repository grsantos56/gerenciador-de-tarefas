package com.gerenciador.gerenciador.de.tarefas.service;

import com.gerenciador.gerenciador.de.tarefas.dto.UsuarioDTO;
import com.gerenciador.gerenciador.de.tarefas.model.Usuario;
import com.gerenciador.gerenciador.de.tarefas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Registra um novo usuário a partir do DTO fornecido.
     * Codifica a senha antes de salvar o usuário no repositório.
     *
     * @param dto O DTO contendo os dados do usuário a ser registrado.
     * @return O usuário registrado.
     */

    public Usuario registrarUsuario(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(dto.getNomeCompleto());
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha())); 
        return usuarioRepository.save(usuario);
    }

    /**
     * Lista todos os usuários registrados no sistema.
     *
     * @return Uma lista de usuários.
     */

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    
    /**
     * Busca um usuário pelo ID fornecido.
     * Lança uma exceção se o usuário não for encontrado.
     *
     * @param id O ID do usuário a ser buscado.
     * @return O usuário encontrado.
     */

    public Usuario buscarUsuarioPorId(Long id) {
    return usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
