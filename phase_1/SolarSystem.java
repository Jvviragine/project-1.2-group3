package phase_1;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SolarSystem extends JPanel implements ActionListener
{
    JLabel label[] = new JLabel[10];
    BufferedImage image[] = new BufferedImage[10];
    ImageIcon icon[] = new ImageIcon[10];
    ImageIcon zoomIcon[] = new ImageIcon[9];
    private final int DELAY = 1;
    private int earthDiameter;
    Main carl = new Main();
    private double xProbeDouble = 0, yProbeDouble = 0;
    private int xProbe = 0, yProbe = 0;
    
    private ArrayList<ArrayList<Double>> probePath = carl.probePath;
    private double[][] venusPath = carl.venus.fromListToArray2D(carl.venus.getPositionsArray());
    private double[][] earthPath = carl.earth.fromListToArray2D(carl.earth.getPositionsArray());
    private double[][] moonPath = carl.moon.fromListToArray2D(carl.moon.getPositionsArray());
    private double[][] marsPath = carl.mars.fromListToArray2D(carl.mars.getPositionsArray());
    private double[][] jupiterPath = carl.jupiter.fromListToArray2D(carl.jupiter.getPositionsArray());
    private double[][] saturnPath = carl.saturn.fromListToArray2D(carl.saturn.getPositionsArray());
    private double[][] titanPath = carl.titan.fromListToArray2D(carl.titan.getPositionsArray());
    private int counter = 0;

    public SolarSystem()
    {
        celestialObjects.list();
        //Coordinates of the sun
        final int sunX = (int) ((celestialObjects.mars.getDistanceFromSun()/SolarSystemViewer.scale) + 120) -((SolarSystemViewer.h/8 + 1)) + 100;
        final int sunY = (int) (SolarSystemViewer.h/2 -((SolarSystemViewer.h/8 + 1))) + 100;

        String picID[] = {"cbSun.png", "cbVenus.png", "cbEarth.png", "cbMoon.png", "cbMars.png", "cbJupiter.png", "cbSaturn.png", "cbTitan.png", "cbNormandy.png", "space.png"}; 

        for(int i = 0; i < picID.length; i++)
        {
            celestialObjects r = new celestialObjects();
            if(i<picID.length - 1)
            {
                r = celestialObjects.getBody(i);
            }
            try 
            {
                if(i == 0)// sun
                {
                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/20, SolarSystemViewer.h/20, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/16, SolarSystemViewer.h/16, Image.SCALE_SMOOTH));
                    
                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds(sunX, sunY, SolarSystemViewer.h/20 + 1, SolarSystemViewer.h/20 + 1);
                }
                else if(i == 1)// venus
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/38, SolarSystemViewer.h/38, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/30, SolarSystemViewer.h/30, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)+(SolarSystemViewer.h/38 + 1), (int) -(sunY+ay)+(SolarSystemViewer.h/38 + 1) + (2*sunY), SolarSystemViewer.h/30 + 1, SolarSystemViewer.h/30 + 1);
                }
                else if(i == 2)// earth
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;
                    earthDiameter = SolarSystemViewer.h/38 + 1;

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/38, SolarSystemViewer.h/38, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/30, SolarSystemViewer.h/30, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)+(SolarSystemViewer.h/38 + 1), (int) -(sunY+ay)+(SolarSystemViewer.h/38 + 1) + (2*sunY), SolarSystemViewer.h/30 + 1, SolarSystemViewer.h/30 + 1);
                }
                else if(i == 3)// moon
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/65, SolarSystemViewer.h/65, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/50, SolarSystemViewer.h/50, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)+(SolarSystemViewer.h/65 + 1), (int) -(sunY+ay)+(SolarSystemViewer.h/65 + 1) + (2*sunY), SolarSystemViewer.h/50 + 1, SolarSystemViewer.h/50 + 1);
                }
                else if(i == 4)// mars
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/38, SolarSystemViewer.h/38, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/30, SolarSystemViewer.h/30, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)+(SolarSystemViewer.h/38 + 1), (int) -(sunY+ay)+(SolarSystemViewer.h/38 + 1) + (2*sunY), SolarSystemViewer.h/30 + 1, SolarSystemViewer.h/30 + 1);
                }
                else if(i == 5)// jupiter
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/30, SolarSystemViewer.h/30, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/25, SolarSystemViewer.h/25, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)+(SolarSystemViewer.h/30 + 1), (int) -(sunY+ay)+(SolarSystemViewer.h/30 + 1) + (2*sunY), SolarSystemViewer.h/25 + 1, SolarSystemViewer.h/25 + 1);
                }
                else if(i == 6)// saturn
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/15, SolarSystemViewer.h/25, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/12, SolarSystemViewer.h/20, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)+(SolarSystemViewer.h/15 + 1)/2, (int) -(sunY+ay)+(SolarSystemViewer.h/25 + 1)/2 + (2*sunY), SolarSystemViewer.h/12 + 1, SolarSystemViewer.h/20 + 1);
                }
                else if(i == 7)// titan
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/65, SolarSystemViewer.h/65, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/50, SolarSystemViewer.h/50, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)+(SolarSystemViewer.h/65 + 1), (int) -(sunY+ay)+(SolarSystemViewer.h/65 + 1) + (2*sunY), SolarSystemViewer.h/50 + 1, SolarSystemViewer.h/50 + 1);
                }      
                else if(i == 8)// probe
                {
                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/30, SolarSystemViewer.h/30, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/20, SolarSystemViewer.h/20, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int) (-2*earthDiameter + (sunX)-SolarSystemViewer.h/15), (int) (-earthDiameter + -(sunY)-(SolarSystemViewer.h/15 + (sunY))), SolarSystemViewer.h/30 + 1, SolarSystemViewer.h/30 + 1);

                }
                else if(i == 9)// space
                {
                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h, SolarSystemViewer.h, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds(0, 0, SolarSystemViewer.h, SolarSystemViewer.h);
                }      
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        
        setLayout(null);
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(null);
        labelPanel.setOpaque(false);
        labelPanel.setBounds(0, 0, SolarSystemViewer.h, SolarSystemViewer.h);
        add(labelPanel);

        for(int j = 0; j < 10; j++)
        {
            labelPanel.add(label[j]);
        }

        for(int t = 0; t < 9; t++)
        {
            int h = t;
            label[t].addMouseListener(new MouseAdapter() 
            {
                @Override
                public void mouseEntered(MouseEvent e) 
                {
                    label[h].setIcon(zoomIcon[h]);
                }
    
                @Override
                public void mouseExited(MouseEvent e) {
                    label[h].setIcon(icon[h]);
                }
            });
        }     
        
        JPanel overlayPanel = new JPanel();
        overlayPanel.setOpaque(false);
        overlayPanel.setBounds(labelPanel.getBounds());
        add(overlayPanel);

        // Start the timer to update the positions of the labels
        Timer timer = new Timer(DELAY, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                counter+= 10;

                double xVenusDouble = venusPath[counter][0];
                int xVenus = sunX + (int) (xVenusDouble/SolarSystemViewer.scale);
                double yVenusDouble = venusPath[counter][1];
                int yVenus = sunY + (int) (yVenusDouble/SolarSystemViewer.scale);

                double xEarthDouble = earthPath[counter][0];
                int xEarth = sunX + (int) (xEarthDouble/SolarSystemViewer.scale);
                double yEarthDouble = earthPath[counter][1];
                int yEarth = sunY + (int) (yEarthDouble/SolarSystemViewer.scale);

                double xMoonDouble = moonPath[counter][0];
                int xMoon = sunX + (int) (xMoonDouble/SolarSystemViewer.scale);
                double yMoonDouble = moonPath[counter][1];
                int yMoon = sunY + (int) (yMoonDouble/SolarSystemViewer.scale);

                double xMarsDouble = marsPath[counter][0];
                int xMars = sunX + (int) (xMarsDouble/SolarSystemViewer.scale);
                double yMarsDouble = marsPath[counter][1];
                int yMars = sunY + (int) (yMarsDouble/SolarSystemViewer.scale);

                double xJupiterDouble = jupiterPath[counter][0];
                int xJupiter = sunX + (int) (xJupiterDouble/SolarSystemViewer.scale);
                double yJupiterDouble = jupiterPath[counter][1];
                int yJupiter = sunY + (int) (yJupiterDouble/SolarSystemViewer.scale);

                double xSaturnDouble = saturnPath[counter][0];
                int xSaturn = sunX + (int) (xSaturnDouble/SolarSystemViewer.scale);
                double ySaturnDouble = saturnPath[counter][1];
                int ySaturn = sunY + (int) (ySaturnDouble/SolarSystemViewer.scale);

                double xTitanDouble = titanPath[counter][0];
                int xTitan = sunX + (int) (xTitanDouble/SolarSystemViewer.scale);
                double yTitanDouble = titanPath[counter][1];
                int yTitan = sunY + (int) (yTitanDouble/SolarSystemViewer.scale);
                
                xProbeDouble = probePath.get(counter).get(0);
                xProbe = sunX + (int) (xProbeDouble/SolarSystemViewer.scale);
                yProbeDouble = probePath.get(counter).get(1);
                yProbe = sunY + (int) (yProbeDouble/SolarSystemViewer.scale);

                label[1].setLocation(xVenus, yVenus);
                label[2].setLocation(xEarth, yEarth);
                label[3].setLocation(xMoon, yMoon);
                label[4].setLocation(xMars, yMars);
                label[5].setLocation(xJupiter, yJupiter);
                label[6].setLocation(xSaturn, ySaturn);
                label[7].setLocation(xTitan, yTitan);
                label[8].setLocation(xProbe, yProbe);

                if(probePath.get(counter).get(0) == UpdateStateOfSystem.getPositionWhenReached().getX()) {
                    System.out.println("Reached!");
                    return;
                }
            }
        });
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}