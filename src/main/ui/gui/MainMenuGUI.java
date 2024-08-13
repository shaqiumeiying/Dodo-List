package ui.gui;

import model.Checklist;
import persistence.JsonLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//This is to generate a main menu user interface

public class MainMenuGUI extends JFrame implements ActionListener {
    private Checklist checklist = new Checklist("Diana's checklist");
    private JsonLoader jsonLoader;
    private LoadChecklist loadChecklist;

    //Referred to JAVA SWING official documentation and code provided on EdX
    //link: https://en.wikipedia.org/wiki/Swing_(Java)

    public MainMenuGUI() {
        super("Diana's checklist");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(420,520));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10,10,10,10));
        setLayout(null);
        this.background();

        jsonLoader = new JsonLoader("./data/checklist.json");
        try {
            checklist = jsonLoader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel label = new JLabel("I want to:", JLabel.LEFT);
        label.setBounds(30,20,150,20);
        this.add(label);
        this.buttonOption();

        //used from code provided on edx
        pack();

        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

    }

    //TODO: add a background image
    //EFFECTS: set up the background image
    private void background() {
        try {
            BufferedImage pic = ImageIO.read(new File("src/main/ui/images/mainmenubackground.jpg"));
            setContentPane(new BackgroundPicture(pic));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: Set up the option buttons on the main menu
    private void buttonOption() {
        JButton checkChecklistOption = new JButton("Load the list");
        checkChecklistOption.setBounds(130,190,150,30);
        this.add(checkChecklistOption);
        checkChecklistOption.setActionCommand("LOAD_CHECKLIST");
        checkChecklistOption.addActionListener(this);

        JButton quitOption = new JButton("Exit");
        quitOption.setBounds(130,230,150,30);
        this.add(quitOption);
        quitOption.setActionCommand("EXIT");
        quitOption.addActionListener(this);


    }

    @Override
    //EFFECTS: gives different reactions according to the button pressed.
    public void actionPerformed(ActionEvent e) {
       /* try {
            checklist = jsonLoader.read();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        */
        if (e.getActionCommand().equals("LOAD_CHECKLIST")) {
            loadChecklist = new LoadChecklist(checklist);
            JOptionPane.showMessageDialog(null, "Checklist loaded.");
           //LOAD+SAVE

        } else if (e.getActionCommand().equals("EXIT")) {
            loadChecklist.dispose();
            System.exit(0);
        }
    }

}
