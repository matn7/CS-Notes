package designPatterns.experienced_design_pattern.creational.singleton;

public class Preferences {

    private static Preferences instance = null;

    protected Preferences() {}

    private synchronized static void createInstance() {
        if (instance == null) {
            instance = new Preferences();
        }
    }

    public static Preferences getInstance() {
        if (instance == null) {
            createInstance();
        }
        return instance;
    }

    public void hello() {
        System.out.println("Hello singleton " + this.hashCode());
    }

}
