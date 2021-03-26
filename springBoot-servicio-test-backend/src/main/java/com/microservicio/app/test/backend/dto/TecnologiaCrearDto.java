package com.microservicio.app.test.backend.dto;

import com.microservicio.app.test.backend.entity.Tecnologia;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class TecnologiaCrearDto {
	
	private Long id;
	private String nombre;
	private Integer version;
	
	public Tecnologia toTecnologia()
	{
		return new Tecnologia(null,this.getNombre(),this.getVersion()); 
	}

}
