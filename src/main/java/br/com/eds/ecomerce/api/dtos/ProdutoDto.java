package br.com.eds.ecomerce.api.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProdutoDto {

    private Long id;
    @NotBlank(message = "O nome do produto não pode ser vazio.")
    private String nome;
    @NotBlank(message = "O valor do produto não pode ser vazio.")
    private BigDecimal valor;
    @NotBlank(message = "O status do produto não pode ser vazio.")
    private boolean disponivel;

}
