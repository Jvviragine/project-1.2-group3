package phase_1;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.Toolkit;

public class SolarSystemViewer
{
    public static Toolkit tk = Toolkit.getDefaultToolkit();
    public static Dimension screenSize = tk.getScreenSize();
    public static int h = screenSize.height;
    public static final int SOLAR_SYSTEM_HEIGHT = h;

    public static double s = (celestialObjects.getMaxDistanceFromSun(1253801723.95465, -760453007.810989, -36697431.1565206));
    public static double m = (celestialObjects.getMaxDistanceFromSun(-159116303.422552, 189235671.561057, 7870476.08522969));
    public static double sum = s + m;
    public static final double scale = Math.ceil(sum / 100000000) * 100000000 / (h-200);
    public static double[][] rocketLaunch = new double[2][3];

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
        solarSystemFrame.setPreferredSize(new Dimension(SOLAR_SYSTEM_HEIGHT, SOLAR_SYSTEM_HEIGHT));
        solarSystemFrame.pack();
        solarSystemFrame.setResizable(false);
        solarSystemFrame.setLocationRelativeTo(null);
        solarSystemFrame.setVisible(true);
    }
}
