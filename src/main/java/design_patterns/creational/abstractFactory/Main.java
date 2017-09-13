package design_patterns.creational.abstractFactory;

/**
 * Created by Mati on 09.07.2017.
 */
public class Main {

    public static void main(String[] args) {
        AbstractDatabaseFactory abstractDatabaseFactory = new MSSqlDatabaseFactory();
        IDatabase database = abstractDatabaseFactory.getDatabase();
    }

}
