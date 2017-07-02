package SnowFlakes;

import java.awt.Point;
import java.util.ArrayList;

/**
 * 
 * @author 
 * @version 
 */
public class SnowflakeModel {
	// instance variables
	private int level;
	private Point start;
	private Point end;
	private ModelListener view;
	private ArrayList<Point> snowPoint;

	/**
	 * constructor of the model of class SnowflakeModel
	 */
	public SnowflakeModel() {
		level = 0;
		start = new Point(100, 300);
		end = new Point(500, 300);
		snowPoint = new ArrayList<>();
		snowPoint.add(start);
		snowPoint.add(end);
		view = null;
	}

	/**
	 * returns the level of this dimension-the method getLevel
	 * 
	 */

	public int getLevel() {
		return level;
	}

	/**
	 * 
	 * returns the level of this dimension-the method getStart
	 */
	public Point getStart() {
		return start;
	}

	/**
	 * 
	 * returns the level of this dimension-the method getEnd
	 */
	public Point getEnd() {
		return end;
	}

	/**
	 * 
	 * returns the points start and end
	 */
	public ArrayList<Point> getPoints() {
		return snowPoint;
	}

	/**
	 * set the level
	 * 
	 * @param l
	 *            setting initial value of the level
	 */
	public void setLevel(int l) {

		if (l < 0) {
			throw new IllegalArgumentException();
		}
		level = l;
		createSnowPoints();
		if (view != null) {
			view.update();
		}
	}

	/**
	 * a method sets the starting point
	 * 
	 * @param s
	 *            starting point
	 */
	public void setStart(Point s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		start = s;
	}

	/**
	 * a method sets the end point object
	 * 
	 * @param e
	 *            ending point of the object
	 */
	public void setEnd(Point e) {
		if (e == null) {
			throw new IllegalArgumentException();
		}
		end = e;
	}

	/**
	 * 
	 * kick-off method clearing the old one and display the current input
	 */
	private void createSnowPoints() {

		snowPoint.clear();
		createSnowPoints(start, end, level);
	}

	/**
	 * recursively create/constructs a dimension with all the points
	 * 
	 * @param s
	 *            start point
	 * @param e
	 *            end point
	 * @param l
	 *            level
	 */
	private void createSnowPoints(Point s, Point e, int l) {

		// base case
		if (l == 0) {
			snowPoint.add(s);
			snowPoint.add(e);
			return;
		}

		int distancex = (e.x - s.x) / 3;
		int distancey = (e.y - s.y) / 3;
		Point point1 = new Point((s.x + distancex), (s.y + distancey));
		Point point2 = new Point((e.x - distancex), (e.y - distancey));

		// recursive case
		double peakX = point1.x + (point2.x - point1.x) * Math.cos(Math.PI / 3.0)
				- (point1.y - point2.y) * Math.sin(Math.PI / 3.0);
		double peakY = point1.y - (point2.x - point1.x) * Math.sin(Math.PI / 3.0)
				- (point1.y - point2.y) * Math.cos(Math.PI / 3.0);
		Point peak = new Point((int) peakX, (int) peakY);

		// recursive call
		createSnowPoints(s, point1, l - 1);
		createSnowPoints(point1, peak, l - 1);
		createSnowPoints(peak, point2, l - 1);
		createSnowPoints(point2, e, l - 1);
	}
/**
 * set the view to listen to the Model
 * @param view the view
 */
	public void setView(ModelListener view) {
		this.view = view;
	}

}
