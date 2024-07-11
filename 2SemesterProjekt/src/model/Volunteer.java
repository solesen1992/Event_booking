package model;

/**
 * 'Volunteer' represents a volunteer in the system.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 * */

public class Volunteer {
	private int volunteerId;
	private String firstName;
	private String lastName;
	private String phoneNo;
	private String email;
	private String street;
	private String memberType;
	private String role;
	private CityZipCode cityZipCode;
	 
	/**
	 * Constructor
	 * */
	public Volunteer(int volunteerId, String firstName, String lastName, String phoneNo, String email, String street,
			String memberType, String role, CityZipCode cityZipCode) {
		
		this.volunteerId = volunteerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
		this.email = email;
		this.street = street;
		this.memberType = memberType;
		this.role = role;
		this.cityZipCode = cityZipCode;
	}

	/**
	 * Getters and setters
	 * */
	public int getVolunteerId() {
		return volunteerId;
	}


	public void setVolunteerId(int volunteerId) {
		this.volunteerId = volunteerId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getPhoneNo() {
		return phoneNo;
	}


	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getMemberType() {
		return memberType;
	}


	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public CityZipCode getCityZipCode() {
		return cityZipCode;
	}


	public void setCityZipCode(CityZipCode cityZipCode) {
		this.cityZipCode = cityZipCode;
	}
	
}
