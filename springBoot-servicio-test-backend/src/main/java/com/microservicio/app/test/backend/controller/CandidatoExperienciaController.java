package com.microservicio.app.test.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservicio.app.test.backend.entity.CandidatoExperiencia;
import com.microservicio.app.test.backend.service.CandidatoExperienciaService;

@RestController
@RequestMapping("api")
public class CandidatoExperienciaController {
	
	@Autowired
	private CandidatoExperienciaService servicio;
	
	@GetMapping("buscar-tecnologia-candidato/{nombre}")
	public ResponseEntity<List<CandidatoExperiencia>> buscarcandidato(@PathVariable String nombre)
	{
		return ResponseEntity.ok(servicio.findByTecnologia(nombre));
		
	} 

}
