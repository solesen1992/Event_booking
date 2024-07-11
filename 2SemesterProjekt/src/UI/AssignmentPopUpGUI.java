package UI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllerLayer.EventCtr;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * GUI dialog window.
 * The user just added one assignment to an event and the dialog window asks, if they want to add more.
 * 
 * It shows the user a yes and no button.
 * If the user says no, the user gets send to the AssignmentConfirmationGUI
 * It the user says yes, the user gets send to the CreateAssignmentPart2GUI to add more assignments.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 * */

public class AssignmentPopUpGUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private EventCtr eventCtr;
 

	/**
	 * Create the dialog.
	 */
	public AssignmentPopUpGUI(EventCtr eventCtr) {
		setLocationRelativeTo(null);
		this.eventCtr = eventCtr;
		setBounds(100, 100, 450, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		/**
		 * Text
		 * */
		JLabel lblNewLabel = new JLabel("Vil du tilføje flere vagter?");
		contentPanel.add(lblNewLabel);
		lblNewLabel.setBounds(30, 30, 200, 14);
		{
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
				/**
				 * Navigation menu: Nej
				 * */
				JButton btnNo = new JButton("   Nej   ");
				// Adds an action listener to the 'No' button to handle click events
				btnNo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Create a new AssignmentConfirmationGUI object and make it visible
						// We do this to pass the EventCtr object to the constructor in the next window
						AssignmentConfirmationGUI assignmentConfirmationGUI = new AssignmentConfirmationGUI(eventCtr);
						assignmentConfirmationGUI.setVisible(true);
						// Close the current GUI
						dispose();
					}
					
				});
				btnNo.setActionCommand("OK");
				buttonPane.add(btnNo);
				getRootPane().setDefaultButton(btnNo);
			}
			{
				
				/**
				 * Navigation menu: Ja
				 * */
				JButton btnYes = new JButton("    Ja    ");
				// Add an action listener to the 'Yes' button to handle click events
				btnYes.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// Create a new CreateAssignmentPart2GUI object and make it visible
						// We do this to pass the EventCtr object to the constructor in the next window
						CreateAssignmentPart2GUI createAssignmentPart2GUI = new CreateAssignmentPart2GUI(eventCtr);
						createAssignmentPart2GUI.setVisible(true);
						dispose();
					}
				});
				btnYes.setActionCommand("Cancel");
				buttonPane.add(btnYes);
			}
		}
		
	}

}
