package fr.iut.uca.v1.dto.issues.issuemodel;

import jakarta.ws.rs.QueryParam;

public class IssueModelGetDTO {
    @QueryParam("index")
    private int index;
    @QueryParam("count")
    private int count;
    @QueryParam("name")
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
