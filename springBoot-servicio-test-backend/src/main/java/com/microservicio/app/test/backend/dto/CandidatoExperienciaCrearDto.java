package com.microservicio.app.test.backend.dto;

import com.microservicio.app.test.backend.entity.Candidato;
import com.microservicio.app.test.backend.entity.CandidatoExperiencia;
import com.microservicio.app.test.backend.entity.Tecnologia;


import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CandidatoExperienciaCrearDto {

	private Long id;
	private Candidato candidato;
	private Tecnologia tecnologia;
	private Integer experiencia;
	
	public CandidatoExperiencia toCandidato()
	{
		return new CandidatoExperiencia(this.getId(), this.getCandidato(), this.getTecnologia(), this.getExperiencia());
	}

}
