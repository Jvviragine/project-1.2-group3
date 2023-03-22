package phase_1;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class SolarSystemViewer 
{
    public static int h = 750;
    public static final int SOLAR_SYSTEM_HEIGHT = h;
    
    //? SCALING DONE BELOW
    //? CALCULATES THE SCALE BY ROUNDING THE MAXIMUM DISTANCE FROM THE SUN TO THE SCREEN 
    //? SCALE = MAXIMUM DISTANCE ROUNDED UP / (SCREEN WIDTH/2) SCREEN WIDTH KM/PX;

    public static double d = (celestialObjects.getSaturnDistanceFromSun(1253801723.95465, -760453007.810989,-36697431.1565206));
    public static final double scale = Math.ceil(d / 100000000) * 100000000 / ((h / 2));

    public static void main(String[] args) {

        JFrame solarSystemFrame = new JFrame();
        solarSystemFrame.setTitle("SOLAR SYSTEM VISUALISATION");
        solarSystemFrame.setUndecorated(true);
        solarSystemFrame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        solarSystemFrame.setBackground(new Color(000000));

        //? THIS IS TO ENSURE THE WINDOW HAS A BLACK BORDER
        //? BLENDS IN WELL WITH THE SCREEN
        //? REMOVES NOISE IN A WAY???
        
        MetalLookAndFeel.setCurrentTheme(new MyDefaultMetalTheme());
        try 
        {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (Exception e) {
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
