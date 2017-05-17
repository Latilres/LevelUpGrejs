import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MunnieShowScreen {

    private JFrame thisWindow;

    MunnieShowScreen(JFrame theWindow){
        this.thisWindow = theWindow;
        createTheWindow();
        createTheShowMunniesStuff();
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

    private void createTheShowMunniesStuff(){
        GridBagConstraints gridBag = new GridBagConstraints();
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.anchor = GridBagConstraints.NORTHWEST;
        gridBag.weightx = 0.5;
        JPanel moreMunnies = new JPanel();
        moreMunnies.setOpaque(false);
        moreMunnies.setBounds(60,20,500,300);
        JTextField howManyMunnies;
        Font font1 = new Font("SansSerif", Font.BOLD, 15);


        ArrayList<String> theMunnies = getListOfFiles();
        int y = 0;
        for (String allMunnies: theMunnies){
            gridBag.gridx = 0;
            gridBag.gridy = y;
            howManyMunnies = new JTextField(allMunnies + ": " + getTheMunniesFromFile(allMunnies));
            howManyMunnies.setFont(font1);
            howManyMunnies.setEditable(false);
            moreMunnies.add(howManyMunnies, gridBag);
            y++;
        }
        thisWindow.add(moreMunnies);
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisWindow.setVisible(false);
                StartScreen woop = new StartScreen();
            }
        });
        gridBag.gridy = y + 1;
        moreMunnies.add(backBtn, gridBag);
        thisWindow.add(moreMunnies);
    }

    private String getTheMunniesFromFile(String file) {
        String theFileName = (file + ".txt");
        File theFile = new File(theFileName);
        Scanner scan;
        try {
            scan = new Scanner(theFile);
            return scan.nextLine();
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }
        return null;
    }


    private ArrayList<String> getListOfFiles(){
        String pickedMunnie;
        ArrayList<String> differentMunnies = new ArrayList<>();
        try {
            File folder = new File(System.getProperty("user.dir"));
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles == null)
                return null;
            for (File file: listOfFiles) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    differentMunnies.add(file.getName().substring(0, file.getName().length() - 4));
                    //pickedMunnie = file.getName().substring(0, file.getName().length() - 4);
                }
            }
        } catch (NullPointerException e){
            e.printStackTrace();
        }
        return differentMunnies;
    }
}
