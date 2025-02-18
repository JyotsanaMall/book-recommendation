package com.example.springairag.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "book.aiapp")
public class VectorStoreProperties {

    private String vectorStorePath;

    private List<Resource> documentsToLoadForBooks;

    private List<Resource> documentsToLoadForAuthors;

    public String getVectorStorePath() {
        return vectorStorePath;
    }

    public void setVectorStorePath(String vectorStorePath) {
        this.vectorStorePath = vectorStorePath;
    }

    public List<Resource> getDocumentsToLoadForBooks() {
        return documentsToLoadForBooks;
    }

    public void setDocumentsToLoadForBooks(List<Resource> documentsToLoad) {
        this.documentsToLoadForBooks = documentsToLoad;
    }

    public List<Resource> getDocumentsToLoadForAuthors() {
        return documentsToLoadForAuthors;
    }

    public void setDocumentsToLoadForAuthors(List<Resource> documentsToLoadForAuthors) {
        this.documentsToLoadForAuthors = documentsToLoadForAuthors;
    }
}
