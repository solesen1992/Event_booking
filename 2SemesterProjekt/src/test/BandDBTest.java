/**
 * 
 */
package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DBLayer.BandDB;
import DBLayer.DataAccessException;
import model.Band;

/**
 * BandDBTest is testing different functionalities of the `BandDB` class, which handles database 
 * operations related to Bands. It uses the JUnit testing framework to define and run the tests.
 * 
 * It includes four tests:
 * 1) `testFindBandsByNames` checks the functionality of the `findBandsByNames` method in the `BandDB` class. 
 * This method is expected to retrieve bands from the database based on a list of band names.
 * 
 * 2) `testFindNonExistentBand` checks the system's behavior when a non-existent band name is searched. 
 * The expected behavior in this case is to return an empty list.
 * 
 * 3) `testCorrectBandConstruction` verifies that the Band objects are being correctly constructed 
 * from the database data. It checks the properties of the first Band object in the returned 
 * list to ensure the genre, description, and name match the known values.
 * 
 * 4) `testfindAllBands` tests the functionality of the `findAllBands()` method from the `BandDB` class. 
 * This method should return a list of all Band objects in the database. 
 * The test asserts that the size of the returned list of bands should be 3.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 * */


class BandDBTest {
	
	/**
	 * Test 1
	 * 
	 * The test checks if findBandsByNames() from BandDB finds and returns the bands 
	 * with the names we specified. It creates a list of band names, finds the bands 
	 * with those names, and adds them all to a list. 
	 * 
	 * It then prints the name, genre and description of each band in the list. 
	 * It also prints the total number of bands found.
	 * 
	 * It also checks if the total number of bands found is equal to the number of 
	 * band names that were searched for, which is 3.
	 */
	@Test
	public void testFindBandsByNames() throws DataAccessException {
	    BandDB BandDB = new BandDB();
	    // A list of band names that we want to find
	    List<String> bandNames = new ArrayList<>();
	    bandNames.add("SPIRACLE");
	    bandNames.add("Foreshadower");
	    bandNames.add("Hudsult");
	    // Creates an empty list to store the results of findBandsByNames()
	    List<Band> allBands = new ArrayList<>();

	    // For each band name in the list, call findBandsByNames() and add the returned 
	    // bands to the allBands list
	    
	    // Loop through each band name in the bandNames list
	    for (String bandName : bandNames) {
	    	// Finds the bands with the current band name and store them in a list
	        List<Band> bands = BandDB.findBandsByNames(bandName);
	        //adds all the elements from the 'bands' list to the allBands list (our empty list from before).
	        allBands.addAll(bands);
	    }

	    // Iterate through each band in allBands and print out each band's name, genre and description
	    for(Band band : allBands) {
	        System.out.println("Test 1: Show/Find all bands in the allBands list: " + band.getBandName() + "  "+ band.getBandGenre() + "  " + band.getBandDescription().toString());
	    }
	    
	    // Print the size of allBands
	    System.out.println("Test 1: The size of the allBands list: " + allBands.size() + " " );
	    // Assert that the size of allBands is 3
	    assertEquals(3, allBands.size());
	}
	
	
	/**
	 * Test 2
	 * 
	 * Question: What happens if we try to retrieve a band that is not in the database?
	 * 
	 * The testFindNonExistentBand method checks the system's behavior when a non-existent 
	 * band name is searched. The expected behavior in this case is to return an empty list.
	 * 
	 * Handling of cases where no bands are found with the given name, 
	 * ensuring it returns an empty list without errors.
	 * */
	@Test
	public void testFindNonExistentBand() throws DataAccessException {
		//Arrange
	    BandDB BandDB = new BandDB();
	    
	    //Act
	    // Attempt to find a band with a name that does not exist in the database
	    List<Band> bands = BandDB.findBandsByNames("NonExistentBandName");
	    
	    //Assert
	    // Assert that the returned list is empty
	    assertEquals(0, bands.size());
	    System.out.println("Test 2: List size when no bands where found with a specific name: " + bands.size());
	}
	
	
	/**
	 * Test 3
	 * 
	 * This test, `testCorrectBandConstruction`, is designed to verify that the Band objects 
	 * are being correctly constructed from the database data.
	 * 
	 * `findBandsByNames()` is called with a known band name as the parameter.
	 * The method should return a list of Band objects that match the provided name.
	 * 
	 * The test then checks the properties of the first Band object in the returned list 
	 * using `assertEquals` to ensure the genre, description, and name match the known values.
	 * 
	 * If the Band object's properties do not match the expected values, the `assertEquals` 
	 * calls will fail, causing the test to fail.
	*/
	@Test
	public void testCorrectBandConstruction() throws DataAccessException {
	    BandDB BandDB = new BandDB();
	    // Finds a band with a known name from the database
	    List<Band> bands = BandDB.findBandsByNames("Hudsult");
	    // Assert that the returned Band object has the correct properties
	    // AssertEquals checks if two values are equal.
	    // get(0) returns the first item from the list
	    assertEquals("Rock ", bands.get(0).getBandGenre());
	    assertEquals("3 medlemmer der er fest aber ", bands.get(0).getBandDescription());
	    assertEquals("Hudsult", bands.get(0).getBandName());
	}
	
	/**
	 * Test 4
	 * 
	 * Tests findAllBands in 'BandDB'. 
	 * 
	 * It checks if the size of the returned list of bands from the database is 3, 
	 * and if it's not, the test will fail.
	 * It prints the name of each band in the list to the console.
	 * */
	
	@Test
	public void testfindAllBands() throws DataAccessException {
	    
	    BandDB BandDB = new BandDB();
	    // takes all the bands from the database
	    List<Band> bands = BandDB.findAllBands();
	    		
	    //Iterates through each band object on the list. Prints the names for each one.
	    		for(Band band : bands) {
	    			 System.out.println("Test 4 - Find all bands: " + band.getBandName());
	    		}
	    		//The size of the returned list should be 3. It fails if it's not 3.
	    		assertEquals(3, bands.size()); 
	}

}
