package org.example.service.impl;

import org.example.domain.entity.Pedido;
import org.example.domain.repository.ClientesRepository;
import org.example.domain.repository.PedidosRepository;
import org.example.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {
    private PedidosRepository pedidosRepository;
    private ClientesRepository clientesRepository;

    public PedidoServiceImpl(PedidosRepository pedidosRepository) {
        this.pedidosRepository = pedidosRepository;
    }

    public void save(Pedido pedido) {

    }
}
