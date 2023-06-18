package fr.iut.uca.model.issues;

import java.time.LocalDate;
import java.util.ArrayList;
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

    public Issue(String id, String title, String author, LocalDate createdAt, IssueStatus status, Category category, IssueModelInfo model, List<IssueField> fields, List<Comment> comments) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("The id cannot be null or blank.");
        }

        this.id = id;

        if (createdAt == null) {
            throw new IllegalArgumentException("The creation date cannot be null.");
        }

        this.createdAt = createdAt;

        setTitle(title);
        setAuthor(author);
        setStatus(status);
        setCategory(category);
        setModel(model);
        fields.forEach(this::addField);
        comments.forEach(this::addComment);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("The title cannot be null or blank.");
        }
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.isBlank()) {
            throw new IllegalArgumentException("The author cannot be null or blank");
        }
        this.author = author;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public IssueStatus getStatus() {
        return status;
    }

    public void setStatus(IssueStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("The status cannot be null");
        }
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("The category cannot be null");
        }
        this.category = category;
    }

    public IssueModelInfo getModel() {
        return model;
    }

    public void setModel(IssueModelInfo model) {
        if (model == null) {
            throw new IllegalArgumentException("The model cannot be null");
        }
        this.model = model;
    }

    public List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
    }

    public List<IssueField> getFields() {
        return Collections.unmodifiableList(fields);
    }

    public void addComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("The comment cannot be null");
        }
        comments.add(comment);
    }

    public void addField(IssueField field) {
        if (field == null) {
            throw new IllegalArgumentException("The field cannot be null");
        }
        fields.add(field);
    }
}
