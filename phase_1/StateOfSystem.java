package phase_1;

import java.util.ArrayList;

//Class representing the current state of the Soolar System
public class StateOfSystem {
    ArrayList <CelestialBody> bodies=new ArrayList<>();
    ArrayList<Vector> currentPositions = new ArrayList<>();
    ArrayList<Vector> currentVelocities = new ArrayList<>();
    ArrayList<Vector> tempnewvelocities = new ArrayList<>();
    ArrayList<ArrayList<Vector>> orbits = new ArrayList<>();
    int timeOfState; // Always an Integer -> T0, T1, T2... Tn
    double timestep;
    private double totalRealTimeElapsed;
    final double G = 6.6743*(Math.pow(10,-20)); // In Meters
    private ArrayList<Double> distancesToTitan = new ArrayList<Double>();
    

    /**
     * Constructor for the StateOfSystem.
     * @param timeStepInSeconds time step
     * @param bodies celestial bodies present on the Solar System
     */
    public StateOfSystem(double timeStepInSeconds, ArrayList<CelestialBody>bodies) 
    {
        for(int i=0;i<bodies.size();i++){
            currentPositions.add(bodies.get(i).getPosition());
            currentVelocities.add(bodies.get(i).getVelocity());
        }
        this.bodies = bodies;
        timeOfState = 0; // Starts at T0
        this.timestep = timeStepInSeconds;

        this.totalRealTimeElapsed = 0; // At T0, no Real Time has passed
        
    }

     // Methods below are methods type "get", "set" or "update" used to extract particular variables or set a new value for them.
    public ArrayList<Vector> getCurrentPositions(){
        return currentPositions;
    }

    public ArrayList<Vector> getCurrentVelocities(){
        return currentVelocities;
    }

    public double getTimeOfState(){
        return timeOfState;
    }

    public void setSingleVelocity(int id, Vector newVelocity)
    {
        ArrayList<Vector> newVelocities = new ArrayList<>();
        for(int i = 0; i<currentVelocities.size(); i++){
            if(i == id)
            {
                newVelocities.add(newVelocity);
            }
            else
            {
                newVelocities.add(currentVelocities.get(i));
            }
        }
        currentVelocities = newVelocities;
    }

    public void setSinglePosition(int id, Vector newPosition)
    {
        ArrayList<Vector> newPositions = new ArrayList<>();
        for(int i = 0; i<currentPositions.size(); i++){
            if(i == id)
            {
                newPositions.add(newPosition);
            }
            else
            {
                newPositions.add(currentPositions.get(i));
            }
        }
        currentPositions = newPositions;
    }

    //to print the positions and velocities
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("positions: ");
        for(int i = 0; i<currentPositions.size(); i++){
            str.append(currentPositions.get(i).toString());
            str.append(", ");
        }

        str.append("\n");
        str.append("velocities: ");
        for(int i = 0; i<currentVelocities.size(); i++){
            str.append(currentVelocities.get(i).toString());
            str.append(", ");
        }
        return str.toString();
    }

    public ArrayList<CelestialBody> getBodiesInSystem() {
        return this.bodies;
    }

    public double getTimeStepInSeconds() {
        return this.timestep;
    }

    public int getCurrentTime() {
        return this.timeOfState;
    }

    public void updateTotalTimePassed() {
        this.totalRealTimeElapsed += timestep;
    }

    public double getTotalTimePassed() {
        return this.totalRealTimeElapsed;
    }

    public void updateTimeOfState() {
        this.timeOfState = this.timeOfState + 1;
    }

}
