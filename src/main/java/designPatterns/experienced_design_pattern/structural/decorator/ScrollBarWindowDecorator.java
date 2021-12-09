package designPatterns.experienced_design_pattern.structural.decorator;

public class ScrollBarWindowDecorator extends WindowDecorator {

    public ScrollBarWindowDecorator(Window window) {
        super(window);
    }

    @Override
    public void draw() {
        System.out.println("Scroll bar drawing");
        window.draw();
    }
}
