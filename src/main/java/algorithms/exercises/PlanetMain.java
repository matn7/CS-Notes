package algorithms.exercises;

import java.util.Date;

public class PlanetMain {

    public static void main(String[] args) {
        Planet planet = new Planet(12.0, "jupiter", new Date());

        System.out.println(planet.getDateOfDiscovery());
        System.out.println(planet.hashCode());

        planet.getDateOfDiscovery().setTime(12L);

        System.out.println(planet.getDateOfDiscovery());
        System.out.println(planet.hashCode());

    }

}
