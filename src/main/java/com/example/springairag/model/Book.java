package com.example.springairag.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.Date;
import java.util.List;

public record Book(@JsonPropertyDescription ("Title of the book") String title,
                   @JsonPropertyDescription ("About the author") Author author,
                   @JsonPropertyDescription ("Book cover image") String image,
                   @JsonPropertyDescription ("Synopsis of the book") String synopsis,
                   @JsonPropertyDescription ("Rating of the book") String rating,
                   @JsonPropertyDescription ("Genres of the book") String genres,
                   @JsonPropertyDescription ("Date on which the book was published") String publicationDate,
                   @JsonPropertyDescription ("Publisher of the book") String publisher,
                   @JsonPropertyDescription ("Number of pages in the book") String noOfPages,
                   @JsonPropertyDescription ("ISBN of the book") String isbn,
                   @JsonPropertyDescription ("Reviews of the book") List<Review> reviews) {
}
