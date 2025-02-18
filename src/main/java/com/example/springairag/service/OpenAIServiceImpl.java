package com.example.springairag.service;

import com.example.springairag.model.BookList;
import com.example.springairag.model.Question;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OpenAIServiceImpl implements OpenAIService {

    @Autowired
    private ChatModel chatModel;

    @Autowired
    private SimpleVectorStore vectorStore;

    @Value("classpath:/templates/rag-prompt-template.st")
    private Resource ragPromptTemplate;

    @Value("classpath:/templates/book-list-response-template.st")
    private Resource bookListResponseTemplate;

    @Value("classpath:/templates/system-prompt-template.st")
    private Resource systemPrompt;

    @Override
    public BookList getAllByGenre(String genre) {
        BeanOutputConverter<BookList> converter = new BeanOutputConverter<>(BookList.class);
        String format = converter.getFormat();

        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemPrompt);
        Message systemMessage = systemPromptTemplate.createMessage();

        PromptTemplate promptTemplate = new PromptTemplate(bookListResponseTemplate);
        Message userMessage = promptTemplate.createMessage(Map.of(
                "question", genre,
                "noOfRecommendations", 10,
                "format", format));
        ChatResponse chatResponse = chatModel.call(new Prompt(List.of(systemMessage, userMessage)));
        return converter.convert(chatResponse.getResult().getOutput().getContent());
    }

    @Override
    public BookList getAnswer(Question question) {
        BeanOutputConverter<BookList> converter = new BeanOutputConverter<>(BookList.class);
        String format = converter.getFormat();

        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemPrompt);
        Message systemMessage = systemPromptTemplate.createMessage();

        PromptTemplate promptTemplate = new PromptTemplate(bookListResponseTemplate);
        Message userMessage = promptTemplate.createMessage(Map.of(
                "question", question.question(),
                "noOfRecommendations", question.noOfRecommendations(),
                "format", format));
        ChatResponse chatResponse = chatModel.call(new Prompt(List.of(systemMessage, userMessage)));
        return converter.convert(chatResponse.getResult().getOutput().getContent());
    }

    @Override
    public BookList getBookWithInfoResponse(Question question) {
        BeanOutputConverter<BookList> converter = new BeanOutputConverter<>(BookList.class);
        String format = converter.getFormat();

        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemPrompt);
        Message systemMessage = systemPromptTemplate.createMessage();

        List<Document> documents = vectorStore.similaritySearch(
                SearchRequest.query(question.question()).withTopK(question.noOfRecommendations() * 2));
        List<String> bookList = documents.stream().map(Document::getContent).toList();

        bookList.forEach(System.out::println);

        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        Message userMessage = promptTemplate.createMessage(
                Map.of("question", question.question(),
//                        "noOfRecommendations", question.noOfRecommendations(),
                        "documents", String.join("\n", bookList),
                        "format", format));
        ChatResponse chatResponse = chatModel.call(new Prompt(List.of(systemMessage, userMessage)));

        return converter.convert(chatResponse.getResult().getOutput().getContent());
    }

}
