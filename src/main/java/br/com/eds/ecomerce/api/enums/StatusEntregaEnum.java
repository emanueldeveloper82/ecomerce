package br.com.eds.ecomerce.api.enums;

import lombok.Getter;

public enum StatusEntregaEnum {

	ENTREGUE("Entregue"),
	CANCELADO("Pendente"),
	PENDENTE("Cancelado");

	@Getter
	private final String status;

	StatusEntregaEnum(String status) {
		this.status = status;
	}

}
