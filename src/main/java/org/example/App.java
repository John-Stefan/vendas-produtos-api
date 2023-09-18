package org.example;

import org.example.domain.entity.Cliente;
import org.example.domain.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App
{
    @Bean
    public CommandLineRunner commandLineRunner(@Autowired ClientesRepository clientesRepository) {
        return args -> {
            Cliente c = new Cliente(null, "Aladimos");
            clientesRepository.save(c);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
