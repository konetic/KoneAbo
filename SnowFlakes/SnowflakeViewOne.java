package SnowFlakes;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * 
 * @author Tesfaye
 * @version 3
 */
public class SnowflakeViewOne extends JPanel implements ModelListener {

	private SnowflakeModel model;

	public SnowflakeViewOne(SnowflakeModel m) {
		model = m;
	}

	/**
	 * 
	 * draw all the lines between those points and paint
	 * 
	 * @param g,
	 *            the graphics
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ArrayList<Point> snowpt = model.getPoints();// brings all the list

		for (int i = 0; i < snowpt.size(); i += 2) {
			Point start = snowpt.get(i);
			Point end = snowpt.get(i + 1);
			g.drawLine(start.x, start.y, end.x, end.y);
		}

	}

	@Override // repaint the screen
	public void update() {

		repaint();
	}

}
