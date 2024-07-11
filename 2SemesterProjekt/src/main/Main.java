package main;

import DBLayer.DataAccessException;
import UI.MainGUI;
import observer.EventChecker;
import observer.EventSubject;
import observer.EventSubjectIF;

/**
 * 'Main' is the entry point of the program. It starts the program.
 * 
 * It opens the mainGUI which is an observer.
 * 
 * We're making a new thread for EventChecker to run in, and the threads starts. 
 * This allows the EventChecker to check for events again and again without blocking 
 * or slowing down the GUI.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 * */
 
public class Main {
    public static void main(String[] args) throws DataAccessException {
        System.out.println("Starting application...");
        //Makes an instance of EventSubject. Singleton.
        //to be able to run the registerObserver method.
        EventSubjectIF eventSubject = EventSubject.getInstance();

        //Makes an instance of mainGUI and opens the mainGUI window
        MainGUI mainGUI = new MainGUI();
        mainGUI.setVisible(true);
        //mainGUI is registered as an observer
        eventSubject.registerObserver(mainGUI);

        //Creates an EventChecker. Checks for new events every 5 seconds.
        EventChecker eventChecker = new EventChecker(eventSubject, 5000);

        //Creates new thread for EventChecker to run in so it doesn't disturb the GUI
        Thread checkerThread = new Thread(eventChecker);
        // Start the checkerThread. When we call start() the run() method starts in EventChecker.
        checkerThread.start();
    }
}