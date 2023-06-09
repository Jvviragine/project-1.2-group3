package phase_1;

import java.util.ArrayList;

// This Class will be responsible for Updating the State of the Solar System
public class UpdateStateOfSystem {
    
    // Instance Fields
    private StateOfSystem solarSystem;
    private ArrayList<CelestialBody> bodiesInSystem = new ArrayList<CelestialBody>();
    private double timeStepInSeconds;
    private int currentTimeOfState;
    final double G = 6.6743*(Math.pow(10,-20));
    private ArrayList<Double> distancesToTitan = new ArrayList<Double>();
    private double radiusOfTitan = 2.575;
    private double c = 0.0*radiusOfTitan;
    private double yearInSec = 60*60*24*365;
    static Vector positionWhenReached = new Vector();

    // Constructor 
    public UpdateStateOfSystem(StateOfSystem solarSystem) {

        this.solarSystem = solarSystem;
        bodiesInSystem = this.solarSystem.getBodiesInSystem();
        timeStepInSeconds = this.solarSystem.getTimeStepInSeconds();
        this.currentTimeOfState = this.solarSystem.getCurrentTime();
    }

    // UPDATING FUNCTIONS

    // Method to just calculate the Values for T0 -> Force and Acceleration
    public void calculateMissingValuesForT0() {

        //System.out.println("State of our Solar System at T0: " + "\n");

        for (int i = 1; i < bodiesInSystem.size(); i++) { // STARTS AT 1 BECAUSE THE SUN IS AT INDEX 0
            setNetForceActingOnABody(bodiesInSystem.get(i));
        } 

        for (int i = 1; i < bodiesInSystem.size(); i++) {
            setNetAccelerationActingOnABody(bodiesInSystem.get(i));
        }
        //System.out.println("The Distance from the Probe to Titan = " + calculateDistanceFromProbeToTitan(bodiesInSystem.get(bodiesInSystem.size()-1), bodiesInSystem.get(7)) + " KM"); 
        //System.out.println("And the Total Real Time passed = " + solarSystem.getTotalTimePassed() + " seconds = " + (solarSystem.getTotalTimePassed() / (60 * 60 * 24 * 365)) + " Years");
        // Now, for T0, we also have NetForce and NetAcceleration
    }

    // Method Responsible for Updating the State of Our Solar System -> From Tn to T1 -> João's Methods
    public void updateStateOfSolarSystem() {

        solarSystem.updateTimeOfState();
        // System.out.println("Now our Solar System is on State T = " + solarSystem.getTimeOfState() + "\n");

        // Set new Positions -> Improved Euler Method 
        for (int i = 1; i < bodiesInSystem.size(); i++) { // STARTS AT 1 BECAUSE THE SUN IS AT INDEX 0
            setNewPosition(bodiesInSystem.get(i));
        } // Now all the New Positions for all the Bodies have been Established

        // Calculate the Net Forces for Each Body in Our Solar System -> We have to decide about the SUN - Doesn't get affected by the Forces
        for (int i = 1; i < bodiesInSystem.size(); i++) { // STARTS AT 1 BECAUSE THE SUN IS AT INDEX 0
            setNetForceActingOnABody(bodiesInSystem.get(i));
        } // Now we have all Updated Net Forces in the Bodies of the System

        // Calculates the Net Acceleration for Each Body in our Solar System
        for (int i = 1; i < bodiesInSystem.size(); i++) { // STARTS AT 1 BECAUSE THE SUN IS AT INDEX 0
            setNetAccelerationActingOnABody(bodiesInSystem.get(i));
        } // Now we have all Updated Net Accelerations in the Bodies of the System

        // Sets the New Velocities for the New State in which the Solar System is in
        for (int i = 1; i < bodiesInSystem.size(); i++) { // STARTS AT 1 BECAUSE THE SUN IS AT INDEX 0
            setNewVelocity(bodiesInSystem.get(i));
        } // Now we have the Initial Velocity for this new State 

        solarSystem.updateTotalTimePassed(); // Increases the Total Real Time Passed by DeltaT(TimeStep Chosen)

        // WATCH OUT FOR THE INDEX OF PROBE AND TITAN
        // System.out.println("The Distance from the Probe to Titan = " + calculateDistanceFromProbeToTitan(bodiesInSystem.get(10), bodiesInSystem.get(7)) + " KM"); 
        // System.out.println("Has the Distance between the Probe and Titan decreased? " + hasDistanceBetweenProbeAndTitanDecreased(bodiesInSystem.get(8), bodiesInSystem.get(7)));
        // System.out.println("And the Total Real Time passed = " + solarSystem.getTotalTimePassed() + " seconds = " + (solarSystem.getTotalTimePassed() / (60 * 60 * 24 * 365)) + " Years");
        // System.out.println("\n");
    }

