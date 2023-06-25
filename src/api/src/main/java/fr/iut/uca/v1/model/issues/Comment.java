package fr.iut.uca.v1.model.issues;

import com.aayushatharva.brotli4j.common.annotations.Local;

import java.time.LocalDate;
import java.util.Objects;

public class Comment {

    private final LocalDate createdAt;

    private final String author;

    private String content;

    public Comment(LocalDate createdAt, String author, String content) throws IllegalArgumentException {

        if (createdAt == null) {
            throw new IllegalArgumentException("The creation date cannot be null.");
        }
        this.createdAt = createdAt;

        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("Author cannot be null or blank");
        }
        this.author = author;
        setContent(content);
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public String getAuthor() {
        return author;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;
        return Objects.equals(getCreatedAt(), comment.getCreatedAt()) && Objects.equals(getAuthor(), comment.getAuthor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCreatedAt(), getAuthor());
    }
}
