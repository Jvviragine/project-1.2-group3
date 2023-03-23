package phase_1;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class SolarSystem extends JPanel implements ActionListener
{
    JLabel label[] = new JLabel[9];
    BufferedImage image[] = new BufferedImage[9];
    ImageIcon icon[] = new ImageIcon[9];
    ImageIcon zoomIcon[] = new ImageIcon[9];
    private double angle = 0;
    private final int DELAY = 10;
    private int radiusVenus, radiusEarth, radiusMoon, radiusMars, radiusJupiter, radiusSaturn, radiusTitan;
    private int xStartEarth, yStartEarth;
    private int sunDiameter, earthDiameter, saturnDiameter;

    public SolarSystem()
    {

        celestialObjects.list();
        final int sunX = (int) ((celestialObjects.mars.getDistanceFromSun()/SolarSystemViewer.scale) + 120)-((SolarSystemViewer.h/8 + 1)/2);
        final int sunY = (int) SolarSystemViewer.h/3-((SolarSystemViewer.h/8 + 1)/2);

        String picID[] = {"cbSun.png", "cbVenus.png", "cbEarth.png", "cbMoon.png", "cbMars.png", "cbJupiter.png", "cbSaturn.png", "cbTitan.png", "URETHRA!.png"}; 

        for(int i = 0; i < picID.length; i++)
        {
            celestialObjects r = celestialObjects.getBody(i);
            try 
            {
                if(i == 0)// sun
                {
                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/20, SolarSystemViewer.h/20, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/16, SolarSystemViewer.h/16, Image.SCALE_SMOOTH));
                    
                    sunDiameter = SolarSystemViewer.h/8 + 1;
                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds(sunX, sunY, SolarSystemViewer.h/8 + 1, SolarSystemViewer.h/8 + 1);
                }
                else if(i == 1)// venus
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;
                    radiusVenus = (int) (r.getDistanceFromSun() / SolarSystemViewer.scale);

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/38, SolarSystemViewer.h/38, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/30, SolarSystemViewer.h/30, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)+(SolarSystemViewer.h/38 + 1)/2, (int) -(sunY+ay)+(SolarSystemViewer.h/38 + 1)/2 + (2*sunY), SolarSystemViewer.h/30 + 1, SolarSystemViewer.h/30 + 1);
                }
                else if(i == 2)// earth
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;
                    radiusEarth = (int) (r.getDistanceFromSun() / SolarSystemViewer.scale);
                    earthDiameter = SolarSystemViewer.h/38 + 1;
                    xStartEarth = (int)(sunX+ax)+(SolarSystemViewer.h/38 + 1)/2;
                    yStartEarth = (int) -(sunY+ay)+(SolarSystemViewer.h/38 + 1)/2 + (2*sunY);

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/38, SolarSystemViewer.h/38, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/30, SolarSystemViewer.h/30, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)+(SolarSystemViewer.h/38 + 1)/2, (int) -(sunY+ay)+(SolarSystemViewer.h/38 + 1)/2 + (2*sunY), SolarSystemViewer.h/30 + 1, SolarSystemViewer.h/30 + 1);
                }
                else if(i == 3)// moon
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;
                    radiusMoon = 20;

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/65, SolarSystemViewer.h/65, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/50, SolarSystemViewer.h/50, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)+(SolarSystemViewer.h/65 + 1)/2, (int) -(sunY+ay)+(SolarSystemViewer.h/65 + 1)/2 + (2*sunY), SolarSystemViewer.h/50 + 1, SolarSystemViewer.h/50 + 1);
                }
                else if(i == 4)// mars
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;
                    radiusMars = (int) (r.getDistanceFromSun() / SolarSystemViewer.scale);

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/38, SolarSystemViewer.h/38, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/30, SolarSystemViewer.h/30, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)+(SolarSystemViewer.h/38 + 1)/2, (int) -(sunY+ay)+(SolarSystemViewer.h/38 + 1)/2 + (2*sunY), SolarSystemViewer.h/30 + 1, SolarSystemViewer.h/30 + 1);
                }
                else if(i == 5)// jupiter
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;
                    radiusJupiter = (int) (r.getDistanceFromSun() / SolarSystemViewer.scale);

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/30, SolarSystemViewer.h/30, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/25, SolarSystemViewer.h/25, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)+(SolarSystemViewer.h/30 + 1)/2, (int) -(sunY+ay)+(SolarSystemViewer.h/30 + 1)/2 + (2*sunY), SolarSystemViewer.h/25 + 1, SolarSystemViewer.h/25 + 1);
                }
                else if(i == 6)// saturn
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;
                    radiusSaturn = (int) (r.getDistanceFromSun() / SolarSystemViewer.scale);
                    saturnDiameter = SolarSystemViewer.h/16 + 1;

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/15, SolarSystemViewer.h/25, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/14, SolarSystemViewer.h/20, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)+(SolarSystemViewer.h/18 + 1)/2, (int) -(sunY+ay)+(SolarSystemViewer.h/25 + 1)/2 + (2*sunY), SolarSystemViewer.h/14 + 1, SolarSystemViewer.h/20 + 1);
                }
                else if(i == 7)// titan
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;
                    radiusTitan = 20;

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/65, SolarSystemViewer.h/65, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/50, SolarSystemViewer.h/50, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)+(SolarSystemViewer.h/65 + 1)/2, (int) -(sunY+ay)+(SolarSystemViewer.h/65 + 1)/2 + (2*sunY), SolarSystemViewer.h/50 + 1, SolarSystemViewer.h/50 + 1);
                }      
                else if(i == 8)// probe
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;
                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/10, SolarSystemViewer.h/10, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/8, SolarSystemViewer.h/8, Image.SCALE_SMOOTH));

                    System.out.println(ax);
                    System.out.println(ay);

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)+(SolarSystemViewer.h/38 + 1)/2, (int) -(sunY+ay)+(SolarSystemViewer.h/38 + 1)/2 + (2*sunY), SolarSystemViewer.h/10 + 1, SolarSystemViewer.h/10 + 1);
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
        labelPanel.setBounds(0, 0, SolarSystemViewer.h, SolarSystemViewer.h);
        add(labelPanel);
 
        for(int j = 0; j < 9; j++)
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
                angle += 0.05;
                if(angle >= 20.5) {
                    return;
                }

                int xVenus = sunX + sunDiameter / 3 + (int)(radiusVenus * Math.cos(0.5 * angle + 4.4));
                int yVenus = sunY + sunDiameter / 3 + (int)(radiusVenus * Math.sin(0.5 * angle + 4.4));

                int xEarth = sunX + sunDiameter / 3 + (int)(radiusEarth * Math.cos(0.308 * angle + 2.5));
                int yEarth = sunY + sunDiameter / 3 + (int)(radiusEarth * Math.sin(0.308 * angle + 2.5));

                int xMoon = xEarth + earthDiameter / 4 + (int)(radiusMoon * Math.cos(4.16 * angle + 3.8));
                int yMoon = yEarth + earthDiameter / 4 + (int)(radiusMoon * Math.sin(4.16 * angle + 3.8));
                
                int xMars = sunX + sunDiameter / 3 + (int)(radiusMars * Math.cos(0.164 * angle + 3.8));
                int yMars = sunY + sunDiameter / 3 + (int)(radiusMars * Math.sin(0.164 * angle + 3.8));
                
                int xJupiter = sunX + sunDiameter / 3 + (int)(radiusJupiter * Math.cos(0.026 * angle + 5.7));
                int yJupiter = sunY + sunDiameter / 3 + (int)(radiusJupiter * Math.sin(0.026 * angle + 5.7));
                
                int xSaturn = sunX + sunDiameter / 3 + (int)(radiusSaturn * Math.cos(0.010 * angle + 0.7));
                int ySaturn = sunY + sunDiameter / 3 + (int)(radiusSaturn * Math.sin(0.010 * angle + 0.7));
                
                int xTitan = 10 + xSaturn + saturnDiameter / 4 + (int)(radiusTitan * Math.cos(7.03 * angle + Math.PI));
                int yTitan = ySaturn + saturnDiameter / 4 - 4 + (int)(radiusTitan * Math.sin(7.03 * angle + Math.PI));
                
                int xProbe = xStartEarth + (int) angle * 20;
                int yProbe = yStartEarth + (int) angle * 20;
                
                label[1].setLocation(xVenus, yVenus);
                label[2].setLocation(xEarth, yEarth);
                label[3].setLocation(xMoon, yMoon);
                label[4].setLocation(xMars, yMars);
                label[5].setLocation(xJupiter, yJupiter);
                label[6].setLocation(xSaturn, ySaturn);
                label[7].setLocation(xTitan, yTitan);
                label[8].setLocation(xProbe, yProbe);
            }
        });
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
// mport javax.imageio.ImageIO;
        
