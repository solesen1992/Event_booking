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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controllerLayer.EventCtr;

/**
 * GUI for CreateAssignmentPart2GUI.
 * It extends a JFrame which is a window with a title bar and a close button.
 * 
 * It shows the user the information they need to fill out to create an assignment.
 * That's information such as: What is the job, how many needs to do the job etc.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 * */

public class CreateAssignmentPart2GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Assignment;
	private JTextField textField_StartDateTime;
	private JTextField textField_EndDateTime;
	private JComboBox<String> personCountComboBox;
	private EventCtr eventCtr;
	private JTextArea textAreaBeskrivelse;	
	private MainGUI mainGUI;

	/**
	 * Create the frame. 
	 */
	public CreateAssignmentPart2GUI(EventCtr eventCtr) {
		setLocationRelativeTo(null);
		this.eventCtr = eventCtr;
		mainGUI = new MainGUI();

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
		 * Navigation button: Nyt event
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
		 * Navigation button: Vagt oversigt
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
		JButton btnCreateAssignment = new JButton("  Opret vagt ");
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
		 * Navigation button: Log ud
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
		panel_1.setPreferredSize(new Dimension(700, 100));
		contentPane.add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(null);

		/**
		 * Text
		 * */
		JLabel lblNewAssigment = new JLabel("Ny vagt");
		lblNewAssigment.setBounds(50, 0, 300, 16);
		panel_1.add(lblNewAssigment);

		JFrame frame = new JFrame();
		String[] personCounts = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		
		/**
		 * Assignment name
		 * */
		textField_Assignment = new JTextField();
		textField_Assignment.setBounds(50, 63, 200, 20);
		panel_1.add(textField_Assignment);
		textField_Assignment.setColumns(10);
		
		JLabel lblAssignment = new JLabel("Arbejdsopgave");
		lblAssignment.setBounds(50, 46, 300, 16);
		panel_1.add(lblAssignment);

		/**
		 * Start date and time
		 * */
		JLabel lblStartDateTime = new JLabel("Start dato og tid");
		lblStartDateTime.setBounds(50, 121, 300, 16);
		panel_1.add(lblStartDateTime);

		textField_StartDateTime = new JTextField();
		textField_StartDateTime.setColumns(10);
		textField_StartDateTime.setBounds(50, 139, 200, 20);
		panel_1.add(textField_StartDateTime);

		/**
		 * End date and time
		 * */
		JLabel lblEndDateTime = new JLabel("Slut dato og tid");
		lblEndDateTime.setBounds(50, 203, 300, 16);
		panel_1.add(lblEndDateTime);

		textField_EndDateTime = new JTextField();
		textField_EndDateTime.setColumns(10);
		textField_EndDateTime.setBounds(50, 220, 200, 20);
		panel_1.add(textField_EndDateTime);

		/**
		 * Drop down with how many volunteers that need to do the assignment
		 * */
		JPanel panel_PersonCountDropDownMenu = new JPanel();
		panel_PersonCountDropDownMenu.setBounds(50, 280, 200, 73);
		panel_1.add(panel_PersonCountDropDownMenu);
		GridBagLayout gbl_panel_PersonCountDropDownMenu = new GridBagLayout();
		gbl_panel_PersonCountDropDownMenu.columnWidths = new int[] { 0, 0 };
		gbl_panel_PersonCountDropDownMenu.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_PersonCountDropDownMenu.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_PersonCountDropDownMenu.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_PersonCountDropDownMenu.setLayout(gbl_panel_PersonCountDropDownMenu);
		personCountComboBox = new JComboBox<>(personCounts);
		GridBagConstraints gbc_personCountComboBox = new GridBagConstraints();
		gbc_personCountComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_personCountComboBox.gridx = 0;
		gbc_personCountComboBox.gridy = 0;
		panel_PersonCountDropDownMenu.add(personCountComboBox, gbc_personCountComboBox);
		personCountComboBox.setSelectedIndex(-1);
		personCountComboBox.setSelectedIndex(-1);

		JLabel lblPersonCount = new JLabel("Antal af personer");
		lblPersonCount.setBounds(50, 261, 200, 14);
		panel_1.add(lblPersonCount);

		/**
		 * Description
		 * */
		JLabel lblBDescription = new JLabel("Beskrivelse");
		lblBDescription.setBounds(359, 47, 200, 14);
		panel_1.add(lblBDescription);

		JScrollPane scrollPaneBeskrivelse = new JScrollPane();
	    scrollPaneBeskrivelse.setBounds(360, 63, 300, 235);
	    panel_1.add(scrollPaneBeskrivelse);

	    textAreaBeskrivelse = new JTextArea(); 
	    scrollPaneBeskrivelse.setViewportView(textAreaBeskrivelse);

	    /**
		 * Button: Næste
		 * */
		JButton btnNæste = new JButton("Næste");
		btnNæste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Calls the method addAndCreateAssignment()
				addAndCreateAssignment();
				// Creates a new instance of AssignmentPopUpGUI and makes it visible
				AssignmentPopUpGUI assignmentPopUpGUI = new AssignmentPopUpGUI(eventCtr);
				assignmentPopUpGUI.setVisible(true);
				// Disposes the current window
				dispose();
			}

		});
		btnNæste.setBounds(601, 519, 89, 23);
		panel_1.add(btnNæste);

		/**
		 * Button: Tilbage
		 * */
		JButton btnTilbage = new JButton("Tilbage");
		btnTilbage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateAssignmentPart1GUI createAssignmentPart1GUI = new CreateAssignmentPart1GUI(eventCtr);
				createAssignmentPart1GUI.setVisible(true);
				dispose();
			}
		});
		btnTilbage.setBounds(502, 519, 89, 23);
		panel_1.add(btnTilbage);

		/**
		 * Button: Afbryd
		 * */
		JButton btnAfbryd = new JButton("Afbryd");
		btnAfbryd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//creates a new mainGUI window and makes it visible
				MainGUI mainGUI = new MainGUI();
				mainGUI.setVisible(true);
				//Closes current window
				dispose();
			}
		});
		btnAfbryd.setBorder(null);
		btnAfbryd.setBackground(Color.WHITE);
		btnAfbryd.setBounds(403, 519, 89, 23);
		panel_1.add(btnAfbryd);
	}

	/**
	 * Use case 2 - 3 in interaction diagram
	 * 
	 * Method to add and create an assignment
	 * */
	private void addAndCreateAssignment() {
		// Gets the selected item in the personCountComboBox and convert it to a string (1, 2, 3 etc)
		String personCount = (String) personCountComboBox.getSelectedItem();
		// Gets the text from the textFields
		String assignmentType = textField_Assignment.getText();
		String assignmentDescription = textAreaBeskrivelse.getText();
		
		// Gets the start time of the assignment from the textField_StartDateTime 
		// and convert it to a LocalDateTime object
		LocalDateTime assignmentStartTime = LocalDateTime.parse(textField_StartDateTime.getText(),
				DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm"));
		
		// Gets the end time of the assignment from the textField_EndDateTime 
		//and convert it to a LocalDateTime object
		LocalDateTime assignmentEndTime = LocalDateTime.parse(textField_EndDateTime.getText(),
				DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm"));
		
		// Gets the event id from the eventCtr object
		int eventId = eventCtr.getEvent().getEventId();
		
		// Converts the personCount string to an integer
		int personCountInt = Integer.parseInt(personCount);
		
		// For each person, up to the number of people specified in personCount
		 for (int i = 0; i < personCountInt; i++){
			// If all necessary fields are filled out
			 if((assignmentType != null && assignmentDescription != null && 
					    assignmentStartTime != null && assignmentEndTime != null && eventId != 0 )){
				 // Add and create the assignment
				 eventCtr.addAndCreateAssignment(assignmentType, assignmentDescription, assignmentStartTime, assignmentEndTime, eventId);
		}  
			 else {
				 mainGUI.displayErrorMessage("problem with add and creating assignment");
			 }
		 }
	}

}
