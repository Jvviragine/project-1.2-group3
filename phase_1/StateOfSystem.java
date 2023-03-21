package phase_1;

import java.util.ArrayList;

public class StateOfSystem {
    ArrayList <CelestialBody> bodies=new ArrayList<>();
    ArrayList<Vector> positions = new ArrayList<>();
    ArrayList<Vector> velocities = new ArrayList<>();
    ArrayList<Vector> tempnewvelocities = new ArrayList<>();
    double timeOfState;
    double timestep;
    final double G = 6.6743*(Math.pow(10,-20));
    

    //constructor
    public StateOfSystem(double t0, ArrayList<CelestialBody>bodies) {
        for(int i=0;i<bodies.size();i++){
            positions.add(bodies.get(i).getPosition());
            velocities.add(bodies.get(i).getVelocity());
        }
        this.bodies = bodies;
        timeOfState = t0;
        timestep=0.1; //change to relevant timestep;
    }
    
    public void currentState(int period){
        for(int i=0;i<period;i++){
            updateVelocity();
            updatePosition();
            velocities=tempnewvelocities;
        }
    }

    public ArrayList<Vector> getPositions(){
        return positions;
    }

    public ArrayList<Vector> getVelocities(){
        return velocities;
    }

    public double getTimeOfState(){
        return timeOfState;
    }

    public void setTime(double time){
        timeOfState = time;
    }

    public void updatePosition(){
        System.out.println("Start Position:" + positions);
        ArrayList <Vector> newpositions=new ArrayList<>();
        for(int i=0;i<positions.size();i++){ 
            Vector incr=(velocities.get(i)).multi(timestep);
            Vector updated =positions.get(i).add(incr);
            bodies.get(i).setPosition(updated);
            newpositions.add(updated);
        }
        positions=newpositions;
        System.out.println("End Position:" + positions);
    }

    public void updateVelocity(){
        System.out.println("Start Velocity:" + velocities);
        ArrayList <Vector> newvelocities=new ArrayList<>();
        newvelocities.add(velocities.get(0));
        for(int i=1;i<velocities.size();i++){//skip sun when calculating velocity
            Vector netforce=getForce(bodies.get(i)).multi(-1);
            Vector acceleration=netforce.multi(1/bodies.get(i).getMass());//acceleration
            Vector incr=(acceleration.multi(timestep));
            Vector updated =velocities.get(i).add(incr);
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

    public Vector getNetForceActingOnABody(CelestialBody body) {

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

        return netForce;
    }

    public void setSingleVelocity(int id, Vector newVelocity){
        ArrayList<Vector> newVelocities = new ArrayList<>();
        for(int i = 0; i<velocities.size(); i++){
            if(i == id){
                newVelocities.add(newVelocity);
            }else{
                newVelocities.add(velocities.get(i));
            }
        }
        velocities = newVelocities;
    }

    public void setSinglePosition(int id, Vector newPosition){
        ArrayList<Vector> newPositions = new ArrayList<>();
        for(int i = 0; i<positions.size(); i++){
            if(i == id){
                newPositions.add(newPosition);
            }else{
                newPositions.add(positions.get(i));
            }
        }
        positions = newPositions;
    }

    public String toString(){//to print the positions and velocities
        StringBuilder str = new StringBuilder();
        str.append("positions: ");
        for(int i = 0; i<positions.size(); i++){
            str.append(positions.get(i).toString());
            str.append(", ");
        }

        str.append("\n");
        str.append("velocities: ");
        for(int i = 0; i<velocities.size(); i++){
            str.append(velocities.get(i).toString());
            str.append(", ");
        }
        return str.toString();
    }

}
