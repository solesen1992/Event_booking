package model;

import java.time.LocalDateTime;

/**
 * A type of 'Event' that represents a bar event. 
 * This class extends the `Event` class, inheriting all of its methods and attributes.
 * In addition to the inherited characteristics, the `Bar` class introduces an additional attribute:
 * `drinkDiscount`: a double representing the discount on drinks offered at the event.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 * */

public class Bar extends Event {
	private double drinkDiscount;
	
	/**
	 * Constructor.
	 * The constructor is being used to initialize the attributes when a new object is created.
	 * The Bar class has a constructor that initializes the inherited attributes by calling 
	 * the superclass constructor, and also sets the drinkDiscount attribute.
	 * */
	
	public Bar(String eventType, String eventTitle, LocalDateTime eventStartTime,
			LocalDateTime eventEndTime, Volunteer volunteer, int eventId) {
		super(eventType, eventTitle, eventStartTime, eventEndTime, volunteer,eventId);
	}

	/**
	 * Getters and setters
	 * The setters allows the values of these attributes to be changed.
	 * The getters are used to access the current values of the different attributes.
	 * */
	public double getDrinkDiscount() {
		return drinkDiscount;
	}

	public void setDrinkDiscount(double drinkDiscount) {
		this.drinkDiscount = drinkDiscount;
	}
	
}
