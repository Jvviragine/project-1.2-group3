package phase_1;

import java.lang.reflect.Array;
import java.util.ArrayList;

// This is the Class Responsible for Launching the Whole Simulation

public class Main {


    // Main Method -> Where all the Calculations will be Instantiated and GUIs Launched
    public static void main(String[] args) {
        
        // Launch the JFrame with Initial Conditions -> Garrick
            // Get the Initial Position and Velocity for the Probe
            // Close the JFrame

        // Calculate the Orbit of Each Celestial Body so it can be plotted (with the exception of Probe) -> Tamar
            // Return each one of them so the GUI Team can Plot
        
        // Calculate the Path of the Probe either until it reaches Titan or for 1 Earth Year - João and Ula
            // Return the Coordinates of the Path to the GUI Team can plot it

        // Create a new Solar System

        // Create the Sun
        Vector sunInitialPositions = new Vector(0, 0, 0);
        Vector sunInitialVelocity = new Vector(0, 0, 0);
        CelestialBody sun = new CelestialBody(sunInitialPositions, sunInitialVelocity, 1.9885*Math.pow(10, 30), "The Sun");

        // Create Venus
        Vector venusInitialPosition = new Vector((-28216773.9426889), (103994008.541512), (3012326.64296788));
        Vector venusInitialVelocity = new Vector((-34.0236737066136), (-8.96521274688838), (1.84061735279188));
        CelestialBody venus = new CelestialBody(venusInitialPosition, venusInitialVelocity,48.685*Math.pow(10,23),225,"Venus");

        // Create the Earth
        Vector earthInitialPosition = new Vector((-148186906.893642), (-27823158.5715694), (33746.8987977113));
        Vector earthInitialVelocity = new Vector((5.05251577575409), (-29.3926687625899), (0.00170974277401292));
        CelestialBody earth = new CelestialBody(earthInitialPosition, earthInitialVelocity, 5.97219*Math.pow(10,24),366, "The Earth");

        //Create the Moon
        Vector moonInitialPosition = new Vector((-148458048.395164), (-27524868.1841142), (70233.6499287411));
        Vector moonInitialVelocity = new Vector((4.34032634654904), (-30.0480834180741), (-0.0116103535014229));
        CelestialBody moon = new CelestialBody(moonInitialPosition, moonInitialVelocity, 7.349*Math.pow(10,22),1, "The Moon");

        // Create Mars
        Vector marsInitialPosition = new Vector((-159116303.422552), (189235671.561057), (7870476.08522969));
        Vector marsInitialVelocity = new Vector((-17.6954469224752), (-13.4635253412947), (0.152331928200531));
        CelestialBody mars = new CelestialBody(marsInitialPosition, marsInitialVelocity, 6.4171*Math.pow(10,23),687, "Mars");

        // Create Jupiter
        Vector jupiterInitialPosition = new Vector((692722875.928222), (258560760.813524), (-16570817.7105996));
        Vector jupiterInitialVelocity = new Vector((-4.71443059866156), (12.8555096964427), (0.0522118126939208));
        CelestialBody jupiter = new CelestialBody(jupiterInitialPosition, jupiterInitialVelocity,189818722*Math.pow(10,19),4333, "Jupiter");

        // Create Saturn
        Vector saturnInitialPosition = new Vector((1253801723.95465), (-760453007.810989), (-36697431.1565206));
        Vector saturnInitialVelocity = new Vector((4.46781341335014), (8.23989540475628), (-0.320745376969732));
        CelestialBody saturn = new CelestialBody(saturnInitialPosition, saturnInitialVelocity,5.6834*Math.pow(10,26),10759, "Saturn");

        // Create Titan
        Vector titanInitialPosition = new Vector((1254501624.95946), (-761340299.067828), (-36309613.8378104));
        Vector titanInitialVelocity = new Vector((8.99593229549645), (11.1085713608453), (-2.25130986174761));
        CelestialBody titan = new CelestialBody(titanInitialPosition, titanInitialVelocity,13455.3*Math.pow(10,19),16, "Titan");

        // Create Uranus
        Vector uranusInitialPositions = new Vector((1958732435.99338), (2191808553.21893 ), (-17235283.8321992));
        Vector uranusInitialVelocity = new Vector((-5.12766216337626), (4.22055347264457), (0.082119033640306386));
        CelestialBody uranus = new CelestialBody(uranusInitialPositions, uranusInitialVelocity, 13455.3*Math.pow(10,19), "Uranus");

        // Create Neptune
        Vector neptuneInitialPositions = new Vector((4454487339.09447), (-397895128.763904), (-94464151.3421107));
        Vector neptuneInitialVelocity = new Vector((0.447991656952326), (5.44610697514907), (-0.122638125365954102));
        CelestialBody neptune = new CelestialBody(neptuneInitialPositions, neptuneInitialVelocity, 13455.3*Math.pow(10,19), "Neptune");

        // Create the Probe
        //Vector probeInitialPosition = new Vector((-148186906.893642), (-27823158.5715694), (33746.8987977113));
        Vector probeInitialPosition = new Vector(-148458048.395164+6370, -27524868.1841142, 70233.6499287411);
        Vector probeInitialVelocity = new Vector(42.42270135156, -43.62738201925, -3.1328169170);
        CelestialBody probe = new CelestialBody(probeInitialPosition, probeInitialVelocity,  50000, "Probe");

        // Collection of Celestial Bodies
        ArrayList<CelestialBody> celestialBodies = new ArrayList<CelestialBody>();
        celestialBodies.add(sun);
        celestialBodies.add(venus);
        celestialBodies.add(earth);
        celestialBodies.add(moon);
        celestialBodies.add(mars);
        celestialBodies.add(jupiter);
        celestialBodies.add(saturn);
        celestialBodies.add(titan);
        celestialBodies.add(uranus);
        celestialBodies.add(neptune);
        celestialBodies.add(probe);

        // Open the login window for entering the initial positions and velocities
        Login login = new Login();
        probe.setPosition(login.initPos);
        probe.setVelocity(login.initVelo);

        //Calculate the orbits of each planet skipping the uranus and neptune
        double[][] orbitOfVenus = OrbitFinder.getOrbit(venus, celestialBodies);
        double[][] orbitOfEarth = OrbitFinder.getOrbit(earth, celestialBodies);
        double[][] orbitOfMoon = OrbitFinder.getOrbit(moon, celestialBodies);
        double[][] orbitOfMars = OrbitFinder.getOrbit(mars, celestialBodies);
        double[][] orbitOfJupiter = OrbitFinder.getOrbit(jupiter, celestialBodies);
        double[][] orbitOfSaturn = OrbitFinder.getOrbit(saturn, celestialBodies);
        double[][] orbitOfTitan = OrbitFinder.getOrbit(titan, celestialBodies);

        int timeStepInSeconds = 60 * 60; // 1 Month of Timestep
        StateOfSystem solarSystemState3 = new StateOfSystem(timeStepInSeconds, celestialBodies); // Giging it a Time Step of
        
        // Create the Object that can Update the State of the Solar System
        UpdateStateOfSystem solarSystemUpdater3 = new UpdateStateOfSystem(solarSystemState3);
        solarSystemUpdater3.calculateMissingValuesForT0();


        double min = probeInitialPosition.dist(titanInitialPosition);
        double minTime = 0;
        while(!solarSystemUpdater3.reachedTitan(probe, titan, solarSystemState3.getTotalTimePassed())){
            solarSystemUpdater3.updateStateOfSolarSystem();
            //System.out.println(probe.getPosition().dist(titanInitialPositions));
            if(probe.getPosition().dist(titan.getPosition())<min){
                min = probe.getPosition().dist(titan.getPosition());
                minTime = solarSystemState3.getTotalTimePassed();
            }
        }
        System.out.println(solarSystemUpdater3.getPositionWhenReached());
        System.out.println("\n");
        System.out.println("min distance to titan: " + min);
        System.out.println("\n");
        System.out.println(minTime);

        int positionsSize = earth.getPositionsArray().size()-1;

        System.out.println("Error on the X Coordinate = " + (Math.abs((earth.getPositionsArray().get(0).getX()) - earth.getPositionsArray().get(positionsSize).getX()) / (earth.getPositionsArray().get(0).getX())) * 100 + " %");
        System.out.println("Error on the Y Coordinate = " + (Math.abs((earth.getPositionsArray().get(0).getY()) - earth.getPositionsArray().get(positionsSize).getY()) / (earth.getPositionsArray().get(0).getY())) * 100 + " %");
        System.out.println("Error on the Z Coordinate = " + (Math.abs((earth.getPositionsArray().get(0).getZ()) - earth.getPositionsArray().get(positionsSize).getZ()) / (earth.getPositionsArray().get(0).getZ())) * 100 + " %");



    }
}