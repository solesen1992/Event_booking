
/**
 * 
 */
package UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DBLayer.DataAccessException;
import controllerLayer.EventCtr;
import model.Event;
import observer.EventObserverIF;

/**
 * `MainGUI` is the front page/starting point of the event management system. It
 * shows us an overview of the already existing events in a table.
 * 
 * The class has a method called `showAllEvents()`, which fetches all events
 * from a database using an `EventCtr` object and populates a table in the GUI
 * with this data. Each row in the table corresponds to an event and displays
 * information such as the event title, type, location, start time, and end
 * time.
 * 
 * This class also includes an `ActionListener` for buttons, which defines what
 * happens when these buttons are clicked. For example, when the "Nyt event"
 * button is clicked, a new window for creating an event is opened. When the
 * "Lokationer" button is clicked, a window for viewing event locations is
 * opened.
 * 
 * The `main()` method of this class sets up the main frame to be visible and to
 * close the application when the frame is closed. It also catches and handles
 * exceptions appropriately.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */
public class MainGUI extends JFrame implements EventObserverIF {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private EventCtr eventCtr;
	private Event event;

	/**
	 * Create the frame. Constructor.
	 */
	public MainGUI() {
		setLocationRelativeTo(null);
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
		 */
		JLabel lblNewLabel = new JLabel("1000Fryd");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.ipady = 30;
		gbc_lblNewLabel.ipadx = 10;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		buttonMenuPanel.add(lblNewLabel, gbc_lblNewLabel);

		/**
		 * Navigation menu
		 */
		JButton btnEvents = new JButton("      Events      ");
		btnEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAllEvents();
			}
		});
		GridBagConstraints gbc_btnEvents = new GridBagConstraints();
		gbc_btnEvents.insets = new Insets(0, 0, 5, 0);
		gbc_btnEvents.ipady = 10;
		gbc_btnEvents.ipadx = 50;
		gbc_btnEvents.gridx = 0;
		gbc_btnEvents.gridy = 1;
		buttonMenuPanel.add(btnEvents, gbc_btnEvents);

		/**
		 * Space between buttons
		 */
		JLabel Space1 = new JLabel("           ");
		GridBagConstraints gbc_Space1 = new GridBagConstraints();
		gbc_Space1.insets = new Insets(0, 0, 5, 0);
		gbc_Space1.gridx = 0;
		gbc_Space1.gridy = 2;
		buttonMenuPanel.add(Space1, gbc_Space1);

		/**
		 * Navigation menu
		 */
		JButton btnNewEvent = new JButton("    Nyt event   ");
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
				dispose();
			}
		});

		/**
		 * Space between buttons
		 */
		JLabel Space2 = new JLabel("           ");
		GridBagConstraints gbc_Space2 = new GridBagConstraints();
		gbc_Space2.insets = new Insets(0, 0, 5, 0);
		gbc_Space2.gridx = 0;
		gbc_Space2.gridy = 4;
		buttonMenuPanel.add(Space2, gbc_Space2);

		/**
		 * Navigation menu
		 */
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
		 */
		JLabel Space3 = new JLabel("           ");
		GridBagConstraints gbc_Space3 = new GridBagConstraints();
		gbc_Space3.insets = new Insets(0, 0, 5, 0);
		gbc_Space3.gridx = 0;
		gbc_Space3.gridy = 6;
		buttonMenuPanel.add(Space3, gbc_Space3);

		/**
		 * Navigation menu
		 */
		JButton btnCreateAssignment = new JButton("    Opret vagt");
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
		 */
		JLabel Space4 = new JLabel("           ");
		GridBagConstraints gbc_Space4 = new GridBagConstraints();
		gbc_Space4.insets = new Insets(0, 0, 5, 0);
		gbc_Space4.gridx = 0;
		gbc_Space4.gridy = 8;
		buttonMenuPanel.add(Space4, gbc_Space4);

		/**
		 * Navigation menu
		 */
		JButton btnEnrollAssignment = new JButton(" Tilmeld vagt ");
		GridBagConstraints gbc_btnEnrollAssignment = new GridBagConstraints();
		gbc_btnEnrollAssignment.insets = new Insets(0, 0, 5, 0);
		gbc_btnEnrollAssignment.ipady = 10;
		gbc_btnEnrollAssignment.ipadx = 50;
		gbc_btnEnrollAssignment.gridx = 0;
		gbc_btnEnrollAssignment.gridy = 9;
		buttonMenuPanel.add(btnEnrollAssignment, gbc_btnEnrollAssignment);

		/**
		 * Space between buttons
		 */
		JLabel Space5 = new JLabel("           ");
		GridBagConstraints gbc_Space5 = new GridBagConstraints();
		gbc_Space5.insets = new Insets(0, 0, 5, 0);
		gbc_Space5.gridx = 0;
		gbc_Space5.gridy = 10;
		buttonMenuPanel.add(Space5, gbc_Space5);

		/**
		 * Navigation menu
		 */
		JButton btnMyAssignments = new JButton("  Mine vagter ");
		GridBagConstraints gbc_btnMyAssignments = new GridBagConstraints();
		gbc_btnMyAssignments.ipady = 10;
		gbc_btnMyAssignments.ipadx = 50;
		gbc_btnMyAssignments.insets = new Insets(0, 0, 5, 0);
		gbc_btnMyAssignments.gridx = 0;
		gbc_btnMyAssignments.gridy = 11;
		buttonMenuPanel.add(btnMyAssignments, gbc_btnMyAssignments);

		/**
		 * Space between buttons
		 */
		JLabel Space6 = new JLabel("           ");
		GridBagConstraints gbc_Space6 = new GridBagConstraints();
		gbc_Space6.insets = new Insets(0, 0, 5, 0);
		gbc_Space6.gridx = 0;
		gbc_Space6.gridy = 12;
		buttonMenuPanel.add(Space6, gbc_Space6);

		/**
		 * Navigation menu
		 */
		JButton btnLocations = new JButton("  Lokationer ");
		GridBagConstraints gbc_btnLocations = new GridBagConstraints();
		gbc_btnLocations.ipady = 10;
		gbc_btnLocations.ipadx = 50;
		gbc_btnLocations.insets = new Insets(0, 0, 5, 0);
		gbc_btnLocations.gridx = 0;
		gbc_btnLocations.gridy = 13;
		buttonMenuPanel.add(btnLocations, gbc_btnLocations);

		/**
		 * Space between buttons
		 */
		JLabel Space9 = new JLabel("           ");
		GridBagConstraints gbc_Space9 = new GridBagConstraints();
		gbc_Space9.insets = new Insets(0, 0, 5, 0);
		gbc_Space9.gridx = 0;
		gbc_Space9.gridy = 14;
		buttonMenuPanel.add(Space9, gbc_Space9);

		/**
		 * Space between buttons
		 */
		JLabel Space7 = new JLabel("           ");
		GridBagConstraints gbc_Space7 = new GridBagConstraints();
		gbc_Space7.insets = new Insets(0, 0, 5, 0);
		gbc_Space7.gridx = 0;
		gbc_Space7.gridy = 15;
		buttonMenuPanel.add(Space7, gbc_Space7);

		/**
		 * Space between buttons
		 */
		JLabel Space8 = new JLabel("           ");
		GridBagConstraints gbc_Space8 = new GridBagConstraints();
		gbc_Space8.insets = new Insets(0, 0, 5, 0);
		gbc_Space8.gridx = 0;
		gbc_Space8.gridy = 16;
		buttonMenuPanel.add(Space8, gbc_Space8);

		/**
		 * Navigation menu
		 */
		JButton btnLogUd = new JButton("Log ud");
		GridBagConstraints gbc_btnLogUd = new GridBagConstraints();
		gbc_btnLogUd.ipadx = 80;
		gbc_btnLogUd.gridx = 0;
		gbc_btnLogUd.gridy = 17;
		buttonMenuPanel.add(btnLogUd, gbc_btnLogUd);

		/**
		 * Panel in the right side of the screen
		 */
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(700, 10));
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(null);

		/**
		 * Text for upcoming events
		 */
		JLabel lblNewLabel_1 = new JLabel("Kommende events");
		lblNewLabel_1.setBounds(50, 26, 300, 14);
		panel_1.add(lblNewLabel_1);

		/**
		 * Table with an overview of all the events Makes empty rows Columns with names
		 */
		table = new JTable();
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
		scrollPane.setBounds(50, 60, 600, 470);

		// Adding the scrollPane to the panel
		panel_1.add(scrollPane);

		try {
			eventCtr = new EventCtr(event);
		} catch (DataAccessException e1) {
			// We then call the displayErrorMessage function with a custom error message
			displayErrorMessage("Error occured try again");
		}
		showAllEvents();
		// Part of the observer pattern. Gets the events from the database again
		update();
	}

	// takes the error message as String and show it in a Dialog
	public void displayErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Fetch events from the database and adds them to the 'events' list
	 */
	private void showAllEvents() {
		try {

			// Get events from the database
			List<Event> events = eventCtr.showAllEvents();

			// Getting the table model
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			// Clearing the table
			model.setRowCount(0);

			// Adding each event to the table
			for (Event event : events) {
				model.addRow(new Object[] { event.getEventTitle(), event.getEventType(), event.getLocationId(),
						event.getEventStartTime(), event.getEventEndTime() });
			}
		} catch (DataAccessException e) {
			displayErrorMessage("kunne ikke finde nogen events prøv igen");
		}

	}

	/**
	 * Part of the observer pattern Calls the method showAllEvents to get the events
	 * from the database again
	 */
	@Override
	public void update() {
		showAllEvents();
	}
}
