package phase_1;
import java.util.ArrayList;
public class EulerUpdateStateofSystem {
    // Instance Fields
    private StateOfSystem solarSystem;
    private ArrayList<CelestialBody> bodiesInSystem = new ArrayList<CelestialBody>();
    private double timeStepInSeconds;
    private int currentTimeOfState;
    final double G = 6.6743*(Math.pow(10,-20));

    // Constructor 
    public EulerUpdateStateofSystem(StateOfSystem solarSystem) {

        this.solarSystem = solarSystem;
        bodiesInSystem = this.solarSystem.getBodiesInSystem();
        timeStepInSeconds = this.solarSystem.getTimeStepInSeconds();
        this.currentTimeOfState = this.solarSystem.getCurrentTime();
    }

    // Method Responsible for Updating the State of Our Solar System -> From Tn to T1 -> Tamar's Methods
    public void updateStateOfSolarSystemEuler() {
        updatePosition();
    }

//  ------------------------Euler's Update Position Method -> Tamar-------------------
    //Updates the position of the body

    public void updatePosition(){

        //Initial velocities are stored
        ArrayList <Vector> initialvelocities = new ArrayList<>();

        for(int i=0;i<bodiesInSystem.size();i++){
            Vector initialvelocity = bodiesInSystem.get(i).getVelocity();
            initialvelocities.add(initialvelocity);
        }
    
        //Velocity method is called, to ensure updated positions do not affect calculations
        updateVelocity();

        //Position is updated using Euler's method.
        //Xi+1 = Xi + (h * Vi)
        for(int i=0;i<bodiesInSystem.size();i++){ 

            //System.out.println("Initial Position of " + bodiesInSystem.get(i).getName() + " :"  + bodiesInSystem.get(i).getPosition());
            
            Vector incr = (initialvelocities.get(i).multi(timeStepInSeconds));
            Vector updated = bodiesInSystem.get(i).getPosition().add(incr);
            bodiesInSystem.get(i).setPosition(updated);
            
            bodiesInSystem.get(i).getPositionsArray().add(updated);
            
            //System.out.println("Final Position of " + bodiesInSystem.get(i).getName() + " :"  + bodiesInSystem.get(i).getPosition());
        }
    }


//  ------------------------Euler's Update Velocity Methods -> Tamar-------------------
    //Updates the velocity of the body
    
    public void updateVelocity(){
        
        //Updates velocities of all bodies, except the Sun (index 0)
        for(int i=1;i<bodiesInSystem.size();i++){
           
            Vector netforce = (getForce(bodiesInSystem.get(i))).multi(-1);
            Vector acceleration = netforce.multi(1/bodiesInSystem.get(i).getMass());

            Vector incr = (acceleration.multi(timeStepInSeconds));
            Vector updated = bodiesInSystem.get(i).getVelocity().add(incr);

            bodiesInSystem.get(i).setVelocity(updated);
            bodiesInSystem.get(i).getVelocitiesArray().add(updated);
            
        }
    }
    
    /**
     * Finds the net force on a body.
     * @param body the body on which net force is to be calculated
     * @return vector representing FiG
     */
    public Vector getForce(CelestialBody body){
        Vector force = new Vector();
    
        for(int i=0; i<bodiesInSystem.size(); i++){

            //own body should not be added to net force 
            if(bodiesInSystem.get(i)!=body){ 
                
                Vector bodyposition = body.getPosition(); //Xi
                Vector otherposition = bodiesInSystem.get(i).getPosition(); //Xj
    
                double masses = body.getMass()*bodiesInSystem.get(i).getMass(); //Mi*Mj
    
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
