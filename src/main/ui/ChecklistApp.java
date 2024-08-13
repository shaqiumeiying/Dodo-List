package ui;

//this class construct an interactible console
//Sorry for making exception changes in the earlier phase.
//I thought phase 2 was about robust so i made some changes in phase 2
//and i would like to use that changes I made in phase 4
import exceptions.IndexNoneExistException;
import exceptions.ListAlreadyEmptyException;
import model.Checklist;
import model.Event;
import persistence.JsonLoader;
import persistence.JsonSaver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Checklist Application
public class ChecklistApp {
    private static final String JSON_FILE_STORED_AT = "./data/checklist.json";
    private Checklist checklist;
    private final Scanner input = new Scanner(System.in);
    private JsonSaver jsonSaver;
    private JsonLoader jsonLoader;


    // EFFECTS: runs the checklist application
    // referenced from TellerApp but did not copy paste from it.
    // Link of TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp
    public ChecklistApp() throws FileNotFoundException {
        checklist = new Checklist("Diana's checklist");
        jsonSaver = new JsonSaver(JSON_FILE_STORED_AT);
        jsonLoader = new JsonLoader(JSON_FILE_STORED_AT);
        runChecklist();
    }

    public void runChecklist() {
        String operation;


        while (true) {
            menu();
            separating();
            operation = input.nextLine();
            separating();

            if (operation.equals("9")) {
                break;
            } else {
                lookingForOptions(operation);
            }
        }

        System.out.println("\nSee ya next time!");
        separating();
    }



    // EFFECTS: Opening page for user to select option
    // referenced from TellerApp but did not copy paste from it.
    // Link of TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void menu() {
        System.out.println("\nI want to:");
        System.out.println("\t1 -> Add an event");
        System.out.println("\t2 -> Remove an event");
        System.out.println("\t3 -> Mark an event as DONE");
        System.out.println("\t4 -> Mark an event as URGENT");
        System.out.println("\t5 -> View the checklist");
        System.out.println("\t6 -> Empty the checklist");
        System.out.println("\t7 -> Save the checklist");
        System.out.println("\t8 -> Load the checklist");
        System.out.println("\t9 -> Quit~");
    }

    // MODIFIES: this
    // EFFECTS: read user's command and operate
    // referenced from TellerApp but did not copy paste from it.
    // Link of TellerApp: https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void lookingForOptions(String operation) {
        if (operation.equals("1")) {
            createEvent();

        } else if (operation.equals("2")) {
            removeEvent();

        } else if (operation.equals("3")) {
            markItemAsDone();

        } else if (operation.equals("4")) {
            markItemAsUrgent();

        } else if (operation.equals("5")) {
            checkIsThereAList();

        } else if (operation.equals("6")) {
            System.out.println(emptyTheChecklistInConsole());

        } else if (operation.equals("7")) {
            saveChecklist();

        } else if (operation.equals("8")) {
            loadChecklist();

        } else {
            System.out.println("Please enter the correct number!");
            separating();
        }
    }

    // MODIFIES: this
    // EFFECTS: create an event and added to the list and print.
    private void createEvent() {
        System.out.println("Please add an event below:");
        String description = input.nextLine();
        System.out.println("Is this event Urgent?");
        System.out.println("Enter true or false");
        String urgentStatus = input.nextLine();
        if (urgentStatus.equals("true")) {
            System.out.println(checklist.addEvent(new Event(description, false, true)));
        } else if (urgentStatus.equals("false")) {
            System.out.println(checklist.addEvent(new Event(description, false, false)));
        } else {
            System.out.println("Cannot set urgent status, please try again!");
        }
        separating();

    }

    // MODIFIES: this
    // EFFECTS: remove an event and added to the list and print.
    private void removeEvent() {
        if (checklist.size() == 0) {
            System.out.println("You have to add something before removing!");
        } else {
            System.out.println(checklist.printChecklist());
            System.out.println("Which event would you like to remove?");
            System.out.println("Enter a number below:");
            int eventNumber = input.nextInt();
            input.nextLine();
            try {
                checklist.removeSpecificEvent(eventNumber - 1);
                System.out.print("Event removed!");
            } catch (IndexNoneExistException e) {
                System.out.print("Wrong input! Please try again!");
            }
        }
    }






    // MODIFIES: this
    // EFFECTS: set an item as done and print.
    private void markItemAsDone() {
        if (checklist.size() == 0) {
            System.out.println("You have to add something to your list before marking them as Done!");
        } else if (checklist.printNotDoneList().equals("[]")) {
            System.out.println("You have finished EVERYTHING in the list!");
        } else {
            System.out.println(checklist.printNotDoneList());
            System.out.println("Here are some unfinished events in your list");
            System.out.println("Which one would you like to checkoff?");
            int eventNumber = input.nextInt();
            input.nextLine();
            if (checklist.markEventAsDone(eventNumber - 1)) {
                try {
                    checklist.removeSpecificEvent(eventNumber - 1);
                } catch (IndexNoneExistException e) {
                    e.printStackTrace();
                }
                System.out.println("Yay, you did it!!");
            } else {
                System.out.println("There might be something wrong here......Please try again.");
            }
        }
        separating();
    }

    // MODIFIES: this
    // EFFECTS:  set an item as Urgent and print.
    private void markItemAsUrgent() {
        if (checklist.size() == 0) {
            System.out.println("You have to add something to your list before marking them as Urgent!");
        } else if (checklist.printNotUrgentList().equals("[]")) {
            System.out.println("Everything is Urgent!");
            System.out.println("Hurry up and finish them!");
        } else {
            System.out.println(checklist.printNotUrgentList());
            System.out.println("Here are normal events in your list");
            System.out.println("Which one would you like to make as URGENT?");
            int eventNumber = input.nextInt();
            input.nextLine();
            if (checklist.markEventAsUrgent(eventNumber - 1)) {
                try {
                    checklist.removeSpecificEvent(eventNumber - 1);
                } catch (IndexNoneExistException e) {
                    e.printStackTrace();
                }
                System.out.println("Now it's URGENT! Do it ASAP.");
            } else {
                System.out.println("There might be something wrong here...Please try again.");
            }
        }
        separating();
    }

    // MODIFIES: this
    // EFFECTS: check if the current list has items in it, return under different
    //          senarios and print.
    private void checkIsThereAList() {
        if (checklist.size() == 0) {
            System.out.println("Nothing is in the list");
        } else {
            System.out.println("Do not forget to:");
            System.out.println(checklist.printChecklist());
        }
        separating();
    }

    // MODIFIES: this
    // EFFECTS: empty the list and print
    private String emptyTheChecklistInConsole() {
        try {
            checklist.emptyTheList();
            return "Now it's empty! Add some event here to check off~";
        } catch (ListAlreadyEmptyException e) {
            return "You cannot empty an empty list~";
        } finally {
            separating();
        }
    }

    // MODIFIES: this
    // EFFECTS: create a separation line
    private void separating() {
        System.out.println("..........................................");
    }

    // EFFECTS: saves the checklist to file
    private void saveChecklist() {
        try {
            jsonSaver.open();
            jsonSaver.write(checklist);
            jsonSaver.close();
            System.out.println("Saved " + checklist.getName() + " to " + JSON_FILE_STORED_AT);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_FILE_STORED_AT);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads checklist from file
    private void loadChecklist() {
        try {
            checklist = jsonLoader.read();
            System.out.println("Loaded " + checklist.getName() + " from " + JSON_FILE_STORED_AT);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_FILE_STORED_AT);
        }
    }


}










