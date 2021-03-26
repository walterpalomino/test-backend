package com.microservicio.app.test.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microservicio.app.test.backend.dto.CandidatoDto;
import com.microservicio.app.test.backend.entity.Candidato;
import com.microservicio.app.test.backend.exception.InvalidDataException;
import com.microservicio.app.test.backend.service.CandidatoService;

@Controller
@RequestMapping("/api")
public class CandidatoController {

	@Autowired
	private CandidatoService servicio;

	@GetMapping("/listado-candidato")
	private ResponseEntity<List<CandidatoDto>> listaCandidato() {

		return ResponseEntity.ok(servicio.findAll());

	}

	@GetMapping("/buscar-candidato/{id}")
	private ResponseEntity<Candidato> budcarTecnologia(@PathVariable Long id) {
		return ResponseEntity.ok(servicio.findById(id));
	}

	@PostMapping("/crear-candidato")
	private ResponseEntity<Candidato> agregarTecnologia(@Valid @RequestBody Candidato c, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException(result);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.addCandidato(c));

	}

	@PutMapping("/actualizar-candidato/{id}")
	private ResponseEntity<Candidato> actualizarTecnologia(@Valid @PathVariable Long id, @RequestBody Candidato c,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException(result);
		}
		return ResponseEntity.ok(servicio.updateCandidato(id, c));
	}

	@DeleteMapping("/eliminar-candidato/{id}")
	private ResponseEntity<?> eliminarTecnologia(@PathVariable Long id) {
		servicio.deleteCandidato(id);
		return ResponseEntity.ok().build();
	}

}
