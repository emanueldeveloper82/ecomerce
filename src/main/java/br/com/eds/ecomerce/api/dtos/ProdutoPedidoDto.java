package br.com.eds.ecomerce.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProdutoPedidoDto {
    private Long id;
    private ProdutoDto produto;
    @NotBlank(message = "A quantidade não pode ser vazia.")
    private Integer quantidade;
}
