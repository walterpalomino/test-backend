package com.microservicio.app.test.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicio.app.test.backend.dto.TecnologiaCrearDto;
import com.microservicio.app.test.backend.dto.TecnologiaDto;
import com.microservicio.app.test.backend.service.TecnologiaService;
import com.microservicio.app.test.backend.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TecnologiaController.class)
class TecnologiaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TecnologiaService tecnologiaService;

    @MockBean
    private UserServiceImpl userService;

    ObjectMapper objectMapper;

    private TecnologiaDto tecnologiaDto;
    private TecnologiaCrearDto tecnologiaCrearDto;

    private static final long NUMBER_ONE = 1l;
    private static final String NOMBRE_TECNOLOGIA_JAVA = "java";

    @BeforeEach
    void setUp() {

        tecnologiaDto = new TecnologiaDto(NUMBER_ONE, NOMBRE_TECNOLOGIA_JAVA,9);
        tecnologiaCrearDto = new TecnologiaCrearDto(null, NOMBRE_TECNOLOGIA_JAVA,9);

        objectMapper = new ObjectMapper();
    }

    @Test
    @WithMockUser
    void listaTecnologiaTest() throws Exception {

        List<TecnologiaDto> tecnologias = new ArrayList<>();
        when(tecnologiaService.findAll()).thenReturn(tecnologias);

        mockMvc.perform(get("/api/listado").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @WithMockUser
    void budcarTecnologia() throws Exception {

        when(tecnologiaService.findByNombre(NOMBRE_TECNOLOGIA_JAVA)).thenReturn(tecnologiaDto);

        mockMvc.perform(get("/api/buscar/{nombre}",NOMBRE_TECNOLOGIA_JAVA).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value(NOMBRE_TECNOLOGIA_JAVA));
    }

    @Test
    @WithMockUser
    void agregarTecnologiaTest() throws Exception {

        when(tecnologiaService.addTecnologia(tecnologiaCrearDto)).thenReturn(tecnologiaDto);

        mockMvc.perform(post("/api/crear-tecnologia")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tecnologiaCrearDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value(NOMBRE_TECNOLOGIA_JAVA));

    }

    @Test
    @WithMockUser
    void actualizarTecnologiaTest() throws Exception {

        when(tecnologiaService.updateTecnologia(NUMBER_ONE, tecnologiaCrearDto)).thenReturn(tecnologiaDto);

        mockMvc.perform(put("/api/actualizar-tecnologia/{id}", NUMBER_ONE)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tecnologiaCrearDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value(NOMBRE_TECNOLOGIA_JAVA));

    }

    @Test
    @WithMockUser
    void eliminarTecnologiaTest() throws Exception {

        mockMvc.perform(delete("/api/eliminar-tecnologia/{id}", NUMBER_ONE)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(tecnologiaService, times(1)).deleteTecnologia(NUMBER_ONE);

    }
}