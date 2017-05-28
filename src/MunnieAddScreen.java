import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class MunnieAddScreen implements ActionListener, FocusListener {

    private JFrame thisWindow;
    private JComboBox listOfMunnies;
    private String pickedMunnie;
    private JTextField howManyMunnies;
    private JTextField newMunnies;
    private JButton addMunniesBtn;
    private JButton addNewMunniesBtn;

    MunnieAddScreen(JFrame theWindow){
        this.thisWindow = theWindow;
        createTheWindow();
        createTheAddMunniesPart();
        thisWindow.pack();
        thisWindow.setVisible(true);
    }

    private void createTheWindow(){
        try {
            thisWindow.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("lvlupbg.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //thisWindow.setLayout(new GridBagLayout());
    }

    private void createTheAddMunniesPart(){
        GridBagConstraints gridBag = new GridBagConstraints();
        JPanel moreMunnies = new JPanel();
        moreMunnies.setOpaque(false);
        // ~~~~~~~~~~~~~~Borde orka fixa funktion~~~~~~~~~~~~~~~~~~~~~~~~
        ArrayList<String> theMunnies;
        int nrOfMunnies;
        int index = 0;
        //addNewMunnies.setOpaque(false);
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
            System.out.println(pickedMunnie);
        });
        // ~~~~~~~~~~~~~~till hit~~~~~~~~~~~~~~~~~~~~~~~~
        // rutnätet som drop-down, munnie-fältet och add-knapppen använder
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.anchor = GridBagConstraints.NORTHWEST;
        gridBag.weightx = 0.5;
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        moreMunnies.setBounds(60,160,500,100);
        moreMunnies.add(listOfMunnies, gridBag);
        howManyMunnies = new JTextField ("How many munnies to add");
        howManyMunnies.setEditable(true);
        howManyMunnies.addFocusListener(this);
        howManyMunnies.setForeground(Color.GRAY);
        gridBag.gridx = 1;
        moreMunnies.add(howManyMunnies,gridBag);
        addMunniesBtn = new JButton("Add the Munnies!");
        addMunniesBtn.addActionListener(this);
        gridBag.gridx = 2;
        moreMunnies.add(addMunniesBtn, gridBag);
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thisWindow.setVisible(false);
                    StartScreen woop = new StartScreen();
            }
        });
        gridBag.gridy = 2;
        moreMunnies.add(backBtn);
        thisWindow.add(moreMunnies);
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
                    pickedMunnie = file.getName().substring(0, file.getName().length() - 4);
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
            howManyMunnies.setForeground(Color.BLACK);
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == howManyMunnies && howManyMunnies.getText().equals("")) {
            howManyMunnies.setForeground(Color.GRAY);
            howManyMunnies.setText("No munnies?");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == addMunniesBtn){
            if (isNumeric(howManyMunnies)){
                int munniesToAddInt = Integer.parseInt(howManyMunnies.getText());
                int munniesToLog = munniesToAddInt;
                int munniesTotal = munniesToAddInt;

                String theFileName = (pickedMunnie + ".txt");
                File theFile = new File(theFileName);
                File allTheMunniesFile = new File(System.getProperty("user.dir") + "/allmänt.txt");
                Scanner scan;
                Scanner allMunniesScan;
                try{
                    scan = new Scanner(theFile);
                    String munnieInFile = scan.nextLine();
                    String allTheMunnies;
                    if (pickedMunnie != "allmänt"){
                        allMunniesScan = new Scanner(allTheMunniesFile);
                        allTheMunnies = allMunniesScan.nextLine();
                        munniesTotal += Integer.parseInt(allTheMunnies);
                    }
                    munniesToAddInt += Integer.parseInt(munnieInFile);
                    System.out.println(munniesToAddInt);
                } catch (FileNotFoundException fnfe){
                    fnfe.printStackTrace();
                }
                try {
                    FileWriter theWriter = new FileWriter(theFileName);
                    String munniesToAddStr = Integer.toString(munniesToAddInt);
                    theWriter.write(munniesToAddStr);
                    theWriter.close();
                    if (pickedMunnie != "allmänt"){
                        FileWriter allMunniesWriter = new FileWriter(System.getProperty("user.dir") + "/allmänt.txt");
                        allMunniesWriter.write(Integer.toString(munniesTotal));
                        allMunniesWriter.close();
                    }
                } catch (IOException ioe){
                    ioe.printStackTrace();
                }
                logEverything(munniesToLog);
            } else
                JOptionPane.showMessageDialog(null, "Behöver ju vara pengar...", "Felaktigt format.", JOptionPane.WARNING_MESSAGE);
            howManyMunnies.setForeground(Color.GRAY);
            howManyMunnies.setText("More?");
        }
    }

    private void logEverything(int munnieToLog){
        try {
            PrintWriter out = new PrintWriter(new FileWriter("loggedMunnies.log", true));
            out.println(new Date().toString() + " - " + pickedMunnie + ": " + munnieToLog);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static boolean isNumeric(JTextField str) {
        try {
            double d = Double.parseDouble(str.getText());
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
