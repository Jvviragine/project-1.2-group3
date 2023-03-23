package phase_1;

import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolarSystemViewer{
    public static Toolkit tk = Toolkit.getDefaultToolkit();
    public static Dimension screenSize = tk.getScreenSize();
    public static int h = screenSize.height;
    public static final int SOLAR_SYSTEM_HEIGHT = h;

    // public static final double scale = d/(((h/2)-(5*h/100))); backup scale
    // scale = maximum distance rounded up / (screen width/2) screen width km/px;
    // current scale

    public static double s = (celestialObjects.getMaxDistanceFromSun(1253801723.95465, -760453007.810989, -36697431.1565206));
    public static double m = (celestialObjects.getMaxDistanceFromSun(-159116303.422552, 189235671.561057, 7870476.08522969));
    public static double sum = s + m;
    public static final double scale = Math.ceil(sum / 100000000) * 100000000 / (h-200);

    public static void main(String[] args) {

        // JFrame initialConditions = new JFrame();
        // initialConditions.setTitle("Starting Conditions");
        // JPanel format = new JPanel();
        // format.setLayout(new GridLayout(1, 7));
        // JPanel x1 = new JPanel();
        // JPanel x2 = new JPanel();
        // JPanel x3 = new JPanel();
        // JPanel v1 = new JPanel();
        // JPanel v2 = new JPanel();
        // JPanel v3 = new JPanel();
        // JButton confirm = new JButton();
        // JLabel x11 = new JLabel("x0");
        // JLabel x21 = new JLabel("y0");
        // JLabel x31 = new JLabel("z0");
        // JLabel v11 = new JLabel("v1");
        // JLabel v21 = new JLabel("v2");
        // JLabel v31 = new JLabel("v3");
        // JTextField x12 = new JTextField();
        // JTextField x22 = new JTextField();
        // JTextField x32 = new JTextField();
        // JTextField v12 = new JTextField();
        // JTextField v22 = new JTextField();
        // JTextField v32 = new JTextField();
        // initialConditions.add(format);
        // format.add(x1);
        // format.add(x2);
        // format.add(x3);
        // format.add(v1);
        // format.add(v2);
        // format.add(v3);
        // format.add(confirm);
        // x1.add(x11);
        // x1.add(x12);
        // x2.add(x21);
        // x2.add(x22);
        // x3.add(x31);
        // x3.add(x32);
        // v1.add(v11);
        // v1.add(v12);
        // v2.add(v21);
        // v2.add(v22);
        // v3.add(v31);
        // v3.add(v32);
        // format.setVisible(true);
        // try {
        //     double input1 = Double.parseDouble(x12.getText());
        //     double input2 = Double.parseDouble(x22.getText());
        //     double input3 = Double.parseDouble(x32.getText());
        //     double input4 = Double.parseDouble(v12.getText());
        //     double input5 = Double.parseDouble(v22.getText());
        //     double input6 = Double.parseDouble(v32.getText());
        // } catch (NumberFormatException e) {
        //     // TODO: handle exception
        // } 
        // confirm.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         if(input1 != 0 && input2 != 0 && input3 != 0 && input4 != 0 && input5 != 0 && input6 != 0)
        //     format.setVisible(false);
        //     }
        // });
     

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
