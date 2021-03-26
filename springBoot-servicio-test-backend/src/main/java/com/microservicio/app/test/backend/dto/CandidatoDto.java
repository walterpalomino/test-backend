package com.microservicio.app.test.backend.dto;

import com.microservicio.app.test.backend.entity.Candidato;
import com.microservicio.app.test.backend.entity.TipoDocumento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CandidatoDto {

	private Long id;
	private String nombre;
	private String apellido;
	private TipoDocumento tipo;
	private String numDocumento;
	
	public CandidatoDto(Candidato c) {
		
		this.id = c.getId();
		this.nombre = c.getNombre();
		this.apellido = c.getApellido();
		this.tipo = c.getTipo();
		this.numDocumento = c.getNumDocumento();
	}

}
