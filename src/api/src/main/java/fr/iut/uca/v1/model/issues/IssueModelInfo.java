package fr.iut.uca.v1.model.issues;

public class IssueModelInfo {
    private String name;

    private String shortDescription;

    private String description;

    public IssueModelInfo(String name, String shortDescription, String description) {
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
