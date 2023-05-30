package fr.iut.uca.model.surveys;

import org.bson.types.ObjectId;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Survey {

    private final ObjectId id;

    private String title;

    private final Date createdAt;

    private Date publishedAt;

    private Date endAt;

    private String description;

    private final List<Question> questions = new ArrayList<>();

    public Survey(ObjectId id, String title, Date createdAt, Date publishedAt, Date endAt, String description) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.publishedAt = publishedAt;
        this.endAt = endAt;
        this.description = description;
    }

    public ObjectId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
