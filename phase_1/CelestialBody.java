package phase_1;
public class CelestialBody{
    Vector position;
    Vector velocity;
    double mass;
    double radius;

    // Added more Instance Fields
    Vector netForce;
    Vector netAcceleration;

    public CelestialBody(Vector initialposition,Vector initialvelocity,double mass,double radius){
        this.position=initialposition;
        this.velocity=initialvelocity;
        this.mass=mass;
        this.radius=radius;
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

    public void setNetAcceleration(Vector netAcceleration) {
        this.netAcceleration = netAcceleration;
    }

}