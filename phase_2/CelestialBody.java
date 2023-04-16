package phase_2;

import java.util.ArrayList;

/**
 * Class CelestialBody represents a body in the Solar System.
 */
public class CelestialBody{
    private Vector position;
    private Vector velocity;
    private double mass;
    private double radius;
    private int period;
    private String name;
    private Vector netForce;
    private Vector netAcceleration;

    // Have a History of all the Positions and Velocities
    private ArrayList<Vector> positions = new ArrayList<Vector>();
    private ArrayList<Vector> velocities = new ArrayList<Vector>();
    
    /**
     * First constructor for the Celestial Body.
     * @param initialposition initial position for the body, Vector
     * @param initialvelocity initial velocity for the body, Vector
     * @param mass mass of the body, double
     * @param name name of the vody, String
     */
    public CelestialBody(Vector initialposition,Vector initialvelocity,double mass, String name){
        this.position=initialposition;
        this.velocity=initialvelocity;
        this.mass=mass;
        this.radius=radius;
        this.positions.add(initialposition);
        this.velocities.add(initialvelocity);
        this.name = name;
    }

    /**
     * Second constructor for the Celestial body, takes also the bodies full orbit period as an extra parameter.
     * @param initialposition 
     * @param initialvelocity
     * @param mass
     * @param period period of time in which the body revolves fully around the Sun, represented in seconds, int
     * @param name
     */
    public CelestialBody(Vector initialposition,Vector initialvelocity,double mass, int period, String name){
        this.position=initialposition;
        this.velocity=initialvelocity;
        this.mass=mass;
        this.radius=radius;
        this.positions.add(initialposition);
        this.velocities.add(initialvelocity);
        this.name = name;
        this.period=period;
    }
    
    // Methods below are methods type "get" or "set" used to extract particular variables or set a new value for them.

    public double getMass(){
        return mass;
    }

    public int getPeriod(){
        return period;
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

    public double[][] fromListToArray2D(ArrayList<Vector> positions){
        double[][] pos = new double[positions.size()][2];
        for(int i = 0;i<pos.length; i++){
            pos[i][0] = positions.get(i).getX();
            pos[i][1] = positions.get(i).getY();
        }
        return pos;
    }

}