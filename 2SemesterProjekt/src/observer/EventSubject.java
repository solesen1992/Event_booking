package observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer pattern.
 * EventSubject implements the EventSubjectIF interface.
 * 
 * EventSubject maintains a list of observers ('EventObserverIF'). 
 * It provides methods to add (`registerObserver`) and remove (`removeObserver`) observers from the list.
 * 
 * The 'getInstance' method is implementing the Singleton design pattern to ensure that 
 * only one instance of the 'EventSubject' class exists.
 * 
 * When an event occurs, the EventSubject class uses notifyObservers() to go through the list 
 * of observers and call their 'update' method. This way, all registered observers are 
 * informed about the new event update in our database.
 *  
 * @author [Gruppe 3]
 * @version 1.0
 * */

public class EventSubject implements EventSubjectIF {
    private static EventSubject instance; //Singleton
    private List<EventObserverIF> observers;

    // Private constructor to prevent instantiation. Singleton
    private EventSubject() {
        observers = new ArrayList<>();
    }

    // Public method to provide access to the single instance
    // Singleton
    // The EventSubject.getInstance() method returns an instance of EventSubjectIF
    public static synchronized EventSubjectIF getInstance() {
    	// If the instance has not been created yet, create it
        if (instance == null) {
            instance = new EventSubject();
        }
        // If there's already an instance, return instance of this class
        return instance;
    }

    /**
     * Methods registers observers.
     * The observer passed as a parameter is added to this list.
     * */
    @Override
    public void registerObserver(EventObserverIF observer) {
    	// Adds the observer to the list
        observers.add(observer);
    }

    /**
     * Method removes observers from the list.
     * Removes the observer, that was passed as a parameter, from the list.
     * */
    @Override
    public void removeObserver(EventObserverIF observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all registered observers about changes to the EventSubject.
     * NotifyObserver gets through the list of observers and uses the update method on each one.
     * All event observer objects have an update method.
     * Calls update on all the observers we have.
     * */
    @Override
    public void notifyObservers() {
        for (EventObserverIF observer : observers) {
            observer.update();
        }
    }
}