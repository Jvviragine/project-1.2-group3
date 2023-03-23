package phase_1;

import java.util.Arrays;
import javax.swing.*;
import java.awt.*;

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

