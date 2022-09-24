package com.predilletto.app.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.predilletto.app.domain.model.Entrega;

public interface EntregaRepository extends JpaRepository<Entrega, Long>{
	
}
