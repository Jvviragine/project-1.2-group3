package phase_1;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.security.spec.EllipticCurve;
import java.util.Arrays;


public class SolarSystem extends JPanel 
{
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
    
    public void paintComponent(Graphics g) 
    {
        Graphics2D g2D = (Graphics2D) g;
        g2D.setStroke(new BasicStroke((float)1));
        g.setColor(Color.WHITE);

        for(int i = 0; i < 50; i++)
        {
            g2D.setStroke(new BasicStroke((float) (Math.random()*0.2)));
            g.setColor(new Color(((int)Math.round(Math.random()*100000))));
            double x = Math.random()*740;
            double y = Math.random()*740;
            if((x < 340 || x > 400) || (y < 340 || y > 400))
            {
                Line2D.Double star = new Line2D.Double(x, y, x, y);
                g2D.draw(star);
            }
        }

        for(int i = 0; i < 250; i++)
        {
            g2D.setStroke(new BasicStroke((float) (Math.random()*0.2)));
            g.setColor(Color.WHITE);
            double x = Math.random()*740;
            double y = Math.random()*740;
            if((x < 340 || x > 400) || (y < 340 || y > 400))
            {
                Line2D.Double star = new Line2D.Double(x, y, x, y);
                g2D.draw(star);
            }
        }

        // 1px = 3513513.51351km  
        // furtherest distance in a 370px radius = 1,300,000,000km
        // approximate distance to titan = 1,250,000,000km or 355.76923077px 
        
        g2D.setStroke(new BasicStroke((float) 0.3959056923));
        g.setColor(Color.ORANGE);
        Line2D.Double sun = new Line2D.Double(370, 370, 370,370);
        g2D.draw(sun);

        g2D.setStroke(new BasicStroke(1));
        g.setColor(Color.GREEN);
        Ellipse2D.Double venusOrbit = new Ellipse2D.Double(370-29.6, 370-29.6, 29.6*2, 29.6*2);
        g2D.draw(venusOrbit);

        g2D.setStroke(new BasicStroke(1));
        g.setColor(Color.BLUE);
        Ellipse2D.Double earthOrbit = new Ellipse2D.Double(370-42.1230769231, 370-42.1230769231, 42.1230769231*2, 42.1230769231*2);
        g2D.draw(earthOrbit);

        g2D.setStroke(new BasicStroke(1));
        g.setColor(Color.RED);
        Ellipse2D.Double marsOrbit = new Ellipse2D.Double(370-53.7923076924, 370-53.7923076924, 53.7923076924*2, 53.7923076924*2);
        g2D.draw(marsOrbit);

        g2D.setStroke(new BasicStroke(1));
        g.setColor(Color.YELLOW);
        Ellipse2D.Double jupiterOrbit = new Ellipse2D.Double(370-197.238461539, 370-197.238461539, 197.238461539*2, 197.238461539*2);
        g2D.draw(jupiterOrbit);

        g2D.setStroke(new BasicStroke(1));
        g.setColor(Color.PINK);
        Ellipse2D.Double saturnOrbit = new Ellipse2D.Double(370-355.76923077, 370-355.76923077, 355.76923077*2, 355.76923077*2);
        g2D.draw(saturnOrbit);

    }
}

