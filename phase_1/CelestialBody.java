package phase_1;

import java.util.ArrayList;

public class CelestialBody{
    private Vector position;
    private Vector velocity;
    private double mass;
    private double radius;
    private String name;

    // Added more Instance Fields
    private Vector netForce;
    private Vector netAcceleration;

    // Have a History of all the Positions and Velocities
    private ArrayList<Vector> positions = new ArrayList<Vector>();
    private ArrayList<Vector> velocities = new ArrayList<Vector>();
    

    public CelestialBody(Vector initialposition,Vector initialvelocity,double mass, String name){
        this.position=initialposition.multi(1000);
        this.velocity=initialvelocity.multi(1000);
        this.mass=mass;
        this.radius=radius;
        this.positions.add(initialposition);
        this.velocities.add(initialvelocity);
        this.name = name;
    }
    
    public double getMass(){
        return mass;
    }

    public void setPosition(Vector position){
        this.position=position;
    }

    public Vector getPosition(){
        return position;
    }

    public void setVelocity(Vector velocity){
        this.velocity=velocity;
    }

    public Vector getVelocity(){
        return velocity;
    }

    public double getRadius(){
        return radius;
    }

    public void setNetForce(Vector netForce) {
        this.netForce = netForce;
    }

    public Vector getNetForce() {
        return this.netForce;
    }

    public void setNetAcceleration(Vector netAcceleration) {
        this.netAcceleration = netAcceleration;
    }

    public Vector getNetAcceleration() {
        return this.netAcceleration;
    }

    public ArrayList<Vector> getPositionsArray() {
        return this.positions;
    }

    public ArrayList<Vector> getVelocitiesArray() {
        return this.velocities;
    }

    public String getName() {
        return this.name;
    }

    public void addNewPosition(Vector newPosition) {
        positions.add(newPosition);
    }

    public void addNewVelocity(Vector newVelocity) {
        velocities.add(newVelocity);
    }

}