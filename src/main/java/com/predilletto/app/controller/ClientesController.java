package com.predilletto.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.predilletto.app.domain.model.Cliente;
import com.predilletto.app.domain.repository.ClienteRepository;
import com.predilletto.app.domain.service.CatalogoClienteService;

@RestController
@RequestMapping ("/clientes")
public class ClientesController {
	
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CatalogoClienteService catalogoClienteService; 
	
	@GetMapping
	public List<Cliente> listar  () { 
		
	return clienteRepository.findAll();
	
	}
	
	@GetMapping ("/{clienteId}")
	public ResponseEntity<Cliente>  obter ( @PathVariable Long clienteId) { 
		
		return clienteRepository.findById(clienteId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adcionar (@Valid @RequestBody Cliente cliente) {
		
		catalogoClienteService.salvar(cliente);
		return (cliente);
	}
	
	@PutMapping ("/{clienteId}")
	public ResponseEntity<Cliente> atualizar ( @PathVariable Long clienteId , @Valid @RequestBody Cliente cliente){ 
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		catalogoClienteService.salvar(cliente);; 
		return ResponseEntity.ok(cliente); 
	}
	
	@DeleteMapping ("/{clienteId}")
	public ResponseEntity<Void> remover (@PathVariable Long clienteId) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
	}
		catalogoClienteService.excluir(clienteId);
		
		return ResponseEntity.noContent().build(); 

} 
	}
