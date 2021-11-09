package com.microservicio.app.test.backend.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.microservicio.app.test.backend.dto.CandidatoCrearDto;
import com.microservicio.app.test.backend.dto.CandidatoDto;
import com.microservicio.app.test.backend.entity.Candidato;
import com.microservicio.app.test.backend.repository.CandidatoRepository;

@Service
public class CandidatoServiceImpl implements CandidatoService {

	@Autowired
	private CandidatoRepository candidatoRepository;

	@Override
	public List<CandidatoDto> findAll() {

		List<Candidato> candidatos = candidatoRepository.findAll();

		return candidatos.stream()
				.map(c -> new CandidatoDto(c.getId(), c.getNombre(), c.getApellido(), c.getTipo(), c.getNumDocumento()))
				.collect(Collectors.toList());

	}

	@Override
	public CandidatoDto findById(Long id) {

		Optional<CandidatoDto> candidato = candidatoRepository.findById(id).map(c -> new CandidatoDto(c.getId(), c.getNombre(), c.getApellido(), c.getTipo() , c.getNumDocumento()));
		
		if (candidato.isEmpty()) {
			throw new NoSuchElementException("No existe candidato con el id: " + id);
		}
		
		return candidato.get();

	}

	@Override
	public CandidatoDto addCandidatoDto(CandidatoCrearDto candidato) {
		
		if (candidatoRepository.exists(Example.of(candidato.toCandidato()))) {
			throw new DuplicateKeyException("Ya existe el candidato con numero documento " + candidato.getNombre() + " "
					+ candidato.getNumDocumento());
		}

		return new CandidatoDto( candidatoRepository.save(candidato.toCandidato()));

	}

	@Override
	public CandidatoDto updateCandidatoDto(Long id, CandidatoCrearDto candidato) {

		candidato.setId(this.findById(id).getId());
		return new CandidatoDto(candidatoRepository.save(candidato.toCandidato()));
	}

	@Override
	public void deleteCandidato(Long id) {

		this.findById(id);

		candidatoRepository.deleteById(id);

	}

}
