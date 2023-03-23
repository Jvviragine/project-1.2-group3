package phase_1;

import java.util.ArrayList;

public class OrbitFinder {

    /**
     * Returns the orbit of a celestial object in an array of x and y coordinates
     * @param body,bodies the body for which the orbit is to be found and the list of all bodies 
     * @return double array containing x and y coordinates of points on the planets orbit.
     */
    public static double[][] getOrbit(CelestialBody body, ArrayList<CelestialBody> bodies){
        int timeStepInSeconds;

        /*Planets with larger orbit periods will have a larger time step (one day),
          Planets with shorter orbit periods will have a smaller time step (one hour).
        */
        if(body.getPeriod()>400)timeStepInSeconds = 60 * 60 * 24;
        else timeStepInSeconds = 60 * 60;

        StateOfSystem solarSystemState = new StateOfSystem(timeStepInSeconds, bodies);  
        EulerUpdateStateofSystem solarSystemUpdater = new EulerUpdateStateofSystem(solarSystemState);

        /*Ensures that the total number of times the system is updated corresponds
        (is proportionate) with the time step.
        */
        int lastT = body.getPeriod() * ((24 * 60 * 60) / timeStepInSeconds); //period in days converted to seconds/minutes/hours
        for (int t = 1; t <= (int)lastT; t++) {
            solarSystemUpdater.updateStateOfSolarSystemEuler();
        }   

        return convertToArray(body.getPositionsArray());
    }

    /**
     * Converts a vector arraylist into a double array for use in the GUI
     * @param arraylist
     * @return converted array
     */

    public static double[][]convertToArray(ArrayList<Vector>arraylist){

        double[][]coordinates=new double[arraylist.size()][2];

        for(int i=0;i<arraylist.size();i++){
            coordinates[i][0]=arraylist.get(i).getX();
            coordinates[i][1]=arraylist.get(i).getY();
        }

        return coordinates;
    }
}
