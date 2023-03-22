package phase_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.security.spec.EllipticCurve;
import java.util.Arrays;
import java.util.Iterator;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

        
public class SolarSystem extends JPanel implements ActionListener
{
    BufferedImage trial;
    ImageObserver observer;        
    Timer timer = new Timer(1, this);

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

        try 
        {
            // g2D.setColor(Color.WHITE);
            // g2D.drawLine(0, 0, 800, 800);
            // g2D.drawLine(0, 800, 800, 0);
            String path = "cbSun.png";
            trial = ImageIO.read(getClass().getResource(path));
            g2D.drawImage(trial, SolarSystemViewer.h/2 - 5, SolarSystemViewer.h/2 - 5, 10, 10, observer);
            g2D.drawString("Sun", SolarSystemViewer.h/2 - 10, 400 - 9);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        // g2D.setStroke(new BasicStroke(1));
        // g.setColor(Color.GREEN);
        // Ellipse2D.Double venusOrbit = new Ellipse2D.Double(370-29.6, 370-29.6, 29.6*2, 29.6*2);
        // g2D.draw(venusOrbit);

        String image[] = {"cbVenus.png", "cbEarth.png", "cbMoon.png", "cbMars.png", "cbJupiter.png", "cbSaturn.png", "cbTitan.png"}; 
        String label[] = {"VENUS", "EARTH", "MOON", "MARS", "JUPITER", "SATURN", "TITAN"}; 
        celestialObjects.list();
        int i = 0;
        int t = 0;

        while(i < image.length)
        {
            t = i+1;
            celestialObjects r = celestialObjects.getBody(t);
            double ax = r.x1/SolarSystemViewer.scale;
            double ay = r.x2/SolarSystemViewer.scale;

            try 
            {
                String path = image[i];
                trial = ImageIO.read(getClass().getResource(path));
                g2D.drawImage(trial, (int) Math.round(-1*((SolarSystemViewer.h/2 - 3)-ax)+(SolarSystemViewer.h)) ,(int) Math.round((SolarSystemViewer.h/2 - 3)-ay) , 7, 7, observer);
                g2D.drawString(label[i], (int) Math.round(-1*((SolarSystemViewer.h/2 + 10)-ax)+(SolarSystemViewer.h)) , (int) Math.round((SolarSystemViewer.h/2 - 5)-ay));

            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }

            i++;
        }  

    }

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
