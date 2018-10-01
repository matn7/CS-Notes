package design_patterns.basic.creational.factory;

public interface Plane {
    // Any Plane that factory returns must implement this interface
    void model();
    // Plane interface is key point in factory pattern
    // Define base class type (interface plane in this case), and then have any number
    // of subclasses which implements contract defined by the base class.

}
