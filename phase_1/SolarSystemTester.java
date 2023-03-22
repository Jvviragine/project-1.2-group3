package phase_1;

import java.util.ArrayList;

public class SolarSystemTester {
    
    public static void main(String[] args) {
        
        // Create a new Solar System

        // Create the Sun
        Vector sunInitialPositions = new Vector(0, 0, 0);
        Vector sunInitialVelocity = new Vector(0, 0, 0);
        CelestialBody sun = new CelestialBody(sunInitialPositions, sunInitialVelocity, 1.9885*Math.pow(10, 30), "The Sun");

        // Create the Probe
        // Vector probeInitialPosition = new Vector((-148186906.893642), (-27823158.5715694), (33746.8987977113));
        // Vector probeInitialVelocity = new Vector(0, 0, 0);
        // CelestialBody probe = new CelestialBody(probeInitialPosition, probeInitialVelocity,  50000, 0);

        // Create Venus
        Vector venusInitialPosition = new Vector((-28216773.9426889), (103994008.541512), (3012326.64296788));
        Vector venusInitialVelocity = new Vector((-34.0236737066136), (-8.96521274688838), (1.84061735279188));
        CelestialBody venus = new CelestialBody(venusInitialPosition, venusInitialVelocity,48.685*Math.pow(10,23), "Venus");

        // Create the Earth
        Vector earthInitialPosition = new Vector((-148186906.893642), (-27823158.5715694), (33746.8987977113));
        Vector earthInitialVelocity = new Vector((5.05251577575409), (-29.3926687625899), (0.00170974277401292));
        CelestialBody earth = new CelestialBody(earthInitialPosition, earthInitialVelocity, 5.97219*Math.pow(10,24), "The Earth");

        //Create the Moon
        Vector moonInitialPosition = new Vector((-148458048.395164), (-27524868.1841142), (70233.6499287411));
        Vector moonInitialVelocity = new Vector((4.34032634654904), (-30.0480834180741), (-0.0116103535014229));
        CelestialBody moon = new CelestialBody(moonInitialPosition, moonInitialVelocity, 7.349*Math.pow(10,22), "The Moon");

        // Create Mars
        Vector marsInitialPosition = new Vector((-159116303.422552), (189235671.561057), (7870476.08522969));
        Vector marsInitialVelocity = new Vector((-17.6954469224752), (-13.4635253412947), (0.152331928200531));
        CelestialBody mars = new CelestialBody(marsInitialPosition, marsInitialVelocity, 6.4171*Math.pow(10,23), "Mars");

        // Create Jupiter
        Vector jupiterInitialPosition = new Vector((692722875.928222), (258560760.813524), (-16570817.7105996));
        Vector jupiterInitialVelocity = new Vector((-4.71443059866156), (12.8555096964427), (0.0522118126939208));
        CelestialBody jupiter = new CelestialBody(jupiterInitialPosition, jupiterInitialVelocity,189818722*Math.pow(10,19),"Jupiter");

        // Create Saturn
        Vector saturnInitialPosition = new Vector((1253801723.95465), (-760453007.810989), (-36697431.1565206));
        Vector saturnInitialVelocity = new Vector((4.46781341335014), (8.23989540475628), (-0.320745376969732));
        CelestialBody saturn = new CelestialBody(saturnInitialPosition, saturnInitialVelocity,5.6834*Math.pow(10,26), "Saturn");

        // Create Titan
        Vector titanInitialPosition = new Vector((1254501624.95946), (-761340299.067828), (-36309613.8378104));
        Vector titanInitialVelocity = new Vector((8.99593229549645), (11.1085713608453), (-2.25130986174761));
        CelestialBody titan = new CelestialBody(titanInitialPosition, titanInitialVelocity,13455.3*Math.pow(10,19), "Titan");
        
        // Collection of Celestial Bodies
        ArrayList<CelestialBody> celestialBodies = new ArrayList<CelestialBody>();
        celestialBodies.add(sun);
        //celestialBodies.add(probe);
        celestialBodies.add(venus);
        celestialBodies.add(earth);
        celestialBodies.add(moon);
        celestialBodies.add(mars);
        celestialBodies.add(jupiter);
        celestialBodies.add(saturn);
        celestialBodies.add(titan);

        

        // Creating the Solar System
        int timeStepInSeconds = 60*60*24; // 1 Day of Timestep
        StateOfSystem solarSystemState = new StateOfSystem(timeStepInSeconds, celestialBodies); // Giging it a Time Step of 
        
        // Create the Object that can Update the State of the Solar System
        EulerUpdateStateofSystem solarSystemUpdater = new EulerUpdateStateofSystem(solarSystemState);

        // Loop controlling how many TimeSteps we are going to take
        int lastT = 2190;
        for (int t = 1; t <= lastT; t++) {
            solarSystemUpdater.updateStateOfSolarSystemEuler();
        }
        
        //StateOfSystem solarSystemState = new StateOfSystem(0, null, null, null);


    }
}
