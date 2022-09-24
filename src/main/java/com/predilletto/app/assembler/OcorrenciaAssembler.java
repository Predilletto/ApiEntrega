package com.predilletto.app.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.predilletto.app.domain.model.Ocorrencia;
import com.predilletto.app.model.OcorrenciaModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {

	private ModelMapper modelMapper; 
	
	public OcorrenciaModel toModel (Ocorrencia ocorrencia) {
	 return modelMapper.map(ocorrencia, OcorrenciaModel.class); 
	
	}
	
	public List<OcorrenciaModel> toCollectionModel (List<Ocorrencia> ocorrencias)  {
		return ocorrencias.stream().map(this :: toModel).collect(Collectors.toList());
	}
	
}
