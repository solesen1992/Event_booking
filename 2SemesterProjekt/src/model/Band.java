package model;
/**
 * 
 * This class represents a Band object.
 * It contains information about the band's genre, description, and name.
 *
 * @author [Gruppe 3]
 * @version 1.0
 */

public class Band  {
	private String bandGenre;
	private String bandDescription;
	private String bandName;
	 
	/**
	 * Constructor.
	 * The constructor is being used to initialize the attributes when a new object is created.
	 * */
	public Band(String bandGenre, String bandDescription, String bandName) {
		this.bandGenre = bandGenre;
		this.bandDescription = bandDescription;
		this.bandName = bandName;
	}
	
	/**
	 * Getters and setters
	 * The setters allows the values of these attributes to be changed.
	 * The getters are used to access the current values of the different attributes.
	 * */
	
	// Get the genre of the band
	public String getBandGenre() {
		return bandGenre;
	}
	
	// Set the genre of the band
	public void setBandGenre(String bandGenre) {
		this.bandGenre = bandGenre;
	}
	
	/**
	 * 2.3 in the interaction diagram
	 * */
	// Get the description of the band
	public String getBandDescription() {
		return bandDescription;
	}
	
	// Set the description of the band
	public void setBandDescription(String bandDescription) {
		this.bandDescription = bandDescription;
	}
	
	// Get the name of the band
	public String getBandName() {
		return bandName;
	}
	
	// Set the name of the band
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}

}
