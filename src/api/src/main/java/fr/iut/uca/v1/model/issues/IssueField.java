package fr.iut.uca.v1.model.issues;

public class IssueField extends IssueModelField {

    private String value;

    public IssueField(String title, String description, boolean required, String value) {
        super(title, description, required);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        IssueField that = (IssueField) o;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}
