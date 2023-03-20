package phase_1;

import java.util.ArrayList;

public class StateOfSystem {

    ArrayList<Vector> positions = new ArrayList<>();
    ArrayList<Vector> velocities = new ArrayList<>();
    double timeOfState;

    //constructor
    public StateOfSystem(double t0, ArrayList<Vector> positions, ArrayList<Vector> velocities) {
        this.positions = positions;
        this.velocities = velocities;
        timeOfState = t0;
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
