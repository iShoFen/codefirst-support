package fr.iut.uca.entity.issues;

import java.util.ArrayList;
import java.util.List;

public class IssueModelEntity {

    private String id;

    private String name;

    private String shortDescription;

    private String description;

    private CategoryEntity category;

    private final List<IssueModelFieldEntity> fields = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<IssueModelFieldEntity> getFields() {
        return fields;
    }

    public void setFields(List<IssueModelFieldEntity> fields) {
        this.fields.clear();
        this.fields.addAll(fields);
    }

}
