package com.microservicio.app.test.backend.service;

import java.util.List;

import com.microservicio.app.test.backend.dto.CandidatoExperienciaCrearDto;
import com.microservicio.app.test.backend.dto.CandidatoExperienciaDto;
import com.microservicio.app.test.backend.entity.Candidato;

public interface CandidatoExperienciaService {
	
	public List<CandidatoExperienciaDto> findAll();
	public List<CandidatoExperienciaDto> findByTecnologia(String nombre);
	public CandidatoExperienciaDto addCandidatoExperiencia(CandidatoExperienciaCrearDto c);
	public CandidatoExperienciaDto updateCandidatoExperiencia(Long id, CandidatoExperienciaCrearDto crearExperiencia);
	public void deleteCandidatoExperiencia(Long id);
	public void deleteCandidato(Candidato candidato);
}
