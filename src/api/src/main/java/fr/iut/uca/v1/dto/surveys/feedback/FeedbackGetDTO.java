package fr.iut.uca.v1.dto.surveys.feedback;

import fr.iut.uca.v1.dto.OperatorDTO;
import jakarta.ws.rs.QueryParam;

import java.util.Date;

public class FeedbackGetDTO {
    @QueryParam("index")
    private int index;

    @QueryParam("count")
    private int count;

    @QueryParam("author")
    private String author;

    @QueryParam("created_at")
    private Date createdAt;

    @QueryParam("end_date")
    private Date endDate;

    @QueryParam("operator")
    private OperatorDTO operator;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public OperatorDTO getOperator() {
        return operator;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setOperator(OperatorDTO operator) {
        this.operator = operator;
    }
}
