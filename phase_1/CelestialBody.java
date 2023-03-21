package phase_1;
public class CelestialBody{
    double mass;
    Vector initialposition;
    Vector initialvelocity;

    public CelestialBody(Vector initialposition,Vector initalvelocity,double mass){
        this.initialposition=initialposition;
        this.initialvelocity=initalvelocity;
        this.mass=mass;
    }
    
    public double getMass(){
        return mass;
    }

}