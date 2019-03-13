package design_patterns.experienced_design_pattern.creational.builder;

public class SqlQuery implements Query {
    private String from;
    private String where;

    @Override
    public void execute() {
        System.out.println("Executing sql query from " + from + " where " + where);
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setWhere(String where) {
        this.where = where;
    }
}
