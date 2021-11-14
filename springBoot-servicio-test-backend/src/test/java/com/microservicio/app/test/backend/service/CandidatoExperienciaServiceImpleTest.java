package com.microservicio.app.test.backend.service;

import com.microservicio.app.test.backend.dto.CandidatoExperienciaCrearDto;
import com.microservicio.app.test.backend.dto.CandidatoExperienciaDto;
import com.microservicio.app.test.backend.dto.TecnologiaDto;
import com.microservicio.app.test.backend.entity.Candidato;
import com.microservicio.app.test.backend.entity.CandidatoExperiencia;
import com.microservicio.app.test.backend.entity.Tecnologia;
import com.microservicio.app.test.backend.entity.TipoDocumento;
import com.microservicio.app.test.backend.repository.CandidatoExperienciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CandidatoExperienciaServiceImpleTest {

    @Mock
    private CandidatoExperienciaRepository candidatoExperienciaRepository;

    @InjectMocks
    private CandidatoExperienciaServiceImple candidatoExperienciaService;

    private CandidatoExperienciaDto candidatoExperienciaDto;
    private CandidatoExperiencia candidatoExperiencia;
    private Candidato candidato;
    private Tecnologia tecnologia;
    private CandidatoExperienciaCrearDto candidatoExperienciaCrearDto;

    @BeforeEach
    void setUp() {
        candidato = new Candidato(1l, "pepe", "perez",new TipoDocumento(1l,"DNI"),"12345678");
        tecnologia = new Tecnologia(1l, "java", 8);
        candidatoExperienciaDto = new CandidatoExperienciaDto(1l, candidato, tecnologia, 5);
        candidatoExperiencia = new CandidatoExperiencia(1l, candidato, tecnologia, 5);
        candidatoExperienciaCrearDto = new CandidatoExperienciaCrearDto(1l, candidato, tecnologia, 5);
    }

    @Test
    void addCandidatoExperienciaTest() {

        when(candidatoExperienciaRepository.save(candidatoExperiencia)).thenReturn(candidatoExperiencia);
        candidatoExperienciaDto = candidatoExperienciaService.addCandidatoExperiencia(candidatoExperienciaCrearDto);

        assertEquals(5, candidatoExperienciaDto.getExperiencia());
    }

    @Test
    void findAllTest() {
    }

    @Test
    void updateCandidatoExperienciaTest() {
    }

    @Test
    void deleteCandidatoExperienciaTest() {
    }

    @Test
    void deleteCandidatoTest() {
    }
}