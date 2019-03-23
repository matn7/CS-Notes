package algorithms.exercises;


import java.util.List;

class MyListRestricted<T extends Number> {
    private List<T> values;

    void add(T value) {
        values.add(value);
    }

    void remove(T value) {
        values.remove(value);
    }

    T get(int index) {
        return values.get(index);
    }
}

class MyRestrictedTest<F extends Number> {
    public static void main(String[] args) {
        MyRestrictedTest<Number> myRestrictedTest = new MyRestrictedTest<Number>();
    }
}