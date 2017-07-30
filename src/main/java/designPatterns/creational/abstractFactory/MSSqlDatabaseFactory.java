package designPatterns.creational.abstractFactory;

/**
 * Created by Mati on 09.07.2017.
 */
public class MSSqlDatabaseFactory extends AbstractDatabaseFactory {
    @Override
    IDatabase getDatabase() {
        return new MSSqlDatabase();
    }
}
