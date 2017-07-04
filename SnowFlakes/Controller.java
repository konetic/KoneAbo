package SnowFlakes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.text.View;
/**
 * 
 * @author Tesfaye
 * @version 3
 */
public class Controller implements ActionListener {

	private SnowflakeModel theModel;
	public Controller(SnowflakeModel theModel) {

		this.theModel = theModel; //
	}

	@Override  
	/**
	 * get a command to the event
	 * @param e the event
	 */
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals("Increase")) {
			int level = theModel.getLevel();

			try {//testing the level if goes beyond the limit and show message

				theModel.setLevel(level + 1);

			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(null, "level can't be above ten");
			}

		}
		if (command.equals("Decrease")) {
			int level = theModel.getLevel();
			try {//testing the level for not below zero and hold exceptions

				theModel.setLevel(level - 1);

			} catch (IllegalArgumentException ex) {
				JOptionPane.showMessageDialog(null, "level can't be below zero");
			}

		}
	}

}
