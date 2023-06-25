package fr.iut.uca.v1.model.surveys;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Survey {

    private final String id;

    private String title;

    private final LocalDate createdAt;

    private LocalDate publishedAt;

    private LocalDate endAt;

    private String description;

    private final List<Question> questions = new ArrayList<>();

    private Feedback feedback;

    public Survey(String id, String title, LocalDate createdAt, LocalDate publishedAt, LocalDate endAt, String description, List<Question> questions, Feedback feedbacks) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("The id cannot be null or blank.");
        }

        this.id = id;

        if (createdAt == null) {
            throw new IllegalArgumentException("The creation date cannot be null.");
        }

        this.createdAt = createdAt;
        init(title, publishedAt, endAt, description, questions);
    }

    public Survey(String title, LocalDate createdAt, LocalDate publishedAt, LocalDate endAt, String description, List<Question> questions) throws IllegalArgumentException {
        this.id = null;

        if (createdAt == null) {
            throw new IllegalArgumentException("The creation date cannot be null.");
        }

        this.createdAt = createdAt;
        init(title, publishedAt, endAt, description, questions);
    }

    private void init(String title, LocalDate publishedAt, LocalDate endAt, String description, List<Question> questions) throws IllegalArgumentException {
        setTitle(title);
        setPublishedAt(publishedAt);
        setEndAt(endAt);
        setDescription(description);
        questions.forEach(this::addQuestion);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws IllegalArgumentException {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("The title cannot be null or blank.");
        }

        this.title = title;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(LocalDate publishedAt) throws IllegalArgumentException {
        if (publishedAt != null && publishedAt.isBefore(createdAt)) {
            throw new IllegalArgumentException("The published date cannot be before the creation date.");
        }
        this.publishedAt = publishedAt;
    }

    public LocalDate getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDate endAt) throws IllegalArgumentException {
        if (endAt != null && endAt.isBefore(publishedAt)) {
            throw new IllegalArgumentException("The end date cannot be before the published date.");
        }
        this.endAt = endAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) throws IllegalArgumentException {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("The description cannot be null or blank.");
        }
        this.description = description;
    }

    public List<Question> getQuestions() {
        return Collections.unmodifiableList(questions);
    }

    public void addQuestion(Question question) throws IllegalArgumentException {
        if (question == null) {
            throw new IllegalArgumentException("The question cannot be null.");
        }

        if (questions.contains(question)) {
            throw new IllegalArgumentException("The question is already in the survey.");
        }

        questions.add(question);
    }

    public boolean removeQuestion(Question question) {
        return questions.remove(question);
    }

    public void updateQuestion(Question question) throws IllegalArgumentException {
        if (question == null) {
            throw new IllegalArgumentException("The question cannot be null.");
        }

        if (!questions.contains(question)) {
            questions.add(question);
        }

        questions.set(questions.indexOf(question), question);
    }

    public Optional<Feedback> getFeedback() {
        return Optional.ofNullable(feedback);
    }

    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
    }
}
