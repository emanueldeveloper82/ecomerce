package br.com.eds.ecomerce.api.enums;

import lombok.Getter;

public enum StatusEntregaEnum {

	ENTREGUE("Entregue"),
	CANCELADO("Cancelado"),
	PENDENTE("Pendente");

	@Getter
	private final String status;

	StatusEntregaEnum(String status) {
		this.status = status;
	}

}
