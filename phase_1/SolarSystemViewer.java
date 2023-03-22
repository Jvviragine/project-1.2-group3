package phase_1;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class SolarSystemViewer {
    public static int h = 740;
    public static final int SOLAR_SYSTEM_HEIGHT = h;

    // public static final double scale = d/(((h/2)-(5*h/100))); backup scale
    // scale = maximum distance rounded up / (screen width/2) screen width km/px;
    // current scale

    public static double s = (celestialObjects.getMaxDistanceFromSun(1253801723.95465, -760453007.810989, -36697431.1565206));
    public static double m = (celestialObjects.getMaxDistanceFromSun(-159116303.422552, 189235671.561057, 7870476.08522969));
    public static double sum = s + m;
    public static final double scale = Math.ceil(sum / 100000000) * 100000000 / (h-200);

    public static void main(String[] args) {

        JFrame solarSystemFrame = new JFrame();
        solarSystemFrame.setTitle("Solar System Visualization");
        solarSystemFrame.setUndecorated(true);
        solarSystemFrame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        solarSystemFrame.setBackground(Color.BLACK);

        MetalLookAndFeel.setCurrentTheme(new MyDefaultMetalTheme());
        try {
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
