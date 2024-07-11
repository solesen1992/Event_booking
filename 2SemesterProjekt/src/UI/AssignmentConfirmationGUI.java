package UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
 * GUI confirmation page for Assignment.
 * It extends a JFrame which is a window with a title bar and a close button.
 * It shows the user the assignments they just added to the event and asks them to confirm.
 * If they confirm, it updates the AssignmentMainGUI and closes this window.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 * */

public class AssignmentConfirmationGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private EventCtr eventCtr;
	private AssignmentMainGUI assignmentMainGUI;
	private AssignmentCtr assignmentCtr;
 
	/**
	 * Creates the frame.
	 * Constructor.
	 */
	public AssignmentConfirmationGUI(EventCtr eventCtr) {
		//Center window on the screen
		setLocationRelativeTo(null);
		try {
			this.assignmentCtr = new AssignmentCtr();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Sets the eventCtr field to the passed in value
		this.eventCtr = eventCtr;
		
		this.assignmentMainGUI = new AssignmentMainGUI(eventCtr);
		
		//default close operation for the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//size on frame
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	
		/**
		 * Our navigation menu
		 * */
		JPanel buttonMenuPanel = new JPanel();
		contentPane.add(buttonMenuPanel, BorderLayout.WEST);
		GridBagLayout gbl_buttonMenuPanel = new GridBagLayout();
		 // Sets the column widths of the GridBagLayout
		gbl_buttonMenuPanel.columnWidths = new int[] { 200, 0 };
		gbl_buttonMenuPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_buttonMenuPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_buttonMenuPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		buttonMenuPanel.setLayout(gbl_buttonMenuPanel);
	
		/**
		 * 1000fryd name
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
		 * Events button
		 * */
		JButton btnEvents = new JButton("    Events    ");
		GridBagConstraints gbc_btnEvents = new GridBagConstraints();
		// Sets the button's external padding
		gbc_btnEvents.insets = new Insets(0, 0, 5, 0);
		// Sets the button's internal padding in the y-direction
		gbc_btnEvents.ipady = 10;
		// Sets the button's internal padding in the x-direction
		gbc_btnEvents.ipadx = 50;
		// Sets the button's x-coordinate in its grid
		gbc_btnEvents.gridx = 0;
		// Set the button's y-coordinate in its grid
		gbc_btnEvents.gridy = 1;
		// Adds the button to the buttonMenuPanel
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
		 * The right part of the screen.
		 * Contains a table, buttons etc.
		 * */
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(700, 10));
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(null);
	
		/**
		 * Text on the right part of the screen
		 * */
		JLabel lblNewLabel_1 = new JLabel("Vil du bekræfte at disse vagter skal tilføjes til eventet?");
		lblNewLabel_1.setBounds(50, 26, 300, 14);
		panel_1.add(lblNewLabel_1);
	
		/**
		 * Table with the just created assignments
		 * */
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Event", "Arbejdsopgave", "Start", "Slut", "Beskrivelse"}));
		table.setBounds(30, 50, 640, 480);
		panel_1.add(table);
	
		// Adding data to the JTable
	      model = (DefaultTableModel) table.getModel();
	
		// Setting the model to the table
		table.setModel(model);
	
		// Adding the table to a JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 60, 600, 370);
	
		// Adding the scrollPane to the panel
		panel_1.add(scrollPane);
		
		
		/**
		 * Button: Bekræft
		 * */
		JButton btnBekræft = new JButton("Bekræft");
		//when the button is clicked
		btnBekræft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Assignments gets added to the database
					eventCtr.confirmAssignments(eventCtr.getEvent());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DataAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// Updates AssignmentMainGUI with the updated list of assignments
			   showAddedAssignments();
			  // Makes the assignmentMainGUI visible to the user
              assignmentMainGUI.setVisible(true);
			}
		});
		//the buttons x and y coordinates and width and height
		btnBekræft.setBounds(561, 487, 89, 23);
		//adds the button to panel_1
		panel_1.add(btnBekræft);
		
		/**
		 * Button: Tilbage
		 * */
		JButton btnTilbage = new JButton("Tilbage");
		btnTilbage.setBounds(462, 487, 89, 23);
		panel_1.add(btnTilbage);
		
		/**
		 * Button: Afbryd
		 * */
		JButton btnAfbryd = new JButton("Afbryd");
		btnAfbryd.setBackground(new Color(255, 255, 255));
		btnAfbryd.setBounds(363, 487, 89, 23);
		panel_1.add(btnAfbryd);
		showAddedAssignments();
	}
	
	/**
	 * showAddedAssignments in the table
	 * */
	private void showAddedAssignments() {
		//Takes the list of assignments associated with an event
        List<Assignment> assignments = eventCtr.getEvent().getAssignments();

        //calls the method updateTable with assignments as argument
        updateTable(assignments);
	}
	
	/**
	 * Gets called on by the showAddedAssignments method
	 * It updates the row data in the table - using assignment details.
	 * */
	private void updateTable(List<Assignment> assignments) {
		// Clears existing rows
        model.setRowCount(0);
        //Goes through all the assignments we just got
        for (Assignment assignment : assignments) {
        	//For each assignment we create a new row in the table and fills it with the details.
            model.addRow(new Object[]{
                    assignment.getEventId(),
                    assignment.getAssignmentType(),
                    assignment.getAssignmentStartTime().toString(),
                    assignment.getAssignmentEndTime().toString(),
                    assignment.getAssignmentDescription()
            });
        }
    }
}
