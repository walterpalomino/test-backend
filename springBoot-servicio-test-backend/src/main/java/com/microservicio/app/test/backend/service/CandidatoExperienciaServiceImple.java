package com.microservicio.app.test.backend.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.microservicio.app.test.backend.entity.Candidato;
import com.microservicio.app.test.backend.entity.CandidatoExperiencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.microservicio.app.test.backend.dto.CandidatoExperienciaCrearDto;
import com.microservicio.app.test.backend.dto.CandidatoExperienciaDto;
import com.microservicio.app.test.backend.dto.TecnologiaDto;
import com.microservicio.app.test.backend.entity.Tecnologia;
import com.microservicio.app.test.backend.repository.CandidatoExperienciaRepository;

@Service
public class CandidatoExperienciaServiceImple implements CandidatoExperienciaService {

	@Autowired
	private CandidatoExperienciaRepository repo;
	
	@Autowired
	private TecnologiaService tecnologiaServicio;
	
	
	@Override
	public List<CandidatoExperienciaDto> findByTecnologia(String nombre) {
				
		TecnologiaDto tecnologiaDto = tecnologiaServicio.findByNombre(nombre);	
		Tecnologia tecnologia = new Tecnologia(tecnologiaDto.getId(),tecnologiaDto.getNombre(),tecnologiaDto.getVersion()); 
			
		return repo.findByTecnologia(tecnologia).stream().map(t -> new CandidatoExperienciaDto(t.getId(), t.getCandidato(), t.getTecnologia(), t.getExperiencia())).collect(Collectors.toList());
					
	}

	@Override
	public CandidatoExperienciaDto addCandidatoExperiencia(CandidatoExperienciaCrearDto crearCandidatoExperiencia) {
				 
		if (repo.exists(Example.of(crearCandidatoExperiencia.toCandidato())))
		{
			throw new DuplicateKeyException("La experiencia de la tecnologia " + crearCandidatoExperiencia.getTecnologia().getNombre() +" del candidato  " 
		 + crearCandidatoExperiencia.getCandidato().getNombre() + " ya existe");
		}
		
		if (crearCandidatoExperiencia.getExperiencia() < 1) {
			throw new IllegalArgumentException("La experiencia debe ser mayor a cero");
		}
		
		return new CandidatoExperienciaDto(repo.save(crearCandidatoExperiencia.toCandidato()));
	}


	@Override
	public List<CandidatoExperienciaDto> findAll() {
		
		return repo.findAll().stream().map(c -> new CandidatoExperienciaDto(c.getId(), c.getCandidato(), c.getTecnologia(), c.getExperiencia())).collect(Collectors.toList());
		
	}

	@Override
	public CandidatoExperienciaDto updateCandidatoExperiencia(Long id, CandidatoExperienciaCrearDto crearExperiencia) {
		
		if(repo.findById(id).isEmpty())
		{
			throw new NoSuchElementException("No existe candidato experiencia con el id: " + id);
		}
		
		if (crearExperiencia.getExperiencia() < 1) {
			throw new IllegalArgumentException("La experiencia debe ser mayor a cero");
		}
		
		crearExperiencia.setId(id);
		
		return new CandidatoExperienciaDto(repo.save(crearExperiencia.toCandidato()));
	}

	@Override
	public void deleteCandidatoExperiencia(Long id) {
		
		if(repo.findById(id).isEmpty())
		{
			throw new NoSuchElementException("No existe candidato experiencia con el id: " + id);
		}
		
		repo.deleteById(id);
		
	}

	@Override
	public void deleteCandidato(Candidato candidato) {

		List<CandidatoExperiencia> listaCandidato = repo.findByCandidato(candidato);
		repo.deleteAll(listaCandidato);
	}


}
