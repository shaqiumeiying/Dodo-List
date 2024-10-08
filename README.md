# Dodo list - a  Desktop Checklist Application

After a long time of thinking, I decided to make a checklist as my first java project.


### Some Thoughts - Why is this project of interest to you?

I want to make a checklist because I've been thinking about making a checklist myself long ago. A checklist can be used
under multiple circumstances. For example, you can have a shopping cart checklist (shopping items), you can make a
training plan checklist, house chores checklist, and many checklists you want. Now this project is a great opportunity
to slowly merge all the stuff I've learned from 210 into an actual project. To be honest, I'm really interested in user
experience and user interface design, which is the final phase for this project I guess? Because I like designing icons,
making mockups for an app... But we have to complete the full implementation of this app before actually design the 
with which users can interact. Yeah so overall it makes me the thrill to think that this will be my first app built 
from scratch. Just the excitement makes me interested in this project.

And... I dunno but I'll probably name the app a *"Dodo List"*.(for no reason I just love dodo birds)


### Target Users - Who will use it?

Everyone can use this list, from kids to elders, from student to workers. But the main target group are probably 
**teenager and adults**.


### Functionality - What will the application do?

This ***Checklist*** can:

-**register many events** into a  checklist.(function 1.)

-**removes unwanted events** from the list.(function 2.)

-be able to **change the done** status of an event.(function 3.)

-be able to **change the urgent** level of an event.(function 4.)

-remake the checklist, **empty everything**.(function 5.)

-a place where people can **view the whole list**(function 6.)

-be able to **quit**.(function 7.)

-be able to **save the checklist**.(function 8.)(unfinished)

-be able to **load the checklist**.(function 9.)(unfinished)

<br>

-**changes font size** (if possible)

-**changes background color!!!!** (if possible)

<br>

### User Stories - People with Different needs

As a user, I would like to add some events to the list.

As a user, I would like to remove some events from the list.

As a user, I would like to view the whole list.

As a user, I want to use the app to check off everything I've bought when I'm shopping.

As a user, I probably want to remove everything if I'm not satisfied with the list I created.

As a user, I might need to change some event to "urgent" , even though I set it as "not urgent" at first.

As a user, I need bigger font that I can read properly when I set up my checklist.

As a user, I want to change the font to my favorite color.

As a user, I would like to have a simple and neat user interface.

<br>

**Newly added user stories**

As a user, I would like to save my checklist for further use.

As a user, I would like to load my checklist I saved last time.

<br>

***Phase 4: Task 2***

*Option 1 selected*:

Test and design a class in your model package that is robust.  You must have at least one method 
that throws a checked exception.  You must have one test for the case where the exception is expected and another 
where the exception is not expected.

*Changes made*:

- Inside **Checklist.java**, method **emptyTheList()** throws a "ListAlreadyEmptyException".
    
    - Methods inside file **ChecklistApp.java** changed correspondingly:
        - emptyTheChecklistInConsole()

    - Methods inside file **ChecklistTest.java** changed correspondingly:
        - testEmptyTheList()
    
    
- Inside **Checklist.java**, method **removeSpecificEvent()** throws a "IndexNoneExistException".
    
    - Methods inside file **ChecklistApp.java** changed correspondingly:
        - removeEvent()
        - markItemAsDone()
        - markItemAsUrgent()

    - Methods inside file **ChecklistTest.java** changed correspondingly:
        - testAddTodo()
        - testRemoveSpecificCheck()
        - testListSize()

<br>

***Phase 4: Task 3***

![Final UML diagram](/UMLFiles/images/UML_Design_Diagram.png "UML phase 4")

*Reflection:*

If I had more time, I would like to improve my code according to Simple Responsibility Principle. I did not show the
methods used inside my diagram, for example inside Checklist.java, it has some methods called printChecklist(),
printNotDoneList() and so on, which are not the job for a checklist to do, there should be a new class like
"ChecklistPrinter" or "PrintManager" to handle these methods. 

Also some of the methods share a similar body with each
other, causing some errors that compiler can not detect. I remember when I was designing markEventAsDone() and 
markEventAsUrgent() in Checklist.java, I directly copied one's body from another and forgot to change one variable name,
the tests are failing and I couldn't find the reason. After a long time of checking I finally found that the error was
inside one of the duplicated body.

I could've also improve the structure by following the composite pattern. For example I can create a new abstract class 
as the component, checklist as a composite, and event as a leaf. Inside the component I can make it have abstract
methods called makeEventUrgent() and makeEventDone(). Inside leaf(Event) it will override and has an implemented body to 
change states according to user's command; and inside composite(Checklist) it will also override the method in super 
class and having a for loop as the body (check which one's state needs to be changed)


