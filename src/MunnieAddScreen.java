import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MunnieAddScreen implements ActionListener, FocusListener {

    private JFrame thisWindow;
    private JComboBox listOfMunnies;
    private String pickedMunnie;
    private JTextField howManyMunnies;
    private JButton addMunniesBtn;

    MunnieAddScreen(JFrame theWindow){
        this.thisWindow = theWindow;
        createTheWindow();
        createTheAddMunniesPart();
    }

    private void createTheWindow(){
        try {
            thisWindow.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("lvlupbg.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        thisWindow.setLayout(new GridBagLayout());
        thisWindow.pack();
        thisWindow.setVisible(true);
    }

    private void createTheAddMunniesPart(){
        GridBagConstraints gridBag = new GridBagConstraints();
        JPanel moreMunnies = new JPanel();
        ArrayList<String> theMunnies;
        int nrOfMunnies;
        int index = 0;
        //moreMunnies.setLayout(new GridBagLayout());
        moreMunnies.setOpaque(false);
        //moreMunnies.add(createListOfMunnies());
        theMunnies = getListOfFiles();
        nrOfMunnies = theMunnies.size();
        String[] theMunniesStrArr = new String[nrOfMunnies];
        for (String munniesCollecting: theMunnies){
            theMunniesStrArr[index] = munniesCollecting;
            index++;
        }
        listOfMunnies = new JComboBox(theMunniesStrArr);
        listOfMunnies.setSelectedIndex(nrOfMunnies-1);
        listOfMunnies.addActionListener(e -> {
            JComboBox cb = (JComboBox)e.getSource();
            pickedMunnie = (String)cb.getSelectedItem();
        });

        // rutnätet som drop-down, munnie-fältet och add-knapp
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        //gridBag.anchor = GridBagConstraints.CENTER;
        gridBag.weightx = 0.5;
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        moreMunnies.setBounds(20,20,600,75);
        moreMunnies.add(listOfMunnies, gridBag);
        howManyMunnies = new JTextField ("How many munnies do you want to add?");
        howManyMunnies.setEditable(true);
        //howManyMunnies.addActionListener(e -> {        });
        gridBag.gridx = 1;
        moreMunnies.add(howManyMunnies,gridBag);
        addMunniesBtn = new JButton("Add the Munnies!");
        addMunniesBtn.addActionListener(this);
        gridBag.gridx = 2;
        moreMunnies.add(addMunniesBtn, gridBag);

        thisWindow.add(moreMunnies);
        thisWindow.setVisible(true);
    }

    private ArrayList<String> getListOfFiles(){
        ArrayList<String> differentMunnies = new ArrayList<>();
        try {
            File folder = new File(System.getProperty("user.dir"));
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles == null)
                return differentMunnies;
            for (File file: listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    differentMunnies.add(file.getName().substring(0, file.getName().length() - 4));
                }
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return differentMunnies;
    }

    public void focusGained(FocusEvent e) {
        if (e.getSource() == howManyMunnies) {
            howManyMunnies.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == howManyMunnies && howManyMunnies.getText().equals("")) {
            howManyMunnies.setText("No munnies?");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addMunniesBtn){ //todo och texten i howManyMunnies är int
            // howManyMunnies.getText()
            //todo lägg till beloppet i rätt fil. try öppna -> läs in belopp -> lägg till belopp -> stäng fil
        }

    }
}
