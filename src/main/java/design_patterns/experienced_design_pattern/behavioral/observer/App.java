package design_patterns.experienced_design_pattern.behavioral.observer;

public class App {

    public static void main(String[] args) {
        Button button = new Button();
        InputText text = new InputText();
        List list = new List();

        button.attachObserver(text);
        button.attachObserver(list);


        button.clicked();
    }

}
