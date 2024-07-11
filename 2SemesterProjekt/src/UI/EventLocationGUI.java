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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import model.EventLocation;

/**
 * `EventLocationGUI` where the user can add an location to the event.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */

public class EventLocationGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private MainGUI mainGUI;


	/**
	 * Create the frame.
	 * Constructor.
	 * @param eventCtr 
	 */
	public EventLocationGUI(EventCtr eventCtr) {
		setLocationRelativeTo(null);
		mainGUI = new MainGUI();
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
		 * Navigation menu
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
		 * Navigation menu
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
		 * Navigation menu
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
		 * Navigation menu
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
		 * Navigation menu
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
		 * Navigation menu
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
		 * Navigation menu
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
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(700, 10));
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(null);
		
		/**
		 * Text
		 * */
		JLabel lblNewLabel_1 = new JLabel("Tilføj lokation");
		lblNewLabel_1.setBounds(50, 26, 300, 14);
		panel_1.add(lblNewLabel_1);
		
		
		/**
		 * Table with event locations
		 * */
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// The row number at the mouse click point is obtained
				int row = table.rowAtPoint(e.getPoint());
               
				// If the row number is greater than or equal to 0 (meaning a valid row was clicked)
                if (row >= 0) {
                	// The value at the clicked row and the first column (column index 0) is retrieved
                    Object eventLocationsNameValue = table.getValueAt(row, 0);

                    // If the retrieved value is not null, it is set as the event location
                    if (eventLocationsNameValue != null) {
                        try {
							eventCtr.setEventLocation(eventLocationsNameValue.toString());
						// If a DataAccessException occurs, an error message is displayed
						} catch (DataAccessException e1) {
							// TODO Auto-generated catch block
							mainGUI.displayErrorMessage("eventLocationen kunne ikke sættes på");
						}
                    }
			}
			}
		});
		
		// The model has an empty data array and a column name array
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Navn", "Kapacitet", "Adresse", "Bynavn", "Telefon", "Mail"
			}
		));
		table.setBounds(30, 50, 640, 480);
		panel_1.add(table);
		
		// Adding data to the JTable
				DefaultTableModel model = (DefaultTableModel)table.getModel();

				// Setting the model to the table
				table.setModel(model);

				// Adding the table to a JScrollPane
				JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setBounds(50, 60, 600, 445);

				// Adding the scrollPane to the panel
				panel_1.add(scrollPane);
				
				/**
				 * Navigation button
				 * */
				JButton btnNæste = new JButton("Næste");
				btnNæste.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//New instance, open window and close this one
						ConfirmationGUI confirmationGUI = new ConfirmationGUI(eventCtr);
						confirmationGUI.setVisible(true);
						dispose();
					}
				});
				btnNæste.setBounds(589, 530, 89, 23);
				panel_1.add(btnNæste);
				
				/**
				 * Navigation button
				 * */
				JButton btnTilbage = new JButton("Tilbage");
				btnTilbage.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						new CreateConcertGUI(eventCtr).setVisible(true);
					}
				});
				btnTilbage.setBounds(477, 530, 89, 23);
				panel_1.add(btnTilbage);
				
				/**
				 * Navigation button
				 * */
				JButton btnAfbryd = new JButton("Afbryd");
				btnAfbryd.setBorder(null);
				btnAfbryd.setBackground(SystemColor.menu);
				btnAfbryd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						new MainGUI().setVisible(true);
					}
				});
				btnAfbryd.setBounds(364, 530, 89, 23);
				panel_1.add(btnAfbryd);
				
				/**
				 * Navigation button
				 * */
				JButton btnOpretNyLokation = new JButton("Opret ny lokation");
				btnOpretNyLokation.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				btnOpretNyLokation.setBounds(525, 22, 150, 23);
				panel_1.add(btnOpretNyLokation);
	}
	
	/**
	 * This method updates the event locations table with a new list of event locations.
	 */
	public void updateEventLocationsTable(List<EventLocation> eventLocations) {
		// Get the table model
	    DefaultTableModel model = (DefaultTableModel) table.getModel();
	 // Clear the table before adding new data
	    model.setRowCount(0);
	    
	    // Iterate through each Event Location in the list
	    for (EventLocation location : eventLocations) {
	    	// Add a new row to the table with the following data:
	        // location name, capacity, street, city zip code, phone number, and email
	    	// model.addRow(new Object[] adds an empty row
	        model.addRow(new Object[] {
	            location.getLocationName(),
	            location.getCapacity(),
	            location.getStreet(),
	            location.getCityZipCode().getCity(),
	            location.getPhoneNo(),
	            location.getEmail()
	        });
	    }
	}
}
