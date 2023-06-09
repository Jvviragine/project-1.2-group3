package phase_1;

import java.util.ArrayList;

public class SolarSystemOrbitTester {

    public static void main(String[] args) {
        
        // Create a new Solar System

        // Create the Sun
        Vector sunInitialPositions = new Vector(0, 0, 0);
        Vector sunInitialVelocity = new Vector(0, 0, 0);
        CelestialBody sun = new CelestialBody(sunInitialPositions, sunInitialVelocity, 1.9885*Math.pow(10, 30), "Sun");

        // Create Venus
        Vector venusInitialPositions = new Vector((-28216773.9426889), (103994008.541512), (3012326.64296788));
        Vector venusInitialVelocity = new Vector((-34.0236737066136), (-8.96521274688838), (1.84061735279188));
        CelestialBody venus = new CelestialBody(venusInitialPositions, venusInitialVelocity, 48.685*Math.pow(10,23), "Venus");

        // Create the Earth
        Vector earthInitialPositions = new Vector((-148186906.893642), (-27823158.5715694), (33746.8987977113));
        Vector earthInitialVelocity = new Vector((5.05251577575409), (-29.3926687625899), (0.00170974277401292));
        CelestialBody earth = new CelestialBody(earthInitialPositions, earthInitialVelocity, 5.97219*Math.pow(10,24), "Earth");

        // Create the Moon
        Vector moonInitialPositions = new Vector((-148458048.395164), (-27524868.1841142), (70233.6499287411));
        Vector moonInitialVelocity = new Vector((4.34032634654904), (-30.0480834180741), (-0.0116103535014229));
        CelestialBody moon = new CelestialBody(moonInitialPositions, moonInitialVelocity, 7.349*Math.pow(10,22), "Moon");

        // Create Mars
        Vector marsInitialPositions = new Vector((-159116303.422552), (189235671.561057), (7870476.08522969));
        Vector marsInitialVelocity = new Vector((-17.6954469224752), (-13.4635253412947), (0.152331928200531));
        CelestialBody mars = new CelestialBody(marsInitialPositions, marsInitialVelocity, 6.4171*Math.pow(10,23) , "Mars");

        // Create Jupiter
        Vector jupiterInitialPositions = new Vector((692722875.928222), (258560760.813524), (-16570817.7105996));
        Vector jupiterInitialVelocity = new Vector((-4.71443059866156), (12.8555096964427), (0.0522118126939208));
        CelestialBody jupiter = new CelestialBody(jupiterInitialPositions, jupiterInitialVelocity, 189818722*Math.pow(10,19), "Jupiter");

        // Create Saturn
        Vector saturnInitialPositions = new Vector((1253801723.95465), (-760453007.810989), (-36697431.1565206));
        Vector saturnInitialVelocity = new Vector((4.46781341335014), (8.23989540475628), (-0.320745376969732));
        CelestialBody saturn = new CelestialBody(saturnInitialPositions, saturnInitialVelocity, 5.6834*Math.pow(10,26), "Saturn");

        // Create Titan
        Vector titanInitialPositions = new Vector((1254501624.95946), (-761340299.067828), (-36309613.8378104));
        Vector titanInitialVelocity = new Vector((8.99593229549645), (11.1085713608453), (-2.25130986174761));
        CelestialBody titan = new CelestialBody(titanInitialPositions, titanInitialVelocity, 13455.3*Math.pow(10,19), "Titan");

        // Create Neptune
        Vector neptuneInitialPositions = new Vector((4454487339.09447), (-397895128.763904), (-94464151.3421107));
        Vector neptuneInitialVelocity = new Vector((0.447991656952326), (5.44610697514907), (-0.122638125365954102));
        CelestialBody neptune = new CelestialBody(neptuneInitialPositions, neptuneInitialVelocity, 13455.3*Math.pow(10,19), "Neptune");

        // Create Uranus
        Vector uranusInitialPositions = new Vector((1958732435.99338), (2191808553.21893 ), (-17235283.8321992));
        Vector uranusInitialVelocity = new Vector((-5.12766216337626), (4.22055347264457), (0.082119033640306386));
        CelestialBody uranus = new CelestialBody(uranusInitialPositions, uranusInitialVelocity, 13455.3*Math.pow(10,19), "Uranus");

        // Create the Probe
        
        //Vector probeInitialPosition = new Vector(-148186906.893642 + 6700, -27823158.5715694 + 6700, 33746.8987977113 + 6700);

        //Vector probeInitialPosition = new Vector(0 ,0 , -6370);

        Vector probeInitialPosition = new Vector((-148457000.395164+6370), (-27524560.0841140), (70233.0000087411));
        //Vector probeInitialVelocity = new Vector(40.41793413, -43.6266632, -3.13596209);

        //Vector probeInitialPosition = new Vector((-148458048.395164+6370), (-27524868.1841142), (70233.6499287411));
        Vector probeInitialVelocity = new Vector(42.41899999, -43.6276633, -3.13401000);

        // 99738.71668423818 KM
        //Vector probeInitialPosition = new Vector((-148457000.395164+6370), (-27524560.0841140), (70233.6499287411)); 
        //Vector probeInitialVelocity = new Vector(42.41899999, -43.6276633, -3.13401000);

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
        celestialBodies.add(neptune);
        celestialBodies.add(uranus);
        celestialBodies.add(probe);

        // Creating the Solar System
        int timeStepInSeconds = 60; // 1 HOUR of Timestep
        StateOfSystem solarSystemState3 = new StateOfSystem(timeStepInSeconds, celestialBodies); // Giging it a Time Step of
        
        // Create the Object that can Update the State of the Solar System
        UpdateStateOfSystem solarSystemUpdater3 = new UpdateStateOfSystem(solarSystemState3);
        solarSystemUpdater3.calculateMissingValuesForT0();

        // Loop controlling how many TimeSteps we are going to take
        int lastT = (365 * 24 * 60); // 1 Hour Passed by
        for (int t = 1; t <= lastT; t++) {
            solarSystemUpdater3.updateStateOfSolarSystem();
        }
        // System.out.println(earth.getPositionsArray().get(0));
        // System.out.println(earth.getPositionsArray().get(lastT));

        // System.out.println();
        // System.out.println(probe.getPositionsArray().get(0));
        // System.out.println(probe.getPositionsArray().get(lastT));

        // System.out.println(venus.getPositionsArray().get(0));
        // System.out.println(venus.getPositionsArray().get(lastT));

        // System.out.println();
        // System.out.println(saturn.getPositionsArray().get(0));
        // System.out.println(saturn.getPositionsArray().get(lastT));


        // System.out.println("Error on the X Coordinate = " + (Math.abs((earth.getPositionsArray().get(0).getX()) - earth.getPositionsArray().get(lastT).getX()) / (earth.getPositionsArray().get(0).getX())) * 100 + " %");
        // System.out.println("Error on the Y Coordinate = " + (Math.abs((earth.getPositionsArray().get(0).getY()) - earth.getPositionsArray().get(lastT).getY()) / (earth.getPositionsArray().get(0).getY())) * 100 + " %");
        // System.out.println("Error on the Z Coordinate = " + (Math.abs((earth.getPositionsArray().get(0).getZ()) - earth.getPositionsArray().get(lastT).getZ()) / (earth.getPositionsArray().get(0).getZ())) * 100 + " %");


        


    }
    
}
