package design_patterns.experienced_design_pattern.structural.decorator;

public class App {

    public static void main(String[] args) {
        Window window = new Window();
        IconWindowDecorator iconWindowDecorator = new IconWindowDecorator(window);
        ScrollBarWindowDecorator scrollBarWindowDecorator = new ScrollBarWindowDecorator(iconWindowDecorator);

        scrollBarWindowDecorator.draw();
    }

}
