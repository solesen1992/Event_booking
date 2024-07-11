package DBLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * The `DBConnection` class is used for managing the connection to the SQL database. 
 * It's a singleton class, meaning it ensures that only one instance of the connection is ever created.
 * 
 * The class provides methods for starting, committing, and rolling back a database transaction. 
 * These methods are used to ensure the integrity of the database when performing operations that 
 * involve multiple steps. If one step fails, the transaction can be rolled back to prevent the database 
 * from being left in an inconsistent state.
 * 
 * The `executeInsertWithIdentity` methods are used to execute SQL insert commands and return the ID 
 * of the inserted record. This can be useful when you need to link the inserted record to other 
 * records in the database.
 * 
 * The `getConnection` method is used to retrieve the existing connection to the database. 
 * This can be useful when you need to perform database operations using the connection.
 * 
 * The `disconnect` method is used to close the connection to the database when you're done using it. 
 * This is important for freeing up resources.
 * 
 * @author knol
 * @version 1.0
 */

public class DBConnection {
	private Connection connection = null;
	//static type that helps connecting with the database.
	//It's a singleton - it holds an reference to the only instance of the class.
	private static DBConnection dbConnection; //Singleton
	
	private static final String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String dbName = "DMA-CSD-S233_10510643";
	 private static final String serverAddress = "hildur.ucn.dk";
	private static final int serverPort = 1433;
	private static final String userName = "DMA-CSD-S233_10510643";
	private static final String password = "Password1!";

	/**
	 * Singleton
	 * The constructor is private to prevent other classes from creating instances of it.
	 * When the DBConnection instance is created, it attempts to establish a connection to the database.
	 * We use less resources when only one gets a connection at a time + creates data consistency.
    */
	private DBConnection() throws DataAccessException {
		//Connection to the database
		String connectionString = String.format("jdbc:sqlserver://%s:%d;databaseName=%s;user=%s;password=%s;encrypt=false",
				serverAddress, serverPort, dbName, userName, password);
		try {
			Class.forName(driverClass);
			//Connection to the database is established
			connection = DriverManager.getConnection(connectionString);
		} catch (ClassNotFoundException e) {
			throw new DataAccessException("Missing JDBC driver", e);

		} catch (SQLException e) {
			throw new DataAccessException(String.format("Could not connect to database %s@%s:%d user %s", dbName,
					serverAddress, serverPort, userName), e);
		}
	}

	/**
	 * Singleton
	 * This method ensures that only one instance of the DBConnection class is created.
	 * It returns the single instance of the class. If the instance does not exist, this method creates it.
    */
	public static synchronized DBConnection getInstance() throws DataAccessException {
		//If no instance of DBConnection exists, a new one is created
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}
		//The instance of DBConnection is returned.
		return dbConnection;
	}

	/**
	 * This method is used to start a transaction in the database.
    */
	public void startTransaction() throws DataAccessException {
		try {
			//Changes to the database will not be committed automatically (because of false)
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DataAccessException("Could not start transaction.", e);
		}
	}

	/**
	 * This method is used to commit a database transaction.
    */
	public void commitTransaction() throws DataAccessException {
		try {
			try {
				//Tries to commit the changes in the database transaction
				connection.commit();
			} catch (SQLException e) {
				throw e;
			} finally {
				//No matter if the commit was succesfull or not, we set the connections 
				//autocommit back to true
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not commit transaction", e);
		}
	}

	/**
	 * This method is used to rollback a database transaction in case an operation fails
    */
	public void rollbackTransaction() throws DataAccessException {
		try {
			try {
				//Attempts to rollback any changes made during the current transaction
				connection.rollback();
			} catch (SQLException e) {
				throw e;
			} finally {
				connection.setAutoCommit(true);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not rollback transaction", e);
		}
	}

	/**
	 * SQL STATEMENT
	 * This method is used to execute an insert statement using a prepared statement 
	 * and return the auto-generated key.
    */
	public int executeInsertWithIdentity(String sql) throws DataAccessException {
		System.out.println("DBConnection, Inserting: " + sql);
		int res = -1;
		try (Statement s = connection.createStatement()) {
			res = s.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
			if (res > 0) {
				ResultSet rs = s.getGeneratedKeys();
				rs.next();
				res = rs.getInt(1);
			}
		} catch (SQLException e) {
			throw new DataAccessException("Could not execute insert (" + sql + ").", e);
		}
		return res;
	}

	/**
	 * PREPARED STATEMENT
	 * This method is used to execute an insert statement using a PreparedStatement and 
	 * return the auto-generated key.
    */
	public int executeInsertWithIdentity(PreparedStatement ps) throws DataAccessException {
		// requires prepared statement to be created with the additional argument PreparedStatement.RETURN_GENERATED_KEYS  
		// Initializes a result variable to -1 to signify an unsuccessful insert operation by default
		int res = -1;
		try {
			// Executes the prepared statement in our database.
			// and stores the number of affected rows in res
			res = ps.executeUpdate();
			// If the insert operation was successful (affected one or more rows)
			if (res > 0) {
				// Get the generated keys (our EventID) from the insert operation / our database
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				// Retrieves the first column of the first row, 
				// which is the auto-generated key, and store it in res
				//takes the first row / eventID in the resultset as an int
				res = rs.getInt(1);
			}
			// Catch any SQL exceptions that occur during this process
		} catch (SQLException e) {
			// If an exception occurs, throw a DataAccessException with a message 
			// and the original SQLException
			throw new DataAccessException("Could not execute insert", e);
		}
		// Return the result (either -1 if the insert operation was unsuccessful,
	    // or the auto-generated key if the insert operation was successful)
		return res;
	}

	/** 
	 * This method is used to get the database connection.
    */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * This method is used to disconnect from the database.
    */
	public void disconnect() {
		try {
			//closes the database connection
			connection.close();
		} catch (SQLException e) {
			//if there's an error closing the connection, print the stack trace
			e.printStackTrace();
		}
	}
	
}
