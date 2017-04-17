import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PicPanel extends JPanel {

    private String imagePath;
    private BufferedImage bgImage;

    /**
     * @param imagePath Path of picture in string
     */
    PicPanel(String imagePath){
        this.imagePath = imagePath;
        imageLoader();
    }

    /**
     * Uses imagePath to create new file which then can be painted.
     */
    private void imageLoader(){
        try {
            bgImage = ImageIO.read(this.getClass().getResourceAsStream(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Paints background image.
     * @param g graphics object
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(bgImage,0,0,this);
    }
}