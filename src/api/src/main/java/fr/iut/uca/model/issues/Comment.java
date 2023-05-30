package fr.iut.uca.model.issues;

import java.util.Date;

public class Comment {

    private final Date createdAt;

    private String author;

    private String content;

    public Comment(Date createdAt, String author, String content) {
        this.createdAt = createdAt;
        this.author = author;
        this.content = content;
    }

    public Date getCreatedAt() {
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
