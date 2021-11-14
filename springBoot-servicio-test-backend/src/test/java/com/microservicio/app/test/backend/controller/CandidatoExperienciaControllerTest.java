package com.microservicio.app.test.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicio.app.test.backend.dto.CandidatoExperienciaCrearDto;
import com.microservicio.app.test.backend.dto.CandidatoExperienciaDto;
import com.microservicio.app.test.backend.entity.Candidato;
import com.microservicio.app.test.backend.entity.CandidatoExperiencia;
import com.microservicio.app.test.backend.entity.Tecnologia;
import com.microservicio.app.test.backend.entity.TipoDocumento;
import com.microservicio.app.test.backend.service.CandidatoExperienciaService;
import com.microservicio.app.test.backend.service.CandidatoExperienciaServiceImple;
import com.microservicio.app.test.backend.service.CandidatoService;
import com.microservicio.app.test.backend.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(CandidatoExperienciaController.class)
class CandidatoExperienciaControllerTest {

    @MockBean
    private CandidatoExperienciaService candidatoExperienciaService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;

    private CandidatoExperienciaDto candidatoExperienciaDto;
    private CandidatoExperiencia candidatoExperiencia;
    private Candidato candidato;
    private Tecnologia tecnologia;
    private CandidatoExperienciaCrearDto candidatoExperienciaCrearDto;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

        candidato = new Candidato(1l, "pepe", "perez",new TipoDocumento(1l,"DNI"),"12345678");
        tecnologia = new Tecnologia(1l, "java", 8);
        candidatoExperienciaDto = new CandidatoExperienciaDto(1l, candidato, tecnologia, 5);
        candidatoExperiencia = new CandidatoExperiencia(1l, candidato, tecnologia, 5);
        candidatoExperienciaCrearDto = new CandidatoExperienciaCrearDto(1l, candidato, tecnologia, 5);

        objectMapper = new ObjectMapper();
    }

    @Test
    void listadoExperienciaTest() throws Exception {

        List<CandidatoExperienciaDto> experiencias = new ArrayList<>();
        experiencias.add(candidatoExperienciaDto);

        when(candidatoExperienciaService.findAll()).thenReturn(experiencias);

        mockMvc.perform(get("/api/listado-experiencia").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void buscarcandidatoTest() throws Exception {

        List<CandidatoExperienciaDto> experiencias = new ArrayList<>();
        experiencias.add(candidatoExperienciaDto);

        when(candidatoExperienciaService.findByTecnologia("java")).thenReturn(experiencias);

        mockMvc.perform(get("/api/buscar-tecnologia-candidato/{nombre}", "java").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void addExperienciaCandidatoTest() throws Exception {

        when(candidatoExperienciaService.addCandidatoExperiencia(candidatoExperienciaCrearDto)).thenReturn(candidatoExperienciaDto);

        mockMvc.perform(post("/api/crearExperiencia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .content(objectMapper.writeValueAsString(candidatoExperienciaCrearDto)))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.experiencia").value(5));
    }

    @Test
    void actualizarExperienciaCandidatoTest() {
    }

    @Test
    void eleiminarExperienciaTest() {
    }
}