package design_patterns.experienced_design_pattern.behavioral.strategy;

public class App {

    public static void main(String[] args) {
        Chess chess = new Chess();

        System.out.println("Calculate next step: " + chess.calculateNextStep());
        chess.setAlgorithm(new HardAlgorithm());
        System.out.println("Calculate next step: " + chess.calculateNextStep());
    }

}
