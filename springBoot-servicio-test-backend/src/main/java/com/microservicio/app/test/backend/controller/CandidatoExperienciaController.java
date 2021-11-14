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

import com.microservicio.app.test.backend.dto.CandidatoExperienciaCrearDto;
import com.microservicio.app.test.backend.dto.CandidatoExperienciaDto;
import com.microservicio.app.test.backend.exception.InvalidDataException;
import com.microservicio.app.test.backend.service.CandidatoExperienciaService;

@RestController
@RequestMapping("/api/")
public class CandidatoExperienciaController {
	
	private static final Log log = LogFactory.getLog(CandidatoExperienciaController.class);
	
	@Autowired
	private CandidatoExperienciaService servicio;
	
	@GetMapping("/listado-experiencia")
	public ResponseEntity<List<CandidatoExperienciaDto>> listadoExperiencia(){
		
		log.info("Obteniendo todas las experiencias por candidato");
		return ResponseEntity.ok(servicio.findAll());
	}
	
	@GetMapping("buscar-tecnologia-candidato/{nombre}")
	public ResponseEntity<List<CandidatoExperienciaDto>> buscarcandidato(@PathVariable String nombre)
	{
		log.info("Buscando los candidatos por Tecnologia con el nombre " + nombre);
		return ResponseEntity.ok(servicio.findByTecnologia(nombre));
		
	} 
	
	@PostMapping("/crearExperiencia")
	public ResponseEntity<CandidatoExperienciaDto> addExperienciaCandidato(@Valid @RequestBody CandidatoExperienciaCrearDto crearCandidatoExperiencia, BindingResult result)
	{
		if(result.hasErrors())
		{
			throw new InvalidDataException(result);
		}
		
		log.info("Creando experiencia de candidato " + crearCandidatoExperiencia);
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.addCandidatoExperiencia(crearCandidatoExperiencia));
	}
	
	@PutMapping("/actualizar-experiencia/{id}")
	public ResponseEntity<CandidatoExperienciaDto> actualizarExperienciaCandidato(@PathVariable Long id, @Valid @RequestBody CandidatoExperienciaCrearDto crearCandidatoExperiencia, BindingResult result)
	{
		if(result.hasErrors())
		{
			throw new InvalidDataException(result);
		}
		
		log.info("Actualizando la experiencia del candidato con id " + id);
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.updateCandidatoExperiencia(id,crearCandidatoExperiencia));
		
	}
	
	@DeleteMapping("/eleiminar-experiencia/{id}")
	public ResponseEntity<?> eleiminarExperiencia(@PathVariable Long id)
	{
		servicio.deleteCandidatoExperiencia(id);
		log.info("Eliminar experiencia del candidato con id " + id);
		
		return ResponseEntity.ok().build();
	}

	
}
