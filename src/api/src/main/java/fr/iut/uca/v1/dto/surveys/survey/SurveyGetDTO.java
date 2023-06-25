package fr.iut.uca.v1.dto.surveys.survey;

import fr.iut.uca.v1.dto.OperatorDTO;
import jakarta.ws.rs.QueryParam;

import java.util.Date;

public class SurveyGetDTO {
    @QueryParam("index")
    private int index;

    @QueryParam("count")
    private int count;

    @QueryParam("created_at")
    private Date createdAt;

    @QueryParam("published_at")
    private Date publishedAt;

    @QueryParam("end_at")
    private Date endAt;

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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public OperatorDTO getOperator() {
        return operator;
    }

    public void setOperator(OperatorDTO operator) {
        this.operator = operator;
    }
}
