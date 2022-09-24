package com.predilletto.app.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.predilletto.app.domain.input.EntregaInputModel;
import com.predilletto.app.domain.model.Entrega;
import com.predilletto.app.model.EntregaModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaAssembler {
	
	private ModelMapper modelMapper; 
	
	
	public EntregaModel toModel (Entrega entrega) { 
		return modelMapper.map(entrega, EntregaModel.class);
		
	}
	
	public List<EntregaModel> toCollectionModel (List<Entrega> entregas) { 
		return entregas.stream()
				.map(this :: toModel)
				.collect(Collectors.toList());
	}
	
	public Entrega toEntity (EntregaInputModel entregaInputModel) {
		return modelMapper.map(entregaInputModel, Entrega.class);
	}

}
