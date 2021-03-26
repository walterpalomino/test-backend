package com.microservicio.app.test.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservicio.app.test.backend.dto.TecnologiaDto;
import com.microservicio.app.test.backend.entity.CandidatoExperiencia;
import com.microservicio.app.test.backend.entity.Tecnologia;
import com.microservicio.app.test.backend.repository.CandidatoExperienciaRepository;

@Service
public class CandidatoExperienciaServiceImple implements CandidatoExperienciaService {

	@Autowired
	private CandidatoExperienciaRepository repo;
	
	@Autowired
	private TecnologiaService tecnologiaServicio;
	
	
	@Override
	public List<CandidatoExperiencia> findByTecnologia(String nombre) {
				
		TecnologiaDto t = tecnologiaServicio.findByNombre(nombre);	
		Tecnologia a = new Tecnologia(t.getId(),t.getNombre(),t.getVersion()); 
			
		List<CandidatoExperiencia> candidatosExperiencias = repo.findByTecnologia(a);
		
		return candidatosExperiencias; 
		
	}
	
	

}
