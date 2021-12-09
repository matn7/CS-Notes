package designPatterns.experienced_design_pattern.creational.builder;

public class QueryBuilderDirector {

    public Query buildQuery(String from, String where, QueryBuilder builder) {
        builder.from(from);
        builder.where(where);
        return builder.getQuery();
    }

}
