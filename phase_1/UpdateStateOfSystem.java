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

    // Constructor 
    public UpdateStateOfSystem(StateOfSystem solarSystem) {

        this.solarSystem = solarSystem;
        bodiesInSystem = this.solarSystem.getBodiesInSystem();
        timeStepInSeconds = this.solarSystem.getTimeStepInSeconds();
        this.currentTimeOfState = this.solarSystem.getCurrentTime();
    }

    // UPDATING FUNCTIONS

    // Method Responsible for Updating the State of Our Solar System -> From Tn to T1 -> João's Methods
    public void updateStateOfSolarSystem() {

        // Calculate the Net Forces for Each Body in Our Solar System -> We have to decide about the SUN - Doesn't get affected by the Forces
        for (int i = 0; i < bodiesInSystem.size(); i++) {
            setNetForceActingOnABody(bodiesInSystem.get(i));
        } // Now we have all Updated Net Forces in the Bodies of the System

        // Calculates the Net Acceleration for Each Body in our Solar System
        for (int i = 0; i < bodiesInSystem.size(); i++) {
            setNetAccelerationActingOnABody(bodiesInSystem.get(i));
        } // Now we have all Updated Net Accelerations in the Bodies of the System
        
        // Sets the New Velocities for the New State in which the Solar System is in
        for (int i = 0; i < bodiesInSystem.size(); i++) {
            setNewVelocity(bodiesInSystem.get(i));
        } // Now we have the Initial Velocity for this new State 

        // Set new Positions -> Improved Euler Method 
        for (int i = 0; i < bodiesInSystem.size(); i++) {
            setNewPrecisePosition(bodiesInSystem.get(i));
        } // Now all the New Positions for all the Bodies have been Established

        System.out.println("Now our Solar Syste is on State T = " + currentTimeOfState);
    }

    // Method Responsible for Updating the State of Our Solar System -> From Tn to T1 -> Tamar's Methods
    public void updateStateOfSolarSystemEuler() {

    }

    // Euler Solvers -> All the Calculation Functions -> Joãos Version
    public void setNetForceActingOnABody(CelestialBody body) {

        // Stores all the Forces acting on a Certain Celestial Body
        ArrayList<Vector> forcesOnBody = new ArrayList<Vector>();

        // Stores the Net Force acting on a Celestial Body
        Vector netForce = new Vector();

        // Go through every pair of Forces
        for (int i = 0; i < bodiesInSystem.size(); i++) {
            
            // We have to Check if it's not the Current Body
            CelestialBody otherBody = bodiesInSystem.get(i);
            if (bodiesInSystem.get(i) != body) {

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
        for (int i = 0; i < forcesOnBody.size(); i++) {
            
            netForce = netForce.add(forcesOnBody.get(i));
        }

        // The Net Force will be associated with 1 Specific Body (Stored in CelestialBody Class)
        body.setNetForce(netForce);
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
    }

    // Find the new Position Vector -> Purely using Euler's Method
    public void setNewPosition(CelestialBody body) {

        // This Method would have to be called BEFORE SetNewVelocity (same as Tamar's)
        Vector previousVelocity = body.getVelocity(); 
        Vector initialPosition = body.getPosition();

        Vector newPosition = initialPosition.add(previousVelocity.multi(timeStepInSeconds)); // A purely LINEAR Approximation (considers the velocity Constant during tn -> tn+1)
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

}
