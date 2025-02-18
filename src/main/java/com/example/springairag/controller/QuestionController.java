package com.example.springairag.controller;

import com.example.springairag.model.BookList;
import com.example.springairag.model.Question;
import com.example.springairag.service.OpenAIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class QuestionController {

    @Autowired
    private OpenAIService openAIService;

    @GetMapping("/{genre}")
    public BookList getAllByGenre(@PathVariable("genre") String genre) {
        return openAIService.getAllByGenre(genre);
    }

    @PostMapping("/ask")
    public BookList askQuestion(@RequestBody Question question) {
        return openAIService.getAnswer(question);
    }

    @PostMapping("/bookWithInfo")
    public BookList getBookWithInfoResponse(@RequestBody Question question) {
        return openAIService.getBookWithInfoResponse(question);
    }
}
