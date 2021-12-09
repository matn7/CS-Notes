package designPatterns.experienced_design_pattern.creational.singleton;

public class App {

    public static void main(String[] args) {
        Preferences.getInstance().hello();

        MyPreferences.getInstance().hello();
    }

}
