package phase_1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.security.spec.EllipticCurve;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SolarSystem extends JPanel 
{
    BufferedImage trial;
    ImageObserver observer;

    public void paintComponent(Graphics g) 
    {
        Graphics2D g2D = (Graphics2D) g;
    
        for(int i = 0; i < 50; i++)
        {
            g2D.setStroke(new BasicStroke((float) (Math.random()*0.2)));
            g.setColor(new Color(((int)Math.round(Math.random()*100000))));
            double x = Math.random()*SolarSystemViewer.h;
            double y = Math.random()*SolarSystemViewer.h;
            if((x < SolarSystemViewer.h/2 - 25 || x > SolarSystemViewer.h/2 + 25) || (y < SolarSystemViewer.h/2 - 25 || y > SolarSystemViewer.h/2 + 25))
            {
                Line2D.Double star = new Line2D.Double(x, y, x, y);
                g2D.draw(star);
            }
        }

        for(int i = 0; i < 250; i++)
        {
            g2D.setStroke(new BasicStroke((float) (Math.random()*0.2)));
            g.setColor(Color.WHITE);
            double x = Math.random()*SolarSystemViewer.h;
            double y = Math.random()*SolarSystemViewer.h;
            if((x < SolarSystemViewer.h/2 - 25 || x > SolarSystemViewer.h/2 + 25) || (y < SolarSystemViewer.h/2 - 25 || y > SolarSystemViewer.h/2 + 25))            {
                Line2D.Double star = new Line2D.Double(x, y, x, y);
                g2D.draw(star);
            }
        }
        
        g2D.setStroke(new BasicStroke((float) (1400000/SolarSystemViewer.scale)));
        g.setColor(Color.ORANGE);
        Line2D.Double sun = new Line2D.Double(SolarSystemViewer.h/2, SolarSystemViewer.h/2, SolarSystemViewer.h/2,SolarSystemViewer.h/2);
        g2D.draw(sun);

        try 
        {
            trial = ImageIO.read(getClass().getResource("sun.png"));
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        g2D.drawString("sun", SolarSystemViewer.h/2, 400);
        g2D.drawImage(trial, SolarSystemViewer.h/2 - 25, SolarSystemViewer.h/2 - 25, 50, 50, observer);


        // g2D.setStroke(new BasicStroke(1));
        // g.setColor(Color.GREEN);
        // Ellipse2D.Double venusOrbit = new Ellipse2D.Double(370-29.6, 370-29.6, 29.6*2, 29.6*2);
        // g2D.draw(venusOrbit);

        // g2D.setStroke(new BasicStroke(1));
        // g.setColor(Color.BLUE);
        // Ellipse2D.Double earthOrbit = new Ellipse2D.Double(370-42.1230769231, 370-42.1230769231, 42.1230769231*2, 42.1230769231*2);
        // g2D.draw(earthOrbit);

        // g2D.setStroke(new BasicStroke(1));
        // g.setColor(Color.RED);
        // Ellipse2D.Double marsOrbit = new Ellipse2D.Double(370-53.7923076924, 370-53.7923076924, 53.7923076924*2, 53.7923076924*2);
        // g2D.draw(marsOrbit);

        // g2D.setStroke(new BasicStroke(1));
        // g.setColor(Color.YELLOW);
        // Ellipse2D.Double jupiterOrbit = new Ellipse2D.Double(370-197.238461539, 370-197.238461539, 197.238461539*2, 197.238461539*2);
        // g2D.draw(jupiterOrbit);

        // g2D.setStroke(new BasicStroke(1));
        // g.setColor(Color.PINK);
        // Ellipse2D.Double saturnOrbit = new Ellipse2D.Double(370-355.76923077, 370-355.76923077, 355.76923077*2, 355.76923077*2);
        // g2D.draw(saturnOrbit);



        // String image[] = {"venus.png", "earth.png", "moon.png", "mars.png", "jupiter.png", "saturn.png", "titan.png"}; 
        // String label[] = {"venus", "earth", "moon", "mars", "jupiter", "saturn", "titan"}; 

        // for(//for i: object in array)
        // {
        //     double x = celestialObjects(i).getX1();
        //     double y = celestialObjects(i).getX2();

        //     trial = ImageIO.read(getClass().getResource(image[i]));
        //     g2D.drawImage(trial, SolarSystemViewer.h/2, SolarSystemViewer.h/2, 50, 50, observer);

        //     Ellipse2D.Double arrayObject = new Ellipse2D.Double((x/SolarSystemViewer.scale)+(SolarSystemViewer.h)-(10/2), -y/SolarSystemViewer.scale+(SolarSystemViewer.h)-(10/2), 10, 10);
        //     g2D.draw(arrayObject);

        //}        
    }
}

