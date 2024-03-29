package designPatterns.experienced_design_pattern.behavioral.observer;

import java.util.ArrayList;

public class Button implements Subject {

    java.util.List<Observer> observers = new ArrayList<>();

    public void clicked() {
        change();
    }


    @Override
    public void attachObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void detachObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void change() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
