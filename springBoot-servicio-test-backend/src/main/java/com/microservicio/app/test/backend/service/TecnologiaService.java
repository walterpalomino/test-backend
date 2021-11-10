package com.microservicio.app.test.backend.service;

import java.util.List;

import com.microservicio.app.test.backend.dto.TecnologiaCrearDto;
import com.microservicio.app.test.backend.dto.TecnologiaDto;
import com.microservicio.app.test.backend.entity.Tecnologia;

public interface TecnologiaService{

	public List<TecnologiaDto> findAll();
	public Tecnologia findById(long id);
	public TecnologiaDto findByNombre(String nombre);
	public TecnologiaDto addTecnologia(TecnologiaCrearDto tecnologia);
	public TecnologiaDto updateTecnologia(Long id, TecnologiaCrearDto tecnologia);
	public void deleteTecnologia(Long id); 
	
}
