package com.example.springairag.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record Author(@JsonPropertyDescription("Name of the author") String name,
                     @JsonPropertyDescription("About the author") String description,
                     @JsonPropertyDescription("Total number of books published by the author") int totalNoOfBooks) {
}
