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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DBLayer.DataAccessException;
import controllerLayer.EventCtr;
import model.Band;
import model.Concert;
import model.Event;
import model.EventLocation;

/**
 * GUI for creating concerts.
 * 
 * The `displayEventLocations` method is used to update the GUI with event
 * locations that can accommodate the estimated number of attendees.
 * 
 * The `showAddedBands` method updates a table in the GUI with bands that have
 * been added to the concert.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */

public class CreateConcertGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldMaxAntalDeltagere;
	private JTextField textFieldPris;
	private EventLocationGUI eventLocationGUI;
	private String selectedBand;
	private int estimatedAttendees;
	private JTable tableTilføjedeBands;
	private EventCtr eventCtr;
	private Event event;
	private JTable tableBands;
	private MainGUI mainGUI;
	private JTextArea textAreaBeskrivelse;

	/**
	 * Create the frame. Constructor. 
	 * 
	 * @param eventLocationGUI
	 * @throws SQLException
	 */
	public CreateConcertGUI(EventCtr eventCtr) {
		setLocationRelativeTo(null);
		this.eventLocationGUI = new EventLocationGUI(eventCtr);
		mainGUI = new MainGUI();
		tableTilføjedeBands = new JTable();

		this.eventCtr = eventCtr;
		this.event = eventCtr.getEvent();

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
		 * Panel to the right
		 * */
		JPanel panelNyKoncert = new JPanel();
		panelNyKoncert.setPreferredSize(new Dimension(700, 10));
		contentPane.add(panelNyKoncert, BorderLayout.EAST);
		panelNyKoncert.setLayout(null);

		/**
		 * Maximum estimated attendees
		 * */
		textFieldMaxAntalDeltagere = new JTextField();
		textFieldMaxAntalDeltagere.setBounds(44, 95, 100, 20);
		panelNyKoncert.add(textFieldMaxAntalDeltagere);
		textFieldMaxAntalDeltagere.setColumns(10);

		// Adding the table to a JScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(300, 95, 350, 290);

		// Adding the scrollPane to the panel
		panelNyKoncert.add(scrollPane);

		/**
		 * Table with band, genre, description
		 * */
		tableBands = new JTable();
		tableBands.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Band", "Genre", "Beskrivelse" }));
		scrollPane.setViewportView(tableBands);

		/**
		 * ComboBox
		 * Choose a band name and add them to the panel
		 * */
		JComboBox<String> comboBoxBandNavn = new JComboBox<String>();
		comboBoxBandNavn.setEditable(true);
		comboBoxBandNavn.setBounds(370, 405, 170, 20);
		panelNyKoncert.add(comboBoxBandNavn);
		//Method to get names into the comboBox / drop down
		try {
			// Retrieve all bands
			List<Band> bands = eventCtr.findAllBands();

			// Populate the JComboBox with band names
			for (Band band : bands) {
				comboBoxBandNavn.addItem(band.getBandName());
			}
		} catch (DataAccessException e) {
			mainGUI.displayErrorMessage("kunne ikke finde nogen bands prøv igen");
		}

		/**
		 * Button: Tilføj band
		 * Adds the band to the table
		 * */
		JButton btnTilføjBand = new JButton("Tilføj band ");
		btnTilføjBand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (event instanceof Concert) {
					Concert concert = (Concert) event;
				// Gets the selected band from the combo box
				String selectedBandName = (String) comboBoxBandNavn.getSelectedItem();
				// Creates a list to hold the selected band name
				List<String> selectedBandNames = new ArrayList<>();
				List<String> bandNamesOnTheConcert = new ArrayList<>();
				 
			    for (Band band :  concert.getBands()) {
					bandNamesOnTheConcert.add(band.getBandName());
				}
				
				if(!bandNamesOnTheConcert.contains(selectedBandName)) {
				selectedBandNames.add(selectedBandName);
				showAddedBands(selectedBandNames);
				}else {
					mainGUI.displayErrorMessage("du kan ikke tilføje to af de samme bands på en koncert");
				}
				// Adds the list of bands to the "Tilføjede bands" table
				
			}}
		});
		btnTilføjBand.setBounds(550, 405, 100, 20);
		panelNyKoncert.add(btnTilføjBand);

		/**
		 * Band name
		 * */
		JLabel lblBandNavn = new JLabel("Band navn");
		lblBandNavn.setBounds(300, 409, 100, 14);
		panelNyKoncert.add(lblBandNavn);

		/**
		 * Koncert
		 * */
		JLabel lblNyKoncert = new JLabel("Koncert");
		lblNyKoncert.setBounds(31, 11, 200, 14);
		panelNyKoncert.add(lblNyKoncert);

		/**
		 * Max estimated attendees
		 * */
		JLabel lblMaxAntlDeltagere = new JLabel("Max antal deltagere");
		lblMaxAntlDeltagere.setBounds(44, 70, 200, 14);
		panelNyKoncert.add(lblMaxAntlDeltagere);

		/**
		 * Price
		 * */
		JLabel lblPris = new JLabel("Pris kr ");
		lblPris.setBounds(44, 156, 150, 14);
		panelNyKoncert.add(lblPris);

		textFieldPris = new JTextField();
		textFieldPris.setBounds(44, 178, 100, 20);
		panelNyKoncert.add(textFieldPris);
		textFieldPris.setColumns(10);
		
		/**
		 * Description
		 * */
		JLabel lblBeskrivelse = new JLabel("Beskrivelse ");
		lblBeskrivelse.setBounds(44, 238, 200, 14);
		panelNyKoncert.add(lblBeskrivelse);
		// textFieldBeskrivelse.setColumns(1);

		/**
		 * Added bands
		 * */
		JLabel lblTilføjedeBands = new JLabel("Tilføjede bands");
		lblTilføjedeBands.setBounds(300, 70, 200, 14);
		panelNyKoncert.add(lblTilføjedeBands);

		/**
		 * Button: Næste
		 * */
		JButton btnNæste = new JButton("Næste");
		btnNæste.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Gets the selected item (bandName) from comboBoxBandNavn and assigns it to selectedBand
				//selectedBand = (String) comboBoxBandNavn.getSelectedItem();
				// Retrieve the estimated number of attendees from a JTextField
				addConcertInformation();

				// Call the displayEventLocations method with the estimated number of attendees
				displayEventLocations(estimatedAttendees);

				//Open the eventLocationGUI and close this window
				eventLocationGUI.setVisible(true);
				dispose();
			}
		});
		btnNæste.setBounds(601, 521, 89, 23);
		panelNyKoncert.add(btnNæste);

		/**
		 * Button: Tilbage
		 * */
		JButton btnTilbage = new JButton("Tilbage");
		btnTilbage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Closes this window
				dispose();
				// Opens the CreateEventGUI window and makes it visible.
				new CreateEventGUI(eventCtr).setVisible(true);
			}
		});
		btnTilbage.setBounds(502, 521, 89, 23);
		panelNyKoncert.add(btnTilbage);

		/**
		 * Button: Afbryd
		 * */
		JButton btnAfbryd = new JButton("Afbryd");
		btnAfbryd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Closes window and opens mainGUI
				dispose();
				new MainGUI().setVisible(true);
			}
		});
		btnAfbryd.setBackground(SystemColor.menu);
		btnAfbryd.setBorder(null);
		btnAfbryd.setBounds(403, 521, 89, 23);
		panelNyKoncert.add(btnAfbryd);
		
	
	    JScrollPane scrollPaneBeskrivelse = new JScrollPane();
	    scrollPaneBeskrivelse.setBounds(44, 261, 200, 170);
	    panelNyKoncert.add(scrollPaneBeskrivelse);

	    textAreaBeskrivelse = new JTextArea(); 
	    scrollPaneBeskrivelse.setViewportView(textAreaBeskrivelse);
	}

	/**
	 * The `displayEventLocations` method is used to update the GUI with event
	 * locations that can accommodate the estimated number of attendees.
	 */
	public void displayEventLocations(int estimatedAttendees) {
		List<EventLocation> eventLocations = null;

		try {
			// Calls the method to filter event locations based on their capacity and
			// availability
			eventLocations = eventCtr.filterEventLocationOnCapacityAndAvailability();
		} catch (DataAccessException e) {
			// If there is a DataAccessException while executing the above code
			// TODO Auto-generated catch block
			mainGUI.displayErrorMessage("Could not find any available locations");
		}

		// Updates the event locations table and shows the available locations
		eventLocationGUI.updateEventLocationsTable(eventLocations);

	}

	/**
	 * The `showAddedBands` method updates a table in the GUI with bands that have
	 * been added to the concert.
	 */
	private void showAddedBands(List<String> bandNames) {
		eventCtr.addBandsToConcert(bandNames);
		if (event instanceof Concert) {
			Concert concert = (Concert) event;
			// Get events from the database
			List<Band> addedBands = concert.getBands();
			// TODO Implement no duplicates of bands

			// Getting the table model
			DefaultTableModel model = (DefaultTableModel) tableBands.getModel();

			// Clearing the table
			model.setRowCount(0);

			// Adding each band to the table
			for (Band band : addedBands) {
				// Adds a new row to the table for each band, with columns for the band's name,
				// genre, and description
				model.addRow(new Object[] { band.getBandName(), band.getBandGenre(), band.getBandDescription() });
			}
		}
	}

	/**
	 * The `addConcertInformation` method collects the data entered in the GUI and
	 * uses it to update the concert's details.
	 */
	private void addConcertInformation() {
		// Calls the verifyConcertObjectCreation method from the eventCtr object to
		// check if a concert object has been created properly
		eventCtr.verifyCreateEventObject();
		// Retrieves the text from textFieldMaxAntalDeltagere & converts the string to
		// an integer
		String estimatedAttendeesToEvent = textFieldMaxAntalDeltagere.getText();
		int estimatedAttendees = 0;

		// Tries to make the String to an Integer
		try {
			estimatedAttendees = Integer.parseInt(estimatedAttendeesToEvent);
		} catch (NumberFormatException e) {
			mainGUI.displayErrorMessage("kun tal i antal deltager");
		}

		// Retrieves the text from textAreaBeskrivelse
		String descriptionToEvent = textAreaBeskrivelse.getText();
		// Retrieves the text from textFieldPris and converts the string of price to an
		// integer
		String priceToConcert = textFieldPris.getText();
		int priceForConcert = Integer.parseInt(priceToConcert);

		// Checks if the estimated number of attendees is not zero
		// If estimatedAttendees is not zero, the addConcertInformation method from the
		// eventCtr object
		// is called to set the attributes for the event
		// If it is zero, an error message is displayed
		if (estimatedAttendees != 0) {
			eventCtr.addConcertInformation(estimatedAttendees, priceForConcert, descriptionToEvent);

		} else {
			mainGUI.displayErrorMessage("Husk antal deltagere");
		}
	}

}
