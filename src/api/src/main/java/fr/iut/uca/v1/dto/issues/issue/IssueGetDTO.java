package fr.iut.uca.v1.dto.issues.issue;

import fr.iut.uca.v1.dto.OperatorDTO;
import fr.iut.uca.v1.dto.issues.IssueStatusDTO;
import jakarta.ws.rs.QueryParam;

import java.util.Date;

public class IssueGetDTO {
    @QueryParam("index")
    private int index;

    @QueryParam("count")
    private int count;
    @QueryParam("status")
    private IssueStatusDTO status;

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

    public IssueStatusDTO getStatus() {
        return status;
    }

    public void setStatus(IssueStatusDTO status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
