package com.microservicio.app.test.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

import com.microservicio.app.test.backend.dto.CandidatoCrearDto;
import com.microservicio.app.test.backend.dto.CandidatoDto;
import com.microservicio.app.test.backend.exception.InvalidDataException;
import com.microservicio.app.test.backend.service.CandidatoService;

@Controller
@RequestMapping("/api")
public class CandidatoController {

	private static final Log log = LogFactory.getLog(CandidatoController.class);

	@Autowired
	private CandidatoService servicio;

	@GetMapping("/listado-candidato")
	private ResponseEntity<List<CandidatoDto>> listaCandidato() {

		log.info("Obteniendo todos los candidatos");
		return ResponseEntity.ok(servicio.findAll());

	}

	@GetMapping("/buscar-candidato/{id}")
	private ResponseEntity<CandidatoDto> budcarTecnologia(@PathVariable Long id) {

		log.info("Buscando candidato con id " + id);
		return ResponseEntity.ok(servicio.findById(id));
	}

	@PostMapping("/crear-candidato")
	private ResponseEntity<CandidatoDto> agregarTecnologia(@Valid @RequestBody CandidatoCrearDto c,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException(result);
		}
		log.info("Creando Candidato " + c);
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.addCandidatoDto(c));

	}

	@PutMapping("/actualizar-candidato/{id}")
	private ResponseEntity<CandidatoDto> actualizarCandidato(@Valid @PathVariable Long id,
			@RequestBody CandidatoCrearDto c, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException(result);
		}

		log.info("Actualizando candidato con id " + id);
		return ResponseEntity.ok(servicio.updateCandidatoDto(id, c));
	}

	@DeleteMapping("/eliminar-candidato/{id}")
	private ResponseEntity<?> eliminarTecnologia(@PathVariable Long id) {

		servicio.deleteCandidato(id);
		log.info("Eliminar candidato con id " + id);
		return ResponseEntity.ok().build();
	}

}
