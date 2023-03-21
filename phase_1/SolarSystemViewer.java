package phase_1;

import java.awt.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class SolarSystemViewer 
{
    public static int h = 740;
    public static final int SOLAR_SYSTEM_HEIGHT = h;

    // public static final double scale = Math.ceil(d/100000000)*100000000/((h/2));
    // scale = maximum distance rounded up / (screen width/2) screen width km/px

    public static double d = (celestialObjects.getDistanceFromSun(1253801723.95465, -760453007.810989, -36697431.1565206));
    public static final double scale = d/(((h/2)-(5*h/100)));

    public static void main(String[] args) 
    {
        
        JFrame solarSystemFrame = new JFrame();
        solarSystemFrame.setTitle("Solar System Visualization");
        solarSystemFrame.setUndecorated(true);
        solarSystemFrame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
  
        solarSystemFrame.setBackground(new Color(000000));

        MetalLookAndFeel.setCurrentTheme(new MyDefaultMetalTheme());
        try 
        {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    
        SwingUtilities.updateComponentTreeUI(solarSystemFrame);
        solarSystemFrame.setVisible(true);
        
        SolarSystem solarSystem = new SolarSystem();
        solarSystemFrame.add(solarSystem);
        solarSystemFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        solarSystem.setPreferredSize(new Dimension(SOLAR_SYSTEM_HEIGHT, SOLAR_SYSTEM_HEIGHT));
        solarSystemFrame.pack();
        solarSystemFrame.setResizable(false);
        solarSystemFrame.setLocationRelativeTo(null);
        solarSystemFrame.setVisible(true);
    }
}

class MyDefaultMetalTheme extends DefaultMetalTheme 
{
    public ColorUIResource getWindowTitleInactiveBackground() 
    {
      return new ColorUIResource(java.awt.Color.black);
    }
  
    public ColorUIResource getWindowTitleBackground() 
    {
      return new ColorUIResource(java.awt.Color.black);
    }
  
    public ColorUIResource getPrimaryControlHighlight() 
    {
      return new ColorUIResource(java.awt.Color.black);
    }
  
    public ColorUIResource getPrimaryControlDarkShadow() 
    {
      return new ColorUIResource(java.awt.Color.black);
    }
  
    public ColorUIResource getPrimaryControl() 
    {
      return new ColorUIResource(java.awt.Color.black);
    }
  
    public ColorUIResource getControlDarkShadow() 
    {
      return new ColorUIResource(java.awt.Color.black);
    }
  
    public ColorUIResource getControl() 
    {
      return new ColorUIResource(java.awt.Color.black);
    }
  }
