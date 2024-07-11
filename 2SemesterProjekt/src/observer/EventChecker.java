package observer;

import DBLayer.DataAccessException;
import DBLayer.EventDB;

/**
 * Observer pattern
 * 
 * Implements Runnable interface. It makes it possible to make a thread and it only uses the method run().
 * 
 * @author [Gruppe 3]
 * @version 1.0
 * */

public class EventChecker implements Runnable {
    private EventSubjectIF eventSubject;
    private EventDB eventDB;
    private int maxId;
    private int sleepDuration;
 
    /**
     * Constructor
     * */
    public EventChecker(EventSubjectIF eventSubject, int sleepDuration) throws DataAccessException {
        this.eventSubject = eventSubject;
        eventDB = new EventDB();
        this.sleepDuration = sleepDuration;
        this.maxId = getMaxIdFromDB();
    }
    
    /**
     * Gets the maxID from the DB layer.
     * */
    public int getMaxIdFromDB() throws DataAccessException {
        return eventDB.getMaxEventId();
    }
    
    /**
     * Checks if a new event has been added in the database
     * */
    private boolean isNewEventAdded() throws DataAccessException {
    	//Gets the maxID from eventDB
        int currentMaxId = getMaxIdFromDB();

        //Compares the current maxId with the one in the constructor.
        if (currentMaxId != maxId) {
        	// If yes, update the stored maximum ID and return true
            maxId = currentMaxId;
            return true;
        }
        // If no, return false. No changes were made.
        return false;
    }

    /**
     * If the maxID is changed, it notify the observers and asks them to update the display.
     * 
     * eventSubject.notifyObservers() asks all the observers to update itself.
     * In our case it updates the display in the MainGUI (the overview of events).
     * */
    @Override
    public void run() {
        System.out.println("Starting EventChecker thread...");
        // Starts an infinite loop
        while (true) {
            try {
            	//if isNewEventAdded is true / a new event has been added
                if (isNewEventAdded()) {
                	// If yes, notify all observers to update themselves
                    eventSubject.notifyObservers();
                }
                try {
                	// If no event was added, put the thread to sleep for a specified duration
                    Thread.sleep(sleepDuration);
                    
                 // Print the stack trace if there's an InterruptedException
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
             // Print the stack trace if there's an DataAccessException
            } catch (DataAccessException e) {
                e.printStackTrace();
            }
        }
    }
}