package com.microservicio.app.test.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicio.app.test.backend.entity.CandidatoExperiencia;
import com.microservicio.app.test.backend.entity.Tecnologia;

@Repository
public interface CandidatoExperienciaRepository extends JpaRepository<CandidatoExperiencia, Long> {

	public List<CandidatoExperiencia> findByTecnologia(Tecnologia t);
	public List<CandidatoExperiencia> findByCandidato(long id);
}
