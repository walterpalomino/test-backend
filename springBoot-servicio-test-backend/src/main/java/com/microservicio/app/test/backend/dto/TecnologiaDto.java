package com.microservicio.app.test.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor

public class TecnologiaDto {
	
	private Long id;
	private String nombre;
	private Integer version;
	
	public TecnologiaDto(String nombre, Integer version) {
		
		this.nombre = nombre;
		this.version = version;
	}



}
