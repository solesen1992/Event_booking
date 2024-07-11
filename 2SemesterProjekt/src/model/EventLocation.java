package model;

/**
 * The class represents an event location with various attributes and methods.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */

public class EventLocation {
	private int locationId;
	private String locationName;
	private String street;
	private String email;
	private String phoneNo;
	private int capacity;
	private CityZipCode cityZipCode;
 
	/**
	 * Constructor.
	 * The constructor is being used to initialize the locationId, locationName, street, email, 
	 * phoneNo, capacity, and cityZipCode attributes when a new EventLocation object is created.
	 * */
	public EventLocation(int locationId, String locationName, String street, String email, String phoneNo, int capacity,
			 CityZipCode cityZipCode) {
		super();
		this.locationId = locationId;
		this.locationName = locationName;
		this.street = street;
		this.email = email;
		this.phoneNo = phoneNo;
		this.capacity = capacity;
		this.cityZipCode = cityZipCode;
	}

	/**
	 * Getters and setters 
	 * The setters allows the values of these attributes to be changed.
	 * The getters are used to access the current values of the different attributes.
	 * */
	
	/**
	 * LocationId
	 * */
	public int getLocationId() {
		return locationId;
	}
	
	/**
	 * LocationName
	 * */
	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * Street
	 * */
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * Email
	 * */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * phoneNo
	 * */
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	/**
	 * Capacity
	 * */
	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * CityZipCode.
	 * */
	public CityZipCode getCityZipCode() {
		return cityZipCode;
	}

	/**
	 * Returns a string representation of the EventLocation object. This string includes the 
	 * names and values of all the attributes.
	 * */
	@Override
	public String toString() {
		return "EventLocation [locationId=" + locationId + ", locationName=" + locationName + ", street=" + street
				+ ", email=" + email + ", phoneNo=" + phoneNo + ", capacity=" + capacity + ", cityZipCode="
				+ cityZipCode + "]";
	}
	
}
