package com.theboys.trabalho;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EntityScan("com.theboys.trabalho.models")
@OpenAPIDefinition(info = @Info(
		title = "Atividade - Eduardo Henrique",
		version = "1.0",
		description = "CRUD de Produtos"))
public class TrabalhoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhoApplication.class, args);
	}

}
