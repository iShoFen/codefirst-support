package fr.iut.uca.repository.mongo.codec.issues;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueCodec {

    @BsonProperty(value = "_id")
    private ObjectId id;

    private String title;

    private String author;

    @BsonProperty(value = "created_at")
    private LocalDate createdAt;

    private IssueStatusCodec status;

    private CategoryCodec category;

    private IssueModelInfoCodec model;

    private final List<CommentCodec> comments = new ArrayList<>();

    private final List<IssueFieldCodec> fields = new ArrayList<>();

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public IssueStatusCodec getStatus() {
        return status;
    }

    public void setStatus(IssueStatusCodec status) {
        this.status = status;
    }

    public CategoryCodec getCategory() {
        return category;
    }

    public void setCategory(CategoryCodec category) {
        this.category = category;
    }

    public IssueModelInfoCodec getModel() {
        return model;
    }

    public void setModel(IssueModelInfoCodec model) {
        this.model = model;
    }

    public List<CommentCodec> getComments() {
        return comments;
    }

    public void setComments(List<CommentCodec> comments) {
        this.comments.clear();
        this.comments.addAll(comments);
    }

    public List<IssueFieldCodec> getFields() {
        return fields;
    }

    public void setFields(List<IssueFieldCodec> fields) {
        this.fields.clear();
        this.fields.addAll(fields);
    }
}
