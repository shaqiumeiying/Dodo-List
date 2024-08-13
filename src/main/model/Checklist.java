package model;

import exceptions.IndexNoneExistException;
import exceptions.ListAlreadyEmptyException;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**Represent a list of to-dos with a maximum limit
 * Refers to the JsonSerializationDemo
 * Here's the Citation:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
//Sorry for making exception changes in the earlier phase.
//I thought phase 2 was about robust so i made some changes in phase 2
//and i would like to use that changes I made in phase 4
public class Checklist implements Writable {
    private String name;
    private final ArrayList<Event> checklist;
    public static final int MAX_EVENT = 5;


    //construct an empty list with a name
    //EFFECTS: initializes an empty CheckList
    public Checklist(String name) {
        this.name = name;
        checklist = new ArrayList<>();
    }

    public String getName() {
        return name;
    }


    //MODIFIES: this
    //EFFECTS: add a event to this checklist if the checklist is still empty
    public String addEvent(Event event) {
        if (!listIsFull()) {
            checklist.add(event);
            return addedEventUrgent(event);
        } else {
            return "Can not do more stuff, busy now!!";
        }
    }


    //MODIFIES:this
    //EFFECTS: remove the to-do corresponds to the input index
    public void removeSpecificEvent(int index) throws IndexNoneExistException {

        if (index < checklist.size() && index >= 0) {
            checklist.remove(index);
            //return "Event removed!";
        } else {
            throw new IndexNoneExistException();
        }
    }


    //EFFECTS: return corresponding String when event is urgent(true)/normal(false)
    public String addedEventUrgent(Event event) {
        if (event.isUrgent()) {
            return "Event " + event.getDescription() + " is Urgent, please finish it ASAP";
        } else {
            return "Successfully added!";
        }
    }

    //MODIFIES: event
    //EFFECTS: change the  status of done according to the index given
    public boolean markEventAsDone(int index) {
        if (index < checklist.size() && index >= 0) {
            Event event = checklist.get(index);
            event.makeItDone();
            return true;
        } else {
            return false;
        }
    }

    //MODIFIES: event
    //EFFECTS: change the  status of urgrrnt according to the index given
    public boolean markEventAsUrgent(int index) {
        if (index < checklist.size() && index >= 0) {
            Event event = checklist.get(index);
            event.makeItUrgent();
            return true;
        } else {
            return false;
        }
    }


    // EFFECTS: prints the whole checklist to the screen
    public String printChecklist() {
        ArrayList<String> list = new ArrayList<>();
        for (Event event : checklist) {
            list.add("(" + (checklist.indexOf(event) + 1) + "):  " + event.getDescription());
        }
        return list.toString();
    }

    // EFFECTS: prints the checklist that contains all the undone event to the screen
    public String printNotDoneList() {
        ArrayList<String> list = new ArrayList<>();
        for (Event event : checklist) {
            if (!event.getDone()) {
                list.add("(" + (checklist.indexOf(event) + 1) + "):  " + event.getDescription());
            }
        }
        return list.toString();
    }

    // EFFECTS: prints the checklist that contains all the not urgent event to the screen
    public String printNotUrgentList() {
        ArrayList<String> list = new ArrayList<>();
        for (Event event : checklist) {
            if (!event.getUrgent()) {
                list.add("(" + (checklist.indexOf(event) + 1) + "):  " + event.getDescription());
            }
        }
        return list.toString();
    }


    // Found a built in method called "indexOf" when browsing StackOverflow, so i deleted the method i created. Also
    // I found some bug inside my code so I gave up on it.
    // Here's the link:
    // https://stackoverflow.com/questions/5767325/how-can-i-remove-a-specific-item-from-an-array/5767357#5767357

    /*
    //EFFECTS: get the index of the event in the list
    public int getIndex(Event event) {
        for (int i = 0; i < checklist.size(); i++) {
            Event event1 = checklist.get(i);
            if (event1.getDescription().equals(event.getDescription())) {
                return ++i;
            }
        }
        return -1;
    }
    */

    //EFFECTS: return true if the list is full, false otherwise
    public boolean listIsFull() {
        return checklist.size() == MAX_EVENT;
    }


    //EFFECTS: empty the whole list
    public void emptyTheList() throws ListAlreadyEmptyException {
        if (checklist.size() == 0) {
            throw new ListAlreadyEmptyException();
        } else {
            checklist.clear();
        }
    }

    // EFFECTS: returns an unmodifiable list of events in this checklist
    public List<Event> getChecklist() {
        return Collections.unmodifiableList(checklist);
    }


    //EFFECTS: find the number of events in the list
    public int size() {
        return checklist.size();
    }

    //EFFECTS: return true if the checklist contains the event
    public boolean contains(Event event) {
        return checklist.contains(event);
    }

    //

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("checklist", checklistToJson());
        return json;
    }

    // EFFECTS: returns event in this checklist as a JSON array
    private JSONArray checklistToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Event e : checklist) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }
}



