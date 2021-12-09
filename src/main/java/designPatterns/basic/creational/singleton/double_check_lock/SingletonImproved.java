package designPatterns.basic.creational.singleton.double_check_lock;

public class SingletonImproved implements Cloneable {

    private volatile static SingletonImproved singleton;

    private SingletonImproved() {
    }

    // double-check-locking
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
