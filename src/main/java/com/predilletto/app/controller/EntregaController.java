package com.predilletto.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.predilletto.app.assembler.EntregaAssembler;
import com.predilletto.app.domain.input.EntregaInputModel;
import com.predilletto.app.domain.model.Entrega;
import com.predilletto.app.domain.repository.EntregaRepository;
import com.predilletto.app.domain.service.CriacaoEntregaService;
import com.predilletto.app.model.EntregaModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping ("/entregas")
@RestController 

public class EntregaController {

	private CriacaoEntregaService criacaoEntregaService; 
	private EntregaRepository entregaRepository; 
	private EntregaAssembler entregaAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel criar (@Valid @RequestBody EntregaInputModel entregaInputModel) {
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInputModel); 
		Entrega entregasolicitada = criacaoEntregaService.criar(novaEntrega);
		return entregaAssembler.toModel(entregasolicitada); 
	}
	
	@GetMapping
	public List<EntregaModel> listar () { 
		return entregaAssembler.toCollectionModel(entregaRepository.findAll()); 
	}
	
	@GetMapping ("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar (@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId).map(entrega -> {
			return ResponseEntity.ok(entregaAssembler.toModel(entrega));
		})
				.orElse(ResponseEntity.notFound().build());
		
	}
	
}
