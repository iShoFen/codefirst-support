package fr.iut.uca.v1.model.issues;

import com.aayushatharva.brotli4j.common.annotations.Local;

import java.time.LocalDate;

public class Comment {

    private final LocalDate createdAt;

    private String author;

    private String content;

    public Comment(LocalDate createdAt, String author, String content) {
        this.createdAt = createdAt;
        this.author = author;
        this.content = content;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
