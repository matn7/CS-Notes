package designPatterns.experienced_design_pattern.behavioral.strategy;

public class EasyAlgorithm implements ChessAlgorithm {

    @Override
    public int calculateNextStep() {
        return 1;
    }
}
