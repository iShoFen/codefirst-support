package fr.iut.uca.model.surveys;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Survey {

    private final String id;

    private String title;

    private final LocalDate createdAt;

    private LocalDate publishedAt;

    private LocalDate endAt;

    private String description;

    private final List<Question> questions = new ArrayList<>();

    public Survey(String id, String title,LocalDate createdAt,LocalDate publishedAt,LocalDate endAt, String description, List<Question> questions) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.publishedAt = publishedAt;
        this.endAt = endAt;
        this.description = description;
        this.questions.addAll(questions);
    }

    public Survey(String id, String title,LocalDate createdAt,LocalDate publishedAt,LocalDate endAt, String description) {
        this(id, title, createdAt, publishedAt, endAt, description, new ArrayList<>());
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDate publishedAt) {
        this.publishedAt = publishedAt;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDate endAt) {
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
