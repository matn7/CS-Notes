package designPatterns.creational.factory;

/**
 * Created by Mati on 09.07.2017.
 */
public class DatabaseFactory {

    public static IDatabase getDatabase(){
        String databaseClassName = readFromConfigFile("database-class-name");
        IDatabase database = null;
        try {
            database = (IDatabase) Class.forName(databaseClassName).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return database;
    }

    private static String readFromConfigFile(String key) {
        // The config file has key-value pairs
        // return value coresponding to key specified
        return null;
    }

}
