package org.example.rest.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemPedidoDTO {
    private Integer produto;
    private Integer quantidade;
}
