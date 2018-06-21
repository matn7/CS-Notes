package design_patterns.experienced_design_pattern.creational;

public interface QueryBuilder {

    void from(String from);

    void where(String where);

    Query getQuery();

}
