
package Client;

import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.geom.Line2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import java.awt.Point;

/**
 * @author Yuzhe You (No.1159774)
 */

public class PatternDesign {
	private Shape drawTool;

	/**
	 * @param drawTool
	 */
	public void setDrawTool(Shape drawTool) {
		this.drawTool = drawTool;
	}


	public PatternDesign() {
		super();
	}

	public Shape getDrawTool() {
		return drawTool;
	}

	/**
	 * @param in
	 * @param out
	 */
	public void addCircle(Point in, Point out) {
		int absY = Math.abs(in.x - out.x);
		int absX = Math.abs(in.y - out.y);
		drawTool = new Ellipse2D.Double(Math.min(in.x, out.x), Math.min(in.y, out.y), Math.max(absY, absX), Math.max(absY, absX));
	}

	/**
	 * @param in
	 * @param out
	 */
	public void addOval(Point in, Point out) {
		drawTool = new Ellipse2D.Double(Math.min(in.x, out.x), Math.min(in.y, out.y), Math.abs(in.x - out.x), Math.abs(in.y - out.y));
	}

	/**
	 * @param in
	 * @param out
	 */
	public void addLine(Point in, Point out) {
		drawTool = new Line2D.Double(in.x, in.y, out.x, out.y);
	}

	/**
	 * @param in
	 * @param out
	 */
	public void addRectangle(Point in, Point out) {
		drawTool = new Rectangle2D.Double(Math.min(in.x, out.x), Math.min(in.y, out.y), Math.abs(in.x - out.x), Math.abs(in.y - out.y));
	}


	/**
	 * @param in
	 */
	public void addTxt(Point in) {
		drawTool = new RoundRectangle2D.Double(in.x - 5, in.y - 20, 125, 30, 10, 10);
	}

}
