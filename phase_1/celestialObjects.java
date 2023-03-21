package phase_1;

public class celestialObjects 
{
    double massSpaceship = 50000;
    double gravitationalConstant = Math.pow(6.6743*10,-20);
    static double x1;
    static double x2;
    static double x3;
    static double v1;
    static double v2;
    static double v3;
    static double mass;
    
    celestialObjects sun = new celestialObjects(0, 0, 0, 0, 0, 0, Math.pow(1.9885*10,30));
    celestialObjects venus = new celestialObjects(-28216773.9426889, 103994008.541512, 3012326.64296788, -34.0236737066136, -8.96521274688838, 1.84061735279188, Math.pow(48.685*10,23));
    celestialObjects earth = new celestialObjects(-148186906.893642, -27823158.5715694, 33746.8987977113, 5.05251577575409, -29.3926687625899, 0.00170974277401292, Math.pow(5.97219*10,24));
    celestialObjects moon = new celestialObjects(-148458048.395164, -27524868.1841142, 70233.6499287411, 4.34032634654904, -30.0480834180741, -0.0116103535014229, Math.pow(7.349*10,22));
    celestialObjects mars = new celestialObjects(-159116303.422552, 189235671.561057, 7870476.08522969, -17.6954469224752, -13.4635253412947, 0.152331928200531, Math.pow(6.4171*10,23));
    celestialObjects jupiter = new celestialObjects(692722875.928222, 258560760.813524, -16570817.7105996, -4.71443059866156, 12.8555096964427, 0.0522118126939208, Math.pow(189818722*10,19));
    celestialObjects saturn = new celestialObjects(1253801723.95465, -760453007.810989, -36697431.1565206, 4.46781341335014, 8.23989540475628, -0.320745376969732, Math.pow(5.6834*10,26));
    celestialObjects titan = new celestialObjects(1254501624.95946, -761340299.067828, -36309613.8378104, 8.99593229549645, 11.1085713608453, -2.25130986174761, Math.pow(13455.3*10,19));

    celestialObjects(double x1, double x2, double x3, double v1, double v2, double v3, double mass)
    {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.mass = mass;
    }

    public static double getX1()
    {
        return x1;
    }

    public static double getX2()
    {
        return x2;
    }

    public static double getX3()
    {
        return x3;
    }

    public static double getV1()
    {
        return v1;
    }

    public static double getV2()
    {
        return v2;
    }

    public static double getV3()
    {
        return v3;
    }

    public static double getMass()
    {
        return mass;
    }

    public static double getDistanceFromSun(double x1, double x2, double x3)
    {
        double distanceSun = Math.sqrt(Math.pow(x1,2) + Math.pow(x2,2) + Math.pow(x3,2));
        
        return distanceSun;
    }
}

