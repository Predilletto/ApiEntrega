package com.predilletto.app.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.predilletto.app.domain.exception.DomainException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@Entity
public class Entrega {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ConvertGroup (from = Default.class,to = ValidationGroups.ClienteId.class)
	@Valid 
	@NotBlank
	@NotNull
	@ManyToOne
	private Cliente cliente; 
	
	@NotBlank
	@NotNull
	@Valid
	@Embedded
	private Destinatario destinatario; 
	
	@NotBlank
	@Valid
	@NotNull
	private BigDecimal taxa;
	
	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencias = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	@JsonProperty(access =Access.READ_ONLY)
	private StatusEntrega status; 
	
	@JsonProperty(access =Access.READ_ONLY)
	private OffsetDateTime dataPedido;
	
	@JsonProperty(access =Access.READ_ONLY)
	private OffsetDateTime dataFinalizacao;

	
	public Ocorrencia adicionarOcorrencia(String descricao) {
		
		Ocorrencia ocorrencia = new Ocorrencia( );
		ocorrencia.setDescricao(descricao);
		ocorrencia.setEntrega(this);
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		
		this.getOcorrencias().add(ocorrencia);
		return ocorrencia;
		
		}


	public void finalizar() {
		if (!podeFinalizar()) {
			throw new DomainException("Entrega não pdoe ser finalizada.");
		}
		setStatus(StatusEntrega.FINALIZADA);
		setDataFinalizacao(OffsetDateTime.now());
		
	}
	
	
	public boolean podeFinalizar ( ) {
		return StatusEntrega.PENDENTE.equals(getStatus()); 
	}
	
	
	
	

}
