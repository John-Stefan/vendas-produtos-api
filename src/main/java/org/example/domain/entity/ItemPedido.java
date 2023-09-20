package org.example.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_pedido")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @Column(name = "quantidade")
    private Integer quantidade;
}
