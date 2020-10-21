package br.com.eds.ecomerce.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProdutoDto {

    private Long id;
    private String nome;
    private BigDecimal valor;
    private boolean disponivel;

}
