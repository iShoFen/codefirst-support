package fr.iut.uca.entity.surveys;

import java.util.List;

public class QuestionEntity extends QuestionInfoEntity {

    private String description;

    private List<String> choices;

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
        this.choices = choices;
    }
}
