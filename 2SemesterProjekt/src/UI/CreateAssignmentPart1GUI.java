package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


import DBLayer.DataAccessException;
import controllerLayer.EventCtr;
import model.Event;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * GUI for CreateAssignmentPart1GUI
 * On this page, the user has to choose an event they want to add an assignment to.
 * The user gets presented for a table with the events that are currently in the database.
 * The user has to choose an event and click next.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 * */

public class CreateAssignmentPart1GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private EventCtr eventCtr;
	private MainGUI mainGUI;


	/**
	 * Create the frame. 
	 */
	public CreateAssignmentPart1GUI(EventCtr eventCtr) {
		setLocationRelativeTo(null);
		this.eventCtr = eventCtr;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel buttonMenuPanel = new JPanel();
		contentPane.add(buttonMenuPanel, BorderLayout.WEST);
		GridBagLayout gbl_buttonMenuPanel = new GridBagLayout();
		gbl_buttonMenuPanel.columnWidths = new int[] { 200, 0 };
		gbl_buttonMenuPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_buttonMenuPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_buttonMenuPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		buttonMenuPanel.setLayout(gbl_buttonMenuPanel);

		/**
		 * 1000fryd
		 * */
		JLabel lblNewLabel = new JLabel("1000Fryd");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.ipady = 30;
		gbc_lblNewLabel.ipadx = 10;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		buttonMenuPanel.add(lblNewLabel, gbc_lblNewLabel);

		/**
		 * Navigation button: Events
		 * */
		JButton btnEvents = new JButton("    Events    ");
		GridBagConstraints gbc_btnEvents = new GridBagConstraints();
		gbc_btnEvents.insets = new Insets(0, 0, 5, 0);
		gbc_btnEvents.ipady = 10;
		gbc_btnEvents.ipadx = 50;
		gbc_btnEvents.gridx = 0;
		gbc_btnEvents.gridy = 1;
		buttonMenuPanel.add(btnEvents, gbc_btnEvents);
		
		//When the navigation button is clicked, it will open the window and close the old one
		btnEvents.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open the window to see overview of events
				MainGUI mainGUI = new MainGUI();
				mainGUI.setVisible(true);
				// Dispose the current window
				dispose();
			}
		});

		/**
		 * Space between buttons
		 * */
		JLabel Space1 = new JLabel("           ");
		GridBagConstraints gbc_Space1 = new GridBagConstraints();
		gbc_Space1.insets = new Insets(0, 0, 5, 0);
		gbc_Space1.gridx = 0;
		gbc_Space1.gridy = 2;
		buttonMenuPanel.add(Space1, gbc_Space1);

		/**
		 * Navigation menu: Nyt event
		 * */
		JButton btnNewEvent = new JButton("  Nyt event  ");
		GridBagConstraints gbc_btnNewEvent = new GridBagConstraints();
		gbc_btnNewEvent.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewEvent.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewEvent.ipady = 10;
		gbc_btnNewEvent.ipadx = 50;
		gbc_btnNewEvent.gridx = 0;
		gbc_btnNewEvent.gridy = 3;
		buttonMenuPanel.add(btnNewEvent, gbc_btnNewEvent);
		
		//When the navigation button is clicked, it will open the window and close the old one
		btnNewEvent.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open the window to create a new event
				CreateEventGUI createEventGUI = new CreateEventGUI(eventCtr);
				createEventGUI.setVisible(true);
				// Dispose the current window
				dispose();
			}
		});

		/**
		 * Space between buttons
		 * */
		JLabel Space2 = new JLabel("           ");
		GridBagConstraints gbc_Space2 = new GridBagConstraints();
		gbc_Space2.insets = new Insets(0, 0, 5, 0);
		gbc_Space2.gridx = 0;
		gbc_Space2.gridy = 4;
		buttonMenuPanel.add(Space2, gbc_Space2);

		/**
		 * Navigation menu: Vagt oversigt
		 * */
		JButton btnAssignmentOverview = new JButton("Vagt oversigt");
		GridBagConstraints gbc_btnAssignmentOverview = new GridBagConstraints();
		gbc_btnAssignmentOverview.ipady = 10;
		gbc_btnAssignmentOverview.ipadx = 50;
		gbc_btnAssignmentOverview.insets = new Insets(0, 0, 5, 0);
		gbc_btnAssignmentOverview.gridx = 0;
		gbc_btnAssignmentOverview.gridy = 5;
		buttonMenuPanel.add(btnAssignmentOverview, gbc_btnAssignmentOverview);
		
		//When the navigation button is clicked, it will open the window and close the old one
		btnAssignmentOverview.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open the window to create a new event
				AssignmentMainGUI assignmentMainGUI = new AssignmentMainGUI(eventCtr);
				assignmentMainGUI.setVisible(true);
				// Dispose the current window
				dispose();
			}
		});

		/**
		 * Space between buttons
		 * */
		JLabel Space3 = new JLabel("           ");
		GridBagConstraints gbc_Space3 = new GridBagConstraints();
		gbc_Space3.insets = new Insets(0, 0, 5, 0);
		gbc_Space3.gridx = 0;
		gbc_Space3.gridy = 6;
		buttonMenuPanel.add(Space3, gbc_Space3);

		/**
		 * Navigation button: Opret vagt
		 * */
		JButton btnCreateAssignment = new JButton("  Opret vagt");
		GridBagConstraints gbc_btnCreateAssignment = new GridBagConstraints();
		gbc_btnCreateAssignment.ipady = 10;
		gbc_btnCreateAssignment.ipadx = 50;
		gbc_btnCreateAssignment.insets = new Insets(0, 0, 5, 0);
		gbc_btnCreateAssignment.gridx = 0;
		gbc_btnCreateAssignment.gridy = 7;
		buttonMenuPanel.add(btnCreateAssignment, gbc_btnCreateAssignment);
		
		//When the navigation button is clicked, it will open the window and close the old one
		btnCreateAssignment.addActionListener((ActionListener) new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open the window to create a new event
				CreateAssignmentPart1GUI createAssignmentPart1GUI = new CreateAssignmentPart1GUI(eventCtr);
				createAssignmentPart1GUI.setVisible(true);
				// Dispose the current window
				dispose();
			}
		});

		/**
		 * Space between buttons
		 * */
		JLabel Space4 = new JLabel("           ");
		GridBagConstraints gbc_Space4 = new GridBagConstraints();
		gbc_Space4.insets = new Insets(0, 0, 5, 0);
		gbc_Space4.gridx = 0;
		gbc_Space4.gridy = 8;
		buttonMenuPanel.add(Space4, gbc_Space4);

		/**
		 * Tilmeld vagt
		 * */
		JButton btnEnrollAssignment = new JButton("Tilmeld vagt");
		GridBagConstraints gbc_btnEnrollAssignment = new GridBagConstraints();
		gbc_btnEnrollAssignment.insets = new Insets(0, 0, 5, 0);
		gbc_btnEnrollAssignment.ipady = 10;
		gbc_btnEnrollAssignment.ipadx = 50;
		gbc_btnEnrollAssignment.gridx = 0;
		gbc_btnEnrollAssignment.gridy = 9;
		buttonMenuPanel.add(btnEnrollAssignment, gbc_btnEnrollAssignment);

		/**
		 * Space between buttons
		 * */
		JLabel Space5 = new JLabel("           ");
		GridBagConstraints gbc_Space5 = new GridBagConstraints();
		gbc_Space5.insets = new Insets(0, 0, 5, 0);
		gbc_Space5.gridx = 0;
		gbc_Space5.gridy = 10;
		buttonMenuPanel.add(Space5, gbc_Space5);

		/**
		 * Navigation button: Mine vagter
		 * */
		JButton btnMyAssignments = new JButton(" Mine vagter");
		GridBagConstraints gbc_btnMyAssignments = new GridBagConstraints();
		gbc_btnMyAssignments.ipady = 10;
		gbc_btnMyAssignments.ipadx = 50;
		gbc_btnMyAssignments.insets = new Insets(0, 0, 5, 0);
		gbc_btnMyAssignments.gridx = 0;
		gbc_btnMyAssignments.gridy = 11;
		buttonMenuPanel.add(btnMyAssignments, gbc_btnMyAssignments);

		/**
		 * Space between buttons
		 * */
		JLabel Space6 = new JLabel("           ");
		GridBagConstraints gbc_Space6 = new GridBagConstraints();
		gbc_Space6.insets = new Insets(0, 0, 5, 0);
		gbc_Space6.gridx = 0;
		gbc_Space6.gridy = 12;
		buttonMenuPanel.add(Space6, gbc_Space6);

		/**
		 * Navigation button: Lokationer
		 * */
		JButton btnLocations = new JButton(" Lokationer");
		GridBagConstraints gbc_btnLocations = new GridBagConstraints();
		gbc_btnLocations.ipady = 10;
		gbc_btnLocations.ipadx = 50;
		gbc_btnLocations.insets = new Insets(0, 0, 5, 0);
		gbc_btnLocations.gridx = 0;
		gbc_btnLocations.gridy = 13;
		buttonMenuPanel.add(btnLocations, gbc_btnLocations);

		/**
		 * Space between buttons
		 * */
		JLabel Space9 = new JLabel("           ");
		GridBagConstraints gbc_Space9 = new GridBagConstraints();
		gbc_Space9.insets = new Insets(0, 0, 5, 0);
		gbc_Space9.gridx = 0;
		gbc_Space9.gridy = 14;
		buttonMenuPanel.add(Space9, gbc_Space9);

		/**
		 * Space between buttons
		 * */
		JLabel Space7 = new JLabel("           ");
		GridBagConstraints gbc_Space7 = new GridBagConstraints();
		gbc_Space7.insets = new Insets(0, 0, 5, 0);
		gbc_Space7.gridx = 0;
		gbc_Space7.gridy = 15;
		buttonMenuPanel.add(Space7, gbc_Space7);

		/**
		 * Space between buttons
		 * */
		JLabel Space8 = new JLabel("           ");
		GridBagConstraints gbc_Space8 = new GridBagConstraints();
		gbc_Space8.insets = new Insets(0, 0, 5, 0);
		gbc_Space8.gridx = 0;
		gbc_Space8.gridy = 16;
		buttonMenuPanel.add(Space8, gbc_Space8);

		/**
		 * Navigation menu: Log ud
		 * */
		JButton btnSignOut = new JButton("Log ud");
		GridBagConstraints gbc_btnSignOut = new GridBagConstraints();
		gbc_btnSignOut.ipadx = 80;
		gbc_btnSignOut.gridx = 0;
		gbc_btnSignOut.gridy = 17;
		buttonMenuPanel.add(btnSignOut, gbc_btnSignOut);

		/**
		 * Panel on the right side of the screen
		 * */
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(700, 10));
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(null);

		/**
		 * Text
		 * */
		JLabel lblNewLabel_1 = new JLabel("Vælg et event du vil oprette vagter for");
		lblNewLabel_1.setBounds(50, 26, 300, 14);
		panel_1.add(lblNewLabel_1);

		/**
		 * Table
		 * 
		 * Has an mouseListener that registers when the user clicks on a row.
		 * When a mouse click is detected on a row of the table, it gets the row number 
		 * where the click occurred and retrieves the value in the first column (0-indexed) 
		 * of that row, which is assigned to the object 'eventTitel'.
		 * 
		 * If 'eventTitel' is not null, it enters a loop that iterates through all events 
		 * in the event controller (eventCtr).
		 * 
		 * If it finds an event where the event title matches 'eventTitel', it sets that
		 *  event in the event controller.
		 * */
		table = new JTable();
		// Adding a mouse listener to the table
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			// This method is called when the mouse is clicked
			public void mouseClicked(MouseEvent e) {
				// Getting the row number where the mouse was clicked
				int row = table.rowAtPoint(e.getPoint());

				// Checking if the row number is valid
				if (row >= 0) {
					// Getting the value at the specified row and column (0 in this case)
					// It retrieves the value from the first column of the table
					Object eventTitel = table.getValueAt(row, 0);

					// Checking if the value is not null
					if (eventTitel != null) {
						try {
							// Trying to find the event
							for (Event event : eventCtr.showAllEvents())
								// Checking if the event title matches the selected value
								if (event.getEventTitle().equals(eventTitel.toString())) {
									// Add the selected value to eventCtr
									eventCtr.setEvent(event);
								}
						} catch (DataAccessException e1) {
							// TODO Auto-generated catch block
							// Displaying an error message if an exception is thrown
							mainGUI.displayErrorMessage("could not find the event");
						}

					}
				}
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Titel", "Type", "Lokation", "Start", "Slut" }));
		table.setBounds(30, 50, 640, 480);
		panel_1.add(table);

		// Adding data to the JTable
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		// Setting the model to the table
		table.setModel(model);

		// Adding the table to a JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 60, 600, 370);

		// Adding the scrollPane to the panel
		panel_1.add(scrollPane);

		/**
		 * Button: Næste
		 * */
		JButton btnNext = new JButton("Næste");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CreateAssignmentPart2GUI with eventCtr as a parameter
				// We do this to pass the EventCtr object to the constructor in the next window
				CreateAssignmentPart2GUI createAssignmentPart2GUI = new CreateAssignmentPart2GUI(eventCtr);
				//shows GUI
				createAssignmentPart2GUI.setVisible(true);
			}
		});
		btnNext.setBounds(563, 463, 89, 23);
		panel_1.add(btnNext);
		showAllEventsForAssignemnts();
	}

	/**
	 * This method retrieves all the events from the database and displays them in a table.
	 * 
	 * It clears all existing data in the table. After that, it loops through each Event object 
	 * in the events list. For each Event, it adds a new row to the table model, consisting 
	 * of the event's title, type, location ID, start time, and end time.
	 * */
	private void showAllEventsForAssignemnts() {
		try {

			// Gets all events from the database
			List<Event> events = eventCtr.showAllEvents();

			// Accesses the table model from the table object
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			// Clears all rows from the table model
			model.setRowCount(0);

			// Adding each event to the table
			for (Event event : events) {
				// Adds a new row to the table model with the details of the current event
				model.addRow(new Object[] { event.getEventTitle(), event.getEventType(), event.getLocationId(),
						event.getEventStartTime(), event.getEventEndTime() });
			}
		// If a DataAccessException is encountered, it displays an error message
		} catch (DataAccessException e) {
			mainGUI.displayErrorMessage("kunne ikke finde events");
		}
	}

}
