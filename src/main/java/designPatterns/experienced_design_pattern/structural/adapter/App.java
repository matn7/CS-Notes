package designPatterns.experienced_design_pattern.structural.adapter;

public class App {

    public static void main(String[] args) {
        App app = new App();

        LegacyRectangle legacyRectangle = new LegacyRectangle();

        LegacyRectangleAdapter adapter = new LegacyRectangleAdapter(legacyRectangle);

        app.calculateRectangleSize(adapter);
    }


    public void calculateRectangleSize(Rectangle rectangle) {
        System.out.println("Rectangle size: " + rectangle.denominateSize());
    }

}
