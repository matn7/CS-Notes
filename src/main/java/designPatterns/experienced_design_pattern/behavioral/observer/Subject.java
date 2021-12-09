package designPatterns.experienced_design_pattern.behavioral.observer;

public interface Subject {

    void attachObserver(Observer observer);

    void detachObserver(Observer observer);

    void change();

}
