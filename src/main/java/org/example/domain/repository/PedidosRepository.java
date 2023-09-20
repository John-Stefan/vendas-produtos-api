package org.example.domain.repository;

import org.example.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidosRepository extends JpaRepository<Pedido, Integer> {
    Optional<Pedido> findByIdFetchItens(Integer id);
}
