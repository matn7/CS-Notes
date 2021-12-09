package designPatterns.experienced_design_pattern.structural.bridge;

public class WindowsXPWindowImpl extends WindowImpl {
    @Override
    public void draw(int x, int y, int width, int height, String color) {
        System.out.println("Drowing window on WindowsXP coordinates x: " + x + " y: " + y
        + " w: " + width + " h: " + height + " c: " + color);
    }
}
