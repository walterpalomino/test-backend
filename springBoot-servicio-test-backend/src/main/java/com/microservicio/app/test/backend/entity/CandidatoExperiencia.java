package com.microservicio.app.test.backend.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "candidato_experiencia")
public class CandidatoExperiencia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@NotNull(message = "el candidato no puede ser null")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "candidato")
	private Candidato candidato;
	
	@NotNull(message = "la tecnologia no puede ser null")
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="tecnologia")
	private Tecnologia tecnologia;
	
	@NotNull(message = "la experiencia de la tecnologia no puede ser null")
	private Integer experiencia;

	public CandidatoExperiencia(Candidato candidato, Tecnologia tecnologia, Integer experiencia) {
	
		this.candidato = candidato;
		this.tecnologia = tecnologia;
		this.experiencia = experiencia;
	}

	public CandidatoExperiencia(Tecnologia tecnologia, Integer experiencia) {
	
		this.tecnologia = tecnologia;
		this.experiencia = experiencia;
	}
		

}
