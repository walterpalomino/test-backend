package com.microservicio.app.test.backend.service;

import com.microservicio.app.test.backend.dto.CandidatoDto;
import com.microservicio.app.test.backend.entity.Candidato;
import com.microservicio.app.test.backend.entity.TipoDocumento;
import com.microservicio.app.test.backend.repository.CandidatoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CandidatoServiceImplTest {

    @Mock
    private CandidatoRepository candidatoRepository;

    @InjectMocks
    private CandidatoServiceImpl candidatoService;

    Candidato candidato;

    @BeforeEach
    void setUp() {

        candidato = new Candidato(1l, "pepe", "perez",new TipoDocumento(1l,"DNI"),"123456789");
    }

    @Test
    void findAllTest() {

        List<Candidato> listaCandidato = new ArrayList<>();
        listaCandidato.add(candidato);

        Mockito.when(candidatoRepository.findAll()).thenReturn(listaCandidato);

        List<CandidatoDto> candidatos = candidatoService.findAll();

        assertEquals(1, candidatos.size());

    }

    @Test
    void findByIdTest() {
    }

    @Test
    void addCandidatoDtoTest() {
    }

    @Test
    void updateCandidatoDtoTest() {
    }

    @Test
    void deleteCandidatoTest() {
    }
}