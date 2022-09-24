package com.predilletto.app.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.predilletto.app.domain.model.Entrega;
import com.predilletto.app.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

	BuscaEntregaService buscaEntregaService;
	EntregaRepository entregaRepository;
	
	@Transactional
	public void finalizar (Long entregaId) {
		
		Entrega entrega =buscaEntregaService.buscar(entregaId);
		
		entrega.finalizar(); 
		
		entregaRepository.save(entrega); 
		
		
	}
	
}
