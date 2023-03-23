package phase_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.security.spec.EllipticCurve;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

        
public class SolarSystem extends JPanel implements ActionListener
{
    BufferedImage imageBuffer;
    ImageObserver observer;        
    Timer timer = new Timer(1, this);
    private int planetX = 0;
    private int planetY = 0;

    public void paintComponent(Graphics g) 
    {
        Graphics2D g2D = (Graphics2D) g;
        timer.start();
        double starX[] = new double[300];
        double starY[] = new double[300];

        for(int i = 0; i < 300; i++)
        {
            double x = Math.random()*SolarSystemViewer.h;
            double y = Math.random()*SolarSystemViewer.h;
            starX[i] = x;
            starY[i] = y;
        }
        
        //Randomly generate background stars
        for(int i = 0; i < 300; i++)
        {
            g2D.setStroke(new BasicStroke((float) (Math.random()*0.2)));
            g.setColor(new Color(((int)Math.round(Math.random()*100000))));
            if(i>50)
            {
                g.setColor(Color.WHITE);
                if((starX[i] < SolarSystemViewer.h/2 - 25 || starX[i] > SolarSystemViewer.h/2 + 25) || (starY[i] < SolarSystemViewer.h/2 - 25 || starY[i] > SolarSystemViewer.h/2 + 25))            
                {
                    Line2D.Double star = new Line2D.Double(starX[i], starY[i], starX[i], starY[i]);
                    g2D.draw(star);
                }
            }
        }

        int sunX = (int) ((celestialObjects.mars.getDistanceFromSun()/SolarSystemViewer.scale) + 70);
        int sunY = (int) SolarSystemViewer.h/3;

        //Generate the sun
        try 
        {
            // g2D.setColor(Color.WHITE);
            // g2D.drawLine(0, 0, 800, 800);
            // g2D.drawLine(0, 800, 800, 0);
            String path = "cbSun.png";
            imageBuffer = ImageIO.read(getClass().getResource(path));
            
            g2D.drawImage(imageBuffer, sunX, sunY, SolarSystemViewer.h/15, SolarSystemViewer.h/15, observer);
            // g2D.drawString("SUN", sunX, sunY + 80);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        // g2D.setStroke(new BasicStroke(1));
        // g.setColor(Color.GREEN);
        // Ellipse2D.Double venusOrbit = new Ellipse2D.Double(370-29.6, 370-29.6, 29.6*2, 29.6*2);
        // g2D.draw(venusOrbit);

        String image[] = {"cbVenus.png", "cbEarth.png", "cbMoon.png", "cbMars.png", "cbJupiter.png", "cbSaturn.png", "cbTitan.png", "cbNormandy.png"}; 
        String label[] = {"VENUS", "EARTH", "MOON", "MARS", "JUPITER", "SATURN", "TITAN"}; 
        celestialObjects.list();
        int t = 0;
        int adjustH = 0;
        int adjustW = 0;
        int offset = 0;

        double radiusTitan = 2.575;
        double c = 0.1*radiusTitan;
        
        // Create a new Solar System

        // Create the Sun
        Vector sunInitialPositions = new Vector(0, 0, 0);
        Vector sunInitialVelocity = new Vector(0, 0, 0);
        CelestialBody sun = new CelestialBody(sunInitialPositions, sunInitialVelocity, 1.9885*Math.pow(10, 30), "Sun");

        // Create Venus
        Vector venusInitialPositions = new Vector((-28216773.9426889), (103994008.541512), (3012326.64296788));
        Vector venusInitialVelocity = new Vector((-34.0236737066136), (-8.96521274688838), (1.84061735279188));
        CelestialBody venus = new CelestialBody(venusInitialPositions, venusInitialVelocity, 48.685*Math.pow(10,23), "Venus");

        // Create the Earth
        Vector earthInitialPositions = new Vector((-148186906.893642), (-27823158.5715694), (33746.8987977113));
        Vector earthInitialVelocity = new Vector((5.05251577575409), (-29.3926687625899), (0.00170974277401292));
        CelestialBody earth = new CelestialBody(earthInitialPositions, earthInitialVelocity, 5.97219*Math.pow(10,24), "Earth");

        // Create the Moon
        Vector moonInitialPositions = new Vector((-148458048.395164), (-27524868.1841142), (70233.6499287411));
        Vector moonInitialVelocity = new Vector((4.34032634654904), (-30.0480834180741), (-0.0116103535014229));
        CelestialBody moon = new CelestialBody(moonInitialPositions, moonInitialVelocity, 7.349*Math.pow(10,22), "Moon");

        // Create Mars
        Vector marsInitialPositions = new Vector((-159116303.422552), (189235671.561057), (7870476.08522969));
        Vector marsInitialVelocity = new Vector((-17.6954469224752), (-13.4635253412947), (0.152331928200531));
        CelestialBody mars = new CelestialBody(marsInitialPositions, marsInitialVelocity, 6.4171*Math.pow(10,23) , "Mars");

        // Create Jupiter
        Vector jupiterInitialPositions = new Vector((692722875.928222), (258560760.813524), (-16570817.7105996));
        Vector jupiterInitialVelocity = new Vector((-4.71443059866156), (12.8555096964427), (0.0522118126939208));
        CelestialBody jupiter = new CelestialBody(jupiterInitialPositions, jupiterInitialVelocity, 189818722*Math.pow(10,19), "Jupiter");

        // Create Saturn
        Vector saturnInitialPositions = new Vector((1253801723.95465), (-760453007.810989), (-36697431.1565206));
        Vector saturnInitialVelocity = new Vector((4.46781341335014), (8.23989540475628), (-0.320745376969732));
        CelestialBody saturn = new CelestialBody(saturnInitialPositions, saturnInitialVelocity, 5.6834*Math.pow(10,26), "Saturn");

        // Create Titan
        Vector titanInitialPositions = new Vector((1254501624.95946), (-761340299.067828), (-36309613.8378104));
        Vector titanInitialVelocity = new Vector((8.99593229549645), (11.1085713608453), (-2.25130986174761));
        CelestialBody titan = new CelestialBody(titanInitialPositions, titanInitialVelocity, 13455.3*Math.pow(10,19), "Titan");

        // Create the Probe
        //Vector probeInitialPosition = new Vector((-148186906.893642), (-27823158.5715694), (33746.8987977113));
        Vector probeInitialPosition = new Vector(-148186906.893642 + 6700, -27823158.5715694 + 6700, 33746.8987977113 + 6700);
        Vector probeInitialVelocity = new Vector(0, 0, 0);
        CelestialBody probe = new CelestialBody(probeInitialPosition, probeInitialVelocity,  50000, "Probe");

        // Collection of Celestial Bodies
        ArrayList<CelestialBody> celestialBodies = new ArrayList<CelestialBody>();
        celestialBodies.add(sun);
        celestialBodies.add(venus);
        celestialBodies.add(earth);
        celestialBodies.add(moon);
        celestialBodies.add(mars);
        celestialBodies.add(jupiter);
        celestialBodies.add(saturn);
        celestialBodies.add(titan);
        celestialBodies.add(probe);

        int timeStepInSeconds = 60 * 60; // 1 Month of Timestep
        StateOfSystem solarSystemState3 = new StateOfSystem(timeStepInSeconds, celestialBodies); // Giging it a Time Step of
        
        // Create the Object that can Update the State of the Solar System
        UpdateStateOfSystem solarSystemUpdater3 = new UpdateStateOfSystem(solarSystemState3);
        solarSystemUpdater3.calculateMissingValuesForT0();

        // Loop controlling how many TimeSteps we are going to take
        int lastT = (365 * 24) + 6; // Years 10756
        int currT = 0;
        while(probe.getPosition().dist(titanInitialPositions) > radiusTitan+c && currT != lastT){
            solarSystemUpdater3.updateStateOfSolarSystem();
            currT += 1;
        }

        for(int j = 1; j < celestialBodies.size(); j++){
            // for(int i = 1; i < image.length; i++)
            // {
                double ax = celestialBodies.get(j).getPosition().getX()/SolarSystemViewer.scale;
                double ay = celestialBodies.get(j).getPosition().getY()/SolarSystemViewer.scale;
            
                try 
                {
                    System.out.println(ax);
                    String path = image[j - 1];
                    imageBuffer = ImageIO.read(getClass().getResource(path));
                    switch(j - 1) {
                        case 0, 1: 
                            offset = 0;
                            adjustH = SolarSystemViewer.h/38;
                            adjustW = adjustH;
                            break;
                        case 2: 
                            offset = SolarSystemViewer.h/96;
                            adjustH = SolarSystemViewer.h/96;
                            adjustW = adjustH;
                            break;
                        case 3: 
                            offset = 0;
                            adjustH = SolarSystemViewer.h/48;
                            adjustW = adjustH;
                            break;
                        case 4: 
                            offset = 0;
                            adjustH = SolarSystemViewer.h/28;
                            adjustW = adjustH;
                            break;
                        case 5: 
                            offset = 0;
                            adjustH = SolarSystemViewer.h/28;
                            adjustW = SolarSystemViewer.h/16;
                            break;
                        case 6: 
                            offset = 0;
                            adjustH = SolarSystemViewer.h/96;
                            adjustW = adjustH;
                            break;
                        case 7:
                            offset = 0;
                            adjustH = SolarSystemViewer.h/96;
                            adjustW = SolarSystemViewer.h/16;

                    }

                    g2D.drawImage(imageBuffer, (int) ((sunX+SolarSystemViewer.h/30)+ax)-15-offset,(int) ((sunY+SolarSystemViewer.h/30)-ay)-15-offset , adjustW, adjustH, observer);
                    // g2D.drawString(label[i], (int) (sunX+ax)-10, (int) (sunY-ay)-10);

                    // g2D.drawImage(trial, (int) Math.round(-1*((SolarSystemViewer.h/2 - 3)-ax)+(SolarSystemViewer.h)) ,(int) Math.round((SolarSystemViewer.h/2 - 3)-ay) , 7, 7, observer);
                    // g2D.drawString(label[i], (int) Math.round(-1*((SolarSystemViewer.h/2 + 10)-ax)+(SolarSystemViewer.h)) , (int) Math.round((SolarSystemViewer.h/2 - 5)-ay));
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }  
        }
    // }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        // repaint();         
    }

    class OrbitDrawer extends JComponent {
        //nested class used to draw an orbit by tracing between the given points
        
        private double[][] positions;
        //points are stored inside a two dimensional double array which contains a set of (x, y) coordinates for each points
    
        OrbitDrawer(double pos[][]) {
        //generic function that stores the given input points to the local "positions" variable
            positions = new double[pos.length][];
            for (int i = 0; i < pos.length; i++) {
                positions[i] = Arrays.copyOf(pos[i], pos[i].length);
            }
        }
    
        public void paintComponent(Graphics g) {
            //this method connects each points by drawing lines from one to another to form a polygon representing the orbit
            //the more points are given in the entry the smoother and round the orbit will appear
            int numberOfPositions = positions.length;
    
            int[] x = new int[numberOfPositions];
            int[] y = new int[numberOfPositions];
            
            //each x and y coordinates are stored in seperate arrays
            for (int i = 0; i < positions.length; i++) {
                x[i] = (int) positions[i][0];
                y[i] = (int) positions[i][1];
            }
            
            //draw the polygon using Graphics2D
            Graphics2D g2D = (Graphics2D) g;
            g2D.setColor(Color.BLUE);
            g2D.draw(new Polygon(x, y, positions.length));
        }
    
        public void setPositions(double pos[][]) {
            //manually sets the position array from the given input
            positions = new double[pos.length][];
            for (int i = 0; i < pos.length; i++) {
                positions[i] = Arrays.copyOf(pos[i], pos[i].length);
            }
        }
    
        public double[][] getPosition() {
            //get the position array
            return this.positions;
        }
    }

    class PathDrawer extends JComponent {

        private double[][] positions;
        //points are stored inside a two dimensional double array which contains a set of (x, y) coordinates for each points
    
        PathDrawer(double pos[][]) {
        //generic function that stores the given input points to the local "positions" variable
            positions = new double[pos.length][];
            for (int i = 0; i < pos.length; i++) {
                positions[i] = Arrays.copyOf(pos[i], pos[i].length);
            }
        }

        public void paintComponent(Graphics g) {
            //
            Graphics2D g2D = (Graphics2D) g;
            g2D.setColor(Color.RED);
            for (int i = 1; i < positions.length; i++) {
                int x1 = (int) positions[i - 1][0];
                int x2 = (int) positions[i][0];
                
                int y1 = (int) positions[i - 1][1];
                int y2 = (int) positions[i][1];
                
                g2D.drawLine(x1, y1, x2, y2);
            }
        }

        public void setPositions(double pos[][]) {
            //manually sets the position array from the given input
            positions = new double[pos.length][];
            for (int i = 0; i < pos.length; i++) {
                positions[i] = Arrays.copyOf(pos[i], pos[i].length);
            }
        }
    
        public double[][] getPosition() {
            //get the position array
            return this.positions;
        }
    }
}
