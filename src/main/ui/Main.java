package ui;

/* Main function that runs the application
  Refers to the JsonSerializationDemo
  Here's the Citation:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
        */

import ui.gui.MainMenuGUI;

public class Main {
    public static void main(String[] args) {
        /* no need console anymore
        try {
            new ChecklistApp();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot run application: file not found");
        }
        */
        new MainMenuGUI();
    }
}




