package com.bajaj.api.service.impl;

import com.bajaj.api.dto.ApiRequest;
import com.bajaj.api.dto.ApiResponse;
import com.bajaj.api.service.DataProcessingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DataProcessingServiceImpl implements DataProcessingService {

    private static final String USER_ID = "neha_gupta_22032006";
    private static final String EMAIL = "nehagupta230802@acropolis.in";
    private static final String ROLL_NUMBER = "0827IT231091";

    @Override
    public ApiResponse processData(ApiRequest request) {
        List<String> data = request.getData();

        List<String> numbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> oddNumbers = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        List<Character> allAlphaChars = new ArrayList<>();
        long sum = 0;

        for (String item : data) {
            if (item == null || item.isEmpty()) {
                continue;
            }

            if (isNumeric(item)) {
                // Classify as number
                numbers.add(item);
                long num = Long.parseLong(item);
                sum += num;

                if (num % 2 == 0) {
                    evenNumbers.add(item);
                } else {
                    oddNumbers.add(item);
                }
            } else if (isAlphabetic(item)) {
                // Classify as alphabet – store in uppercase
                alphabets.add(item.toUpperCase());
            } else {
                // Mixed or special characters
                specialCharacters.add(item);
            }

            // Extract ALL alphabetical characters from every element (for concat & highest)
            for (char c : item.toCharArray()) {
                if (Character.isLetter(c)) {
                    allAlphaChars.add(c);
                }
            }
        }

        // ── Highest lowercase alphabet ──
        List<String> highestLowercaseAlphabet = new ArrayList<>();
        if (!allAlphaChars.isEmpty()) {
            char highest = allAlphaChars.stream()
                    .map(Character::toLowerCase)
                    .max(Character::compareTo)
                    .orElse('a');
            highestLowercaseAlphabet.add(String.valueOf(highest));
        }

        // ── Alternating-case concatenation (reversed) ──
        List<Character> reversed = new ArrayList<>(allAlphaChars);
        Collections.reverse(reversed);

        StringBuilder concat = new StringBuilder();
        for (int i = 0; i < reversed.size(); i++) {
            char c = reversed.get(i);
            if (i % 2 == 0) {
                concat.append(Character.toUpperCase(c));
            } else {
                concat.append(Character.toLowerCase(c));
            }
        }

        return ApiResponse.builder()
                .success(true)
                .userId(USER_ID)
                .email(EMAIL)
                .rollNumber(ROLL_NUMBER)
                .numbers(numbers)
                .alphabets(alphabets)
                .highestLowercaseAlphabet(highestLowercaseAlphabet)
                .evenNumbers(evenNumbers)
                .oddNumbers(oddNumbers)
                .specialCharacters(specialCharacters)
                .sumOfNumbers(sum)
                .alternatingCaseConcat(concat.toString())
                .build();
    }

    /**
     * Returns true if the string is a valid integer (supports negative numbers).
     */
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Returns true if every character in the string is a letter.
     */
    private boolean isAlphabetic(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.chars().allMatch(Character::isLetter);
    }
}
