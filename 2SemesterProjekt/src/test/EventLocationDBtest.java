package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import DBLayer.DataAccessException;
import DBLayer.EventLocationDB;
import model.CityZipCode;
import model.EventLocation;

/**
 * This tests the functionality of an EventLocationDB class. The class manages EventLocation objects 
 * in a database, which represents locations where events can be held.
 * 
 * `testFindEventLocation()` is testing the ability to find EventLocation objects in the database based 
 * on a passed identifier. It retrieves a list of EventLocations from the database, prints out their details, 
 * and then checks if the count of the list is 4 using the `assertEquals` method.
 * 
 * `testFindEventLocationByName()` is testing the ability to find an EventLocation object by its name. 
 * It creates a known EventLocation object, then attempts to retrieve the same object from the database 
 * using its name. It checks that all details of the retrieved object match the known object using 
 * `assertEquals` to verify the match.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 * 
 * */

class EventLocationDBtest {
 
	/**
	 * `testFindEventLocation()` is testing the ability to find EventLocation objects in the database based 
	 * on a passed identifier. It retrieves a list of EventLocations from the database, prints out their 
	 * details, and then checks if the count of the list is 4 using the `assertEquals` method.
	 * */
	@Test
	public void testFindEventLocation() throws DataAccessException {
		// ELDB står for EventLocationDB og ell betyder EventLocationList
		EventLocationDB ELDB = new EventLocationDB();
		// The list of event locations
		List<EventLocation> ell = ELDB.findEventLocation(0);

		// Traversing through the list of event locations
		for (EventLocation eventLocation : ell) {
			System.out.println(eventLocation.getLocationName() + "  " + eventLocation.getCapacity() + "  "
					+ eventLocation.getCityZipCode().toString());

		}
		
		// Checking the size of the list
		System.out.println(ell.size() + " ");
		// The assertion checks if the size of the list is 4
		assertEquals(4, ell.size());
	}

	/**
	 * Test 2
	 * 
	 * `testFindEventLocationByName()` is testing the ability to find an EventLocation object by its name. 
	 * It creates a known EventLocation object, then attempts to retrieve the same object from the database 
	 * using its name. It checks that all details of the retrieved object match the known object using 
	 * `assertEquals` to verify the match.
	 * 
	 * The parameters are the following:
	 * int locationId, String locationName, String street, String email, String
	 * phoneNo, int capacity,
	 * CityZipCode cityZipCode
	 */
	@Test
	public void testFindEventLocationByName() throws DataAccessException {
		// Creates a new EventLocationDB object
		EventLocationDB EventLocationDB = new EventLocationDB();
		// Creates a new CityZipCode object with test data
		CityZipCode cityZipCode = new CityZipCode(1, 9000, "Aalborg");
		// Initializes an EventLocation object to null
		EventLocation eventLocationFoundInDatabase = null;
		// Creating an instance of EventLocation with test data
		EventLocation eventLocationWithTestData = new EventLocation(2, "1000fryd biograf", "Kattesundet 10", "1000fryd@1000fryd.dk",
				"004598132221", 50, cityZipCode);
		
		// Finds an event location by name using the EventLocationDB object
		eventLocationFoundInDatabase = EventLocationDB.findEventLocationByName("1000fryd biograf");
		
		// The assertions check if the fetched event location matches the test data
		assertEquals(eventLocationWithTestData.getLocationId(), eventLocationFoundInDatabase.getLocationId());
		assertEquals(eventLocationWithTestData.getLocationName(), eventLocationFoundInDatabase.getLocationName());
		assertEquals(eventLocationWithTestData.getStreet(), eventLocationFoundInDatabase.getStreet());
		assertEquals(eventLocationWithTestData.getEmail(), eventLocationFoundInDatabase.getEmail());
		assertEquals(eventLocationWithTestData.getPhoneNo(), eventLocationFoundInDatabase.getPhoneNo());
		assertEquals(eventLocationWithTestData.getCapacity(), eventLocationFoundInDatabase.getCapacity());
		// Assert CityZipCode details
		// The assertions check if the CityZipCode details of the fetched event location match the test data
		assertEquals(eventLocationWithTestData.getCityZipCode().getCityZipCodeId(), eventLocationFoundInDatabase.getCityZipCode().getCityZipCodeId());
		assertEquals(eventLocationWithTestData.getCityZipCode().getZipCode(), eventLocationFoundInDatabase.getCityZipCode().getZipCode());
		assertEquals(eventLocationWithTestData.getCityZipCode().getCity(), eventLocationFoundInDatabase.getCityZipCode().getCity());
	}

}
