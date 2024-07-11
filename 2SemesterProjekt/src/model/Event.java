package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class represents an event with various attributes and methods.
 * 
 * Event serves as a template for other classes. It provides a common structure for its subclasses, 
 * which can inherit its variables and methods. This class on its own cannot be instantiated. 
 * It exists to be extended by other classes, which can provide the specific implementations.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */ 
public abstract class Event {
	private int estimatedAttendees; 
	private int locationId;
	private String eventType;
	private String eventTitle;
	private LocalDateTime eventStartTime;
	private LocalDateTime eventEndTime;
	private Volunteer volunteer;
	private EventLocation eventLocation;
	private String description;
	private int eventId;
	private List<Assignment> assignments;



	
	/**
	 * Constructor.
	 * The constructor is being used to initialize the attributes when a new object is created.
	 * */
	public Event(String eventType, String eventTitle, LocalDateTime eventStartTime,
			LocalDateTime eventEndTime, Volunteer volunteer, int eventId) {
		this.eventType = eventType;
		this.eventTitle = eventTitle;
		this.eventStartTime = eventStartTime;
		this.eventEndTime = eventEndTime;
		this.volunteer = volunteer;
		this.eventId = eventId;
		assignments = new ArrayList<>();
		eventLocation = eventLocation;
	}

	/**
	 * Getters and setters
	 * The setters allows the values of these attributes to be changed.
	 * The getters are used to access the current values of the different attributes.
	 * */
	
	/**
	 * EstimatedAttendees is a part of 2.1 in the interaction diagram
	 * */
	public int getEstimatedAttendees() {
		return estimatedAttendees;
	}

	public void setEstimatedAttendees(int estimatedAttendees) {
		this.estimatedAttendees = estimatedAttendees;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public LocalDateTime getEventStartTime() {
		return eventStartTime;
	}

	public void setEventStartTime(LocalDateTime eventStartTime) {
		this.eventStartTime = eventStartTime;
	}

	public LocalDateTime getEventEndTime() {
		return eventEndTime;
	}

	public void setEventEndTime(LocalDateTime eventEndTime) {
		this.eventEndTime = eventEndTime;
	}
	
	public Volunteer getVolunteer() {
		return volunteer;
	}

	public void setVolunteer(Volunteer volunteer) {
		this.volunteer = volunteer;
	}
	
	public EventLocation getEventLocation() {
		return eventLocation;
	}
	
	public void setEventLocation(EventLocation eventLocation) {
		this.eventLocation = eventLocation;
	}

	public int getEventId() {
		return eventId;
	}
	
	
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	/**
	 * Description is a part of 2.3 in the interaction diagram
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	/**
	 * Used in the GUI layer
	 * Use case 2 - AssignmentConfirmationGUI
	 * 
	 * Gets the list of assignments on each event
	 * Returns a full list of assignments
	 * 
	 * */
	public List<Assignment> getAssignments() {
        return assignments;
    }
	
	/**
	 * Use case 2 - Create Assignment
	 * 3.1. in the interaction diagram
	 * Adds a newly created assignment to the assignments list.
	 * This returns a boolean: Was the operation successful or not?
	 * 
	 * */
	public boolean addAssignment(Assignment assignment) {
		return assignments.add(assignment);	
	}

}

