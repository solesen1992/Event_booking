/**
 * 
 */
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
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import DBLayer.DataAccessException;
import controllerLayer.EventCtr;
import model.Band;
import model.Concert;
import model.Event;

/**
 * ConfirmationGUI represents the GUI for event confirmation.
 * The user has created a new event and sees a list of the details before they click confirm.
 * When they click confirm, it adds the event to the database.
 * 
 * The printEventDetails method is used to print the details of an event.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */

public class ConfirmationGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextPane txtpnStringType;
	private EventCtr eventCtr;
	private Event event;
	private MainGUI mainGUI;

	/**
	 * Create the frame.
	 * Constructor for ConfirmationGUI.
	 */
	public ConfirmationGUI(EventCtr eventCtr) {
		setLocationRelativeTo(null);
		mainGUI = new MainGUI();
		this.eventCtr = eventCtr;
		this.event = eventCtr.getEvent();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel buttonMenuPanel = new JPanel();
		contentPane.add(buttonMenuPanel, BorderLayout.WEST);
		GridBagLayout gbl_buttonMenuPanel = new GridBagLayout();
		gbl_buttonMenuPanel.columnWidths = new int[] {200, 0};
		gbl_buttonMenuPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_buttonMenuPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_buttonMenuPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
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
		 * Right side of the window
		 * */
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(700, 100));
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(null);
		
		/**
		 * Navigation button: Bekræft
		 * */
		JButton btnBekræft = new JButton("Bekræft");
		btnBekræft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   Event completedEvent = null;
                    try {
                    	// Event gets added to the database
						completedEvent = eventCtr.completedEvent();
					} catch (SQLException | DataAccessException e1) {
						mainGUI.displayErrorMessage("Error occured with inserting data to Database");
						// Print the stack trace for debugging
						e1.printStackTrace();
					}
                    //Creates a pop up that asks the user if they want to add assignments to the event now
                     AddAssignmentPopUpGUI addAssignmentPopUpGUI = new AddAssignmentPopUpGUI(eventCtr);
                     addAssignmentPopUpGUI.setVisible(true);
                    // If the completedEvent object is not null
                    if(completedEvent != null) {
                    // Close the confirmation GUI
                    dispose();
                    }
                    
            }
		});
		btnBekræft.setBounds(611, 519, 89, 23);
		panel_1.add(btnBekræft);
		
		/**
		 * Navigation button: Tilbage
		 * */
		JButton btnTilbage = new JButton("Tilbage");
		btnTilbage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Close this window
				dispose();
				// Open a new window: EventLocationGUI
				new EventLocationGUI(eventCtr).setVisible(true);
			}
		});
		btnTilbage.setBounds(499, 519, 89, 23);
		panel_1.add(btnTilbage);
		
		/**
		 * Navigation button: Afbryd
		 * */
		JButton btnAfbryd = new JButton("Afbryd");
		// Remove the border from the button
		btnAfbryd.setBorder(null);
		// Set the button's background color to the system's default menu color
		btnAfbryd.setBackground(SystemColor.menu);
		btnAfbryd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Close the current window when the button is clicked
				dispose();
				// Open the mainGUI window
				new MainGUI().setVisible(true);
			}
		});
		btnAfbryd.setBounds(386, 519, 89, 23);
		panel_1.add(btnAfbryd);
		
		txtpnStringType = new JTextPane();
		txtpnStringType.setEditable(false);
		txtpnStringType.setFont(txtpnStringType.getFont().deriveFont(15f));
		txtpnStringType.setBounds(50, 50, 600, 450);
		panel_1.add(txtpnStringType);
		//Prints the event details
		printEventDetails(eventCtr);
	}
	
	/**
	 * The printEventDetails method is used to print the details of an event.
	 * 
	 * The method takes an `Event` object as an argument. It gathers details from the `Event` object 
	 * like its type, title, start time, end time, estimated attendees, and description. 
	 * It formats the start and end times using a `DateTimeFormatter`.
	 * 
	 * If the `Event` object is an instance of the `Concert` class, it gets the price and bands 
	 * associated with the concert. It loops over the bands and appends each band's name to a string.
	 * 
	 * It prints out all of the gathered details. If the event is a concert, it also prints out 
	 * the price and the list of bands.
	 * */
	public void printEventDetails(EventCtr event) {
		// Gathering event details
		String textTypeEvent ="Type: " + event.getEvent().getEventType();
		String textTitleEvent = "Title: " + event.getEvent().getEventTitle();
		// Formatting the start and end times
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String textFormatedEventStartTime = "Start dato og tid: " + event.getEvent().getEventStartTime().format(formatter);
		String textFormatedEventEndTime =  "Slut dato og tid: " + event.getEvent().getEventEndTime().format(formatter);
		String textParsedEstimatedAttendees = "Max antal deltagere: " + Integer.toString(event.getEvent().getEstimatedAttendees());
		String textDescriptionEvent = "Beskrivelse: " + event.getEvent().getDescription();
		String textPriceConcert ="Pris: " ;
		String textGetConcertBand = "Bands: ";
		String textGetLocation = "Lokation: " + event.getEvent().getEventLocation().getLocationName()+ " " + event.getEvent().getEventLocation().getCityZipCode().getCity()+ " "+event.getEvent().getEventLocation().getCityZipCode().getZipCode(); 
		String textGetVolunteer = "Frivilig: " + event.getEvent().getVolunteer().getFirstName()+" "+ event.getEvent().getVolunteer().getLastName();
	
		
		// Checking if the event is a concert
		if (event.getEvent() instanceof Concert) {
			// If the event is a Concert, it is cast to a Concert object
            Concert concert = (Concert) event.getEvent();
            // The price of the concert is added to the textPriceConcert string
            textPriceConcert += Double.toString(concert.getPrice());
         // For each band in the concert, its name is added to the textGetConcertBand string
         for (Band band : concert.getBands()) {
        	 textGetConcertBand +=  band.getBandName() + ", ";
         }
        
        // A string called compositeString is created, which includes various details about the event
        // \n adds a new line
        String compositeString = "Oversigt "+"\n"+textTypeEvent+ "\n" + textTitleEvent+ "\n" + textFormatedEventStartTime+ "\n" + textFormatedEventEndTime+ "\n"+
        		textParsedEstimatedAttendees+ "\n"+textDescriptionEvent+ "\n"+textPriceConcert+ "\n"+textGetConcertBand+"\n"+ textGetLocation+"\n"+textGetVolunteer;
        // The compositeString is set as the text of txtpnStringType
        txtpnStringType.setText(compositeString);
        }
    }
}
