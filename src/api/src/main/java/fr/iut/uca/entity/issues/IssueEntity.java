package fr.iut.uca.entity.issues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueEntity {

    private String id;

    private String title;

    private String author;

    private LocalDate createdAt;

    private IssueStatusEntity status;

    private IssueModelInfoEntity model;

    private CategoryEntity category;

    private final List<CommentEntity> comments = new ArrayList<>();

    private final List<IssueFieldEntity> fields = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public IssueStatusEntity getStatus() {
        return status;
    }

    public void setStatus(IssueStatusEntity status) {
        this.status = status;
    }

    public IssueModelInfoEntity getModel() {
        return model;
    }

    public void setModel(IssueModelInfoEntity model) {
        this.model = model;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(List<CommentEntity> comments) {
        this.comments.clear();
        this.comments.addAll(comments);
    }

    public List<IssueFieldEntity> getFields() {
        return fields;
    }

    public void setFields(List<IssueFieldEntity> fields) {
        this.fields.clear();
        this.fields.addAll(fields);
    }
}