// public class SolarSystem extends JPanel implements ActionListener
// {
//     BufferedImage trial;
//     BufferedImage imageBuffer;
//     ImageObserver observer;        
//     Timer timer = new Timer(1, this);
//     private int planetX = 0;
//     private int planetY = 0;

//     public void paintComponent(Graphics g) 
//     {
//         Graphics2D g2D = (Graphics2D) g;
//         timer.start();

//         double starX[] = new double[300];
//         double starY[] = new double[300];

// @ -63,9 +64,9 @@ public class SolarSystem extends JPanel implements ActionListener
//             // g2D.drawLine(0, 0, 800, 800);
//             // g2D.drawLine(0, 800, 800, 0);
//             String path = "cbSun.png";
//             trial = ImageIO.read(getClass().getResource(path));
//             imageBuffer = ImageIO.read(getClass().getResource(path));
            
//             g2D.drawImage(trial, sunX, sunY, SolarSystemViewer.h/15, SolarSystemViewer.h/15, observer);
//             g2D.drawImage(imageBuffer, sunX, sunY, SolarSystemViewer.h/15, SolarSystemViewer.h/15, observer);
//             // g2D.drawString("SUN", sunX, sunY + 80);
//         } 
//         catch (IOException e) 
// @ -174,7 +175,7 @@ public class SolarSystem extends JPanel implements ActionListener
//                 {
//                     System.out.println(ax);
//                     String path = image[j - 1];
//                     trial = ImageIO.read(getClass().getResource(path));
//                     imageBuffer = ImageIO.read(getClass().getResource(path));
//                     switch(j - 1) {
//                         case 0, 1: 
//                             offset = 0;
// @ -213,7 +214,7 @@ public class SolarSystem extends JPanel implements ActionListener

//                     }

//                     g2D.drawImage(trial, (int) ((sunX+SolarSystemViewer.h/30)+ax)-15-offset,(int) ((sunY+SolarSystemViewer.h/30)-ay)-15-offset , adjustW, adjustH, observer);
//                     g2D.drawImage(imageBuffer, (int) ((sunX+SolarSystemViewer.h/30)+ax)-15-offset,(int) ((sunY+SolarSystemViewer.h/30)-ay)-15-offset , adjustW, adjustH, observer);
//                     // g2D.drawString(label[i], (int) (sunX+ax)-10, (int) (sunY-ay)-10);

//                     // g2D.drawImage(trial, (int) Math.round(-1*((SolarSystemViewer.h/2 - 3)-ax)+(SolarSystemViewer.h)) ,(int) Math.round((SolarSystemViewer.h/2 - 3)-ay) , 7, 7, observer);

