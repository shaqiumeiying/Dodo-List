package persistence;


import model.Checklist;
import model.Event;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**Refers to the JsonSerializationDemo
 * Here's the Citation:https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */

public class JsonSaverTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Checklist cl = new Checklist("Diana's checklist");
            JsonSaver writer = new JsonSaver("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyChecklist() {
        try {
            Checklist checklist = new Checklist("Diana's checklist");
            JsonSaver writer = new JsonSaver("./data/testSaverEmptyChecklist.json");
            writer.open();
            writer.write(checklist);
            writer.close();

            assertEquals(checklist.size(),0);

            JsonLoader reader = new JsonLoader("./data/testSaverEmptyChecklist.json");
            checklist = reader.read();
            assertEquals("Diana's checklist", checklist.getName());
            assertEquals(0, checklist.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralChecklist() {
        try {
            Event eat = new Event("eat", false, true);
            Event drink = new Event("drink", true, false);

            Checklist checklist = new Checklist("Diana's checklist");
            checklist.addEvent(eat);
            checklist.addEvent(drink);
            JsonSaver writer = new JsonSaver("./data/testSaverGeneralChecklist.json");
            writer.open();
            writer.write(checklist);
            writer.close();

            assertTrue(checklist.contains(eat));
            assertTrue(checklist.contains(drink));

            assertEquals(checklist.size(),2);
            assertEquals(checklist.printChecklist(), "[(1):  eat, (2):  drink]");

            JsonLoader reader = new JsonLoader("./data/testSaverGeneralChecklist.json");
            checklist = reader.read();
            assertEquals("Diana's checklist", checklist.getName());
            List<Event> checklist1 = checklist.getChecklist();
            assertEquals(2, checklist1.size());
            checkEvent("eat",false, true, checklist1.get(0));
            checkEvent("drink", true, false, checklist1.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }

}
