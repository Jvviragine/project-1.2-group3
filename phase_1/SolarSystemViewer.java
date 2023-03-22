
package phase_1;
import java.awt.*;
import javax.swing.*;

public class SolarSystemViewer 
{
    public static void main(String[] args) 
    {
        JFrame Frame = new JFrame();
        Frame.setTitle("Solar System Visualization");
        Frame.setBackground(Color.BLACK);
        SolarSystem solarSystem = new SolarSystem();

        // JLabel planet;
        // ImageIcon sun;

        // sun = new ImageIcon(SolarSystemViewer.getResource("planet boss - sun.png"));
        // planet = new JLabel(sun);
        // SolarSystemViewer.add(planet);

        final int SOLAR_SYSTEM_WIDTH = 740;
        final int SOLAR_SYSTEM_HEIGHT = 740;

        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Frame.add(solarSystem);
        Frame.setPreferredSize(new Dimension(SOLAR_SYSTEM_WIDTH, SOLAR_SYSTEM_HEIGHT)); 
        Frame.pack();
        Frame.setResizable(false);
        Frame.setLocationRelativeTo(null);
        Frame.setVisible(true);
    }
}
