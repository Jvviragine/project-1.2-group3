package phase_1;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

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