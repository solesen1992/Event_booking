package observer;

/**
 * Observer pattern.
 * Interface that's implemented by our EventSubject.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 * */

public interface EventSubjectIF {
	void registerObserver(EventObserverIF observer);
	void removeObserver(EventObserverIF observer);
	void notifyObservers();
}
