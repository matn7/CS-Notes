package design_patterns.basic.creational.singleton.double_check_lock;

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

    // make sure that singleton object can't be cloned
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
