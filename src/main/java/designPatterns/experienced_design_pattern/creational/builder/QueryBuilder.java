package designPatterns.experienced_design_pattern.creational.builder;

public interface QueryBuilder {

    void from(String from);

    void where(String where);

    Query getQuery();

}
