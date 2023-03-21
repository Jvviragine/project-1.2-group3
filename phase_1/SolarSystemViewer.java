
package phase_1;
import java.awt.*;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class SolarSystemViewer 
{
    public static int h = 740;
    public static final int SOLAR_SYSTEM_HEIGHT = h;

    // scale = maximum distance/(screen width/2)-5% of screen width km/px
    public static double d = (celestialObjects.getDistanceFromSun(1253801723.95465, -760453007.810989, -36697431.1565206));
    public static final double scale = d/((h/2)-(h*0.05));

    public static void main(String[] args) 
    {
        JFrame solarSystemFrame = new JFrame();
        solarSystemFrame.setTitle("Solar System Visualization");
        solarSystemFrame.setBackground(new Color(000000));
        SolarSystem solarSystem = new SolarSystem();

        // JLabel planet;
        // ImageIcon sun;

        // sun = new ImageIcon(SolarSystemViewer.getResource("planet boss - sun.png"));
        // planet = new JLabel(sun);
        // SolarSystemViewer.add(planet);

        solarSystemFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        solarSystemFrame.add(solarSystem);
        solarSystem.setPreferredSize(new Dimension(SOLAR_SYSTEM_HEIGHT, SOLAR_SYSTEM_HEIGHT));
        solarSystemFrame.pack();
        solarSystemFrame.setResizable(false);
        solarSystemFrame.setLocationRelativeTo(null);
        solarSystemFrame.setVisible(true);
    }
}
