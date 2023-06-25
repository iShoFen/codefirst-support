package fr.iut.uca.v1.model.issues;

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

    private final IssueModelInfo model;

    private final Category category;

    private final List<Comment> comments = new ArrayList<>();

    private final List<IssueField> fields = new ArrayList<>();

    public Issue(String id, String title, String author, LocalDate createdAt, IssueStatus status, IssueModelInfo model, Category category, List<IssueField> fields, List<Comment> comments) throws IllegalArgumentException {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("The id cannot be null or blank.");
        }
        this.id = id;

        if (createdAt == null) {
            throw new IllegalArgumentException("The creation date cannot be null.");
        }
        this.createdAt = createdAt;

        if (model == null) {
            throw new IllegalArgumentException("The model cannot be null");
        }
        this.model = model;

        this.category = category;

        init(title, author, status, fields, comments);
    }

    public Issue(String title, String author, LocalDate createdAt, IssueModelInfo model, Category category, List<IssueField> fields) throws IllegalArgumentException {
        this.id = null;

        if (createdAt == null) {
            throw new IllegalArgumentException("The creation date cannot be null.");
        }
        this.createdAt = createdAt;

        if (model == null) {
            throw new IllegalArgumentException("The model cannot be null");
        }
        this.model = model;

        this.category = category;

        init(title, author, IssueStatus.OPEN, fields, new ArrayList<>());
    }

    private void init(String title, String author, IssueStatus status, List<IssueField> fields, List<Comment> comments) {
        setTitle(title);
        setAuthor(author);
        setStatus(status);
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

    public IssueModelInfo getModel() {
        return model;
    }

    public Category getCategory() {
        return category;
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

    public void deleteComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("The comment cannot be null");
        }
        comments.remove(comment);
    }

    public void addField(IssueField field) {
        if (field == null) {
            throw new IllegalArgumentException("The field cannot be null");
        }
        fields.add(field);
    }

    public void updateField(IssueField field) {
        if (field == null) {
            throw new IllegalArgumentException("The field cannot be null");
        }
        fields.removeIf(f -> f.getTitle().equals(field.getTitle()));
        fields.add(field);
    }
}
