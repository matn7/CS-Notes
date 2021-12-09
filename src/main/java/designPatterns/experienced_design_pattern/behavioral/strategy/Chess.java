package designPatterns.experienced_design_pattern.behavioral.strategy;

public class Chess {

    private ChessAlgorithm algorithm = new EasyAlgorithm();

    public int calculateNextStep() {
        return algorithm.calculateNextStep();
    }

    public void setAlgorithm(ChessAlgorithm algorithm) {
        this.algorithm = algorithm;
    }
}
