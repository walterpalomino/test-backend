package com.microservicio.app.test.backend.dto;


import com.microservicio.app.test.backend.entity.Candidato;
import com.microservicio.app.test.backend.entity.CandidatoExperiencia;
import com.microservicio.app.test.backend.entity.Tecnologia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
public class CandidatoExperienciaDto {

	private Long id;
	private Candidato candidato;
	private Tecnologia tecnologia;
	private Integer experiencia;
	
	public CandidatoExperienciaDto(CandidatoExperiencia c)
	{
		this.id = c.getId();
		this.candidato = c.getCandidato();
		this.tecnologia = c.getTecnologia();
		this.experiencia = c.getExperiencia();
	}

}
