package designPatterns.experienced_design_pattern.structural.bridge;

public class IconWindow extends Window {

    public void drawIcon() {
        draw(90,90,100,100, "Yello");
        draw(180,180,100,100, "Red");
    }

}
