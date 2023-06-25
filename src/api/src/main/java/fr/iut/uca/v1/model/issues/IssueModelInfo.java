package fr.iut.uca.v1.model.issues;

public class IssueModelInfo {
    private String name;

    private String shortDescription;

    private String description;

    public IssueModelInfo(String name, String shortDescription, String description) throws IllegalArgumentException {
        setName(name);
        this.shortDescription = shortDescription;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }
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
