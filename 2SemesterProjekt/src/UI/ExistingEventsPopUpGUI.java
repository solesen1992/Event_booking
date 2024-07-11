
/**
 * 
 */
package UI;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controllerLayer.EventCtr;
import model.Event;

/**
 * This class creates an pop-up which tells the user there's already existing events 
 * on the chosen date and time.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */

public class ExistingEventsPopUpGUI extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;


	/**
	 * Create the dialog. 
	 * @param eventsOnDate 
	 */
	public ExistingEventsPopUpGUI(EventCtr eventCtr, List<Event> eventsOnDate) {
		setLocationRelativeTo(null);
		CreateConcertGUI createConcertGUI = new CreateConcertGUI(eventCtr);
		setAlwaysOnTop(true);
		setBounds(100, 100, 650, 500);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 638, 432);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(30, 55, 580, 350);
			contentPanel.add(scrollPane);
			
			/**
			 * Table with empty rows
			 * Column names
			 * */
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Titel", "Type", "Lokation", "Start", "Slut"
				}
			));
			scrollPane.setViewportView(table);
		}
		
			populateTable(eventsOnDate);
		
		/**
		* Text
		* */
		JLabel lblNewLabel = new JLabel("Der er fundet events på den dato:");
		lblNewLabel.setBounds(30, 30, 200, 14);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 432, 638, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				
				/**
				 * Navigation button
				 * */
				JButton btnTilbage = new JButton("Tilbage");
				btnTilbage.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Closes this window
						dispose();
					}
				});
				btnTilbage.setActionCommand("OK");
				buttonPane.add(btnTilbage);
				getRootPane().setDefaultButton(btnTilbage);
			}
			{
				
				/**
				 * Navigation button
				 * */
				JButton btnBekræft = new JButton("Bekræft");
				btnBekræft.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Opens new window and closes this one
						createConcertGUI.setVisible(true);
						dispose();
					}
				});
				btnBekræft.setActionCommand("Cancel");
				buttonPane.add(btnBekræft);
			}
			
		}
	}
	
	/**
	 * Use case 1 - Create Event
	 * Uses 1.3 in interaction diagram
	 * 
	 * Populates the table with a list of existing events on the same date and time
	 * */
		private void populateTable(List<Event> eventsOnDate) {
			//Gets the default table that's been made
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			//Puts the information inside the table
			//Iterates through each event in the list
			for (Event event : eventsOnDate) {
				//Adds a row to the table for each event. Adds the event details.
				model.addRow(new Object[] {
					event.getEventTitle(), event.getEventType(), event.getLocationId(), event.getEventStartTime(), event.getEventEndTime()
				});
		
			}
		}
}

