package com.microservicio.app.test.backend.service;

import java.util.List;

import com.microservicio.app.test.backend.entity.Tecnologia;

public interface TecnologiaService{

	public List<Tecnologia> findAll();
	public Tecnologia findByNombre(String nombre);
	public Tecnologia addTecnologia(Tecnologia tecnologia);
	public Tecnologia updateTecnologia(Long id, Tecnologia tecnologia);
	public void deleteTecnologia(Long id);
}
