package persistence;

import model.Checklist;
import model.Event;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**Refers to the JsonSerializationDemo
  * Here's the Citation:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
         */
public class JsonLoaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonLoader reader = new JsonLoader("./data/noSuchFile.json");
        try {
            Checklist checklist = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyChecklist() {
        JsonLoader reader = new JsonLoader("./data/testLoaderEmptyChecklist.json");
        try {
            Checklist cl = reader.read();
            assertEquals("Diana's checklist", cl.getName());
            assertEquals(0, cl.size());
        } catch (IOException e) {
            fail("Cannot read from file");
        }
    }

    @Test
    void testReaderGeneralChecklist() {
        JsonLoader reader = new JsonLoader("./data/testLoaderGeneralChecklist.json");
        try {
            Checklist checklist = reader.read();

            assertEquals(checklist.size(),2);
            assertEquals(checklist.printChecklist(), "[(1):  drink, (2):  eat]");

            assertEquals("Diana's checklist", checklist.getName());
            List<Event> checklist1 = checklist.getChecklist();
            assertEquals(2, checklist1.size());
            checkEvent("eat", false, true, checklist1.get(1) );
            checkEvent("drink", false, false, checklist1.get(0)  );
        } catch (IOException e) {
            fail("Cannot read from file");
        }
    }
}
