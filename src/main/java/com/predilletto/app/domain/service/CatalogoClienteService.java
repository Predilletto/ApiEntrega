package com.predilletto.app.domain.service;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.predilletto.app.domain.exception.DomainException;
import com.predilletto.app.domain.model.Cliente;
import com.predilletto.app.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatalogoClienteService {
	private ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente salvar (Cliente cliente) { 
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		if (emailEmUso) {
			throw new DomainException("Já existe um cliente cadastrado com esse email");
		}
		
		
		return clienteRepository.save(cliente);
	}
	
	public Cliente buscar (Long clienteId) {
		
		return clienteRepository.findById(clienteId)
				.orElseThrow(()-> new DomainException ("cliente nao encontrado"));
	}
	
	
	public void excluir (Long id) {
		clienteRepository.deleteById(id);
	}
	
	
	
}
