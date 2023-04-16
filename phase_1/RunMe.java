package phase_1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Class responsible for running the simulation
public class RunMe implements ActionListener{
    JFrame frame;
    JLabel x1;
    JLabel x2;
    JLabel x3;
    JLabel v1;
    JLabel v2;
    JLabel v3;
    JTextField x1Text;
    JTextField x2Text;
    JTextField x3Text;
    JTextField v1Text;
    JTextField v2Text;
    JTextField v3Text;
    JButton button;
    static Vector initPos = new Vector();
    static Vector initVelo = new Vector();

    public static void main(String[] args) {
        RunMe login = new RunMe();
    }

    //Creating the login window in which the user can input the initial positions and velocities for the probe
    public RunMe(){

        frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(null);        

        JLabel label = new JLabel("Please enter initial positions (x, y, z) in km and velocities (v1, v2, v3) in km/s;p");
        label.setBounds(30, 10, 500, 25);
        panel.add(label);

        JLabel label2 = new JLabel("If the information is left blank, all the values will be set to reach Titan!");
        label2.setBounds(30, 40, 500, 25);
        panel.add(label2);

        x1 = new JLabel("x: ");
        x1.setBounds(180,80,80,25);
        panel.add(x1);

        x1Text = new JTextField(30);
        x1Text.setBounds(200,80,165,25);
        panel.add(x1Text);

        x2 = new JLabel("y: ");
        x2.setBounds(180,120,80,25);
        panel.add(x2);

        x2Text = new JTextField(30);
        x2Text.setBounds(200,120,165,25);
        panel.add(x2Text);

        x3 = new JLabel("z: ");
        x3.setBounds(180,160,80,25);
        panel.add(x3);

        x3Text = new JTextField(30);
        x3Text.setBounds(200,160,165,25);
        panel.add(x3Text);

        v1 = new JLabel("v1: ");
        v1.setBounds(180,200,80,25);
        panel.add(v1);

        v1Text = new JTextField(30);
        v1Text.setBounds(200,200,165,25);
        panel.add(v1Text);

        v2 = new JLabel("v2: ");
        v2.setBounds(180,240,80,25);
        panel.add(v2);

        v2Text = new JTextField(30);
        v2Text.setBounds(200,240,165,25);
        panel.add(v2Text);

        v3 = new JLabel("v3: ");
        v3.setBounds(180,280,80,25);
        panel.add(v3);

        v3Text = new JTextField(30);
        v3Text.setBounds(200,280,165,25);
        panel.add(v3Text);

        button = new JButton("Go to Titan!");
        button.setBounds(200, 320, 165, 25);
        button.addActionListener(this);
        panel.add(button);

        frame.add(panel);
        frame.setSize(600,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    /**
     * Action listener, when the button is clicked the initial values are assigned and we create Main, 
     * which runs the simulation and we run the GUI of the Solar System.
     */
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == button){
            
            try{
                
                initPos.setX(Double.parseDouble(x1Text.getText()));
                initPos.setY(Double.parseDouble(x2Text.getText()));
                initPos.setZ(Double.parseDouble(x3Text.getText()));

                initVelo.setX(Double.parseDouble(v1Text.getText()));
                initVelo.setY(Double.parseDouble(v2Text.getText()));
                initVelo.setZ(Double.parseDouble(v3Text.getText()));

                Main carl = new Main();
                frame.dispose();
                SolarSystemViewer.main(null);

            }catch(Exception p){
                
                System.out.println("Default values used.");

                initPos.setX(-148458048.395164);
                initPos.setY(-27524868.1841142);
                initPos.setZ(70233.6499287411);

                initVelo.setX(42.42270135156);
                initVelo.setY(-43.62738201925);
                initVelo.setZ(-3.1328169170);

                Main carl = new Main();
                frame.dispose();
                SolarSystemViewer.main(null);
            }
        }
    }
}
