package designPatterns.experienced_design_pattern.structural.composite;

public class App {

    public static void main(String[] args) {
        Line line = new Line();
        Rectangle rectangle = new Rectangle();

        GraphicItemGroup graphicItemGroup = new GraphicItemGroup();

        graphicItemGroup.getGraphics().add(line);
        graphicItemGroup.getGraphics().add(rectangle);

        graphicItemGroup.draw();

        Line line1 = new Line();
        GraphicItemGroup graphicItemGroup2 = new GraphicItemGroup();
        graphicItemGroup2.getGraphics().add(line1);
        graphicItemGroup2.getGraphics().add(graphicItemGroup);

        graphicItemGroup2.draw();
    }
}
