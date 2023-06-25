package fr.iut.uca.v1.entity.surveys;

import java.util.ArrayList;
import java.util.List;

public class QuestionEntity extends QuestionInfoEntity {

    private String description;

    private final List<String> choices = new ArrayList<>();

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getChoices() {
        return choices;
    }

    public void setChoices(List<String> choices) {
        this.choices.clear();
        this.choices.addAll(choices);
    }
}
