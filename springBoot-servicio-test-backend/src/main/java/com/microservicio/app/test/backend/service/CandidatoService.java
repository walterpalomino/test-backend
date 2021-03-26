package com.microservicio.app.test.backend.service;

import java.util.List;
import java.util.Optional;

import com.microservicio.app.test.backend.dto.CandidatoDto;
import com.microservicio.app.test.backend.entity.Candidato;

public interface CandidatoService {
	
	public List<CandidatoDto> findAll();
	public Candidato findById(Long id);
	public Candidato addCandidato(Candidato candidato);
	public Candidato updateCandidato(Long id,Candidato candidato);
	public void deleteCandidato(Long id);
	

}
