package phase_1;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SolarSystem extends JPanel
{
    JLabel label[] = new JLabel[8];
    BufferedImage image[] = new BufferedImage[8];
    ImageIcon icon[] = new ImageIcon[8];
    ImageIcon zoomIcon[] = new ImageIcon[8];

    public SolarSystem() 
    {
        celestialObjects.list();
        final int sunX = (int) ((celestialObjects.mars.getDistanceFromSun()/SolarSystemViewer.scale) + 120)-((SolarSystemViewer.h/8 + 1)/2);
        final int sunY = (int) SolarSystemViewer.h/3-((SolarSystemViewer.h/8 + 1)/2);

        String picID[] = {"cbSun.png", "cbVenus.png", "cbEarth.png", "cbMoon.png", "cbMars.png", "cbJupiter.png", "cbSaturn.png", "cbTitan.png"}; 

        for(int i = 0; i < picID.length; i++)
        {
            celestialObjects r = celestialObjects.getBody(i);
            try 
            {
                if(i == 0)// sun
                {
                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/10, SolarSystemViewer.h/10, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/8, SolarSystemViewer.h/8, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds(sunX, sunY, SolarSystemViewer.h/8 + 1, SolarSystemViewer.h/8 + 1);
                }
                else if(i == 1)// venus
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/28, SolarSystemViewer.h/28, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/20, SolarSystemViewer.h/20, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)-(SolarSystemViewer.h/20 + 1)/2, (int) -(sunY+ay)-(SolarSystemViewer.h/20 + 1)/2 + (2*sunY), SolarSystemViewer.h/20 + 1, SolarSystemViewer.h/20 + 1);
                }
                else if(i == 2)// earth
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/28, SolarSystemViewer.h/28, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/20, SolarSystemViewer.h/20, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)-(SolarSystemViewer.h/20 + 1)/2, (int) -(sunY+ay)-(SolarSystemViewer.h/20 + 1)/2 + (2*sunY), SolarSystemViewer.h/20 + 1, SolarSystemViewer.h/20 + 1);
                }
                else if(i == 3)// moon
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/40, SolarSystemViewer.h/40, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/32, SolarSystemViewer.h/32, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)((sunX+ax)-(SolarSystemViewer.h/32 + 1)/2), (int) -((sunY+ay)-(SolarSystemViewer.h/32 + 1)/2 + (2*sunY)), SolarSystemViewer.h/32 + 1, SolarSystemViewer.h/32 + 1);
                }
                else if(i == 4)// mars
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/30, SolarSystemViewer.h/30, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/22, SolarSystemViewer.h/22, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)-(SolarSystemViewer.h/22 + 1)/2, (int) -(sunY+ay)-(SolarSystemViewer.h/22 + 1)/2 + (2*sunY), SolarSystemViewer.h/22 + 1, SolarSystemViewer.h/22 + 1);
                }
                else if(i == 5)// jupiter
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/19, SolarSystemViewer.h/19, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/16, SolarSystemViewer.h/16, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)-(SolarSystemViewer.h/16 + 1)/2, (int) -(sunY+ay)-(SolarSystemViewer.h/16 + 1)/2 + (2*sunY), SolarSystemViewer.h/16 + 1, SolarSystemViewer.h/16 + 1);
                }
                else if(i == 6)// saturn
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/11, SolarSystemViewer.h/19, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/9, SolarSystemViewer.h/16, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int)(sunX+ax)-(SolarSystemViewer.h/9 + 1)/2, (int) -(sunY+ay)-(SolarSystemViewer.h/16 + 1)/2 + (2*sunY), SolarSystemViewer.h/9 + 1, SolarSystemViewer.h/16 + 1);
                }
                else if(i == 7)// titan
                {
                    double ax = r.x1/SolarSystemViewer.scale;
                    double ay = r.x2/SolarSystemViewer.scale;

                    image[i] = ImageIO.read(getClass().getResource(picID[i]));
                    icon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/40, SolarSystemViewer.h/40, Image.SCALE_SMOOTH));
                    zoomIcon[i] = new ImageIcon(image[i].getScaledInstance(SolarSystemViewer.h/32, SolarSystemViewer.h/32, Image.SCALE_SMOOTH));

                    label[i] = new JLabel(icon[i]);

                    label[i].setBounds((int) ((sunX+ax)-(SolarSystemViewer.h/32 + 1)/2), (int) -((sunY+ay)-(SolarSystemViewer.h/32 + 1)/2 + (2*sunY)), SolarSystemViewer.h/32 + 1, SolarSystemViewer.h/32 + 1);
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
 
        for(int j = 0; j < 8; j++)
        {
            labelPanel.add(label[j]);
        }
 
        for(int t = 0; t < 8; t++)
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
    }
}
//! ORBITDRAWER MOVED TO ITS OWN SEPARATE CLASS. DONT FREAK OUT.

