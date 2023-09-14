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
            clientesRepository.salvar(new Cliente("Steven"));
            clientesRepository.salvar(new Cliente("Aladimos"));

            List<Cliente> todosClientes = clientesRepository.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando Clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " atualizado.");
                clientesRepository.atualizar(c);
            });

            todosClientes = clientesRepository.obterTodos();
            todosClientes.forEach(System.out::println);

            System.out.println("Buscando Clientes");
            clientesRepository.buscarPorNome("Ala").forEach(System.out::println);

            System.out.println("Deletando Clientes");
            clientesRepository.obterTodos().forEach(c -> {
                clientesRepository.deletar(c);
            });

            todosClientes = clientesRepository.obterTodos();

            if (todosClientes.isEmpty()) {
                System.out.println("Nenhum cliente encontrado!");
            } else {
                System.out.println("Foram encontrados clientes!");
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
