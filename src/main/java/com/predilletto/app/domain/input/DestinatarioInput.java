package com.predilletto.app.domain.input;

import javax.validation.constraints.NotBlank;

public class DestinatarioInput {
	
	@NotBlank
	private String nome; 
	
	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String numero;
	
	@NotBlank
	private String complemento;
	
	@NotBlank
	private String bairro; 

}
