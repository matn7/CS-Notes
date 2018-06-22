package design_patterns.experienced_design_pattern.creational.abstract_factory;

public class PinkWidgetFactory implements WidgetFactory {

    // Related object created together

    @Override
    public ScrollBar createScrollBar() {
        return new PinkScrollBar();
    }

    @Override
    public Window createWindow() {
        return new PinkWindow();
    }
}
