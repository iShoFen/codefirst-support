package fr.iut.uca.model.issues;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class Issue {

    private final String id;

    private String title;

    private String author;

    private final LocalDate createdAt;

    private IssueStatus status;

    private Category category;

    private IssueModelInfo model;

    private final List<Comment> comments = new ArrayList<>();

    private final List<IssueField> fields = new ArrayList<>();

    public Issue(String id, String title, String author,LocalDate createdAt, IssueStatus status, Category category, IssueModelInfo model, List<IssueField> fields, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.createdAt = createdAt;
        this.status = status;
        this.category = category;
        this.model = model;
        this.fields.addAll(fields);
        this.comments.addAll(comments);
    }

    public Issue(String title, String author, LocalDate createdAt, IssueStatus status, Category category, IssueModelInfo model, List<IssueField> fields) {
        this(null, title, author, createdAt, status, category, model, fields, new ArrayList<>());
    }

    public String getId() {
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

    public LocalDate getCreatedAt() {
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
        return Collections.unmodifiableList(comments);
    }

    public List<IssueField> getFields() {
        return Collections.unmodifiableList(fields);
    }
}
