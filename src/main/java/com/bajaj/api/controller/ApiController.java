package com.bajaj.api.controller;

import com.bajaj.api.dto.ApiRequest;
import com.bajaj.api.dto.ApiResponse;
import com.bajaj.api.service.DataProcessingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ApiController {

    private final DataProcessingService dataProcessingService;

    public ApiController(DataProcessingService dataProcessingService) {
        this.dataProcessingService = dataProcessingService;
    }

    /**
     * POST /bfhl and POST /api – Accepts a JSON body with a "data" array and returns processed results.
     */
    @PostMapping({"/bfhl", "/api"})
    public ResponseEntity<ApiResponse> processData(@Valid @RequestBody ApiRequest request) {
        ApiResponse response = dataProcessingService.processData(request);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /bfhl and GET /api – Returns a simple operation code.
     */
    @GetMapping({"/bfhl", "/api"})
    public ResponseEntity<Map<String, Integer>> getOperationCode() {
        return ResponseEntity.ok(Map.of("operation_code", 1));
    }

    /**
     * GET /health – Returns health status.
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> getHealth() {
        return ResponseEntity.ok(Map.of("status", "UP"));
    }
}
