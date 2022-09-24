package com.predilletto.app.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.predilletto.app.domain.model.Cliente;
import com.predilletto.app.domain.model.Entrega;
import com.predilletto.app.domain.model.StatusEntrega;
import com.predilletto.app.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CriacaoEntregaService {
	
	private CatalogoClienteService catalogoClienteService; 
	private EntregaRepository entregaRepository;
	
	@Transactional
	public Entrega criar (Entrega entrega) { 
		Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());
		
		
		return entregaRepository.save (entrega);
	}

}
