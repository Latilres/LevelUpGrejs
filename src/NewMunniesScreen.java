import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Tengil on 17/05/2017.
 */
public class NewMunniesScreen implements FocusListener, ActionListener {

    private JFrame thisWindow;
    JTextField addNewMunnies;
    JButton addNewMunniesBtn;

    NewMunniesScreen(JFrame theWindow){
        this.thisWindow = theWindow;
        createTheWindow();
        createAddMunniesThing();
        thisWindow.pack();
        thisWindow.setVisible(true);
    }

    private void createTheWindow(){
        try {
            thisWindow.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("lvlupbg.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createAddMunniesThing() {
        JPanel addNewMunniesPnl = new JPanel();
        //addNewMunniesPnl.setLayout(new FlowLayout());
        addNewMunniesPnl.setOpaque(false);
        addNewMunniesPnl.setBounds(100, 120, 300, 100);

        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.anchor = GridBagConstraints.NORTHWEST;
        gridBag.weightx = 0.5;
        gridBag.gridy = 0;
        gridBag.gridx = 0;
        JTextField munniesText = new JTextField("What should the new munnies be called?");
        munniesText.setEditable(false);
        addNewMunniesPnl.add(munniesText,gridBag);
        gridBag.gridx = 1;
        addNewMunnies = new JTextField("Name dat munnies to what you want!");
        addNewMunnies.setEditable(true);
        addNewMunnies.addFocusListener(this);
        addNewMunnies.setForeground(Color.GRAY);
        addNewMunniesPnl.add(addNewMunnies, gridBag);

        gridBag.gridx = 2;
        addNewMunniesBtn = new JButton("More things!");
        addNewMunniesBtn.addActionListener(this);
        addNewMunniesPnl.add(addNewMunniesBtn, gridBag);

        gridBag.gridx = 3;
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisWindow.setVisible(false);
                StartScreen woop = new StartScreen();
            }
        });
        addNewMunniesPnl.add(backBtn, gridBag);

        thisWindow.add(addNewMunniesPnl);
        //thisWindow.add(addNewMunnies);
    }

    public void focusGained(FocusEvent e) {
        if (e.getSource() == addNewMunnies) {
            addNewMunnies.setText("");
            addNewMunnies.setForeground(Color.BLACK);
        }
    }

    public void focusLost(FocusEvent e) {
        if (e.getSource() == addNewMunnies && addNewMunnies.getText().equals("")) {
            addNewMunnies.setForeground(Color.GRAY);
            addNewMunnies.setText("No munnies?");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addNewMunniesBtn) {
            try {
                File file = new File(System.getProperty("user.dir"), addNewMunnies.getText() + ".txt");
                if (file.createNewFile()) {
                    JOptionPane.showMessageDialog(null, "More Munnies! :D", "Woho!", JOptionPane.WARNING_MESSAGE);
                    try {
                        FileWriter theWriter = new FileWriter(file);
                        theWriter.write("0");
                        theWriter.close();
                    } catch (IOException ioe){
                        ioe.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Dat munnies finns redan.", "...", JOptionPane.WARNING_MESSAGE);
                }

            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }else {
            JOptionPane.showMessageDialog(null, "Behöver ju vara pengar...", "Felaktigt format.", JOptionPane.WARNING_MESSAGE);
        }
        addNewMunnies.setForeground(Color.GRAY);
        addNewMunnies.setText("Mer pengar?");
    }
}
