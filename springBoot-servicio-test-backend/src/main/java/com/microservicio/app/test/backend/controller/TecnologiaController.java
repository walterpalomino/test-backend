package com.microservicio.app.test.backend.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.app.test.backend.dto.TecnologiaCrearDto;
import com.microservicio.app.test.backend.dto.TecnologiaDto;
import com.microservicio.app.test.backend.entity.Tecnologia;
import com.microservicio.app.test.backend.exception.InvalidDataException;
import com.microservicio.app.test.backend.service.TecnologiaService;

@RestController
@RequestMapping("/api")
public class TecnologiaController {

	private static final Log log = LogFactory.getLog(TecnologiaController.class);
	
	@Autowired
	TecnologiaService servicio;

	@GetMapping("/listado")
	private ResponseEntity<List<TecnologiaDto>> listaTecnologia() {
		
		log.info("Obteniendo todas las tecnologias");
		return ResponseEntity.ok(servicio.findAll());
	}

	@GetMapping("/buscar/{nombre}")
	private ResponseEntity<TecnologiaDto> budcarTecnologia(@PathVariable String nombre) {
		
		log.info("Buscando las tecnologias por nombre " + nombre);
		return ResponseEntity.ok(servicio.findByNombre(nombre));
	}

	@PostMapping("/crear-tecnologia")
	private ResponseEntity<TecnologiaDto> agregarTecnologia(@Valid @RequestBody TecnologiaCrearDto t,
			BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException(result);
		}
		
		log.info("Creando tecnologia " + t);
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.addTecnologia(t));

	}

	@PutMapping("/actualizar-tecnologia/{id}")
	private ResponseEntity<TecnologiaDto> actualizarTecnologia(@PathVariable @Valid Long id,
			@RequestBody TecnologiaCrearDto t, BindingResult result) {
		if (result.hasErrors()) {
			throw new InvalidDataException(result);
		}
		
		log.info("Actualizando la tecnologia con id " + id);
		return ResponseEntity.ok(servicio.updateTecnologia(id, t));
	}

	@DeleteMapping("/eliminar-tecnologia/{id}")
	private ResponseEntity<?> eliminarTecnologia(@PathVariable Long id) {
		servicio.deleteTecnologia(id);		
		log.info("Eliminar tecnologia con id " + id);
		
		return ResponseEntity.ok().build();
	}
}
