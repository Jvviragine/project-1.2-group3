package phase_1;

import java.util.ArrayList;

// This is the Class containing all the actions for running the simulation

public class Main {
    public double[][] orbitOfVenus;
    public double[][] orbitOfEarth;
    public double[][] orbitOfMoon;
    public double[][] orbitOfMars;
    public double[][] orbitOfJupiter;
    public double[][] orbitOfSaturn;
    public double[][] orbitOfTitan;
    public ArrayList<ArrayList<Double>> probePath = new ArrayList<>();
    CelestialBody sun;
    CelestialBody venus;
    CelestialBody earth;
    CelestialBody moon;
    CelestialBody mars;
    CelestialBody jupiter;
    CelestialBody saturn;
    CelestialBody titan;


    public Main() {

        // Create the Sun
        Vector sunInitialPositions = new Vector(0, 0, 0);
        Vector sunInitialVelocity = new Vector(0, 0, 0);
        sun = new CelestialBody(sunInitialPositions, sunInitialVelocity, 1.9885*Math.pow(10, 30), "The Sun");

        // Create Venus
        Vector venusInitialPosition = new Vector((-28216773.9426889), (103994008.541512), (3012326.64296788));
        Vector venusInitialVelocity = new Vector((-34.0236737066136), (-8.96521274688838), (1.84061735279188));
        venus = new CelestialBody(venusInitialPosition, venusInitialVelocity,48.685*Math.pow(10,23),225,"Venus");

        // Create the Earth
        Vector earthInitialPosition = new Vector((-148186906.893642), (-27823158.5715694), (33746.8987977113));
        Vector earthInitialVelocity = new Vector((5.05251577575409), (-29.3926687625899), (0.00170974277401292));
        earth = new CelestialBody(earthInitialPosition, earthInitialVelocity, 5.97219*Math.pow(10,24),366, "The Earth");

        //Create the Moon
        Vector moonInitialPosition = new Vector((-148458048.395164), (-27524868.1841142), (70233.6499287411));
        Vector moonInitialVelocity = new Vector((4.34032634654904), (-30.0480834180741), (-0.0116103535014229));
        moon = new CelestialBody(moonInitialPosition, moonInitialVelocity, 7.349*Math.pow(10,22),1, "The Moon");

        // Create Mars
        Vector marsInitialPosition = new Vector((-159116303.422552), (189235671.561057), (7870476.08522969));
        Vector marsInitialVelocity = new Vector((-17.6954469224752), (-13.4635253412947), (0.152331928200531));
        mars = new CelestialBody(marsInitialPosition, marsInitialVelocity, 6.4171*Math.pow(10,23),687, "Mars");

        // Create Jupiter
        Vector jupiterInitialPosition = new Vector((692722875.928222), (258560760.813524), (-16570817.7105996));
        Vector jupiterInitialVelocity = new Vector((-4.71443059866156), (12.8555096964427), (0.0522118126939208));
        jupiter = new CelestialBody(jupiterInitialPosition, jupiterInitialVelocity,189818722*Math.pow(10,19),4333, "Jupiter");

        // Create Saturn
        Vector saturnInitialPosition = new Vector((1253801723.95465), (-760453007.810989), (-36697431.1565206));
        Vector saturnInitialVelocity = new Vector((4.46781341335014), (8.23989540475628), (-0.320745376969732));
        saturn = new CelestialBody(saturnInitialPosition, saturnInitialVelocity,5.6834*Math.pow(10,26),10759, "Saturn");

        // Create Titan
        Vector titanInitialPosition = new Vector((1254501624.95946), (-761340299.067828), (-36309613.8378104));
        Vector titanInitialVelocity = new Vector((8.99593229549645), (11.1085713608453), (-2.25130986174761));
        titan = new CelestialBody(titanInitialPosition, titanInitialVelocity,13455.3*Math.pow(10,19),16, "Titan");

        // Create Uranus
        Vector uranusInitialPositions = new Vector((1958732435.99338), (2191808553.21893 ), (-17235283.8321992));
        Vector uranusInitialVelocity = new Vector((-5.12766216337626), (4.22055347264457), (0.082119033640306386));
        CelestialBody uranus = new CelestialBody(uranusInitialPositions, uranusInitialVelocity, 13455.3*Math.pow(10,19), "Uranus");

        // Create Neptune
        Vector neptuneInitialPositions = new Vector((4454487339.09447), (-397895128.763904), (-94464151.3421107));
        Vector neptuneInitialVelocity = new Vector((0.447991656952326), (5.44610697514907), (-0.122638125365954102));
        CelestialBody neptune = new CelestialBody(neptuneInitialPositions, neptuneInitialVelocity, 13455.3*Math.pow(10,19), "Neptune");

        // Create the Probe
        Vector probeInitialPosition = new Vector(0, 0, 0);
        Vector probeInitialVelocity = new Vector(0, 0, 0);
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
        probe.setPosition(RunMe.initPos);
        probe.setVelocity(RunMe.initVelo);

        int timeStepInSeconds = 60 * 60; // 1 hour of timestep
        StateOfSystem solarSystemState3 = new StateOfSystem(timeStepInSeconds, celestialBodies); // Giging it a Time Step of
        
        // Create the Object that can Update the State of the Solar System
        UpdateStateOfSystem solarSystemUpdater3 = new UpdateStateOfSystem(solarSystemState3);
        solarSystemUpdater3.calculateMissingValuesForT0();


        double min = probeInitialPosition.dist(titanInitialPosition);
        double minTime = 0;
        int i = 0;
        String minPos = "";
        String minPost = "";

        //Updating the Solar System until we reach Titan or a year has passed
        while(!solarSystemUpdater3.reachedTitan(probe, titan, solarSystemState3.getTotalTimePassed())){
            probePath.add(new ArrayList<>());
            solarSystemUpdater3.updateStateOfSolarSystem();
            probePath.get(i).add(probe.getPosition().getX());
            probePath.get(i).add(probe.getPosition().getY());
            i++;
            if(probe.getPosition().dist(titan.getPosition())<min){
                min = probe.getPosition().dist(titan.getPosition());
                minPos = probe.getPosition().toString();
                minPost = titan.getPosition().toString();
                minTime = solarSystemState3.getTotalTimePassed();
            }
        }
        System.out.println("dist " + min);
        System.out.println("Probe pos " + minPos);
        System.out.println("Titan pos " + minPost);
        
        if(solarSystemUpdater3.calculateDistanceFromProbeToTitan(probe, titan) <= 2575) {
            System.out.println("Minimum distance to titan: " + min);
            System.out.println("Titan reached at: " + minTime);
            System.out.println("When probe reached Titan it had positions: " + solarSystemUpdater3.getPositionWhenReached().toString());
        }

        // int positionsSize = earth.getPositionsArray().size()-1;
        // System.out.println("Error on the X Coordinate = " + (Math.abs((earth.getPositionsArray().get(0).getX()) - earth.getPositionsArray().get(positionsSize).getX()) / (earth.getPositionsArray().get(0).getX())) * 100 + " %");
        // System.out.println("Error on the Y Coordinate = " + (Math.abs((earth.getPositionsArray().get(0).getY()) - earth.getPositionsArray().get(positionsSize).getY()) / (earth.getPositionsArray().get(0).getY())) * 100 + " %");
        // System.out.println("Error on the Z Coordinate = " + (Math.abs((earth.getPositionsArray().get(0).getZ()) - earth.getPositionsArray().get(positionsSize).getZ()) / (earth.getPositionsArray().get(0).getZ())) * 100 + " %");
        

    }

}