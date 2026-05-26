package com.bajaj.api.service;

import com.bajaj.api.dto.ApiRequest;
import com.bajaj.api.dto.ApiResponse;
import com.bajaj.api.service.impl.DataProcessingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataProcessingServiceImplTest {

    private DataProcessingServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new DataProcessingServiceImpl();
    }

    // ── Example A: Mixed numbers and alphabets ──

    @Test
    @DisplayName("Example A – numbers and alphabets are classified correctly")
    void testExampleA_Classification() {
        ApiRequest request = new ApiRequest(Arrays.asList("A", "1", "334", "4", "R"));
        ApiResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertEquals("neha_gupta_22032006", response.getUserId());
        assertEquals(Arrays.asList("1", "334", "4"), response.getNumbers());
        assertEquals(Arrays.asList("A", "R"), response.getAlphabets());
    }

    @Test
    @DisplayName("Example A – even and odd numbers split correctly")
    void testExampleA_EvenOdd() {
        ApiRequest request = new ApiRequest(Arrays.asList("A", "1", "334", "4", "R"));
        ApiResponse response = service.processData(request);

        assertEquals(Arrays.asList("334", "4"), response.getEvenNumbers());
        assertEquals(Arrays.asList("1"), response.getOddNumbers());
    }

    @Test
    @DisplayName("Example A – sum of numbers is correct")
    void testExampleA_Sum() {
        ApiRequest request = new ApiRequest(Arrays.asList("A", "1", "334", "4", "R"));
        ApiResponse response = service.processData(request);

        assertEquals(339, response.getSumOfNumbers());
    }

    @Test
    @DisplayName("Example A – highest lowercase alphabet")
    void testExampleA_HighestAlphabet() {
        ApiRequest request = new ApiRequest(Arrays.asList("A", "1", "334", "4", "R"));
        ApiResponse response = service.processData(request);

        assertEquals(Collections.singletonList("r"), response.getHighestLowercaseAlphabet());
    }

    // ── Example B: Only alphabets ──

    @Test
    @DisplayName("Only alphabets – no numbers or special chars")
    void testOnlyAlphabets() {
        ApiRequest request = new ApiRequest(Arrays.asList("A", "ABCD", "DOE"));
        ApiResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getNumbers().isEmpty());
        assertEquals(Arrays.asList("A", "ABCD", "DOE"), response.getAlphabets());
        assertEquals(0, response.getSumOfNumbers());
    }

    // ── Example C: Only numbers ──

    @Test
    @DisplayName("Only numbers – no alphabets or special chars")
    void testOnlyNumbers() {
        ApiRequest request = new ApiRequest(Arrays.asList("1", "2", "3", "4", "5"));
        ApiResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(Arrays.asList("1", "2", "3", "4", "5"), response.getNumbers());
        assertTrue(response.getAlphabets().isEmpty());
        assertEquals(15, response.getSumOfNumbers());
        assertEquals(Arrays.asList("2", "4"), response.getEvenNumbers());
        assertEquals(Arrays.asList("1", "3", "5"), response.getOddNumbers());
    }

    // ── Special characters ──

    @Test
    @DisplayName("Mixed elements with special characters")
    void testSpecialCharacters() {
        ApiRequest request = new ApiRequest(Arrays.asList("@", "1", "A", "7fg", "#"));
        ApiResponse response = service.processData(request);

        assertEquals(Arrays.asList("1"), response.getNumbers());
        assertEquals(Arrays.asList("A"), response.getAlphabets());
        assertEquals(Arrays.asList("@", "7fg", "#"), response.getSpecialCharacters());
    }

    // ── Edge case: Empty array ──

    @Test
    @DisplayName("Empty data array returns empty lists")
    void testEmptyArray() {
        ApiRequest request = new ApiRequest(Collections.emptyList());
        ApiResponse response = service.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals(0, response.getSumOfNumbers());
    }

    // ── Alternating case concatenation ──

    @Test
    @DisplayName("Alternating case concatenation – reversed alphabets with alternating case")
    void testAlternatingCaseConcat() {
        // Input alphabetical chars: a, b, c  →  reversed: c, b, a  →  alternating: C, b, A
        ApiRequest request = new ApiRequest(Arrays.asList("a", "b", "c"));
        ApiResponse response = service.processData(request);

        assertEquals("CbA", response.getAlternatingCaseConcat());
    }

    @Test
    @DisplayName("Alternating case concat extracts chars from mixed elements too")
    void testAlternatingCaseConcatMixed() {
        // Alpha chars: A, f, g  →  reversed: g, f, A  →  alternating: G, f, A
        ApiRequest request = new ApiRequest(Arrays.asList("A", "1", "7fg"));
        ApiResponse response = service.processData(request);

        assertEquals("GfA", response.getAlternatingCaseConcat());
    }

    // ── User info constants ──

    @Test
    @DisplayName("Response always contains correct user metadata")
    void testUserMetadata() {
        ApiRequest request = new ApiRequest(Arrays.asList("1"));
        ApiResponse response = service.processData(request);

        assertEquals("neha_gupta_22032006", response.getUserId());
        assertEquals("nehagupta230802@acropolis.in", response.getEmail());
        assertEquals("0827IT231091", response.getRollNumber());
    }
}
