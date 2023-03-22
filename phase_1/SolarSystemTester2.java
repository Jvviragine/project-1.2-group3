package phase_1;

import java.util.ArrayList;

public class SolarSystemTester2 {
    public static void main(String[] args) {
        
        // Create a new Solar System

        // Create the Sun
        Vector sunInitialPositions = new Vector(0, 0, 0);
        Vector sunInitialVelocity = new Vector(0, 0, 0);
        double massOfSun = 1.9885*Math.pow(10, 30);
        CelestialBody sun = new CelestialBody(sunInitialPositions, sunInitialVelocity, massOfSun, "Sun");
        System.out.println(massOfSun);

        // Create the Probe
        Vector probeInitialPosition = new Vector(((-148186906.893642) * 1000), ((-27823158.5715694) * 1000), ((33746.8987977113) * 1000));
        Vector probeInitialVelocity = new Vector(0, 0, 0);
        CelestialBody probe = new CelestialBody(probeInitialPosition, probeInitialVelocity,  50000, "Probe");

        // Collection of Celestial Bodies
        ArrayList<CelestialBody> celestialBodies = new ArrayList<CelestialBody>();
        celestialBodies.add(sun);
        celestialBodies.add(probe);

        // Creating the Solar System
        int timeStepInSeconds = 1; // 1 Second of Timestep
        StateOfSystem solarSystemState = new StateOfSystem(timeStepInSeconds, celestialBodies); // Giging it a Time Step of 
        
        // Create the Object that can Update the State of the Solar System
        UpdateStateOfSystem solarSystemUpdater = new UpdateStateOfSystem(solarSystemState);
        System.out.println();

        // Fill in the Blanks for T0
        solarSystemUpdater.calculateMissingValuesForT0();

        // Loop controlling how many TimeSteps we are going to take
        int lastT = 1;
        for (int t = 1; t <= lastT; t++) {
            solarSystemUpdater.updateStateOfSolarSystem();
        }
        


    }
}
