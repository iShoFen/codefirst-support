package fr.iut.uca.v1.entity.issues;

import java.util.ArrayList;
import java.util.List;

public class IssueModelEntity extends IssueModelInfoEntity {

    private String id;

    private CategoryEntity category;

    private final List<IssueModelFieldEntity> fields = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
