package com.microservicio.app.test.backend.controller;

import com.microservicio.app.test.backend.dto.CandidatoDto;
import com.microservicio.app.test.backend.service.CandidatoService;
import com.microservicio.app.test.backend.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
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

    @BeforeEach
    void setUp() {
    }

    @Test
    @WithMockUser
    void listaCandidatoTest() throws Exception {

        List<CandidatoDto> candidatos = new ArrayList<>();
        when(candidatoService.findAll()).thenReturn(candidatos);

        mockMvc.perform(get("/api/listado-candidato").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }
}