package fr.iut.uca.model.issues;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IssueModel extends IssueModelInfo {

    private final String id;

    private Category category;

    private final List<IssueModelField> fields = new ArrayList<>();

    public IssueModel(String name, String shortDescription, String description, String id, Category category, List<IssueModelField> fields) {
        super(name, shortDescription, description);
        this.id = id;
        this.category = category;
        this.fields.addAll(fields);
    }

    public IssueModel(String name, String shortDescription, String description, String id, Category category) {
        this(name, shortDescription, description, id, category, new ArrayList<>());
    }

    public String getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<IssueModelField> getFields() {
        return fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IssueModel that = (IssueModel) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
