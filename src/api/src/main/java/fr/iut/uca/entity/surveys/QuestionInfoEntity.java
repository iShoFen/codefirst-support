package fr.iut.uca.entity.surveys;

public class QuestionInfoEntity {
    private String title;

    private QuestionTypeEntity type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public QuestionTypeEntity getType() {
        return type;
    }

    public void setType(QuestionTypeEntity type) {
        this.type = type;
    }
}
