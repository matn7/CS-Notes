package designPatterns.creational.abstractFactory;

/**
 * Created by Mati on 09.07.2017.
 */
public abstract class AbstractDatabaseFactory {

    abstract IDatabase getDatabase();

    private String readFromConfigFile(String key) {
        // The config file has key-value pairs,
        // return the value corresponding to the key specified
        return null;
    }


}
