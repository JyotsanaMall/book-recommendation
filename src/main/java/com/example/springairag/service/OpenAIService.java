package com.example.springairag.service;

import com.example.springairag.model.BookList;
import com.example.springairag.model.Question;

public interface OpenAIService {

    BookList getAllByGenre(String genre);

    BookList getAnswer(Question question);

    BookList getBookWithInfoResponse(Question question);
}
