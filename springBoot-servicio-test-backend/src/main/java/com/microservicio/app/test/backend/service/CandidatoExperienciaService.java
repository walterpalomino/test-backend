package com.microservicio.app.test.backend.service;

import java.util.List;

import com.microservicio.app.test.backend.entity.CandidatoExperiencia;

public interface CandidatoExperienciaService {
	
	public List<CandidatoExperiencia> findByTecnologia(String nombre);

}
