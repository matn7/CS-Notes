package design_patterns.experienced_design_pattern.structural.bridge;

public class Window {
    // Bridge pattern
    private WindowImpl window;

    public void draw(int x, int y, int width, int height, String color) {
        window.draw(x,y,width,height,color);
    }

    public void setWindow(WindowImpl window) {
        this.window = window;
    }
}
