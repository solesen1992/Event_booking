package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import controllerLayer.AssignmentCtr;
import controllerLayer.EventCtr;
import model.Assignment;
import model.Event;

/**
 * 
 * GUI for the assignment overview page.
 * It extends a JFrame which is a window with a title bar and a close button.
 * 
 * It shows the user all the created assignments.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 * */

public class AssignmentMainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private EventCtr eventCtr;
	private Event event;
	private MainGUI mainGUI;

 
	/**
	 * Create the frame. Constructor
	 */
	public AssignmentMainGUI(EventCtr eventCtr) {
		setLocationRelativeTo(null);
		this.eventCtr = eventCtr;
		mainGUI = new MainGUI();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//size of frame
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
		 * 1000Fryd
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
		 * Button: Events
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
		JButton btnTilmeldVagt = new JButton("Tilmeld vagt");
		GridBagConstraints gbc_btnTilmeldVagt = new GridBagConstraints();
		gbc_btnTilmeldVagt.insets = new Insets(0, 0, 5, 0);
		gbc_btnTilmeldVagt.ipady = 10;
		gbc_btnTilmeldVagt.ipadx = 50;
		gbc_btnTilmeldVagt.gridx = 0;
		gbc_btnTilmeldVagt.gridy = 9;
		buttonMenuPanel.add(btnTilmeldVagt, gbc_btnTilmeldVagt);

		btnTilmeldVagt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Open the window to view event locations
				EventLocationGUI eventLocationGUI = new EventLocationGUI(eventCtr);
				eventLocationGUI.setVisible(true);
			}
		});

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
		 * Navigation menu: Bands
		 * */
		JButton btnBands = new JButton("     Bands   ");
		GridBagConstraints gbc_btnBands = new GridBagConstraints();
		gbc_btnBands.ipady = 10;
		gbc_btnBands.ipadx = 50;
		gbc_btnBands.insets = new Insets(0, 0, 5, 0);
		gbc_btnBands.gridx = 0;
		gbc_btnBands.gridy = 11;
		buttonMenuPanel.add(btnBands, gbc_btnBands);

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
		JButton btnLogUd = new JButton("Log ud");
		GridBagConstraints gbc_btnLogUd = new GridBagConstraints();
		gbc_btnLogUd.ipadx = 80;
		gbc_btnLogUd.gridx = 0;
		gbc_btnLogUd.gridy = 17;
		buttonMenuPanel.add(btnLogUd, gbc_btnLogUd);

		/**
		 * Right side of the frame
		 * */
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(700, 10));
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(null);

		/**
		 * Text
		 * */
		JLabel lblNewLabel_1 = new JLabel("Oversigt over nuværende vagter");
		lblNewLabel_1.setBounds(50, 26, 300, 14);
		panel_1.add(lblNewLabel_1);

		/**
		 * Table in the right side of the frame
		 * */
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Event", "Arbejdsopgave", "Start", "Slut", "Beskrivelse" }));
		//size of the frame
		table.setBounds(30, 50, 640, 480);
		panel_1.add(table);

		// Adding data to the JTable
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		// Setting the model to the table
		table.setModel(model);

		// Adding the table to a JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 60, 600, 470);

		// Adding the scrollPane to the panel
		panel_1.add(scrollPane);
		showAllAssignments();

	}

	/**
	 * showAllAssignments() is used to display all assignments associated with events in a table.
	 * It begins by creating a list to hold all assignments and an AssignmentCtr object to get 
	 * all assignments from the database. Then, it retrieves all events from the database.
	 * 
	 * The method then loops through each event and each assignment. If an event ID matches an 
	 * assignments event ID, a new row is added to the table with the event title, assignment type, 
	 * start time, end time, description.
	 * 
	 * */
	private void showAllAssignments() {
		List<Assignment> allAssignments = new ArrayList<>();;
		try {
			AssignmentCtr assignmentCtr = new AssignmentCtr();
			//gets all assignments from the database
			allAssignments.addAll(assignmentCtr.findAllAssignments());
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {

			// Get events from the database
			List<Event> events = eventCtr.showAllEvents();

			// Getting the table model
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			// Clearing the table
			model.setRowCount(0);

			// Loops through each event
			for (Event event : events) {
				//Loops through each assignment
				for (Assignment assignment : allAssignments) {
					// If the event ID matches the assignment's event ID, add a row to the table
					if (assignment.getEventId() == event.getEventId()) {
						model.addRow(new Object[] { event.getEventTitle(), assignment.getAssignmentType(),
								assignment.getAssignmentStartTime(), assignment.getAssignmentEndTime(),
								assignment.getAssignmentDescription() });
					}
				}
			}
		} catch (DataAccessException e) {
			// Displays an error message if there is a problem reading the result set
			mainGUI.displayErrorMessage("kunne ikke finde events og arbejdsopgaver");
		}

	}

}
