
package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


import DBLayer.DataAccessException;
import controllerLayer.EventCtr;
import model.Event;

/**
 * GUI for creating events. Before we know what type of event we want to create.
 * We fill out the general information about the event.
 * 
 * It also contains a constructor that initializes all the GUI
 * components and their properties.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */

public class CreateEventGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_eventTitle;
	private JTextField txtYyyymmddhhmm;
	private JTextField textField_eventEndTime;
	private JComboBox<String> eventTypeComboBox;
	private EventCtr eventCtr;
	private String[] eventTypes = { "Bar", "Biograf", "Folkekøkken", "Koncert" };
	private Event event;
	private MainGUI mainGUI;


	/**
	 * Create the frame. Constructor
	 */
	public CreateEventGUI(EventCtr eventCtr) {
		setLocationRelativeTo(null);
		this.eventCtr = eventCtr;
		mainGUI = new MainGUI();
		// this.event = eventCtr.getEvent();
		eventTypeComboBox = new JComboBox<>(eventTypes);
		eventTypeComboBox.setSelectedIndex(-1);

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
		 * Navigation menu: Events
		 * */
		JButton btnEvents = new JButton("    Events    ");
		GridBagConstraints gbc_btnEvents = new GridBagConstraints();
		gbc_btnEvents.insets = new Insets(0, 0, 5, 0);
		gbc_btnEvents.ipady = 10;
		gbc_btnEvents.ipadx = 50;
		gbc_btnEvents.gridx = 0;
		gbc_btnEvents.gridy = 1;
		buttonMenuPanel.add(btnEvents, gbc_btnEvents);

		// When the navigation button is clicked, it will open the window and close the
		// old one
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

		// When the navigation button is clicked, it will open the window and close the
		// old one
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

		// When the navigation button is clicked, it will open the window and close the
		// old one
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
		 * Navigation menu: Opret vagt
		 * */
		JButton btnCreateAssignment = new JButton("  Opret vagt");
		GridBagConstraints gbc_btnCreateAssignment = new GridBagConstraints();
		gbc_btnCreateAssignment.ipady = 10;
		gbc_btnCreateAssignment.ipadx = 50;
		gbc_btnCreateAssignment.insets = new Insets(0, 0, 5, 0);
		gbc_btnCreateAssignment.gridx = 0;
		gbc_btnCreateAssignment.gridy = 7;
		buttonMenuPanel.add(btnCreateAssignment, gbc_btnCreateAssignment);

		// When the navigation button is clicked, it will open the window and close the
		// old one
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
		 * Navigation menu: Tilmeld vagt
		 * */
		JButton btnEnrollAssignment = new JButton("  Tilmeld vagt");
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
		 * Navigation menu: Mine vagter
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
		 * Navigation menu: Lokationer
		 * */
		JButton btnLocations = new JButton("Lokationer");
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
		 * Panel on the right
		 * */
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(700, 100));
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(null);

		/**
		 * Text: Nyt event
		 * */
		JLabel lblNewEvent = new JLabel("Nyt event");
		lblNewEvent.setBounds(50, 0, 300, 16);
		panel_1.add(lblNewEvent);

		/**
		 * Choose event type
		 * */
		JFrame frame = new JFrame();
		String[] eventTypes = { "Bar", "Biograf", "Folkekøkken", "Koncert" };

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);

		JPanel panel_eventTitle = new JPanel();
		panel_eventTitle.setBounds(416, 147, 200, 100);
		panel_1.add(panel_eventTitle);
		GridBagLayout gbl_panel_eventTitle = new GridBagLayout();
		gbl_panel_eventTitle.columnWidths = new int[] { 0, 0 };
		gbl_panel_eventTitle.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_eventTitle.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_eventTitle.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_eventTitle.setLayout(gbl_panel_eventTitle);

		/**
		 * Title
		 * */
		JLabel lblEventTitle = new JLabel("Titel");
		GridBagConstraints gbc_lblEventTitle = new GridBagConstraints();
		gbc_lblEventTitle.anchor = GridBagConstraints.WEST;
		gbc_lblEventTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblEventTitle.gridx = 0;
		gbc_lblEventTitle.gridy = 0;
		panel_eventTitle.add(lblEventTitle, gbc_lblEventTitle);

		textField_eventTitle = new JTextField();
		GridBagConstraints gbc_textField_eventTitle = new GridBagConstraints();
		gbc_textField_eventTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_eventTitle.gridx = 0;
		gbc_textField_eventTitle.gridy = 1;
		panel_eventTitle.add(textField_eventTitle, gbc_textField_eventTitle);
		textField_eventTitle.setColumns(10);

		/**
		 * Start time
		 * */
		JPanel panel_eventStartTime = new JPanel();
		panel_eventStartTime.setBounds(103, 336, 200, 100);
		panel_1.add(panel_eventStartTime);
		GridBagLayout gbl_panel_eventStartTime = new GridBagLayout();
		gbl_panel_eventStartTime.columnWidths = new int[] { 151, 0 };
		gbl_panel_eventStartTime.rowHeights = new int[] { 16, 26, 0 };
		gbl_panel_eventStartTime.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_eventStartTime.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_eventStartTime.setLayout(gbl_panel_eventStartTime);

		JLabel lblEventStartTime = new JLabel("Start tid og dato");
		GridBagConstraints gbc_lblEventStartTime = new GridBagConstraints();
		gbc_lblEventStartTime.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblEventStartTime.insets = new Insets(0, 0, 5, 0);
		gbc_lblEventStartTime.gridx = 0;
		gbc_lblEventStartTime.gridy = 0;
		panel_eventStartTime.add(lblEventStartTime, gbc_lblEventStartTime);

		txtYyyymmddhhmm = new JTextField();
		txtYyyymmddhhmm.setText("yyyy-mm-dd-hh-mm");
		GridBagConstraints gbc_txtYyyymmddhhmm = new GridBagConstraints();
		gbc_txtYyyymmddhhmm.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtYyyymmddhhmm.gridx = 0;
		gbc_txtYyyymmddhhmm.gridy = 1;
		panel_eventStartTime.add(txtYyyymmddhhmm, gbc_txtYyyymmddhhmm);
		txtYyyymmddhhmm.setColumns(10);

		/**
		 * Event type drop down menu
		 * */
		JPanel panel_eventTypeDropDownMenu = new JPanel();
		panel_eventTypeDropDownMenu.setBounds(103, 147, 200, 100);
		panel_1.add(panel_eventTypeDropDownMenu);
		GridBagLayout gbl_panel_eventTypeDropDownMenu = new GridBagLayout();
		gbl_panel_eventTypeDropDownMenu.columnWidths = new int[] { 0, 0 };
		gbl_panel_eventTypeDropDownMenu.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_eventTypeDropDownMenu.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_eventTypeDropDownMenu.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_eventTypeDropDownMenu.setLayout(gbl_panel_eventTypeDropDownMenu);

		JLabel lblEventType = new JLabel("Type af event");
		GridBagConstraints gbc_lblEventType = new GridBagConstraints();
		gbc_lblEventType.anchor = GridBagConstraints.WEST;
		gbc_lblEventType.insets = new Insets(0, 0, 5, 0);
		gbc_lblEventType.gridx = 0;
		gbc_lblEventType.gridy = 0;
		panel_eventTypeDropDownMenu.add(lblEventType, gbc_lblEventType);
		// JComboBox<String> eventTypeComboBox = new JComboBox<>(eventTypes);
		GridBagConstraints gbc_eventTypeComboBox = new GridBagConstraints();
		gbc_eventTypeComboBox.gridx = 0;
		gbc_eventTypeComboBox.gridy = 1;
		panel_eventTypeDropDownMenu.add(eventTypeComboBox, gbc_eventTypeComboBox);
		eventTypeComboBox.setSelectedIndex(-1);

		/**
		 * End time
		 * */
		JPanel panel_eventEndTime = new JPanel();
		panel_eventEndTime.setBounds(416, 336, 200, 100);
		panel_1.add(panel_eventEndTime);
		GridBagLayout gbl_panel_eventEndTime = new GridBagLayout();
		gbl_panel_eventEndTime.columnWidths = new int[] { 0, 0 };
		gbl_panel_eventEndTime.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_eventEndTime.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_eventEndTime.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_eventEndTime.setLayout(gbl_panel_eventEndTime);

		JLabel lblEventEndTime = new JLabel("Slut tid og dato");
		GridBagConstraints gbc_lblEventEndTime = new GridBagConstraints();
		gbc_lblEventEndTime.anchor = GridBagConstraints.WEST;
		gbc_lblEventEndTime.insets = new Insets(0, 0, 5, 0);
		gbc_lblEventEndTime.gridx = 0;
		gbc_lblEventEndTime.gridy = 0;
		panel_eventEndTime.add(lblEventEndTime, gbc_lblEventEndTime);

		textField_eventEndTime = new JTextField();
		textField_eventEndTime.setText("yyyy-mm-dd-hh-mm");
		GridBagConstraints gbc_textField_eventEndTime = new GridBagConstraints();
		gbc_textField_eventEndTime.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_eventEndTime.gridx = 0;
		gbc_textField_eventEndTime.gridy = 1;
		panel_eventEndTime.add(textField_eventEndTime, gbc_textField_eventEndTime);
		textField_eventEndTime.setColumns(10);

		/**
		 * Cancel or proceed
		 * */
		JPanel panel_cancelOrProceed = new JPanel();
		panel_cancelOrProceed.setBounds(500, 513, 200, 30);
		panel_1.add(panel_cancelOrProceed);
		GridBagLayout gbl_panel_cancelOrProceed = new GridBagLayout();
		gbl_panel_cancelOrProceed.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_cancelOrProceed.rowHeights = new int[] { 0, 0 };
		gbl_panel_cancelOrProceed.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_cancelOrProceed.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_cancelOrProceed.setLayout(gbl_panel_cancelOrProceed);

		/**
		 * Button: Afbryd
		 * */
		JButton btnCancel = new JButton("Afbryd");
		btnCancel.setBorder(null);
		btnCancel.setBackground(SystemColor.menu);
		GridBagConstraints gbc_btnCancel = new GridBagConstraints();
		gbc_btnCancel.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancel.gridx = 0;
		gbc_btnCancel.gridy = 0;
		panel_cancelOrProceed.add(btnCancel, gbc_btnCancel);

		// Adding an action listener to the Cancel button
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Close the current window and go back to the MainGUI
				dispose();
				new MainGUI().setVisible(true);
			}
		});

		/**
		 * Button: Næste
		 * */
		JButton btnProceed = new JButton("Næste");
		GridBagConstraints gbc_btnProceed = new GridBagConstraints();
		gbc_btnProceed.gridx = 1;
		gbc_btnProceed.gridy = 0;
		panel_cancelOrProceed.add(btnProceed, gbc_btnProceed);

		// Adding an action listener to the Proceed button
		btnProceed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveInformation();
				dispose();
			}
		});
	}

	/**
	 * The `saveInformation` method collects the user input, creates an event using
	 * the `EventCtr` and handles any exceptions. If the event type is a concert,
	 * the method opens a `CreateConcertGUI`.
	 * 
	 * It checks if the `eventCtr` object is null. If it is, the method creates a new `eventCtr` object.
	 * 
	 * It checks if the event type is null or if the event title is empty. If either of these 
	 * conditions is true, an error message is displayed.
	 * 
	 * If the event type and event title are properly filled out, an event is created and added 
	 * to the `eventsOnDate` list. If there are existing events on the chosen date, a pop-up GUI 
	 * is displayed listing these events. If there are no existing events on the chosen date, 
	 * the `handleGUI` method is called with the event parameters.
	 */
	private void saveInformation() {
		try {
			// Get the values entered by the user
			String eventType = (String) eventTypeComboBox.getSelectedItem();
			String eventTitle = textField_eventTitle.getText();
			// Parses the times of the event from the 'txtYyyymmddhhmm' text field with a 
			//specific date-time format
			LocalDateTime eventStartTime = LocalDateTime.parse(txtYyyymmddhhmm.getText(),
					DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm"));
			LocalDateTime eventEndTime = LocalDateTime.parse(textField_eventEndTime.getText(),
					DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm"));
			// Sets the volunteer ID to 1
			int volunteerId = 1; 

			// Checks if the 'eventCtr' object is null. If it is, a new 'eventCtr' object is created
			if (eventCtr == null) {
				eventCtr = new EventCtr(event);
			}
			
			//The user needs to add these - otherwise an error message is shown
			// Checks if the eventType is null or the eventTitle is empty
			if (eventType == null || eventTitle.isEmpty()) {
				// If true, an error message is displayed. Informs user about missing fields
				mainGUI.displayErrorMessage("Udfyld type og titel felter først");
			} else {
				// The user has added the eventTitle and Type
				// An event is created and added to the eventsOnDate list
				List<Event> eventsOnDate = eventCtr.createEvent(eventType, eventTitle, eventStartTime, eventEndTime,
						volunteerId);
				
				
				// Checks if there are existing events on the chosen date
				if (!eventsOnDate.isEmpty()) {
					// If true, a pop-up GUI is displayed listing the existing events
					ExistingEventsPopUpGUI existingEvents = new ExistingEventsPopUpGUI(eventCtr, eventsOnDate);
					existingEvents.setVisible(true);
				} else {
					// If the returned list is empty. There's no events on the same date.
					// After saving information, handleGUI based on event type
					handleGUI(eventType, eventTitle, eventStartTime, eventEndTime, volunteerId);
				}
			}
		// Catches a 'DateTimeParseException' and displays an error message
		} catch (DateTimeParseException e) {
			mainGUI.displayErrorMessage("dato tid format er: yyyy-MM-dd-HH-mm");
		// Catches a 'DataAccessException' and displays an error message
		} catch (DataAccessException e) {
			mainGUI.displayErrorMessage("kunne ikke lave dette event prøv igen");
		}
	}

	/**
	 * The `handleGUI` method handles the UI changes based on the event type. If the
	 * event type is "koncert", the method creates a new instance of
	 * `CreateConcertGUI`.
	 */
	private void handleGUI(String eventType, String eventTitle, LocalDateTime eventStartTime,
			LocalDateTime eventEndTime, int volunteerId) {
		// Check if the eventType is "koncert"
		if (eventType.equalsIgnoreCase("koncert")) {
			// Create a new instance of CreateConcertGUI, passing eventCtr to the constructor
			CreateConcertGUI createConcertGUI = new CreateConcertGUI(eventCtr);
			createConcertGUI.setVisible(true);
		}
		// Close this window
		dispose();
	}
}
