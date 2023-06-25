package fr.iut.uca.v1.model.issues;

import com.aayushatharva.brotli4j.common.annotations.Local;

import java.time.LocalDate;

public class Comment {

    private final LocalDate createdAt;

    private String author;

    private String content;

    public Comment(LocalDate createdAt, String author, String content) throws IllegalArgumentException {

        if (createdAt == null) {
            throw new IllegalArgumentException("The creation date cannot be null.");
        }
        this.createdAt = createdAt;

        setAuthor(author);
        setContent(content);
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) throws IllegalArgumentException {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be null or blank");
        }
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) throws IllegalArgumentException {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content cannot be null or blank");
        }
        this.content = content;
    }
}
