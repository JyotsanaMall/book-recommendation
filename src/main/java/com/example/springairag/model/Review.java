package com.example.springairag.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record Review(@JsonPropertyDescription("Name of the reviewer") String name,
                     @JsonPropertyDescription("The actual review") String reviewText,
                     @JsonPropertyDescription("Number of likes this review received") String likes,
                     @JsonPropertyDescription("Number of dislikes this review received") String dislikes) {
}
