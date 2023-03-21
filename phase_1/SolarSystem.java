package phase_1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.security.spec.EllipticCurve;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;


/*public class SolarSystem extends JPanel {
    //Next section is temporary
    final double SATURNX = 1253801723.95465;
    final double SATURNY = -760453007.810989;
    final double EARTHX = -148186906.893642;
    final double EARTHY = -27823158.5715694;
    
    final double SOLAR_SYSTEM_PIXEL = 3259670.696; //Distance to Saturn (1466851813.0793) divided by (SOLAR_SYSTEM_WIDTH divided by 2 minus 50)
    final double PLANET_SIZE = 5; //Arbitrary number for now
    final double PLANET_SIZE_REVISED = 20; //For testing out size relations
    
    //Takes the x and y coordinates in km and plots their placement, don't know how to add input variables here yet
    public void paintComponent(Graphics g) {

        Graphics2D g2D = (Graphics2D) g;
        Ellipse2D.Double SaturnOrbit = new Ellipse2D.Double(500-450, 500-450, 450*2, 450*2); //I determined Saturn's distance to the sun to be constantly 450 pixels; this is not an accurate orbit though since it ignores the z-axis
        g2D.draw(SaturnOrbit);
        Ellipse2D.Double Saturn = new Ellipse2D.Double((SATURNX/SOLAR_SYSTEM_PIXEL)+500-(PLANET_SIZE), -1*(SATURNY/SOLAR_SYSTEM_PIXEL)+500-(PLANET_SIZE), PLANET_SIZE*2, PLANET_SIZE*2);
        g2D.draw(Saturn);
        Ellipse2D.Double Earth = new Ellipse2D.Double((EARTHX/SOLAR_SYSTEM_PIXEL)+500-(PLANET_SIZE/2), -1*(EARTHY/SOLAR_SYSTEM_PIXEL)+500-(PLANET_SIZE/2), PLANET_SIZE, PLANET_SIZE);
        g2D.draw(Earth);
    Ellipse2D.Double Sun = new Ellipse2D.Double(500-(PLANET_SIZE_REVISED/2), 500-(PLANET_SIZE_REVISED/2), PLANET_SIZE_REVISED, PLANET_SIZE_REVISED);
        g2D.draw(Sun);
        Line2D.Double lineY = new Line2D.Double(500, 0, 500, 1000);
        g2D.draw(lineY);
        Line2D.Double lineX = new Line2D.Double(0, 500, 1000, 500);
        g2D.draw(lineX);
        g2D.drawString("earth", Math.round(EARTHX/SOLAR_SYSTEM_PIXEL-PLANET_SIZE*3+500), Math.round(-1*EARTHY/SOLAR_SYSTEM_PIXEL+PLANET_SIZE*3+500));
        g2D.drawString("saturn", Math.round(SATURNX/SOLAR_SYSTEM_PIXEL-PLANET_SIZE*4+500), Math.round(-1*SATURNY/SOLAR_SYSTEM_PIXEL+PLANET_SIZE*4+500));
        /* Testing out some generalizations, no clue about the actual format it will be finalized in 
        for(i = 0; i < arr.length; i++){
            Ellipse2D.Double Planet = new Ellipse2D.Double((arr[i].X/SOLAR_SYSTEM_PIXEL)+500-(PLANET_SIZE/2), -1*(arr[i].Y/SOLAR_SYSTEM_PIXEL)+500-(PLANET_SIZE/2), PLANET_SIZE, PLANET_SIZE);
            g2D.draw(Planet);
        }*/ 
        
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
        g2D.setStroke(new BasicStroke((float) (1400000/SolarSystemViewer.scale)));
        g.setColor(Color.ORANGE);
        Line2D.Double sun = new Line2D.Double(SolarSystemViewer.h/2, SolarSystemViewer.h/2, SolarSystemViewer.h/2,SolarSystemViewer.h/2);
        g2D.draw(sun);

        // g2D.setStroke(new BasicStroke(1));
        // g.setColor(Color.GREEN);
        // Ellipse2D.Double venusOrbit = new Ellipse2D.Double(370-29.6, 370-29.6, 29.6*2, 29.6*2);
        // g2D.draw(venusOrbit);

        String image[] = {"venus.png", "earth.png", "moon.png", "mars.png", "jupiter.png", "saturn.png", "titan.png"}; 
        String label[] = {"venus", "earth", "moon", "mars", "jupiter", "saturn", "titan"}; 

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

