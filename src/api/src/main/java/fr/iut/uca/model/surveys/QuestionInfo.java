package fr.iut.uca.model.surveys;

public class QuestionInfo {

    protected String title;

    protected QuestionType type;

    public QuestionInfo(String title, QuestionType type) {
        this.title = title;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public QuestionType getType() {
        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionInfo that = (QuestionInfo) o;

        if (!title.equals(that.title)) return false;
        return type == that.type;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + type.hashCode();
        return result;
    }
}
