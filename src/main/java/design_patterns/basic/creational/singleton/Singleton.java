package design_patterns.basic.creational.singleton;

/**
 * Created by Mati on 09.07.2017.
 */
public class Singleton {

    // Just one private, static instance
    // singleton object
    private static Singleton singleton;

    private Singleton() {
        // private constructor
        // nobody can instantiate outside this class
    }

    // method must be marked synchronized, else it is possible that 2 different threads
    // might enter this method simultaneously and create more than one instance of object
    public static synchronized Singleton getSingleton() {
        if (singleton == null) {
            // the first time someone asks for singleton object
            // this is first and last time that this code will be executed
            singleton = new Singleton();
        }
        return singleton;
    }

}
