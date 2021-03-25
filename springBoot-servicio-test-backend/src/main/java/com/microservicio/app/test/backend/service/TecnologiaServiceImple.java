package com.microservicio.app.test.backend.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.microservicio.app.test.backend.entity.Tecnologia;
import com.microservicio.app.test.backend.repository.TecnologiaRepository;

@Service
public class TecnologiaServiceImple implements TecnologiaService {

	@Autowired
	private TecnologiaRepository repo;

	@Override
	public List<Tecnologia> findAll() {

		return repo.findAll();
	}

	@Override
	public Tecnologia findByNombre(String nombre) {

		Optional<Tecnologia> opt = repo.findByNombre(nombre);
		if (opt.isEmpty()) {
			throw new NoSuchElementException("No existe tecnologia con el nombre: " + nombre);
		}

		return opt.get();
	}

	@Override
	public Tecnologia addTecnologia(Tecnologia tecnologia) {

		if (tecnologia.getVersion() < 1) {
			throw new IllegalArgumentException("La version debe ser mayor a cero");
		}

		if (repo.exists(Example.of(tecnologia))) {
			throw new DuplicateKeyException(
					"Ya existe la tecnologia y version: " + tecnologia.getNombre() + " " + tecnologia.getVersion());
		}

		return repo.save(tecnologia);
	}

	@Override
	public Tecnologia updateTecnologia(Long id, Tecnologia tecnologia) {

		if (tecnologia.getVersion() < 1) {
			throw new IllegalArgumentException("La version debe ser mayor a cero");
		}

		Tecnologia t = repo.findById(id).get();

		t.setNombre(tecnologia.getNombre());
		t.setVersion(tecnologia.getVersion());

		return repo.save(t);
	}

	@Override
	public void deleteTecnologia(Long id) {
		
		if (repo.findById(id).isEmpty()) {
			throw new NoSuchElementException("No existe tecnologia con el id: " + id);
		}
	
		repo.deleteById(id);

	}

}
