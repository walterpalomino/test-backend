package com.microservicio.app.test.backend.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.microservicio.app.test.backend.entity.Candidato;
import com.microservicio.app.test.backend.entity.CandidatoExperiencia;
import com.microservicio.app.test.backend.repository.CandidatoExperienciaRepository;
import com.microservicio.app.test.backend.repository.CandidatoRepository;

@Service
public class CandidatoServiceImpl implements CandidatoService {

	@Autowired
	private CandidatoRepository repo;
	
	@Autowired
	private CandidatoExperienciaRepository repoExperiencia;
	
	@Override
	public List<Candidato> findAll() {
		
		return repo.findAll(); 
	}

	@Override
	public Candidato findById(Long id) {
		
		Optional<Candidato> candidato = repo.findById(id);
		if (candidato.isEmpty()) {
			throw new NoSuchElementException("No existe candidato con el id: " + id);
		}

		return candidato.get();
				
	}

	@Override
	public Candidato addCandidato(Candidato candidato) {
		
		
		if (repo.exists(Example.of(candidato))) {
			throw new DuplicateKeyException(
					"Ya existe el candidato con numero documento " + candidato.getNombre() + " " + candidato.getNumDocumento());
		}
		
		Candidato c = repo.save(new Candidato(candidato.getNombre(), candidato.getApellido(), 
				candidato.getTipo(), candidato.getNumDocumento()));
		
		candidato.getExperiencia().stream()
		.map(p -> repoExperiencia.save(new CandidatoExperiencia(c,p.getTecnologia(),p.getExperiencia())
				                      )).collect(Collectors.toList());
				
		return c;
	}

	@Override
	public Candidato updateCandidato(Long id,Candidato candidato) {
		
		Candidato c = new Candidato();
		c.setNombre(candidato.getNombre());
		c.setApellido(candidato.getApellido());
		c.setTipo(candidato.getTipo());
		c.setNumDocumento(candidato.getNumDocumento());
		
		return repo.save(c);
	}

	@Override
	public void deleteCandidato(Long id) {
		
		if (repo.findById(id).isEmpty()) {
			throw new NoSuchElementException("No existe candidato con el id: " + id);
		}
		repo.deleteById(id);

	}

}
