package com.predilletto.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.predilletto.app.assembler.OcorrenciaAssembler;
import com.predilletto.app.domain.input.OcorrenciaInput;
import com.predilletto.app.domain.model.Entrega;
import com.predilletto.app.domain.model.Ocorrencia;
import com.predilletto.app.domain.service.BuscaEntregaService;
import com.predilletto.app.domain.service.RegistroOcorrenciaService;
import com.predilletto.app.model.OcorrenciaModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

	private RegistroOcorrenciaService registroOcorrenciaService; 
	
	private OcorrenciaAssembler ocorrenciaAssembler; 
	
	private BuscaEntregaService buscaEntregaService;
		
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaModel registrar (@PathVariable Long entregaId, 
			@Valid @RequestBody  OcorrenciaInput ocorrenciaInput) {
		Ocorrencia ocorrenciaregistrada =
		registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());
		
		return ocorrenciaAssembler.toModel(ocorrenciaregistrada);
	}
	
	@GetMapping
	public List<OcorrenciaModel> listar (@PathVariable Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		 
		return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias()); 
	}
	
}
