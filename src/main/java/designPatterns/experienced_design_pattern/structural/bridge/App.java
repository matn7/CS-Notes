package designPatterns.experienced_design_pattern.structural.bridge;

public class App {

    public static void main(String[] args) {
        IconWindow iconWindow = new IconWindow();
        iconWindow.setWindow(new WindowsXPWindowImpl());
        iconWindow.drawIcon();
        iconWindow.setWindow(new LinuxWindowImpl());
        iconWindow.drawIcon();
    }
}
