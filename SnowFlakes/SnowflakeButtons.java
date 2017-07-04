package SnowFlakes;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * 
 * @author Tesfaye
 * @version 3
 */
public class SnowflakeButtons extends JPanel {
	/**
	 * the construct the snow flakes buttons with a layout
	 * @param c the controller
	 */
 public SnowflakeButtons(Controller c){
	 super(new GridLayout(1, 2));
	 JButton increase = new JButton("Increase");
	 JButton decrease = new JButton("Decrease");
	 increase.addActionListener(c);
	 decrease.addActionListener(c);
	 add(increase);
	 add(decrease);
 }
 
	
}
