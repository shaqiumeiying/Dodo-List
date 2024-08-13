package ui.gui;

import exceptions.ListAlreadyEmptyException;
import model.Checklist;
import model.Event;
import persistence.JsonSaver;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

//Referred to components-SimpleTableDemoProject, TableSelectionDemoProject,
//and CheckBoxDemo
//https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html
//https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
//https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
//https://docs.oracle.com/javase/8/docs/api/javax/swing/table/TableModel.html
//https://stackoverflow.com/questions/27490371/jtextfield-not-appearing-in-gui

//This is to generate a loaded checklist table user interface
public class LoadChecklist extends JFrame implements ActionListener {
    private Checklist checklist;

    private JsonSaver jsonSaver;

    private DefaultTableModel defaultTableModel;
    private JTable table;


    //EFFECTS: set up the load checklist user interface
    public LoadChecklist(Checklist checklist) {

        jsonSaver = new JsonSaver("./data/checklist.json");
        this.checklist = checklist;
        //TODO:SET BACKGROUND IMAGE
        this.background();
        String[] columnNames = {"Event", "Done?", "Urgent?"};
        defaultTableModel = new DefaultTableModel(null, columnNames) {
        };
        table = new JTable(defaultTableModel);
        this.renderChecklist();

        //TODO:add

        add(new JScrollPane(table));
        this.setUpOption();
        setTitle("Diana's checklist");
        //used from code provided on edx
        setLayout(new FlowLayout());

        pack();
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    //EFFECTS: render the checklist from local file onto the table
    private void renderChecklist() {
        for (Event e: checklist.getChecklist()) {
            Object[] row = new Object[] {
                    e.getDescription(),
                    e.getDone(),
                    e.getUrgent()
            };
            defaultTableModel.addRow(row);
        }
    }

    //
    //EFFECTS: set up the background image
    private void background() {
        try {
            //TODO: add a background image
            BufferedImage pic = ImageIO.read(new File("src/main/ui/images/loadlistbg.jpg"));
            setContentPane(new BackgroundPicture(pic));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: Set up the option buttons on the main menu
    private void setUpOption() {
        JButton addNewEvent = new JButton(("Add an event"));
        addNewEvent.setBounds(300,20,200,30);
        this.add(addNewEvent);
        addNewEvent.setActionCommand("ADD_EVENT");
        addNewEvent.addActionListener(this);

        JButton emptyChecklistOption = new JButton("Empty the list");
        emptyChecklistOption.setBounds(300,70,200,30);
        this.add(emptyChecklistOption);
        emptyChecklistOption.setActionCommand("EMPTY_CHECKLIST");
        emptyChecklistOption.addActionListener(this);

        JButton saveChecklist = new JButton(("Save the list"));
        saveChecklist.setBounds(300,120,200,30);
        this.add(saveChecklist);
        saveChecklist.setActionCommand("SAVE_CHECKLIST");
        saveChecklist.addActionListener(this);
    }

    /* Implement these features if possible
    JButton markUrgentEvent = new JButton("Mark event as done");
    this.add(markUrgentEvent);
    markUrgentEvent.setActionCommand("MARK_AS_URGENT");
    markUrgentEvent.addActionListener(this);
    markUrgentEvent.setForeground(Color.BLACK);

    JButton markDoneEvent = new JButton("Mark event as done");
    this.add(markDoneEvent);
    markDoneEvent.setActionCommand("MARK_AS_DONE");
    markDoneEvent.addActionListener(this);
    markDoneEvent.setForeground(Color.BLACK);
    */

    @Override
    //EFFECT: override this method with a new purpose
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ADD_EVENT")) {
            new AddEvent(checklist, this);
        } else if (e.getActionCommand().equals("EMPTY_CHECKLIST")) {
            try {
                checklist.emptyTheList();
                JOptionPane.showMessageDialog(null, "You have emptied the checklist");
            } catch (ListAlreadyEmptyException e1) {
                JOptionPane.showMessageDialog(null, "List already empty!");
            }

            dispose();
            new LoadChecklist(checklist);


        } else if (e.getActionCommand().equals("SAVE_CHECKLIST")) {
            try {
                jsonSaver.open();
                JOptionPane.showMessageDialog(null,"Your checklist has been saved");
            } catch (FileNotFoundException e1) {
                JOptionPane.showMessageDialog(null, "Unable to save the list");
            }
            jsonSaver.write(checklist);
            jsonSaver.close();



            new LoadChecklist(checklist);
            dispose();

        }
    }
}






