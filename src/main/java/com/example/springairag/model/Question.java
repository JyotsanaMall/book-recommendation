package com.example.springairag.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record Question(String question,
                       @JsonPropertyDescription("Total number of recommendations required") int noOfRecommendations) {
}
