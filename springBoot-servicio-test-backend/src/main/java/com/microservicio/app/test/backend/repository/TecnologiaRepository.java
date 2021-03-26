package com.microservicio.app.test.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservicio.app.test.backend.entity.Tecnologia;

@Repository
public interface TecnologiaRepository extends JpaRepository<Tecnologia, Long> {
	
	public Optional<Tecnologia> findByNombre(String name);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
