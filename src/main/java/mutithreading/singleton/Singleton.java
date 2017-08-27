package mutithreading.singleton;

/**
 * Created by Mati on 10.07.2017.
 */
public class Singleton {
    // Maintain just one private, static instance
    // this is the singleton object
    private static Singleton singleton;

    private Singleton() {
        // the private constructor is the key
        // nobody can instantiate outside this class
    }

    // Method must be synchronized else
    // It is possible that 2 different threads might
    // enter this method simultaneously and create
    // more than one instance of the object
    public static synchronized Singleton getInstance() {
        if (singleton == null) {
            // This is the first time someone ask for Singleton
            // it will be instantiated - this is the first and last
            // time this code will be executed
            singleton = new Singleton();
        }
        return singleton;
    }

}
