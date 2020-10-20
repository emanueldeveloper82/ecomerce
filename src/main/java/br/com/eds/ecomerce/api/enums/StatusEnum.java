package br.com.eds.ecomerce.api.enums;

import lombok.Getter;

public enum StatusEnum {

	ATIVO("Ativo"),
	INATIVO("Inativo");

	@Getter
	private final String status;

	StatusEnum(String status) {
		this.status = status;
	}

}
