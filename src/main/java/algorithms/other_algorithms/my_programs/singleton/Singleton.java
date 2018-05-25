package algorithms.other_algorithms.my_programs.singleton;

/**
 * Created by Mati on 17.09.2017.
 */
public class Singleton {

    private static volatile Singleton singleton;

    private Singleton() {

    }

    public static Singleton getSingleton() {
        if (singleton ==  null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
