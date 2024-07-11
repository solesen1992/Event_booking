package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllerLayer.EventCtr;

/**
 * AddAssignhmentPopUpGUI creates a pop-up GUI dialog box.
 * 
 * The dialog box asks the user if they want to add assignments to their newly created event.
 * It contains two buttons: Yes and no.
 * If the no button is clicked, the dialog box closes.
 * If the yes button is clicked, the event'
 * 
 * @author [Gruppe 3]
 * @version 1.0
 * */
public class AddAssignmentPopUpGUI extends JDialog {
	// Used for version control
	private static final long serialVersionUID = 1L;
	// Panel to hold the content
	private final JPanel contentPanel = new JPanel();
	private EventCtr eventCtr;
	 

	/**
	 * Create the dialog.
	 * Constructor
	 */
	public AddAssignmentPopUpGUI(EventCtr eventCtr) {
		//Sets the screen in the center
		setLocationRelativeTo(null);
		// Assigns the passed EventCtr object to the class variable
		this.eventCtr = eventCtr;
		//size of screen
		setBounds(100, 100, 450, 150);
		// Setting the layout manager for the content panel
		getContentPane().setLayout(new BorderLayout());
		FlowLayout fl_contentPanel = new FlowLayout();
		contentPanel.setLayout(fl_contentPanel);
		// Setting the border for the content panel
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		// Adding the content panel to the center of the content panel
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		/* Creates a JLabel and adding it to the content panel */
		JLabel lblNewLabel = new JLabel("Vil du tilføje vagter til dette event nu?");
		contentPanel.add(lblNewLabel);
		lblNewLabel.setBounds(30, 30, 200, 14);
		{
			/* Creates a JPanel for the buttons and setting its layout manager */
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				/* Creates a JButton for the "No" option */
				JButton btnNo = new JButton("   Nej   ");
				btnNo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Hides the dialog box when the box is clicked
						setVisible(false);
						MainGUI mainGUI = new MainGUI();
						mainGUI.setVisible(true);
					}
				});
				
				btnNo.setActionCommand("OK");
				// Adds the button to the button panel
				buttonPane.add(btnNo);
				// Sets the "No" button as the default button
				getRootPane().setDefaultButton(btnNo);
			}
			{
				//Creates a JButton for the "Yes" option
				JButton btnYes = new JButton("    Ja    ");
				//Adds an actionListener to the button
				btnYes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Takes the newly created event and adds event ID from the newly created event.
						// setEventID = synchronizes with the database and updates the event object
						// with the newest eventID.
						eventCtr.getEvent().setEventId(eventCtr.getEvent().getEventId());
						//Creates a new CreateAssignmentPart2GUI object and make it visible
						//We do this to pass the EventCtr object to CreateAssignmentPart2GUI's constructor
						CreateAssignmentPart2GUI createAssignmentPart2GUI =  new CreateAssignmentPart2GUI(eventCtr);
						createAssignmentPart2GUI.setVisible(true);
						System.out.println(eventCtr.getEvent().getEventId());
						// Closes the dialog window
						dispose();
					}
				});
				btnYes.setActionCommand("Cancel");
				//Adds the yes button to the button panel
				buttonPane.add(btnYes);
			}
		}
	}	

}
