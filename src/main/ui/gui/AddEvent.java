package ui.gui;

import model.Checklist;
import model.Event;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
//This is to generate an add event user interface

public class AddEvent extends JFrame implements ActionListener {

    private Checklist checklist;
    private LoadChecklist loadChecklist;
    //private JsonSaver jsonSaver;
    //JFrame frame = new JFrame();
    JTextField eventNameField;
    JTextField isUrgentField;


    //MODIFIES: this
    //EFFECTS: set up the add event user interface
    public AddEvent(Checklist checklist, LoadChecklist loadChecklist) {
        super("Add event here:");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        //jsonSaver = new JsonSaver("./data/checklist.json");
        this.checklist = checklist;
        this.loadChecklist = loadChecklist;

        setPreferredSize(new Dimension(500, 350));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(null);

        this.background();
        this.setUpTextBox();

        //directly used from the code provided on edx
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }


    //EFFECTS: set up the background image
    private void background() {
        try {
            //TODO: add a background image
            BufferedImage pic = ImageIO.read(new File("src/main/ui/images/addbg.jpg"));
            setContentPane(new BackgroundPicture(pic));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: set up the input text box
    private void setUpTextBox() {
        JLabel labelAddEvent = new JLabel("Enter event: ");
        labelAddEvent.setBounds(40, 40, 300, 20);
        this.add(labelAddEvent);

        //frame.add(BorderLayout.NORTH, eventField);
        eventNameField = new JTextField(200);
        eventNameField.setBounds(40, 80, 100, 20);
        this.add(eventNameField);

        JLabel labelSetUrgent = new JLabel("Is this urgent? (enter true or false)");
        labelSetUrgent.setBounds(40, 120, 500, 20);
        this.add(labelSetUrgent);

        isUrgentField = new JTextField(200);
        isUrgentField.setBounds(40, 160, 100, 20);
        this.add(isUrgentField);

        JButton pressOK = new JButton("OK");
        pressOK.setBounds(200, 200, 100, 20);
        this.add(pressOK);
        pressOK.setActionCommand("OKAY");
        pressOK.addActionListener(this);

    }

    @Override
    //EFFECT: override this method with a new purpose
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("OKAY")) {
            String description = eventNameField.getText();
            boolean urgent = Boolean.parseBoolean(isUrgentField.getText());

            checklist.addEvent(new Event(description, false, urgent));
            JOptionPane.showMessageDialog(null, "Event added!");


            /*try {
                jsonSaver.open();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            jsonSaver.write(checklist);
            jsonSaver.close();

             */

            //generate the updated list and close the old version
            loadChecklist.dispose();
            new LoadChecklist(checklist);
            dispose();
        }
    }
}



