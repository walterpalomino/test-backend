package com.microservicio.app.test.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table; 
import javax.validation.constraints.NotNull;  
import javax.validation.constraints.Size;

import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "tecnologia")
public class Tecnologia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	
	@Column(nullable = false)
	@NotNull(message = "el nombre de la tecnologia no puede ser null")
	@Size(min = 2, message = "la tecnologia debe tener minimo 2 caracteres")
	private String nombre;
	
	@Column(nullable = false)
	@NotNull(message = "la version de la tecnologia no puede ser null")
	private Integer version;
	
	public Tecnologia(String nombre, Integer version)
	{
		this.nombre = nombre;
		this.version = version;
	}
	
	/*
	 * @OneToMany(mappedBy = "tecnologia", fetch = FetchType.EAGER)
	 * 
	 * @JsonManagedReference private List<CandidatoExperiencia> experiencia;
	 */
	
	
}
