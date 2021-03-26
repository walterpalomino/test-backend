package com.microservicio.app.test.backend.dto;

import com.microservicio.app.test.backend.entity.Candidato;
import com.microservicio.app.test.backend.entity.TipoDocumento;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CandidatoCrearDto {
	
	private String nombre;
	private String apellido; 
	private TipoDocumento tipo;
	private String numDocumento;
		
	public Candidato toCandidato()
	{
		return new Candidato(null, this.getNombre(), this.getApellido(), this.getTipo(),
				this.getNumDocumento());
	}

}
