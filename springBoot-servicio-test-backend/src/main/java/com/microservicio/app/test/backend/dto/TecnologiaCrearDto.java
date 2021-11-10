package com.microservicio.app.test.backend.dto;

import com.microservicio.app.test.backend.entity.Tecnologia;

import lombok.*;


@Data
@AllArgsConstructor @NoArgsConstructor
public class TecnologiaCrearDto {
	
	private Long id;
	private String nombre;
	private Integer version;
	
	public Tecnologia toTecnologia()
	{
		return new Tecnologia(this.getId(),this.getNombre(),this.getVersion()); 
	}

}
