package fr.iut.uca.model.issues;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Issue {

    private final ObjectId id;

    private String title;

    private String author;

    private final Date createdAt;

    private IssueStatus status;

    private Category category;

    private IssueModelInfo model;

    private final List<Comment> comments = new ArrayList<>();

    private final List<IssueField> fields = new ArrayList<>();

    public Issue(ObjectId id, String title, String author, Date createdAt, IssueStatus status, Category category, IssueModelInfo model) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.createdAt = createdAt;
        this.status = status;
        this.category = category;
        this.model = model;
    }

    public ObjectId getId() {
        return id;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public IssueModelInfo getModel() {
        return model;
    }

    public void setModel(IssueModelInfo model) {
        this.model = model;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<IssueField> getFields() {
        return fields;
    }
}
