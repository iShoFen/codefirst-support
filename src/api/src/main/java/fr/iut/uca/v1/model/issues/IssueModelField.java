package fr.iut.uca.v1.model.issues;

public class IssueModelField {

    protected String title;

    protected String description;

    protected boolean required;

    public IssueModelField(String title, String description, boolean required) throws IllegalArgumentException {
        setTitle(title);
        this.description = description;
        this.required = required;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("The title cannot be null or blank");
        }
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IssueModelField that = (IssueModelField) o;

        return title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }
}
