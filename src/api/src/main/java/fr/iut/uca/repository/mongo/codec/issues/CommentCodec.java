package fr.iut.uca.repository.mongo.codec.issues;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.time.LocalDate;

public class CommentCodec {

    @BsonProperty(value = "createdAt")
    private LocalDate createdAt;

    private String author;

    private String content;

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
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
