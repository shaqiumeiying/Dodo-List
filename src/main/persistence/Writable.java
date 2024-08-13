package persistence;

import org.json.JSONObject;
// An interface contains toJson method extended by JsonReader & JsonWriter
// Refers to the JsonSerializationDemo
// Here's the Citation:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
