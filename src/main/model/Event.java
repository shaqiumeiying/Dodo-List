package model;

/*
  One single to-do event, containing an event description [in String] , a done status [in boolean]
  [in boolean] ,and an status urgent [in boolean].
  Refers to the JsonSerializationDemo
  Here's the Citation:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
import org.json.JSONObject;
import persistence.Writable;

public class Event implements Writable {

    //Some fields for a single event to finish
    private String description;         //shows what exactly is this event
    private boolean done;               //shows if the item is done or not
    private boolean urgent;             //shows if the event is urgent(true) or normal(false)
                                        //The reason why I create urgent is because when it gets to phase 4,
                                        // I want the ui to change the font color automatically. where basically it
                                        // highlights the urgent event in a different color.
                                        //event. the boolean status is here is to detect whether it should change the
                                        // font color or not.


    //EFFECTS:a new event which has given description, due date and initial urgent status
    public Event(String description, boolean done, boolean urgent) {
        this.description = description;
        this.done = done;
        this.urgent = urgent;
    }

    //EFFECTS: return true if the event is urgent, otherwise false.
    public boolean isUrgent() {
        return urgent;
    }

    //MODIFIES: this
    //EFFECTS: make the event done(true) if it's not done, otherwise remain unchanged
    public void makeItDone() {
        setDone(true);
    }

    //MODIFIES: this
    //EFFECTS: make the event urgent(true) if it's not urgent, otherwise remain unchanged
    public void makeItUrgent() {
        setUrgent(true);
    }

    //Some getters
    //EFFECT: get description from the event
    public String getDescription() {
        return description;
    }

    //EFFECT: get done status from the event
    public boolean getDone() {
        return done;
    }

    //EFFECT: get urgent status from the event
    public boolean getUrgent() {
        return urgent;
    }

    //Some setters
    //EFFECT: set done status from the event
    public void setDone(boolean done) {
        this.done = done;
    }

    //EFFECT: set urgent status from the event
    public void setUrgent(boolean urgent) {
        this.urgent = urgent;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", description);
        json.put("done", done);
        json.put("urgent", urgent);
        return json;
    }
}



