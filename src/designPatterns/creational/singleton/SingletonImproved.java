package designPatterns.creational.singleton;

/**
 * Created by Mati on 09.07.2017.
 */
public class SingletonImproved implements Cloneable {

    // mark member variable as volatile, so each access this variable is fresh read from memory
    private volatile static SingletonImproved singleton;

    private SingletonImproved() {

    }

    // double-check-locking
    // reduce overhead of acquiring a lock, by first testing lock creation
    public static SingletonImproved getInstance() {
        if (singleton == null) {
            synchronized (SingletonImproved.class) { // lock in between
                if (singleton == null) {
                    singleton = new SingletonImproved();
                }
            }
        }
        return singleton;
    }

    // make sure that singleton object cant be cloned
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
