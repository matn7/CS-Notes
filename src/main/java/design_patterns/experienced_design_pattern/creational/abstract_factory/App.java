package design_patterns.experienced_design_pattern.creational.abstract_factory;

public class App {

    public static void initializeGUI(WidgetFactory factory) {
        initializeGui(factory.createScrollBar(), factory.createWindow());
    }

    public static void initializeGui(ScrollBar bar, Window window) {
        System.out.println("Initialization logic with : " + bar.getClass().getName() +
                           " and : " + window.getClass().getName());
    }

    public static void main(String[] args) {
        WidgetFactory factory = new PinkWidgetFactory();
        initializeGUI(factory);

        factory = new GreenWidgetFactory();
        initializeGUI(factory);

    }

}
