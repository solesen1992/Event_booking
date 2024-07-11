package model;

import java.time.LocalDateTime;

/**
 * The CommunityKitchen is a type of Event.
 * The class extends the Event class, inheriting all of its methods and attributes.
 * In addition to the inherited characteristics, the `CommunityKitchen` class has two additional attributes:
 * `menu`: a string representing the menu for the event.
 * `mealPrice`: an integer representing the price of the meal at the event.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 * */
 
public class CommunityKitchen extends Event {
	private String menu;
	private int mealPrice;

	/**
	 * Constructor.
	 * The constructor is being used to initialize the attributes when a new object is created.
	 * The CommunityKitchen class has a constructor that initializes the inherited attributes 
	 * by calling the superclass constructor.
	 * */
	public CommunityKitchen(String eventType, String eventTitle, LocalDateTime eventStartTime,
			LocalDateTime eventEndTime, Volunteer volunteer, int eventId) {
		super(eventType, eventTitle, eventStartTime, eventEndTime, volunteer, eventId);
	}

	/**
	 * Getters and setters
	 * The setters allows the values of these attributes to be changed.
	 * The getters are used to access the current values of the different attributes.
	 * */
	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public int getMealPrice() {
		return mealPrice;
	}

	public void setMealPrice(int mealPrice) {
		this.mealPrice = mealPrice;
	}
}
