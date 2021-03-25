package com.microservicio.app.test.backend.service;

import java.util.List;

import com.microservicio.app.test.backend.dto.TecnologiaDto;
import com.microservicio.app.test.backend.entity.Tecnologia;

public interface TecnologiaService{

	public List<TecnologiaDto> findAll();
	public TecnologiaDto findByNombre(String nombre);
	public Tecnologia addTecnologia(Tecnologia tecnologia);
	public Tecnologia updateTecnologia(Long id, Tecnologia tecnologia);
	public void deleteTecnologia(Long id);
}
