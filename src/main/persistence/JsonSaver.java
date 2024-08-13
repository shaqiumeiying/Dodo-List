package persistence;

import model.Checklist;
import org.json.JSONObject;

import java.io.*;

/** Represents a saver that saves JSON representation of checklist to file
 *Refers to the JsonSerializationDemo
 * Here's the Citation:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

public class JsonSaver {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: makes writer to write to destination file
    public JsonSaver(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of checklist to file
    public void write(Checklist cl) {
        JSONObject json = cl.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
