package com.microservicio.app.test.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicio.app.test.backend.dto.CandidatoCrearDto;
import com.microservicio.app.test.backend.dto.CandidatoDto;
import com.microservicio.app.test.backend.entity.TipoDocumento;
import com.microservicio.app.test.backend.service.CandidatoService;
import com.microservicio.app.test.backend.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CandidatoController.class)
class CandidatoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CandidatoService candidatoService;

    @MockBean
    private UserServiceImpl userService;

    ObjectMapper objectMapper;

    private CandidatoDto candidatoDto;
    private CandidatoCrearDto candidatoCrearDto;

    private static final long NUMBER_ONE = 1l;
    private static final String NOMBRE_CANDIDATO = "pepe";

    @BeforeEach
    void setUp() {

        candidatoDto = new CandidatoDto(1l, NOMBRE_CANDIDATO, "perez",new TipoDocumento(1l,"DNI"),"12345678");
        candidatoCrearDto = new CandidatoCrearDto(1l, NOMBRE_CANDIDATO, "perez",new TipoDocumento(1l,"DNI"),"12345678");

        objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser
    void listaCandidatoTest() throws Exception {

        List<CandidatoDto> candidatos = new ArrayList<>();
        candidatos.add(candidatoDto);
        when(candidatoService.findAll()).thenReturn(candidatos);

        mockMvc.perform(get("/api/listado-candidato").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    @WithMockUser
    void buscarCandidatoTest() throws Exception {

        when(candidatoService.findById(NUMBER_ONE)).thenReturn(candidatoDto);

        mockMvc.perform(get("/api//buscar-candidato/{id}", NUMBER_ONE).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value(NOMBRE_CANDIDATO));
    }

    @Test
    @WithMockUser
    void agregarCandidatoTest() throws Exception {

        when(candidatoService.addCandidatoDto(candidatoCrearDto)).thenReturn(candidatoDto);

        mockMvc.perform(post("/api/crear-candidato")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(candidatoCrearDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value(NOMBRE_CANDIDATO));

    }

    @Test
    @WithMockUser
    void actualizarCandidatoTest() throws Exception {

        when(candidatoService.updateCandidatoDto(NUMBER_ONE ,candidatoCrearDto)).thenReturn(candidatoDto);

        mockMvc.perform(put("/api/actualizar-candidato/{id}", NUMBER_ONE)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(candidatoCrearDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value(NOMBRE_CANDIDATO));
    }

    @Test
    @WithMockUser
    void eliminarCandidatoTest() throws Exception {

        mockMvc.perform(delete("/api/eliminar-candidato/{id}", NUMBER_ONE)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(candidatoService, times(1)).deleteCandidato(NUMBER_ONE);



    }
}