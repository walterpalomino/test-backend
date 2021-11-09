package com.microservicio.app.test.backend.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.microservicio.app.test.backend.dto.TecnologiaCrearDto;
import com.microservicio.app.test.backend.dto.TecnologiaDto;
import com.microservicio.app.test.backend.entity.Tecnologia;
import com.microservicio.app.test.backend.repository.TecnologiaRepository;

@Service
public class TecnologiaServiceImple implements TecnologiaService {

	@Autowired
	private TecnologiaRepository repo;

	@Override
	public List<TecnologiaDto> findAll() {

		List<Tecnologia> tecnologias = repo.findAll();
		
        return tecnologias.stream().map(t -> new TecnologiaDto(t.getId(), t.getNombre(),t.getVersion())).collect(Collectors.toList());
        
	}

	@Override
	public TecnologiaDto findByNombre(String nombre) {

		Optional<TecnologiaDto> tecnologia = repo.findByNombre(nombre).map(t -> new TecnologiaDto(t.getId(), t.getNombre(), t.getVersion()));
		if (tecnologia.isEmpty()) {
			throw new NoSuchElementException("No existe tecnologia con el nombre: " + nombre);  
		}

		return tecnologia.get();

	}

	@Override
	public TecnologiaDto addTecnologia(TecnologiaCrearDto tecnologia) {

		if (tecnologia.getVersion() < 1) {
			throw new IllegalArgumentException("La version debe ser mayor a cero");
		}

		if (repo.exists(Example.of(tecnologia.toTecnologia()))) {
			throw new DuplicateKeyException(
					"Ya existe la tecnologia y version: " + tecnologia.getNombre() + " " + tecnologia.getVersion());
		}
 
		return new TecnologiaDto(repo.save(tecnologia.toTecnologia())); 
	}

	@Override
	public TecnologiaDto updateTecnologia(Long id, TecnologiaCrearDto tecnologia) {

		if (tecnologia.getVersion() < 1) {
			throw new IllegalArgumentException("La version debe ser mayor a cero");
		}

		tecnologia.setId(id); 		
		return new TecnologiaDto(repo.save(tecnologia.toTecnologia())); 

	}

	@Override
	public void deleteTecnologia(Long id) {
		
		if (repo.findById(id).isEmpty()) {
			throw new NoSuchElementException("No existe tecnologia con el id: " + id);
		}
	
		repo.deleteById(id);

	}

}
