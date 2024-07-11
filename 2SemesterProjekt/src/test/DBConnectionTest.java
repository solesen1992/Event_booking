package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DBLayer.DBConnection;
import DBLayer.DataAccessException;
/**
 * 
 * The class `DBConnectionTest` is a test class used to validate the functionality of the 
 * `DBConnection` class. This class is using the JUnit framework to write and run tests.
 * 
 * The `setUp()` method, annotated with `@BeforeEach`, is run before each test. 
 * It initializes the `dbConnection` object which is an instance of `DBConnection`.
 * 
 * The `tearDown()` method, annotated with `@AfterEach`, is run after each test. 
 * It disconnects the `dbConnection`.
 * 
 * The `testConnectionEstablished()` method is the actual test. It verifies that the database 
 * connection has been properly established. It checks that the `connection` object is not null 
 * and that it is not closed. If either of these conditions is not met, the test will fail.
 *
 * @author [Gruppe 3]
 * @version 1.0
 */

class DBConnectionTest {

	private DBConnection dbConnection;

	/**
	 * Runs before each test.
	 * Creates an instance of DBConnection.
	 * */
	@BeforeEach
	public void setUp() {
		try {
			dbConnection = DBConnection.getInstance();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Runs after each test.
	 * Disconnects the dbConnection.
	 * */
	@AfterEach
	public void tearDown() {
		dbConnection.disconnect();
	}

    /**
     * Test method to check if the database connection is properly established.
     * 
     * First, it retrieves the database connection object by calling `dbConnection.getConnection()`.
     * 
     * Then, it checks if the connection object is not null using the 
     * `assertNotNull("Connection should not be null", connection)` statement. 
     * This ensures that the connection object was created correctly.
     * 
     * Next, it checks if the connection is not closed using the 
     * `assertFalse("Connection should not be closed", connection.isClosed())` statement. 
     * This ensures that the connection to the database is open and ready to be used.
     * 
     * If the connection object is null or the connection is already closed, the respective assertion 
     * will fail, and an error message will be displayed.
     * */
	@Test
	public void testConnectionEstablished() {
		//Gets the database connection
		Connection connection = dbConnection.getConnection();
		//Checks that the connection is not null
		assertNotNull("Connection should not be null", connection);

		try {
			//Checks if the connection is not closed (is it open and ready to be used?)
			assertFalse("Connection should not be closed", connection.isClosed());
		} catch (SQLException e) {
			// If there is an SQL exception, print the stack trace
			e.printStackTrace();
		}
	}

}
