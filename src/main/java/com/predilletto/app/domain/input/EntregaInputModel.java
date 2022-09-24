package com.predilletto.app.domain.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaInputModel {
	
	@Valid
	@NotNull
	private ClienteIdInput cliente;
	
	@Valid
	@NotNull
	private DestinatarioInput destinatario;

	@NotNull
	private BigDecimal taxa;
	
	
}
