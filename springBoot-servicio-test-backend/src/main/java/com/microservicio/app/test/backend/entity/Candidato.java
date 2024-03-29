package com.microservicio.app.test.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "candidato")
public class Candidato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "el nombre no puede ser null")
	@Column(nullable = false)
	@Size(min = 4, message = "El nombre debe tener minimo 4 caracteres")
	private String nombre;
	
	@NotNull(message = "el nombre no puede ser null")
	@Column(nullable = false)
	@Size(min = 4, message = "El nombre debe tener minimo 4 caracteres")
	private String apellido;
	
	@NotNull(message = "tipo de documento no puede ser null")
	@OneToOne
	@JoinColumn(name = "tipo")
	private TipoDocumento tipo;
	
	@NotNull(message = "numero de documento no puede ser null")
	@Column(name = "numero_documento", length = 8)
	@Size(min = 8, message = "El numero de documento debe tener 8 caracteres")
	private String numDocumento;
	

}
