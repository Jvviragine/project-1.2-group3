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

        // Set new Positions -> Improved Euler Method 
        for (int i = 1; i < bodiesInSystem.size(); i++) { // STARTS AT 1 BECAUSE THE SUN IS AT INDEX 0
            setNewPrecisePosition(bodiesInSystem.get(i));
        } // Now all the New Positions for all the Bodies have been Established

        solarSystem.updateTotalTimePassed(); // Increases the Total Real Time Passed by DeltaT(TimeStep Chosen)
        solarSystem.updateTimeOfState();

        System.out.println("Now our Solar System is on State T = " + solarSystem.getTimeOfState());
        System.out.println("And the Total Real Time passed = " + solarSystem.getTotalTimePassed() + " seconds");
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
        for (int i = 0; i < bodiesInSystem.size(); i++) { // STARTS AT 1 BECAUSE THE SUN IS AT INDEX 0
            
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
        for (int i = 0; i < forcesOnBody.size(); i++) { // STARTS AT 1 BECAUSE THE SUN IS AT INDEX 0
            
            netForce = netForce.add(forcesOnBody.get(i));
        }

        // The Net Force will be associated with 1 Specific Body (Stored in CelestialBody Class)
        body.setNetForce(netForce);
        System.out.println("The Force acting on " + body.getName() + " is =  " + body.getNetForce().getX() + " X; " + body.getNetForce().getY() + " Y; " + body.getNetForce().getZ() + " Z");
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


    //Euler's Update Position Method -> Tamar
    public void updatePosition(){
        //ArrayList <Vector> newpositions = new ArrayList<>();
        ArrayList <Vector> initialvelocities = new ArrayList<>();
        for(int i=0;i<bodiesInSystem.length;i++){
            Vector initialvelocity = bodiesInSystem.get(i).getVelocity();
            initialvelocities.add(initialvelocity);
        }
    
        updateVelocity();

        for(int i=0;i<bodiesInSystem.size();i++){ 
            System.out.println("Initial Position of " + bodiesInSystem.get(i).getName + " :"  + bodiesInSystem.get(i).getPosition);
            Vector incr = (initialvelocities.get(i).multi(timeStepInSeconds));
            Vector updated = bodiesInSystem.get(i).getPosition().add(incr);
            bodiesInSystem.get(i).setPosition(updated);
            bodiesInSystem.get(i).getPositionsArray().add(updated);
            //newpositions.add(updated);
            System.out.println("Final Position of " + bodiesInSystem.get(i).getName + " :"  + bodiesInSystem.get(i).getPosition);
        }
    
        //positions = newpositions;
    }

    public void updateVelocity(){
    
        ArrayList <Vector> newvelocities=new ArrayList<>();
        newvelocities.add(velocities.get(0)); //adds sun velocity first (unchanged)
    
        for(int i=1;i<velocities.size();i++){//skip sun (index 0) when calculating velocity
            System.out.println("Initial Velocity of " + bodiesInSystem.get(i).getName+" :" + bodiesInSystem.get(i).getVelocity);
            Vector netforce = (getForce(bodiesInSystem.get(i))).multi(-1);
            Vector acceleration = netforce.multi(1/bodiesInSystem.get(i).getMass());
            Vector incr = (acceleration.multi(timeStepInSeconds));
            Vector updated = bodiesInSystem.get(i).add(incr);
            bodies.get(i).setVelocity(updated);
            newvelocities.add(updated);
            System.out.println("Final Velocity of " + bodiesInSystem.get(i).getName+" :" + bodiesInSystem.get(i).getVelocity);
        }
        tempnewvelocities = newvelocities;
    }
    
    public Vector getForce(CelestialBody body){
        Vector force = new Vector();
    
        for(int i=0; i<bodies.size(); i++){
            if(bodies.get(i)!=body){ 
                //own body should not be added to net force 
                Vector bodyposition = body.getPosition(); //Xi
                Vector otherposition = bodies.get(i).getPosition(); //Xj
    
                double masses = body.getMass()*bodies.get(i).getMass(); //Mi*Mj
    
                Vector numerator = bodyposition.sub(otherposition); //Xi-Xj
                double dist = bodyposition.dist(otherposition); //||Xi-Xj||
                double denominator = Math.pow(dist,2); //||Xi-Xj||^2
    
                Vector unitVector = new Vector(numerator.getX()/dist,numerator.getY()/dist,numerator.getZ()/dist); // numerator/||Xi-Xj||
    
                double gxm = G*masses; //G*MiMj
    
                force = force.add(unitVector.multi(gxm/denominator)); //net force
            }
        }
        return force;
    }

}
