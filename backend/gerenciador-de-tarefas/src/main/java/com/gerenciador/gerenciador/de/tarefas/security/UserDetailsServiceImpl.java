package com.gerenciador.gerenciador.de.tarefas.security;

import com.gerenciador.gerenciador.de.tarefas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Implementação do UserDetailsService para carregar detalhes do usuário a partir do repositório.
 * Utilizado pelo Spring Security para autenticação e autorização.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Carrega os detalhes do usuário pelo nome de usuário.
     * 
     * @param username O nome de usuário a ser carregado.
     * @return UserDetails contendo as informações do usuário.
     * @throws UsernameNotFoundException Se o usuário não for encontrado.
     */
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}

