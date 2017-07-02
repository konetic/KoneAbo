package SnowFlakes;

import java.awt.BorderLayout;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * 
 * @author 
 * @version 
 */
public class SnowflakeMain {

	public static final int HEIGHT = 400; 
	public static final int WIDTH = 600;

	/**
	 * Main method
	 * 
	 */
	public static void main(String[] args) {

		SnowflakeModel m = new SnowflakeModel();

		Controller c = new Controller(m);

		SnowflakeView1 v = new SnowflakeView1(m);
		m.setView(v);
		JFrame JF = new JFrame();// construct the frame
		JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit the program
		JF.add(v, BorderLayout.CENTER);
		SnowflakeButtons sb = new SnowflakeButtons(c);
		JF.add(sb, BorderLayout.SOUTH);

		JF.setSize(600, 400); // set the size of the window
		JF.setVisible(true); // set the component to be seen on the screen
	}

	/**
	 * prompts the user to enter the level of draws
	 * 
	 * @param console
	 *            the user input
	 * @return the next level
	 */
	private static int userInput(Scanner console) {
		System.out.println("Please enter the level of draw you want to see on the model");
		return console.nextInt();
	}

}
