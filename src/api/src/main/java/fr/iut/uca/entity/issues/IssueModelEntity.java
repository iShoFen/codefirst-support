package fr.iut.uca.entity.issues;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IssueModelEntity extends IssueModelInfoEntity {

    private ObjectId id;

    private CategoryEntity category;

    private final List<IssueModelFieldEntity> fields = new ArrayList<>();

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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
