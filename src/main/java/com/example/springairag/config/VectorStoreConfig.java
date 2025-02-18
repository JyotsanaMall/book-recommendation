package com.example.springairag.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.JsonReader;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

@Slf4j
@Configuration
public class VectorStoreConfig {

    private static final String[] BOOKS_KEYS = {"isbn", "text_reviews_count", "series", "country_code", "language_code", "popular_shelves", "asin"
            , "is_ebook", "average_rating", "kindle_asin", "similar_books", "description", "format", "link", "authors", "publisher", "num_pages"
            , "publication_day", "isbn13", "publication_month", "edition_information", "publication_year", "url", "image_url", "book_id"
            , "ratings_count", "work_id", "title", "title_without_series"};

    private static final String[] AUTHORS_KEYS = {"average_rating", "author_id", "text_reviews_count", "name", "ratings_count"};

    @Bean
    public SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel,
                                               VectorStoreProperties vectorStoreProperties) {
        SimpleVectorStore store = new SimpleVectorStore(embeddingModel);
        File vectorStoreFile = new File(vectorStoreProperties.getVectorStorePath());

        if (vectorStoreFile.exists()) {
            store.load(vectorStoreFile);
        } else {
//            log.debug("Loading documents for books into vector store");
            vectorStoreProperties.getDocumentsToLoadForBooks().forEach(document -> {
//                log.debug("Loading document: " + document.getFilename());
                JsonReader jsonReader = new JsonReader(document, BOOKS_KEYS);
                List<Document> docs = jsonReader.get();
                store.add(docs);
            });

//            log.debug("Loading documents for authors into vector store");
//            vectorStoreProperties.getDocumentsToLoadForAuthors().forEach(document -> {
//                log.debug("Loading document: " + document.getFilename());
//                JsonReader jsonReader = new JsonReader(document, AUTHORS_KEYS);
//                List<Document> docs = jsonReader.get();
//                store.add(docs);
//            });

            store.save(vectorStoreFile);
        }

        return store;
    }

}

