package com.microservicio.app.test.backend.dto;

import com.microservicio.app.test.backend.entity.Candidato;
import com.microservicio.app.test.backend.entity.TipoDocumento;

import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CandidatoCrearDto {
	
	private Long id;
	private String nombre;
	private String apellido; 
	private TipoDocumento tipo;
	private String numDocumento;
		
	public Candidato toCandidato()
	{
		return new Candidato(this.getId(), this.getNombre(), this.getApellido(), this.getTipo(),
				this.getNumDocumento());
	}

}
