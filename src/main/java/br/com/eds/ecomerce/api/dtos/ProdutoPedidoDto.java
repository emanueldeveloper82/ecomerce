package br.com.eds.ecomerce.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProdutoPedidoDto {
    private Long id;
    private ProdutoDto produto;
    private Integer quantidade;
}
