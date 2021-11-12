package com.microservicio.app.test.backend.controller;

import com.microservicio.app.test.backend.service.TecnologiaService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(TecnologiaController.class)
class TecnologiaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TecnologiaService tecnologiaService;

    @BeforeEach
    void setUp() {
    }

}