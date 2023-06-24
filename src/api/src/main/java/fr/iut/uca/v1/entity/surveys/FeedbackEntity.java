package fr.iut.uca.v1.entity.surveys;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FeedbackEntity {

    private String id;

    private String surveyId;

    private LocalDate createdAt;

    private String author;

    private QuestionInfoEntity question;

    private final List<String> answers = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

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

    public QuestionInfoEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionInfoEntity question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers.clear();
        this.answers.addAll(answers);
    }
}
