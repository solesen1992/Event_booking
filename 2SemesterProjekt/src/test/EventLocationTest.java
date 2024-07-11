package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.CityZipCode;
import model.EventLocation;
/**
 * 
 * The `EventLocationTest` class contains two test methods: `testEventLocation()` and 
 * `testEventLocationToString()`.
 * 
 * The `testEventLocation()` method tests the constructor of the `EventLocation` class by 
 * creating an instance of `EventLocation` with valid parameters. It then checks if the created 
 * `EventLocation` object is not null and whether the properties of the object match the values passed 
 * to the constructor.
 * 
 * The `testEventLocationToString()` method tests the `toString` method of the `EventLocation` class. 
 * It creates an instance of `EventLocation`, calls its `toString` method, and compares the resulting 
 * string with an expected string to verify that the `toString` method is working correctly.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */

class EventLocationTest {
 
	/**
	 * The `testEventLocation()` method tests the constructor of the `EventLocation` class by 
	 * creating an instance of `EventLocation` with valid parameters. It then checks if the created 
	 * `EventLocation` object is not null and whether the properties of the object match the values passed 
	 * to the constructor.
	 */
	@Test
	public void testEventLocation() {
		// Creating a new CityZipCode object
		CityZipCode cityZipCode = new CityZipCode(1, 9000, "Aalborg");
		// Creating a new EventLocation object
        EventLocation eventLocation = new EventLocation(5,"Rocken", "Ved Stranden 11D", "rocken@rocken.com", "+4541930301", 100, cityZipCode);
        
        // Checking if the object is not null
        assertNotNull(eventLocation);
        
        // Checking whether the given values and the actual values of EventLocation object's 
        //attributes are matching or not
        assertEquals(5, eventLocation.getLocationId());
        assertEquals("Rocken", eventLocation.getLocationName());
        assertEquals("Ved Stranden 11D", eventLocation.getStreet());
        assertEquals("rocken@rocken.com", eventLocation.getEmail());
        assertEquals("+4541930301", eventLocation.getPhoneNo());
        assertEquals(100, eventLocation.getCapacity());
        // Checking if the CityZipCode object in EventLocation object is the same as the one we created
        assertEquals(cityZipCode, eventLocation.getCityZipCode());
    }

	/**
	 * `testEventLocationToString()` tests the `toString` method in `EventLocation`. 
	 * 
	 * The test asserts that the actual output of `eventLocation.toString()` matches `expectedString`. 
	 * If they match, it means the toString() in the EventLocation is working as expected.
	 */
    @Test
    public void testEventLocationToString() {
    	CityZipCode cityZipCode = new CityZipCode(1, 9000, "Aalborg");
        EventLocation eventLocation = new EventLocation(5,"Rocken", "Ved Stranden 11D", "rocken@rocken.com", "+4541930301", 100, cityZipCode);
        String cityZipCodeToString = cityZipCode.toString();
        
        // Checking if the object is not null to ensure it was created succesfully
        assertNotNull(eventLocation);  
        // Creates a string of the expected output of the toString method.
        String expectedString = "EventLocation [locationId=5, locationName=Rocken, street=Ved Stranden 11D, email=rocken@rocken.com, phoneNo=+4541930301, capacity=100, cityZipCode=CityZipCode [cityZipCodeId=1, zipCode=9000, city=Aalborg]]";
        // Asserts that the actual output of eventLocation.toString() matches expectedString
        assertEquals(expectedString, eventLocation.toString());
    }

}
