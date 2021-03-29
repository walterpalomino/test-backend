package com.microservicio.app.test.backend.service;

import java.util.List;
import java.util.Optional;

import com.microservicio.app.test.backend.dto.CandidatoCrearDto;
import com.microservicio.app.test.backend.dto.CandidatoDto;
import com.microservicio.app.test.backend.entity.Candidato;

public interface CandidatoService {
	
	public List<CandidatoDto> findAll();
	public CandidatoDto findById(Long id);
	public CandidatoDto addCandidatoDto(CandidatoCrearDto candidato);
	public CandidatoDto updateCandidatoDto(Long id,CandidatoCrearDto candidato);
	public void deleteCandidato(Long id);
	

}
