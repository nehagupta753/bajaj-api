package com.bajaj.api.controller;

import com.bajaj.api.dto.ApiRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    // ── POST /bfhl ──

    @Test
    @DisplayName("POST /bfhl with valid data returns 200 and correct JSON structure")
    void postBfhl_ValidData_Returns200() throws Exception {
        ApiRequest request = new ApiRequest(Arrays.asList("A", "1", "334", "4", "R"));

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.user_id").value("neha_gupta_22032006"))
                .andExpect(jsonPath("$.email").value("nehagupta230802@acropolis.in"))
                .andExpect(jsonPath("$.roll_number").value("0827IT231091"))
                .andExpect(jsonPath("$.alphabets").isArray())
                .andExpect(jsonPath("$.even_numbers").isArray())
                .andExpect(jsonPath("$.odd_numbers").isArray())
                .andExpect(jsonPath("$.special_characters").isArray())
                .andExpect(jsonPath("$.sum").value("339"))
                .andExpect(jsonPath("$.concat_alphabets").value("Ra"));
    }

    // ── POST /api ──

    @Test
    @DisplayName("POST /api with valid data returns 200 and correct JSON structure")
    void postApi_ValidData_Returns200() throws Exception {
        ApiRequest request = new ApiRequest(Arrays.asList("A", "1", "334", "4", "R"));

        mockMvc.perform(post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.sum").value("339"));
    }

    @Test
    @DisplayName("POST /bfhl with empty data array returns 200 with empty lists")
    void postBfhl_EmptyArray_Returns200() throws Exception {
        ApiRequest request = new ApiRequest(Collections.emptyList());

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(true))
                .andExpect(jsonPath("$.even_numbers").isEmpty())
                .andExpect(jsonPath("$.sum").value("0"));
    }

    @Test
    @DisplayName("POST /bfhl with missing data field returns 400")
    void postBfhl_MissingData_Returns400() throws Exception {
        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(false));
    }

    @Test
    @DisplayName("POST /bfhl with malformed JSON returns 400")
    void postBfhl_MalformedJson_Returns400() throws Exception {
        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{invalid}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(false));
    }

    // ── GET /bfhl and GET /api ──

    @Test
    @DisplayName("GET /bfhl returns operation_code 1")
    void getBfhl_ReturnsOperationCode() throws Exception {
        mockMvc.perform(get("/bfhl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation_code").value(1));
    }

    @Test
    @DisplayName("GET /api returns operation_code 1")
    void getApi_ReturnsOperationCode() throws Exception {
        mockMvc.perform(get("/api"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation_code").value(1));
    }

    // ── GET /health ──

    @Test
    @DisplayName("GET /health returns UP status")
    void getHealth_ReturnsUp() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }
}
