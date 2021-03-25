package com.microservicio.app.test.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicio.app.test.backend.entity.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {

}
