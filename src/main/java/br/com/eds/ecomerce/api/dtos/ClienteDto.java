package br.com.eds.ecomerce.api.dtos;

import br.com.eds.ecomerce.api.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClienteDto {
	private Long id;
	@NotBlank(message = "O nome do Cliente Não pode ser vazio.")
	private String nome;
	@NotBlank(message = "A data de cadastro não pode ser vazia.")
	private LocalDate dataCadastro;
	@NotBlank(message = "O status não pode ser vazio}")
	private StatusEnum status;
}
