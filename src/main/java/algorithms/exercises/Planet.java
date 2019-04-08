package algorithms.exercises;

import java.util.Date;

public final class Planet {

    // Bad
    private final double mass;

    private final String name;

    private final Date dateOfDiscovery;

    public Planet(double mass, String name, Date dateOfDiscovery) {
        this.mass = mass;
        this.name = name;
        this.dateOfDiscovery = new Date(dateOfDiscovery.getTime());
    }

    public double getMass() {
        return mass;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfDiscovery() {
        return new Date(dateOfDiscovery.getTime());
    }
}
