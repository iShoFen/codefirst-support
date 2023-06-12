package fr.iut.uca.repository.mongo;

public abstract class MongoOperators {

    public static final String EQUAL = "$eq";
    public static final String NOT_EQUAL = "$ne";
    public static final String GREATER_THAN = "$gt";
    public static final String GREATER_THAN_OR_EQUALS = "$gte";
    public static final String LESS_THAN = "$lt";
    public static final String LESS_THAN_OR_EQUALS = "$lte";
    public static final String SUM = "$sum";
    public static final String REGEX = "$regex";
    public static final String GROUP = "$group";
    public static final String PROJECT = "$project";
    public static final String MATCH = "$match";
    public static final String SKIP = "$skip";
    public static final String LIMIT = "$limit";
    public static final String SORT = "$sort";
    public static final String UNWIND = "$unwind";
    public static final String LOOKUP = "$lookup";
    public static final String FROM = "from";
    public static final String LOCAL_FIELD = "localField";
    public static final String FOREIGN_FIELD = "foreignField";
    public static final String AS = "as";
    public static final String PIPELINE = "pipeline";
    public static final String ID = "_id";
    public static final String REF = "$";
    public static final String PIPELINE_REF = "$$";
    public static final String LET = "let";
    public static final String EXPR = "$expr";

    private MongoOperators() { }
}
