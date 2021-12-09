package designPatterns.experienced_design_pattern.creational.builder;

public class App {

    public static void main(String[] args) {
        QueryBuilderDirector director = new QueryBuilderDirector();
        String from = "client table";
        String where = "client name = ... ";

        QueryBuilder builder = new SqlQueryBuilder();
        Query query = director.buildQuery(from, where, builder);
        query.execute();

        builder = new MongoDbQueryBuilder();
        query = director.buildQuery(from, where, builder);
        query.execute();

    }

}
