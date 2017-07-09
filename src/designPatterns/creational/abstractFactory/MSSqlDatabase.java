package designPatterns.creational.abstractFactory;

/**
 * Created by Mati on 09.07.2017.
 */
public class MSSqlDatabase extends IDatabase {
    @Override
    IDatabase getDatabase() {
        return null;
    }
}
