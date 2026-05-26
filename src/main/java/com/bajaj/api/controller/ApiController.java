package com.bajaj.api.controller;

import com.bajaj.api.dto.ApiRequest;
import com.bajaj.api.dto.ApiResponse;
import com.bajaj.api.service.DataProcessingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final DataProcessingService dataProcessingService;

    public ApiController(DataProcessingService dataProcessingService) {
        this.dataProcessingService = dataProcessingService;
    }

    /**
     * POST /api – Accepts a JSON body with a "data" array and returns processed results.
     */
    @PostMapping
    public ResponseEntity<ApiResponse> processData(@Valid @RequestBody ApiRequest request) {
        ApiResponse response = dataProcessingService.processData(request);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api – Returns a simple operation code (standard BFHL endpoint).
     */
    @GetMapping
    public ResponseEntity<Map<String, Integer>> getOperationCode() {
        return ResponseEntity.ok(Map.of("operation_code", 1));
    }
}
