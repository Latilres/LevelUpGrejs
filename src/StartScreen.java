import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class StartScreen {

    private JFrame theWindow;
    private JButton addMunnies;
    private JButton showMunnies;
    private JButton closeButton;
    private int screenSizeX = 640;
    private int screenSizeY = 350;

    public static void main(String[] args){
        new StartScreen();
    }

    private StartScreen(){
        createTheWindow();
        createTheButtons();
    }

    private void createTheWindow(){
        theWindow = new JFrame("All the Munnies!");
        theWindow.setPreferredSize(new Dimension(screenSizeX,screenSizeY));
        theWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        theWindow.setLocationRelativeTo(null);
        theWindow.setLayout(null);
        // todo: här tror jag att jag kan vilja fixa med bakgrund sen.
        theWindow.pack();
        theWindow.setVisible(true);
    }

    private void createTheButtons(){
        addMunnies = new JButton("Add Munnies");
        //addMunnies.setBounds(50, 50, 50, 50);
        addMunnies.setBorder(new LineBorder(Color.MAGENTA));
        addMunnies.
    }
}
