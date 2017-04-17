import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StartScreen {

    private JFrame theWindow;
    private boolean addMunniesExist;
    private boolean showMunniesExist;
    private MunnieAddScreen munnieAddObj;

    public static void main(String[] args){
        new StartScreen();
    }

    private StartScreen(){
        createTheWindow();
        createTheButtons();
    }

    private void createTheWindow(){
        int screenSizeX = 645;
        int screenSizeY = 400;
        theWindow = new JFrame("All the Munnies!");
        theWindow.setPreferredSize(new Dimension(screenSizeX,screenSizeY));
        theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theWindow.setLocationRelativeTo(null);
        theWindow.setResizable(false);

        try {
            theWindow.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("lvlup.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Nej  ^^
        JMenuBar jmb = new JMenuBar();
        JMenu jmNej = new JMenu("Nej");
        JMenuItem jmiNej = new JMenuItem("Nej!");
        jmNej.add(jmiNej);
        jmiNej.addActionListener(new ActionListener(){
            @Override
                    public void actionPerformed(ActionEvent evt){
                JOptionPane.showMessageDialog(null, "Nej sa jag ju!", ":(", JOptionPane.WARNING_MESSAGE);
                JOptionPane.showMessageDialog(null,"(o.o)", ":'(", JOptionPane.WARNING_MESSAGE);
                JOptionPane.showMessageDialog(null,"ヽ( `д´*)ノ՞", ":(", JOptionPane.WARNING_MESSAGE);
                JOptionPane.showMessageDialog(null," (╯°□°）╯︵ ┻━┻", ".", JOptionPane.WARNING_MESSAGE);
                JOptionPane.showMessageDialog(null,"(Θ︹Θ)ს", "..", JOptionPane.WARNING_MESSAGE);
                JOptionPane.showMessageDialog(null,"(*-_-)", "...", JOptionPane.WARNING_MESSAGE);
            }
        });

        jmb.add(jmNej);
        theWindow.setJMenuBar(jmb);

        theWindow.pack();
        theWindow.setVisible(true);
    }

    private void createTheButtons(){
        JPanel pnlButton = new JPanel();
        pnlButton.setLayout(new FlowLayout());
        pnlButton.setOpaque(false);

        // addMunnies
        JButton addMunnies = new JButton("Add the munnies");
        addMunnies.setBounds(0, 20, 150, 30);
        addMunnies.setBorder(new LineBorder(Color.MAGENTA));
        addMunnies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                theWindow.setVisible(false);
                if (!addMunniesExist){
                    addMunniesExist = true;
                    munnieAddObj = new MunnieAddScreen(theWindow);
                }

            }
        });
        // close
        JButton closeButton = new JButton("Noooo!");
        closeButton.setBounds(255,40,80,30);
        // showMunnies
        JButton showMunnies = new JButton("Look at all the munnies!");
        showMunnies.setBounds(420, 20, 170, 30);
        showMunnies.setBorder(new LineBorder(Color.MAGENTA));

        pnlButton.setBounds(20,270,600,100);
        pnlButton.add(addMunnies);
        pnlButton.add(closeButton);
        pnlButton.add(showMunnies);
        theWindow.add(pnlButton);
    }
}