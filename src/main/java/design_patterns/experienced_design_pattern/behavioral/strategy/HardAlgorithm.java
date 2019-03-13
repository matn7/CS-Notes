package design_patterns.experienced_design_pattern.behavioral.strategy;

public class HardAlgorithm implements ChessAlgorithm {

    @Override
    public int calculateNextStep() {
        return 3;
    }
}
