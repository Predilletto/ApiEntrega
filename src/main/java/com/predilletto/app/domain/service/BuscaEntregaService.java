package com.predilletto.app.domain.service;

import org.springframework.stereotype.Service;

import com.predilletto.app.domain.exception.EntidadeNaoEncontradaException;
import com.predilletto.app.domain.model.Entrega;
import com.predilletto.app.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
	
	private EntregaRepository entregaRepository; 

	public Entrega buscar(Long entregaId )   {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada") );
	}
	
}
