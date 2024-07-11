package DBLayer;

import java.util.List;

import model.Band;

/**
 * The BandDBIF interface defines methods for accessing band data in the database.
 * 
 * An interface in Java:
 * - Defines a contract for implementing classes.
 * - Supports multiple inheritance, allowing a class to implement multiple interfaces.
 * - Cannot be instantiated directly; used as a blueprint for implementing classes.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */
public interface BandDBIF {
	
	/**
     * Retrieves bands from the database that match the provided band name or all bands.
     * 
     * @param bandName The name of the band to search for
     * @return A list of Band objects matching the provided name
     * @throws DataAccessException if there's an issue accessing data from the database
     */
	List<Band> findBandsByNames(String bandName) throws DataAccessException;
	public List<Band> findAllBands() throws DataAccessException;
}
