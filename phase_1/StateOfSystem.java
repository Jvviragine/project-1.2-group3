package phase_1;

import java.util.ArrayList;

public class StateOfSystem {
    ArrayList <CelestialBody> bodies=new ArrayList<>();
    ArrayList<Vector> currentPositions = new ArrayList<>();
    ArrayList<Vector> currentVelocities = new ArrayList<>();
    ArrayList<Vector> tempnewvelocities = new ArrayList<>();
    ArrayList<ArrayList<Vector>> orbits = new ArrayList<>();
    int timeOfState; // Always an Integer -> T0, T1, T2... Tn
    double timestep;
    final double G = 6.6743*(Math.pow(10,-20));
    

    //constructor
    public StateOfSystem(double timeStepInSeconds, ArrayList<CelestialBody>bodies) {
        for(int i=0;i<bodies.size();i++){
            currentPositions.add(bodies.get(i).getPosition());
            currentVelocities.add(bodies.get(i).getVelocity());
        }
        this.bodies = bodies;
        timeOfState = 0; // Starts at T0
        this.timestep = timeStepInSeconds;
        
    }
    
    public void currentState(int period, int stepTime){
        timestep = stepTime;
        for(int i = 1;i<bodies.size();i++){
            ArrayList<Vector> orbit = new ArrayList<>();
            orbits.add(orbit);
            orbits.get(i-1).add(bodies.get(i).getPosition());
        }
        for(int i=0;i<period;i++){
            updateVelocity();
            updatePosition();
            currentVelocities=tempnewvelocities;
            for(int j = 0; j<orbits.size();j++){
                orbits.get(j).add(bodies.get(j+1).getPosition());
            }
        }
        //System.out.println(orbits.get(0).toString());
        //System.out.println(orbits.get(0).size());
    }

    public ArrayList<Vector> getCurrentPositions(){
        return currentPositions;
    }

    public ArrayList<Vector> getCurrentVelocities(){
        return currentVelocities;
    }

    public double getTimeOfState(){
        return timeOfState;
    }

    // Why this Method?

    // public void setTime(double time){ 
    //     timeOfState = time;
    // }

    public void updatePosition(){
        System.out.println("Start Position:" + currentPositions.get(1));
        ArrayList <Vector> newpositions=new ArrayList<>();
        for(int i=0;i<currentPositions.size();i++){ 
            Vector incr=(currentVelocities.get(i)).multi(timestep);
            Vector updated =currentPositions.get(i).add(incr);
            bodies.get(i).setPosition(updated);
            newpositions.add(updated);
        }
        currentPositions=newpositions;
        System.out.println("End Position:" + currentPositions.get(1));
    }

    public void updateVelocity(){
        System.out.println("Start Velocity:" + currentVelocities);
        ArrayList <Vector> newvelocities=new ArrayList<>();
        newvelocities.add(currentVelocities.get(0));
        for(int i=1;i<currentVelocities.size();i++){//skip sun when calculating velocity
            Vector netforce=getForce(bodies.get(i)).multi(-1);
            Vector acceleration=netforce.multi(1/bodies.get(i).getMass());//acceleration
            Vector incr=(acceleration.multi(timestep));
            Vector updated =currentVelocities.get(i).add(incr);
            bodies.get(i).setVelocity(updated);
            newvelocities.add(updated);
        }
        tempnewvelocities = newvelocities;
        System.out.println("End Velocity:" + tempnewvelocities);
    }

    public Vector getForce(CelestialBody body){
        Vector force=new Vector();
        for(int i=0;i<bodies.size();i++){
            if(bodies.get(i)!=body){
                Vector bodyposition=body.getPosition();
                Vector otherposition=bodies.get(i).getPosition();
                double masses=body.getMass()*bodies.get(i).getMass();
                Vector numerator=bodyposition.sub(otherposition);
                double dist=bodyposition.dist(otherposition);
                double denominator=Math.pow(dist,2);
                Vector unitVector=new Vector(numerator.getX()/dist,numerator.getY()/dist,numerator.getZ()/dist);
                double gxm=G*masses;
                force=force.add(unitVector.multi(gxm/denominator));
            }
        }
        return force;
    }

    public void setNetForceActingOnABody(CelestialBody body) {

        // Stores all the Forces acting on a Certain Celestial Body
        ArrayList<Vector> forcesOnBody = new ArrayList<Vector>();

        // Stores the Net Force acting on a Celestial Body
        Vector netForce = new Vector();

        // Go through every pair of Forces
        for (int i = 0; i < bodies.size(); i++) {
            
            // We have to Check if it's not the Current Body
            CelestialBody otherBody = bodies.get(i);
            if (bodies.get(i) != body) {

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
        Vector newVelocity = previousVelocity.add(currentAcceleration.multi(timestep)); // Time Step is the Step Size for Euler's Method
        body.setVelocity(newVelocity);
    }

    // Find the new Position Vector -> Purely using Euler's Method
    public void setNewPosition(CelestialBody body) {

        // This Method would have to be called BEFORE SetNewVelocity (same as Tamar's)
        Vector previousVelocity = body.getVelocity(); 
        Vector initialPosition = body.getPosition();

        Vector newPosition = initialPosition.add(previousVelocity.multi(timestep)); // A purely LINEAR Approximation (considers the velocity Constant during tn -> tn+1)
    }

    // Finds the New Position by Using a Better Approximation than simple Euler's Version
    public void setNewPrecisePosition(CelestialBody body) {

        // This Method would call SetNewVelocity (or we store on a body the previous velocities as well)
        Vector previousVelocity = body.getVelocity(); // Vn
        setNewVelocity(body); // At this time, the Final Velocity is Calculated
        Vector finalVelocity = body.getVelocity(); // Already gets the new one calculated on the previous step

        Vector averageVelocity = (previousVelocity.add(finalVelocity)).multi(1 / 2); // (Vi + Vf) / 2

        Vector initialPosition = body.getPosition();

        Vector newPosition = initialPosition.add(averageVelocity.multi(timeOfState)); //How it increases the Precision
        body.setPosition(newPosition);
    }

    public void setSingleVelocity(int id, Vector newVelocity){
        ArrayList<Vector> newVelocities = new ArrayList<>();
        for(int i = 0; i<currentVelocities.size(); i++){
            if(i == id){
                newVelocities.add(newVelocity);
            }else{
                newVelocities.add(currentVelocities.get(i));
            }
        }
        currentVelocities = newVelocities;
    }

    public void setSinglePosition(int id, Vector newPosition){
        ArrayList<Vector> newPositions = new ArrayList<>();
        for(int i = 0; i<currentPositions.size(); i++){
            if(i == id){
                newPositions.add(newPosition);
            }else{
                newPositions.add(currentPositions.get(i));
            }
        }
        currentPositions = newPositions;
    }

    public String toString(){//to print the positions and velocities
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

}
