package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.domain.entity.Cliente;
import org.example.domain.entity.ItemPedido;
import org.example.domain.entity.Pedido;
import org.example.domain.entity.Produto;
import org.example.domain.repository.ClientesRepository;
import org.example.domain.repository.ItensPedidoRepository;
import org.example.domain.repository.PedidosRepository;
import org.example.domain.repository.ProdutosRepository;
import org.example.exception.RegraNegocioException;
import org.example.rest.dto.ItemPedidoDTO;
import org.example.rest.dto.PedidoDTO;
import org.example.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {
    private final PedidosRepository pedidosRepository;
    private final ClientesRepository clientesRepository;
    private final ProdutosRepository produtosRepository;
    private final ItensPedidoRepository itensPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO) {
        Integer idCliente = pedidoDTO.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente).orElseThrow(() ->
                new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedidos = converterItems(pedido, pedidoDTO.getItems());
        pedidosRepository.save(pedido);
        itensPedidoRepository.saveAll(itemsPedidos);
        pedido.setItens(itemsPedidos);

        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> itemPedidoDTOS) {
        if (itemPedidoDTOS.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return itemPedidoDTOS.stream().map(peditoDTO -> {
            Integer idProduto = peditoDTO.getProduto();
            Produto produto = produtosRepository
                    .findById(idProduto)
                    .orElseThrow(() -> new RegraNegocioException("Código de produto inválido: " + idProduto));


            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(peditoDTO.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            return itemPedido;

        }).collect(Collectors.toList());
    }
}
