package com.predilletto.app.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.predilletto.app.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaModel {
	
	private Long id; 
	private ClienteModel cliente;
	private DestinatarioModel destinatario;
	private BigDecimal taxa; 
	private StatusEntrega status; 
	private OffsetDateTime dataPedido; 
	private OffsetDateTime dataFinalizacao;

}
