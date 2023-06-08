package fr.iut.uca.entity.issues;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class IssueModelInfoEntity {

    protected String name;

    @BsonProperty(value = "short_description")
    protected String shortDescription;

    protected String description;

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
