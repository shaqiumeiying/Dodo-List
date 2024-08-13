package ui.gui;

import javax.swing.*;
import java.awt.*;

//referred to:
//https://stackoverflow.com/questions/1466240/how-to-set-an-image-as-a-background-for-frame-in-swing-gui-of-java
//https://stackoverflow.com/questions/7109047/add-image-to-gui
//https://www.daniweb.com/programming/software-development/threads/351374/adding-image-to-background-of-gui
//https://www.youtube.com/watch?v=yGcYoz0s94E
//https://stackoverflow.com/questions/1064977/setting-background-images-in-jframe

//Set a background picture class
public class BackgroundPicture extends JComponent {

    private Image image;

    public BackgroundPicture(
            Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
