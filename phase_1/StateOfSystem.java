package phase_1;

import java.util.ArrayList;

public class StateOfSystem {
    ArrayList <CelestialBody> bodies=new ArrayList<>();
    ArrayList<Vector> positions = new ArrayList<>();
    ArrayList<Vector> velocities = new ArrayList<>();
    double timeOfState;
    double timestep;
    final double G = 6.6743*(Math.pow(10,-20));
    

    //constructor
    public StateOfSystem(double t0, ArrayList<CelestialBody>bodies) {
        this.positions = positions;
        this.velocities = velocities;
        this.bodies = bodies;
        timeOfState = t0;
        timestep=0.1; //change to relevant timestep;
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
        ArrayList <Vector> newpositions=new ArrayList<>();
        for(int i=0;i<positions.size();i++){ //skip sun when calculating position
            Vector incr=(velocities.get(i)).multi(timestep);
            Vector updated =positions.get(i).add(incr);
            bodies.get(i).setPosition(updated);
            newpositions.add(updated);
        }
        positions=newpositions;
    }

    public void updateVelocity(){
        ArrayList <Vector> newvelocities=new ArrayList<>();
        for(int i=1;i<positions.size();i++){//skip sun when calculating velocity
            Vector force=getForce(bodies.get(i));
            Vector acceleration=force.multi(bodies.get(i).getMass());//acceleration
            Vector incr=(velocities.get(i)).multi(timestep);
            Vector updated =positions.get(i).add(incr);


            bodies.get(i).setVelocity(updated);
            newvelocities.add(updated);
        }
        positions=newvelocities;
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
                force=unitVector.multi(gxm/denominator);
            }
        }
        return force;
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
