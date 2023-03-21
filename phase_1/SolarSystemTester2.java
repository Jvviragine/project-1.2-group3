package phase_1;

import java.util.ArrayList;

public class SolarSystemTester2 {
    public static void main(String[] args) {
        
        // Create a new Solar System

        // Create the Sun
        Vector sunInitialPositions = new Vector(0, 0, 0);
        Vector sunInitialVelocity = new Vector(0, 0, 0);
        CelestialBody sun = new CelestialBody(sunInitialPositions, sunInitialVelocity, 1,9885*Math.pow(10, 30));

        // Create the Probe
        Vector probeInitialPosition = new Vector((-148186906.893642), (-27823158.5715694), (33746.8987977113));
        Vector probeInitialVelocity = new Vector(0, 0, 0);
        CelestialBody probe = new CelestialBody(probeInitialPosition, probeInitialVelocity,  50000, 0);

        // Collection of Celestial Bodies
        ArrayList<CelestialBody> celestialBodies = new ArrayList<CelestialBody>();
        celestialBodies.add(sun);
        celestialBodies.add(probe);

        StateOfSystem solarSystemState = new StateOfSystem(0, celestialBodies);
        
        //StateOfSystem solarSystemState = new StateOfSystem(0, null, null, null);


    }
}