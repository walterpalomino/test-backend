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
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TecnologiaServiceImpleTest {

    @Mock
    private TecnologiaRepository tecnologiaRepository;

    @InjectMocks
    private TecnologiaServiceImple tecnologiaService;

    private Tecnologia tecnologia;
    private TecnologiaDto tecnologiaDto;
    private static final String NOMBRE_TECNOLOGIA_PYTHON = "python";
    private static final String NOMBRE_TECNOLOGIA_JAVA = "java";
    private static final int LENGTH_LISTA = 1;

    @BeforeEach
    void setUp (){
        tecnologia = new Tecnologia(1l, "java", 8);
        tecnologiaDto = null;
    }

    @Test
    void findAllTest() {
        List<Tecnologia> tecnologias = new ArrayList<>();
        tecnologias.add(tecnologia);

        when(tecnologiaRepository.findAll()).thenReturn(tecnologias);
        List<TecnologiaDto> listaTecnologia = tecnologiaService.findAll();

        assertEquals(LENGTH_LISTA, listaTecnologia.size());
    }

    @Test
    void findByNombreTest() {

        when(tecnologiaRepository.findByNombre(NOMBRE_TECNOLOGIA_JAVA)).thenReturn(Optional.of(tecnologia));
        tecnologiaDto = tecnologiaService.findByNombre(NOMBRE_TECNOLOGIA_JAVA);

        assertEquals(NOMBRE_TECNOLOGIA_JAVA, tecnologiaDto.getNombre());

        assertThrows(NoSuchElementException.class, () ->{
            tecnologiaService.findByNombre(NOMBRE_TECNOLOGIA_PYTHON);
        });
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