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
	private String nome;
	private LocalDate dataCadastro;
	private StatusEnum status;
}
