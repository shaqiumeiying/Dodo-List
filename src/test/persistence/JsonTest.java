package persistence;
import model.Event;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**Refers to the JsonSerializationDemo
 * Here's the Citation:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public class JsonTest {
    protected void checkEvent(String description, boolean done, boolean urgent, Event event) {
        assertEquals(description, event.getDescription());
        assertEquals(done, event.getDone());
        assertEquals(urgent, event.getUrgent());
    }

}
