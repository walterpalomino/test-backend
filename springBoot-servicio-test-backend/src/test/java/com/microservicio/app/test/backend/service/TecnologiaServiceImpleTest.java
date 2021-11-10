package com.microservicio.app.test.backend.service;

import com.microservicio.app.test.backend.dto.TecnologiaDto;
import com.microservicio.app.test.backend.entity.Tecnologia;
import com.microservicio.app.test.backend.repository.TecnologiaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TecnologiaServiceImpleTest {

    @Mock
    private TecnologiaRepository tecnologiaRepository;

    @InjectMocks
    private TecnologiaServiceImple tecnologiaService;

    Tecnologia tecnologia;

    @BeforeEach
    void setUp (){
        tecnologia = new Tecnologia(1l, "java", 8);
    }

    @Test
    void findAllTest() {
        List<Tecnologia> tecnologias = new ArrayList<>();
        tecnologias.add(tecnologia);

        when(tecnologiaRepository.findAll()).thenReturn(tecnologias);
        List<TecnologiaDto> listaTecnologia = tecnologiaService.findAll();

        assertEquals(1, listaTecnologia.size());
    }

    @Test
    void findByNombreTest() {
    }

    @Test
    void addTecnologiaTest() {
    }

    @Test
    void updateTecnologiaTest() {
    }

    @Test
    void deleteTecnologiaTest() {
    }
}