package designPatterns.experienced_design_pattern.structural.decorator;

public class IconWindowDecorator extends WindowDecorator {

    public IconWindowDecorator(Window window) {
        super(window);
    }

    @Override
    public void draw() {
        System.out.println("Drawing icon");
        window.draw();
    }
}
