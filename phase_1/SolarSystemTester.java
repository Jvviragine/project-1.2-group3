package phase_1;

import java.util.ArrayList;

public class SolarSystemTester {
    
    public static void main(String[] args) {
        
        // Create a new Solar System

        // Create the Sun
        Vector sunInitialPositions = new Vector(0, 0, 0);
        Vector sunInitialVelocity = new Vector(0, 0, 0);
        CelestialBody sun = new CelestialBody(sunInitialPositions, sunInitialVelocity, 1,9885*Math.pow(10, 30));

        // Create the Probe
        // Vector probeInitialPosition = new Vector((-148186906.893642), (-27823158.5715694), (33746.8987977113));
        // Vector probeInitialVelocity = new Vector(0, 0, 0);
        // CelestialBody probe = new CelestialBody(probeInitialPosition, probeInitialVelocity,  50000, 0);

        // Create the Earth
        Vector earthInitialPosition = new Vector((-148186906.893642), (-27823158.5715694), (33746.8987977113));
        Vector earthInitialVelocity = new Vector((5.05251577575409), (-29.3926687625899), (0.00170974277401292));
        CelestialBody earth = new CelestialBody(earthInitialPosition, earthInitialVelocity, 5.97219*Math.pow(10,24), 0);

        // Create Saturn
        Vector saturnInitialPosition = new Vector((1253801723.95465), (-760453007.810989), (-36697431.1565206));
        Vector saturnInitialVelocity = new Vector((4.46781341335014), (8.23989540475628), (-0.320745376969732));
        CelestialBody saturn = new CelestialBody(saturnInitialPosition, saturnInitialVelocity,5.6834*Math.pow(10,26), 0);

        // Create Venus
        Vector venusInitialPosition = new Vector((-28216773.9426889), (103994008.541512), (3012326.64296788));
        Vector venusInitialVelocity = new Vector((-34.0236737066136), (-8.96521274688838), (1.84061735279188));
        CelestialBody venus = new CelestialBody(venusInitialPosition, venusInitialVelocity,48.685*Math.pow(10,23), 0);

        // Collection of Celestial Bodies
        ArrayList<CelestialBody> celestialBodies = new ArrayList<CelestialBody>();
        celestialBodies.add(sun);
        //celestialBodies.add(probe);
        celestialBodies.add(venus);
        celestialBodies.add(earth);
        celestialBodies.add(saturn);

        //for(int i=0;i<3;i++){
            StateOfSystem stateOfSolarSystem = new StateOfSystem(0, celestialBodies);
            stateOfSolarSystem.currentState(50);
            //System.out.println(stateOfSolarSystem.currentState(3));
        //}
        
        //StateOfSystem solarSystemState = new StateOfSystem(0, null, null, null);


    }
}
