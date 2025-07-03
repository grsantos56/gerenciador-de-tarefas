package com.gerenciador.gerenciador.de.tarefas;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Gerenciador de Tarefas.
 * Configura o Spring Boot e define as informações da API usando OpenAPI.
 *
 * @author Gabriel Rodrigues dos Santos
 * @version 1.0
 * @since 2025-06-30
 */

@SpringBootApplication
@OpenAPIDefinition(
		info = @io.swagger.v3.oas.annotations.info.Info(
				title = "Gerenciador de Tarefas API",
				version = "1.0",
				description = "API para gerenciar tarefas e usuários"
		))
public class GerenciadorDeTarefasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorDeTarefasApplication.class, args);
	}

}
