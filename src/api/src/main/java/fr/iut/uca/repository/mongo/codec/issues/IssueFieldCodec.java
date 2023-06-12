package fr.iut.uca.repository.mongo.codec.issues;

public class IssueFieldCodec extends IssueModelFieldCodec {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
