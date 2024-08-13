package model;

//Tests of Checklist class
//Sorry for making exception changes in the earlier phase.
//I thought phase 2 was about robust so i made some changes in phase 2
//and i would like to use that changes I made in phase 4
import exceptions.IndexNoneExistException;
import exceptions.ListAlreadyEmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ChecklistTest {
    private Checklist shoppingCart;
    private Checklist carRepair;

    @BeforeEach
    public void setUp() {
        shoppingCart = new Checklist("Mom's shopping cart");
        carRepair = new Checklist("John Car Repair");
    }

    Event buyButter = new Event("BUY BUTTER",true, false);
    Event buyMilk = new Event("BUY MILK",false, true);
    Event repairTires = new Event("REPAIR TIRES", true,true);
    Event getScrewdriver = new Event("FIND SCREWDRIVER", false,false);


    @Test
    public void testAddTodo() {

        //add event when the list is full
        for (int i = 1; i <= Checklist.MAX_EVENT; i++) {
            shoppingCart.addEvent(buyButter);
        }

        shoppingCart.addEvent(buyMilk);
        assertEquals(shoppingCart.addEvent(buyButter), "Can not do more stuff, busy now!!");
        assertEquals(shoppingCart.size(), Checklist.MAX_EVENT);

        //add one to a not full list
        for (int i = 1; i <= Checklist.MAX_EVENT - 1; i++) {
            carRepair.addEvent(repairTires);
        }

        assertEquals(carRepair.addEvent(getScrewdriver), "Successfully added!");
        try {
            carRepair.removeSpecificEvent(1);

        } catch (IndexNoneExistException e) {
            fail("Exception caught");
        }
        assertEquals(carRepair.addEvent(new Event("STOP THE FIRE",true,true)),
                "Event STOP THE FIRE is Urgent, please finish it ASAP");


    }

    @Test
    public void testRemoveSpecificCheck() {
        shoppingCart.addEvent(buyButter);
        shoppingCart.addEvent(buyMilk);
        try {
            shoppingCart.removeSpecificEvent(0);
            //no exception thrown
        } catch (IndexNoneExistException e){
            fail("Exception caught");
        }

        assertTrue(shoppingCart.contains(buyMilk));
        assertEquals(shoppingCart.size(),1);



        try { shoppingCart.removeSpecificEvent(8);
            fail("no exception thrown");
        } catch (IndexNoneExistException e){
            //Exception caught
        }

        assertEquals(shoppingCart.size(),1);

        try { shoppingCart.removeSpecificEvent(-1);
            fail("no exception thrown");
        } catch (IndexNoneExistException e){
            //Exception caught
        }

        assertEquals(shoppingCart.size(),1);

        try {
            shoppingCart.removeSpecificEvent(0);
            //no exception thrown
        } catch (IndexNoneExistException e){
            fail("Exception caught");
        }

        assertEquals(shoppingCart.size(),0);


    }

    @Test
    public void testAddedEventUrgent() {
        shoppingCart.addEvent(buyButter);
        shoppingCart.addEvent(buyMilk);
        assertEquals(shoppingCart.addedEventUrgent(buyMilk),"Event BUY MILK is Urgent, please finish it ASAP");

    }

    @Test
    public void testMarkEventAsDone() {
        shoppingCart.addEvent(buyMilk);
        assertTrue(shoppingCart.markEventAsDone(0));
        assertFalse(shoppingCart.markEventAsDone(-1));
        assertFalse(shoppingCart.markEventAsDone(6));
    }

    @Test
    public void testMarkEventAsUrgent() {
        shoppingCart.addEvent(buyButter);
        assertTrue(shoppingCart.markEventAsUrgent(0));
        assertFalse(shoppingCart.markEventAsUrgent(-1));
        assertFalse(shoppingCart.markEventAsUrgent(6));
    }



    // Found a built in method called "indexOf" when browsing StackOverflow, so i deleted the method i created. Also
    // I found some bug inside my code so I gave up on it.
    // Here's the link:
    // https://stackoverflow.com/questions/5767325/how-can-i-remove-a-specific-item-from-an-array/5767357#5767357

    /*
    @Test
    public void testGetIndex() {
        shoppingCart.addEvent(buyMilk);
        shoppingCart.addEvent(buyButter);

        assertEquals(shoppingCart.indexOf(buyButter),2);
        shoppingCart.removeSpecificEvent(0);
        assertEquals(shoppingCart.indexOf(buyButter),1);
        assertEquals(shoppingCart.indexOf(new Event("eat",true,true)),-1);
    }
    */
    @Test
    public void testPrintChecklist() {
        shoppingCart.addEvent(buyMilk);
        shoppingCart.addEvent(buyButter);
        assertEquals(shoppingCart.printChecklist(),"[(1):  BUY MILK, (2):  BUY BUTTER]");
    }

    @Test
    public void testPrintNotDoneList() {
        Event buyOnion = new Event("BUY ONION", false, false);

        shoppingCart.addEvent(buyMilk);
        shoppingCart.addEvent(buyButter);
        shoppingCart.addEvent(buyOnion);

        assertEquals(shoppingCart.printNotDoneList(),"[(1):  BUY MILK, (3):  BUY ONION]");
        shoppingCart.markEventAsDone(2);
        assertTrue(buyOnion.getDone());
        assertEquals(shoppingCart.printNotDoneList(),"[(1):  BUY MILK]");
    }

    @Test
    public void testPrintNotUrgentList() {
        Event buyChicken = new Event("BUY CHICKEN", false, false);

        shoppingCart.addEvent(buyMilk);
        shoppingCart.addEvent(buyButter);
        shoppingCart.addEvent(buyChicken);
        shoppingCart.addEvent(new Event("BUY ONION", false, true));

        assertEquals(shoppingCart.printNotUrgentList(),"[(2):  BUY BUTTER, (3):  BUY CHICKEN]");
        shoppingCart.markEventAsUrgent(2);
        assertTrue(buyChicken.getUrgent());
        assertEquals(shoppingCart.printNotUrgentList(),"[(2):  BUY BUTTER]");
    }


    @Test
    public void testListSize() {
        shoppingCart.addEvent(buyButter);
        shoppingCart.addEvent(buyMilk);
        shoppingCart.addEvent(new Event("BUY ONION",true,true));

        assertEquals(shoppingCart.size(),3);

        //remove to see if still works
        try {
            shoppingCart.removeSpecificEvent(2);
        } catch (IndexNoneExistException e) {
            e.printStackTrace();
        }
        assertEquals(shoppingCart.size(),2);
    }

    @Test
    public void testListIsFull() {
        assertFalse(shoppingCart.listIsFull());

        //add MAX events to see if full
        for (int i = 1; i <= Checklist.MAX_EVENT; i++) {
            shoppingCart.addEvent(buyButter);
        }
        assertTrue(shoppingCart.listIsFull());

        //add some events to see if full
        carRepair.addEvent(getScrewdriver);
        carRepair.addEvent(repairTires);
        assertFalse(carRepair.listIsFull());

    }

    @Test
    public void testEmptyTheList() {
        try{
            carRepair.emptyTheList();
            fail("No exception caught");
        } catch (ListAlreadyEmptyException e) {
            //caught exception
        }
        assertEquals(carRepair.size(),0);

        carRepair.addEvent(getScrewdriver);
        carRepair.addEvent(repairTires);

        assertEquals(carRepair.size(),2);

        //empty a list
        try{
            carRepair.emptyTheList();
            //no exception caught
        } catch (ListAlreadyEmptyException e) {
            fail("Caught exception");
        }
        assertEquals(carRepair.size(),0);
    }




}