package com.bajaj.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

    @JsonProperty("is_success")
    private boolean success;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("roll_number")
    private String rollNumber;

    @JsonProperty("numbers")
    private List<String> numbers;

    @JsonProperty("alphabets")
    private List<String> alphabets;

    @JsonProperty("highest_lowercase_alphabet")
    private List<String> highestLowercaseAlphabet;

    @JsonProperty("even_numbers")
    private List<String> evenNumbers;

    @JsonProperty("odd_numbers")
    private List<String> oddNumbers;

    @JsonProperty("special_characters")
    private List<String> specialCharacters;

    @JsonProperty("sum_of_numbers")
    private long sumOfNumbers;

    @JsonProperty("alternating_case_concat")
    private String alternatingCaseConcat;
}
