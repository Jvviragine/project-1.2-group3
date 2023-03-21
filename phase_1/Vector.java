package phase_1;

public class Vector{
    private double x;
    private double y;
    private double z;

    //constructor for vector with x,y,z
    public Vector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //constructor of empty vector
    public Vector(){

    }

    public double getX(){
        return x;
    }

    public double getZ(){
        return z;
    }

    public double getY(){
        return y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void setZ(double z){
        this.z = z;
    }

    public Vector add(Vector other){
        Vector v = new Vector();
        v.setX(x + other.getX());
        v.setY(y + other.getY());
        v.setZ(z + other.getZ());
        return v;
    }

    public Vector sub(Vector other){
        Vector v = new Vector();
        v.setX(x - other.getX());
        v.setY(y - other.getY());
        v.setZ(z - other.getZ());
        return v;
    }

    public Vector multi(double s){
        Vector v = new Vector();
        v.setX(x*s);
        v.setY(y*s);
        v.setZ(z*s);
        return v;
    }

    //computes length of a vector
    public double len(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
    }

    //computes euclidean distance between vectors
    public double dist(Vector other){
        return Math.sqrt(Math.pow(x - other.getX(), 2) + Math.pow(y - other.getY(), 2) + Math.pow(z - other.getZ(), 2));
    }

    public String toString(){
        return x + ", " + y + ", " + z;
    }

}