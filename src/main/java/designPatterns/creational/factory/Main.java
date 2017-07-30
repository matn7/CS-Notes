package designPatterns.creational.factory;

/**
 * Created by Mati on 09.07.2017.
 */
public class Main {

    public static void main(String[] args) {
        IDatabase database = DatabaseFactory.getDatabase();
    }

}
