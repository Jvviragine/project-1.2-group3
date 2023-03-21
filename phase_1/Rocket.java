package phase_1;

public class Rocket {
    
    public final int mass = 5000;
    public Vector rocketPosition;
    public Vector rocketVelocity;
    public Vector startPosition;

    public Rocket(Vector rocketPosition, Vector rocketVelocity, Vector startPosition){
        this.rocketPosition = rocketPosition;
        this.rocketVelocity = rocketVelocity;
        this.startPosition = startPosition;
    }

    public Vector getPosition(){
        return rocketPosition;
    }

    public double getXPos(){
        return rocketPosition.getX();
    }

    public double getYPos(){
        return rocketPosition.getY();
    }

    public double getZPos(){
        return rocketPosition.getZ();
    }

    public Vector detVelocity(){
        return rocketVelocity;
    }

    public double getXVel(){
        return rocketVelocity.getX();
    }

    public double getYVel(){
        return rocketVelocity.getY();
    }

    public double getZVel(){
        return rocketVelocity.getZ();
    }

    public Vector getStart(){
        return startPosition;
    }

    public double getXStart(){
        return startPosition.getX();
    }

    public double getYStart(){
        return startPosition.getY();
    }

    public double getZStart(){
        return startPosition.getZ();
    }
}
