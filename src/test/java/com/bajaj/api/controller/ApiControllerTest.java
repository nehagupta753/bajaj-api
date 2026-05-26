package com.bajaj.api.controller;

import com.bajaj.api.dto.ApiRequest;
import com.bajaj.api.service.DataProcessingService;
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

    // ── POST /api ──

    @Test
    @DisplayName("POST /api with valid data returns 200 and correct JSON structure")
    void postApi_ValidData_Returns200() throws Exception {
        ApiRequest request = new ApiRequest(Arrays.asList("A", "1", "334", "4", "R"));

        mockMvc.perform(post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.user_id").value("neha_gupta_22032006"))
                .andExpect(jsonPath("$.email").value("nehagupta230802@acropolis.in"))
                .andExpect(jsonPath("$.roll_number").value("0827IT231091"))
                .andExpect(jsonPath("$.numbers").isArray())
                .andExpect(jsonPath("$.alphabets").isArray())
                .andExpect(jsonPath("$.even_numbers").isArray())
                .andExpect(jsonPath("$.odd_numbers").isArray())
                .andExpect(jsonPath("$.special_characters").isArray())
                .andExpect(jsonPath("$.sum_of_numbers").value(339))
                .andExpect(jsonPath("$.highest_lowercase_alphabet[0]").value("r"));
    }

    @Test
    @DisplayName("POST /api with empty data array returns 200 with empty lists")
    void postApi_EmptyArray_Returns200() throws Exception {
        ApiRequest request = new ApiRequest(Collections.emptyList());

        mockMvc.perform(post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.numbers").isEmpty())
                .andExpect(jsonPath("$.sum_of_numbers").value(0));
    }

    @Test
    @DisplayName("POST /api with missing data field returns 400")
    void postApi_MissingData_Returns400() throws Exception {
        mockMvc.perform(post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.is_success").value(false));
    }

    @Test
    @DisplayName("POST /api with malformed JSON returns 400")
    void postApi_MalformedJson_Returns400() throws Exception {
        mockMvc.perform(post("/api")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{invalid}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.is_success").value(false));
    }

    // ── GET /api ──

    @Test
    @DisplayName("GET /api returns operation_code 1")
    void getApi_ReturnsOperationCode() throws Exception {
        mockMvc.perform(get("/api"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation_code").value(1));
    }
}
