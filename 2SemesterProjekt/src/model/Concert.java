package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The Concert class extends the Event class, inheriting all of its attributes
 * and methods. The class represents a specific type of event: A concert. In
 * addition to the attributes inherited from Event, the Concert class introduces
 * two new attributes: Price for attending the concert. Bands representing the
 * bands performing at the concert.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */
public class Concert extends Event {
 
	private double price;
	private ArrayList<Band> bands;


	/*
	 * 1.2 in the interaction diagram.
	 * 
	 * Constructor. The constructor is being used to initialize the attributes when
	 * a new object is created.
	 * 
	 * The Concert class has a constructor that initializes the eventType,
	 * eventTitle, eventStartTime, eventEndTime, and volunteerId attributes by
	 * calling the superclass constructor. It also initializes the bands attribute
	 * as an empty ArrayList.
	 */

	public Concert(String eventType, String eventTitle, LocalDateTime eventStartTime, LocalDateTime eventEndTime, Volunteer volunteer, int eventId) {
		super(eventType, eventTitle, eventStartTime, eventEndTime, volunteer, eventId);
		this.bands = new ArrayList<Band>();	
	}



	/**
	 * The addBand method is used to add a Band object to the bands ArrayList.
	 */
	public boolean addBand(Band band) {
		bands.add(band);
		return true;
	}

	/**
	 * 2.2 in the interaction diagram
	 * 
	 * Getters and setters The setters allows the values of these attributes to be
	 * changed. The getters are used to access the current values of the different
	 * attributes.
	 */
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * The getBands method is used to retrieve the list of bands performing at the
	 * concert.
	 */
	public ArrayList<Band> getBands() {
		return this.bands;
	}
}
