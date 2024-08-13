package model;

//Tests of Event class

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {



    @Test
    public void testConstructor() {
        String description = "Call mom";

        Event callMom = new Event(description, false, true);
        assertEquals(callMom.getDescription(), description);
        assertFalse(callMom.getDone());
        assertTrue(callMom.getUrgent());
    }

    @Test
    public void testIsUrgent() {
        Event buyButter = new Event("BUY BUTTER", true, false);
        Event buyMilk = new Event("BUY MILK", false, true);

        assertFalse(buyButter.isUrgent());
        assertTrue(buyMilk.isUrgent());

    }

    @Test
    public void testMakeItDone() {
        Event repairTires = new Event("REPAIR TIRES", true, true);
        Event getScrewdriver = new Event("FIND SCREWDRIVER", false, false);

        repairTires.makeItDone();
        getScrewdriver.makeItDone();

        assertTrue(repairTires.getDone());
        assertTrue(getScrewdriver.getDone());
    }

    @Test
    public void testMakeItUrgent() {
        Event chopCucumber = new Event("CHOP CUCUMBER", true, true);
        Event addSalt = new Event("ADD SALT", false, false);

        chopCucumber.makeItUrgent();
        addSalt.makeItUrgent();

        assertTrue(chopCucumber.getUrgent());
        assertTrue(addSalt.getUrgent());
    }

}
