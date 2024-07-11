package controllerLayer;
import model.CityZipCode;
import model.Volunteer;
/**
 * VolunteerCtr represents a volunteer in our system.
 * 
 * We have method called getVolunteer which is a hardcoded VolunteerCtr object.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */

public class VolunteerCtr {

	
	/*
	 * It's a hardcoded Volunteer object.
	 */
	public Volunteer getVolunteer() {
		CityZipCode cityZipcodeCoded = new CityZipCode(1, 9000, "Aalborg");
		Volunteer firstVolunteer = new Volunteer(1, "Bruce", "Lee", "12345678","jens.jensen@email.dk","Møllegade 3","Medlem","role", cityZipcodeCoded);
		return firstVolunteer;
	}	
}