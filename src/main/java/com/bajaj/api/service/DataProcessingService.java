package com.bajaj.api.service;

import com.bajaj.api.dto.ApiRequest;
import com.bajaj.api.dto.ApiResponse;

public interface DataProcessingService {

    /**
     * Processes the input data array and returns classified/computed results.
     *
     * @param request the API request containing the data array
     * @return the API response with all computed fields
     */
    ApiResponse processData(ApiRequest request);
}
