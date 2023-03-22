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

    //Euler's Update Position Method -> Tamar
    public void updatePosition(){
        //ArrayList <Vector> newpositions = new ArrayList<>();
        ArrayList <Vector> initialvelocities = new ArrayList<>();
        for(int i=0;i<bodiesInSystem.size();i++){
            Vector initialvelocity = bodiesInSystem.get(i).getVelocity();
            initialvelocities.add(initialvelocity);
        }
    
        updateVelocity();

        for(int i=0;i<bodiesInSystem.size();i++){ 
            //System.out.println("Initial Position of " + bodiesInSystem.get(i).getName() + " :"  + bodiesInSystem.get(i).getPosition());
            Vector incr = (initialvelocities.get(i).multi(timeStepInSeconds));
            Vector updated = bodiesInSystem.get(i).getPosition().add(incr);
            bodiesInSystem.get(i).setPosition(updated);
            bodiesInSystem.get(i).getPositionsArray().add(updated);
            //newpositions.add(updated);
            System.out.println("Final Position of " + bodiesInSystem.get(i).getName() + " :"  + bodiesInSystem.get(i).getPosition());
        }
    
        //positions = newpositions;
    }

    public void updateVelocity(){
    
        // ArrayList <Vector> newvelocities=new ArrayList<>();
        // newvelocities.add(bodiesInSystem.get(0).getVelocity()); //adds sun velocity first (unchanged)
             
        for(int i=1;i<bodiesInSystem.size();i++){//skip sun (index 0) when calculating velocity
            //System.out.println("Initial Velocity of " + bodiesInSystem.get(i).getName() + " :" + bodiesInSystem.get(i).getVelocity());

            Vector netforce = (getForce(bodiesInSystem.get(i))).multi(-1);
            Vector acceleration = netforce.multi(1/bodiesInSystem.get(i).getMass());

            Vector incr = (acceleration.multi(timeStepInSeconds));
            Vector updated = bodiesInSystem.get(i).getVelocity().add(incr);

            bodiesInSystem.get(i).setVelocity(updated);
            bodiesInSystem.get(i).getVelocitiesArray().add(updated);
            //System.out.println("Final Velocity of " + bodiesInSystem.get(i).getName() +" :" + bodiesInSystem.get(i).getVelocity());
        }
    }
    
    public Vector getForce(CelestialBody body){
        Vector force = new Vector();
    
        for(int i=0; i<bodiesInSystem.size(); i++){
            if(bodiesInSystem.get(i)!=body){ 
                //own body should not be added to net force 
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
