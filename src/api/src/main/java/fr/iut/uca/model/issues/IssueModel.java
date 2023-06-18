package fr.iut.uca.model.issues;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class IssueModel {

    private final String id;

    private String name;

    private String shortDescription;

    private String description;

    private Category category;

    private final List<IssueModelField> fields = new ArrayList<>();

    public IssueModel(String id, String name, String shortDescription, String description, Category category, List<IssueModelField> fields) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
        this.category = category;
        this.fields.addAll(fields);
    }

    public IssueModel(String name, String shortDescription, String description, Category category, List<IssueModelField> fields) {
        this(null, name, shortDescription, description, category, fields);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<IssueModelField> getFields() {
        return Collections.unmodifiableList(fields);
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
