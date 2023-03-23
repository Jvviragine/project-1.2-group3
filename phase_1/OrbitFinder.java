package phase_1;

import java.util.ArrayList;

public class OrbitFinder {

    CelestialBody body;
    ArrayList<CelestialBody>bodies;

    public OrbitFinder(CelestialBody body, ArrayList<CelestialBody> bodies){
        this.body=body;
        this.bodies=bodies;
    }

    public ArrayList<Vector> getOrbit(){
        int timeStepInSeconds;
        if(body.getPeriod()>400)timeStepInSeconds=60*60*24;
        else timeStepInSeconds=60*60;
        StateOfSystem solarSystemState = new StateOfSystem(timeStepInSeconds, bodies); 
        
        EulerUpdateStateofSystem solarSystemUpdater = new EulerUpdateStateofSystem(solarSystemState);

        int lastT = body.getPeriod()*((24*60*60)/timeStepInSeconds); //period in days converted to seconds/minutes/hours
        for (int t = 1; t <= (int)lastT; t++) {
            solarSystemUpdater.updateStateOfSolarSystemEuler();
        }   
        
        System.out.println(body.getPositionsArray());
        return body.getPositionsArray();

    }
}
