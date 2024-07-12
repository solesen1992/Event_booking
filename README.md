# Event booking desktop application
Event booking desktop application for 1000fryd. The project uses observer pattern, threads and a database.
Languages used: Java and MS SQL.

It was a group project made i the spring of 2024.

# Screenshots of desktop application
<img width="800" alt="Event_create" src="https://github.com/solesen1992/Event_booking/assets/123094773/e029dd27-a719-49bd-b431-b776c29c1239">

# Documentation
## Observer pattern and threads
In non-functional requirements, we have the requirement that the system response time must be short, so the volunteers get quick access to the latest information on the overview of events. Updating the event list must happen quickly - whether it is the person themselves or another person who has created the event, as it can otherwise lead to double bookings.

<img width="800" alt="Event_list" src="https://github.com/solesen1992/Event_booking/assets/123094773/3c827e6c-4ee1-4623-b47a-efbbc49381cb">

On the GUI, we have a list of upcoming events that needs to update continuously as volunteers create events. To develop this function, we worked with parallelism, where the window updates itself when a new event is added to the database. The parallelism occurs because the window updates alongside the user working in the GUI to create events. It reloads the event list, even though we are doing something else.

To develop the function, we use the Observer pattern, which is a design pattern. The Observer pattern creates a one-to-many dependency between objects. This means that when one object changes state (in this case, our eventId), all subscribed objects (in this case, our table of events) are notified and updated, so our volunteers quickly get newly created events on the event overview.

<img width="800" alt="observer_pattern" src="https://github.com/solesen1992/Event_booking/assets/123094773/d5a47d8c-15ad-4c47-9036-a393b10c22dd">

In the design class diagram, you can see the following classes:
- EventSubjectIF defines the methods necessary to manage observers.
- EventSubject implements the EventSubjectIF interface. The subject is the object that has a state that can change. The subject keeps track of observers and informs them when changes occur. It uses registerObserver() to add an observer to the list, removeObserver() to remove an observer from the list, and notifyObservers() to notify all observers that there has been a change in the subject's state.
- EventObserverIF is the interface that all observers must implement. Observers contain the update() method, which is called by EventSubject (through notifyObservers()) to notify that there has been a change in the subject. This means a change has occurred in the database where another event has been added. When observers are notified of changes, they do not need to check for changes continuously themselves.
- EventChecker continuously checks for new events in the database through EventDB and notifies EventSubject when a change occurs. EventSubject calls notifyObservers() and notifies the observers.
- EventDB handles database operations for events - including checking if there has been an update of getMaxId(). If the most recently added event ID in the database has changed (e.g., if there were 4 events before and now there are 5), then maxId is updated, representing the most recently added event ID. The change in maxId means that new events are added to the table.
- Main class is responsible for initializing the application. It starts a thread that checks for changes from our EventChecker every 5 seconds. EventChecker calls EventSubjects notify() if there are changes. The Main class does not know who subscribes to EventSubject.
- MainGUI is one of our observers, which has implemented EventObserverIF. When we add a new event to the database, it does not take long before it can be seen in the table in MainGUI.

The Observer pattern contributes to lower coupling between classes. EventSubject can notify multiple EventObservers about changes in the subject's state - without knowing the details of the observers. Lower coupling makes it easier to maintain because changes in the observers do not affect the subjects and vice versa. The Observer pattern promotes 'separation of concerns', as the responsibility for checking events in EventChecker is separate from the responsibility for event handling.

To achieve parallelism, we have implemented the Runnable interface on EventChecker. Runnable is a functional interface that only has the run() method, which is filled with what needs to be executed in a new thread. In our run() method, it checks if eventMaxId has changed, and uses the Observer pattern's notify() method if there is a change. Then the MainGUI's list of events is updated.

When we run the program on two different machines, the update on one machine can be seen on the other.

## Database and ACID-principles
The ACID principles are used in the DBConnection class to ensure reliability and robustness in database operations.

### Atomicity
Atomicity means that a transaction must be fully completed or rolled back in the event of an error. Methods such as startTransaction(), commitTransaction(), and rollbackTransaction() ensure that operations are fully executed or not at all. If errors occur, no changes are made, ensuring consistency in the database.

### Consistency
Consistency means that the database is in a consistent state. The method executeInsertWithIdentity() validates SQL commands before execution to ensure that only valid data is inserted into the database. If an error occurs, it results in an exception that prevents invalid data from being inserted into the database.

### Isolation
Isolation ensures that transactions do not interfere with each other while running concurrently. By setting connection.setAutoCommit(false) in startTransaction(), it is ensured that transactions are not automatically committed and do not interfere with each other. Additionally, one transaction should not accidentally overwrite another transaction's data. If two transactions attempt to update the same data record, one must wait until the other is finished. If errors occur during the execution of a transaction, they are handled using rollbackTransaction(), which rolls back changes to the state before the transaction began. This ensures that erroneous updates are not saved and prevents loss from other transactions.

### Durability
Durability ensures that once something is saved in a database (in our case through commitTransaction()), it remains saved even if the power goes out or the system crashes. In the event of an error, the database can recover the committed changes and ensure data consistency.

## Prepared statements
The company's risk analysis highlights potential security issues that could compromise user safety information. Using prepared statements in the DAO pattern can protect against SQL injections, where users send malicious SQL queries to the database, potentially compromising the system by altering or deleting data.

## MS SQL
Our tables each have a primary key in the form of an ID, where we have used IDENTITY(1,1), which ensures that we get an autogenerated ID, so we do not have to create them manually. The first number represents the first autogenerated ID, which in this case is 1, and the second number represents how much the IDs increase by each time, so the numbers change continuously.

Since the IDs are autogenerated, they do not need to be inserted manually when we insert values into the table. Foreign keys, however, must be filled in. The foreign key refers to a primary key in another table, and the database needs to know which primary key the foreign key refers to and which IDs should be combined if the tables are to be joined.

## Methods
### filterEventLocationOnCapacityAndAvailability()
filterEventLocationOnCapacityAndAvailability() filters event locations based on capacity and availability to avoid double bookings. We start by getting the number of estimated attendees from the event, after which we create three lists. First, we call the method findEventLocation(), which retrieves all locations that can accommodate our estimated attendees (i.e., venues with greater capacity than the number of participants) and places them into the list locationsSortedByCapacity. Then we call checkExistingEventsOnDateAndTime(), which checks for existing events that have the same date and time as our event, and this is placed into the list eventsAtSameDateTime.

Next, we have nested for-each loops. We have two lists with two different types of objects.

The outer loop iterates through the list locationsSortedByCapacity, and for each location, it assumes that the location is available. For each location, it runs an inner loop that iterates through eventsAtSameDateTime and checks if our current event location has an ID that matches an event at the same date and time. If the ID matches, we mark the location as occupied since an event can only have one location. If we find a match, we break out of the method and continue with the next location until we have gone through all the locations.

After this, all locations that did not have a match (i.e., they were not occupied) are placed on a list that is returned. The user can see this list in the GUI of available locations that they can choose for their event.
