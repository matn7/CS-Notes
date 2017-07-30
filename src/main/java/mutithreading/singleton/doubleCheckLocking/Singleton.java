package mutithreading.singleton.doubleCheckLocking;

/**
 * Created by Mati on 10.07.2017.
 */
public class Singleton {

    // Mark member variable as volatile, so each
    // access of this variable is a fresh read from memory

    private volatile static Singleton singleton;

    private Singleton() {

    }

    // Use standard double check locking
    // so that synchronizatio penalty is only
    // interrupted the first time, when Singleton is
    // null. On all subsequent calls this penalty
    // is avoided. The use of volatile keyword then prevents any
    // thread from reading a state version of the Singleton
    public static Singleton getInstance() {
       // Check 1 of double checked lock
        if (singleton == null) {
            synchronized (Singleton.class) {
                // check 2 of double checked lock
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
