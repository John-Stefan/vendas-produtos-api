package org.example;

import org.example.domain.entity.Cliente;
import org.example.domain.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class App
{
    @Bean
    public CommandLineRunner init(@Autowired ClientesRepository clientesRepository) {
        return args -> {
            System.out.println("Salvando Clientes");
            clientesRepository.save(new Cliente("Steven"));
            clientesRepository.save(new Cliente("Aladimos"));

            List<Cliente> todosClientes = clientesRepository.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("Buscando clientes");
            clientesRepository.findByNomeOrId("Ala", 1).forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
