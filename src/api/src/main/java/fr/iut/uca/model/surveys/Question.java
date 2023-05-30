package fr.iut.uca.model.surveys;

import java.util.ArrayList;
import java.util.List;

public class Question extends QuestionInfo {

    private String description;

    private final List<String> choices = new ArrayList<>();

    public Question(String title, String description, QuestionType type) {
        this(title, type, description, new ArrayList<>());
    }

    public Question(String title, QuestionType type, String description, List<String> choices) {
        super(title, type);
        this.description = description;
        this.choices.addAll(choices);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getChoices() {
        return choices;
    }
}
