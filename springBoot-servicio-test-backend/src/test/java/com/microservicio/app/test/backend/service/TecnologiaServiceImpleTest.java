package com.microservicio.app.test.backend.service;

import com.microservicio.app.test.backend.dto.TecnologiaCrearDto;
import com.microservicio.app.test.backend.dto.TecnologiaDto;
import com.microservicio.app.test.backend.entity.Tecnologia;
import com.microservicio.app.test.backend.repository.TecnologiaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    private static final long NUMBER_ONE = 1L;
    private TecnologiaCrearDto tecnologiaCrearDto;
    private static final long NUMBER_TWO = 2l;

    @BeforeEach
    void setUp (){
        tecnologia = new Tecnologia(1l, "java", 8);
        tecnologiaDto = null;
        tecnologiaCrearDto = new TecnologiaCrearDto(1l, "java", 8);
    }

    @Test
    void findAllTest() {
        List<Tecnologia> tecnologias = new ArrayList<>();
        tecnologias.add(tecnologia);

        when(tecnologiaRepository.findAll()).thenReturn(tecnologias);
        List<TecnologiaDto> listaTecnologia = tecnologiaService.findAll();

        assertEquals(NUMBER_ONE, listaTecnologia.size());
    }

    @Test
    void findByIdTest(){

        when(tecnologiaRepository.findById(NUMBER_ONE)).thenReturn(Optional.of(tecnologia));
        tecnologia = tecnologiaService.findById(NUMBER_ONE);

        assertEquals(NOMBRE_TECNOLOGIA_JAVA, tecnologia.getNombre());
        assertThrows( NoSuchElementException.class, ()->{
            tecnologiaService.findById(NUMBER_TWO);
        });
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

        when(tecnologiaRepository.save(tecnologia)).thenReturn(tecnologia);
        tecnologiaDto =  tecnologiaService.addTecnologia(tecnologiaCrearDto);

        assertEquals(NOMBRE_TECNOLOGIA_JAVA, tecnologiaDto.getNombre());

        tecnologiaCrearDto.setVersion(0);
        assertThrows(IllegalArgumentException.class, () ->{
            tecnologiaService.addTecnologia(tecnologiaCrearDto);
        });

    }

    @Test
    void updateTecnologiaTest() {

        when(tecnologiaRepository.findById(NUMBER_ONE)).thenReturn(Optional.of(tecnologia));
        when(tecnologiaRepository.save(tecnologia)).thenReturn(tecnologia);
        tecnologiaDto =  tecnologiaService.updateTecnologia(NUMBER_ONE, tecnologiaCrearDto);

        assertTrue(NOMBRE_TECNOLOGIA_JAVA.equals(tecnologiaDto.getNombre()));

        tecnologiaCrearDto.setVersion(0);
        assertThrows(IllegalArgumentException.class, () ->{
            tecnologiaService.updateTecnologia(NUMBER_ONE, tecnologiaCrearDto);
        });

    }

    @Test
    void deleteTecnologiaTest() {

        when(tecnologiaRepository.findById(NUMBER_ONE)).thenReturn(Optional.of(tecnologia));
        tecnologiaService.deleteTecnologia(NUMBER_ONE);

        verify(tecnologiaRepository, times(1)).deleteById(NUMBER_ONE);
    }
}