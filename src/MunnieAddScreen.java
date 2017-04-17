import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MunnieAddScreen {

    private JFrame thisWindow;
    JComboBox listOfMunnies;

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
        thisWindow.setLayout(new BorderLayout());
        thisWindow.pack();
        thisWindow.setVisible(true);
    }

    private void createTheAddMunniesPart(){
        JPanel moreMunnies = new JPanel();
        ArrayList<String> theMunnies;
        int nrOfMunnies;
        int index = 0;
        moreMunnies.setOpaque(false);
        moreMunnies.setSize(600,50);
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
            String pickedMunnie = (String)cb.getSelectedItem();
        });
    }

    private ArrayList<String> getListOfFiles(){
        ArrayList<String> differentMunnies = new ArrayList<>();
        try {
            File folder = new File("D:\\skola\\java\\LevelUpGrejs");
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
}
