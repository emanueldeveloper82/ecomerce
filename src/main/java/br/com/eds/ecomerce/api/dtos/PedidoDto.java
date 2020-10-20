package br.com.eds.ecomerce.api.dtos;

import br.com.eds.ecomerce.api.entity.ProdutoPedido;
import br.com.eds.ecomerce.api.enums.StatusEntregaEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PedidoDto {
    private Long id;
    private ClienteDto cliente;
    private LocalDate dataCadastro;
    private StatusEntregaEnum statusEntrega;
    private List<ProdutoPedidoDto> produtoPedidos;
}
