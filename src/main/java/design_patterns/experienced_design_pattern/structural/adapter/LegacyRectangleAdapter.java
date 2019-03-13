package design_patterns.experienced_design_pattern.structural.adapter;

public class LegacyRectangleAdapter extends Rectangle {

    private LegacyRectangle legacyRectangle;

    public LegacyRectangleAdapter(LegacyRectangle legacyRectangle) {
        this.legacyRectangle = legacyRectangle;
    }

    @Override
    public Integer denominateSize() {
        return legacyRectangle.calculateSize();
    }
}