    // Euler Solvers -> All the Calculation Functions -> Joãos Version
    public void setNetForceActingOnABody(CelestialBody body) {

        // Stores all the Forces acting on a Certain Celestial Body
        ArrayList<Vector> forcesOnBody = new ArrayList<Vector>();

        // Stores the Net Force acting on a Celestial Body
        Vector netForce = new Vector();

        // Go through every pair of Forces
        for (int i = 0; i < bodiesInSystem.size(); i++) { // STARTS AT 1 BECAUSE THE SUN IS AT INDEX 0
            
            // We have to Check if it's not the Current Body
            CelestialBody otherBody = bodiesInSystem.get(i);
            if (otherBody != body) {

                Vector forceOnBody = new Vector();
                double massesProduct = otherBody.getMass() * body.getMass();
                Vector differenceOfPositions = body.getPosition().sub(otherBody.getPosition());
                double denominatorCubed = Math.pow(differenceOfPositions.len(), 3);

                double scalar = (G * massesProduct) / denominatorCubed;
                forceOnBody = differenceOfPositions.multi(scalar);
                forcesOnBody.add(forceOnBody);
            }
        }

        // Now that we have all the Forces on a Body, we can compute the Resultant Vector
        for (int i = 0; i < forcesOnBody.size(); i++) { // STARTS AT 1 BECAUSE THE SUN IS AT INDEX 0
            
            netForce = netForce.add(forcesOnBody.get(i));
        }

        // The Net Force will be associated with 1 Specific Body (Stored in CelestialBody Class)
        body.setNetForce(netForce.multi(-1));
        //System.out.println("The Force acting on " + body.getName() + " is =  " + body.getNetForce().getX() + " X; " + body.getNetForce().getY() + " Y; " + body.getNetForce().getZ() + " Z");
    }

    // Find the Net Acceleration for Each Componenet
    public void setNetAccelerationActingOnABody(CelestialBody body) {
        double bodyMass = body.getMass();
        Vector netAcceleration = body.getNetForce().multi(1 / bodyMass);
        body.setNetAcceleration(netAcceleration);
    }

    // Find the Final Vn, so, for example, if the Velocity was V0, it will Final V1
    public void setNewVelocity(CelestialBody body) {
        Vector previousVelocity = body.getVelocity();
        Vector currentAcceleration = body.getNetAcceleration();
        Vector newVelocity = previousVelocity.add(currentAcceleration.multi(timeStepInSeconds)); // Time Step is the Step Size for Euler's Method
        body.setVelocity(newVelocity);
        body.addNewVelocity(newVelocity);
    }

    // Find the new Position Vector -> Purely using Euler's Method
    public void setNewPosition(CelestialBody body) {

        // This Method would have to be called BEFORE SetNewVelocity (same as Tamar's)
        Vector previousVelocity = body.getVelocity(); 
        Vector initialPosition = body.getPosition();

        // Assumes that the Velocity does NOT change
        Vector newPosition = initialPosition.add(previousVelocity.multi(timeStepInSeconds)); // A purely LINEAR Approximation (considers the velocity Constant during tn -> tn+1)
        body.setPosition(newPosition);
        body.addNewPosition(newPosition);
    }

    // Finds the New Position by Using a Better Approximation than simple Euler's Version
    public void setNewPrecisePosition(CelestialBody body) {

        // This Method would call SetNewVelocity (or we store on a body the previous velocities as well)
        Vector previousVelocity = body.getVelocity(); // Vn
        setNewVelocity(body); // At this time, the Final Velocity is Calculated
        Vector finalVelocity = body.getVelocity(); // Already gets the new one calculated on the previous step

        Vector averageVelocity = (previousVelocity.add(finalVelocity)).multi(1 / 2); // (Vi + Vf) / 2

        Vector initialPosition = body.getPosition();

        Vector newPosition = initialPosition.add(averageVelocity.multi(timeStepInSeconds)); //How it increases the Precision
        body.setPosition(newPosition);
    }

    public double calculateDistanceFromProbeToTitan(CelestialBody probe, CelestialBody titan) {

        double distance = (probe.getPosition()).dist(titan.getPosition());
        distancesToTitan.add(distance);
        return distance;
    }

    // Method to compare if the Previous Distance has Decreased
    public boolean hasDistanceBetweenProbeAndTitanDecreased(CelestialBody probe, CelestialBody titan) {

        int currentT = solarSystem.getCurrentTime();

        double previousDistance = distancesToTitan.get(currentT - 1);
        double currentDistance = distancesToTitan.get(currentT);

        return (currentDistance < previousDistance);
    }

    // Method that checks whether we reached Titan
    public boolean reachedTitan(CelestialBody probe, CelestialBody titan, double stateTime){
        if(probe.getPosition().dist(titan.getPosition()) < radiusOfTitan || stateTime == yearInSec){
            positionWhenReached = probe.getPosition();
            return true;
        }
        return false;
    }

    public static Vector getPositionWhenReached(){
        return positionWhenReached;
    }
}
