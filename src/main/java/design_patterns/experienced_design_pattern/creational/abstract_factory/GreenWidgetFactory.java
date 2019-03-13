package design_patterns.experienced_design_pattern.creational.abstract_factory;

public class GreenWidgetFactory implements WidgetFactory {
    @Override
    public ScrollBar createScrollBar() {
        return new GreenScrollBar();
    }

    @Override
    public Window createWindow() {
        return new GreenWindow();
    }
}
