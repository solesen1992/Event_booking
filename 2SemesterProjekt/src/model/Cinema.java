package model;

import java.time.LocalDateTime;

/**
 * A type of Event that represents a cinema event.
 * This class extends the Event class. 
 * It has additional attributes movie and movieGenre to store the name of the movie and 
 * its genre being shown at the event.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 * */

public class Cinema extends Event {
	private String movie;
	private String movieGenre;
	
	/**
	 * Constructor.
	 * The constructor is being used to initialize the attributes when a new object is created.
	 * */	
	public Cinema(String eventType, String eventTitle, LocalDateTime eventStartTime,
			LocalDateTime eventEndTime, Volunteer volunteer,int eventId) {
		super(eventType, eventTitle, eventStartTime, eventEndTime, volunteer,eventId);
	}


	/**
	 * Getters and setters
	 * The setters allows the values of these attributes to be changed.
	 * The getters are used to access the current values of the different attributes.
	 * */
	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public String getMovieGenre() {
		return movieGenre;
	}

	public void setMovieGenre(String movieGenre) {
		this.movieGenre = movieGenre;
	}
}
