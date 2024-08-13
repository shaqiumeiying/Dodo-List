package persistence;

import model.Checklist;
import model.Event;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/** Represents a loader that loads checklist from JSON data stored in file
 * Refers to the JsonSerializationDemo
         * Here's the Citation:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
         */
public class JsonLoader {
    private String origin;

    // EFFECTS: constructs reader to read from source file
    public JsonLoader(String origin) {
        this.origin = origin;
    }

    // EFFECTS: reads checklist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Checklist read() throws IOException {
        String jsonData = readFile(origin);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseChecklist(jsonObject);
    }

    // EFFECTS: reads origin file as string and returns it
    private String readFile(String origin) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(origin), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses checklist from JSON object and returns it
    private Checklist parseChecklist(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Checklist cl = new Checklist(name);
        addChecklist(cl, jsonObject);
        return cl;
    }

    // MODIFIES: cl
    // EFFECTS: parses checklist from JSON object and adds them to checklist
    private void addChecklist(Checklist cl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("checklist");
        for (Object json : jsonArray) {
            JSONObject nextEvent = (JSONObject) json;
            addEvent(cl, nextEvent);
        }
    }

    // MODIFIES: checklist
    // EFFECTS: parses event from JSON object and adds it to checklist
    private void addEvent(Checklist checklist, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        boolean done = Boolean.parseBoolean(String.valueOf(jsonObject.getBoolean("done")));
        boolean urgent = Boolean.parseBoolean(String.valueOf(jsonObject.getBoolean("urgent")));

        Event event = new Event(name, done, urgent);
        checklist.addEvent(event);
    }
}
