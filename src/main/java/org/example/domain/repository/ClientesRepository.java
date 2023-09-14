package org.example.domain.repository;

import org.example.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNomeLike(String nomeFiltro);
}
