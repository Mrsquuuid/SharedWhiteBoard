
package Client;


import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.server.UnicastRemoteObject;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import Remote.RMIDataInterface;
import Remote.RMIServerInterface;

/**
 * @author Yuzhe You (No.1159774)
 */


public class Canvas extends JComponent {
	private String userName;
	private boolean isAdmin;
	private BufferedImage bufferedImage;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}

	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}

	public void setColorInUse(Color colorInUse) {
		this.colorInUse = colorInUse;
	}

	public void setSchemaInUse(String schemaInUse) {
		this.schemaInUse = schemaInUse;
	}

	public Point getPrevPoint() {
		return prevPoint;
	}

	public void setPrevPoint(Point prevPoint) {
		this.prevPoint = prevPoint;
	}

	public Point getCurrentPoint() {
		return currentPoint;
	}

	public void setCurrentPoint(Point currentPoint) {
		this.currentPoint = currentPoint;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public RMIServerInterface getServer() {
		return server;
	}

	public void setServer(RMIServerInterface server) {
		this.server = server;
	}

	public BufferedImage getBufferedImage1() {
		return bufferedImage1;
	}

	public void setBufferedImage1(BufferedImage bufferedImage1) {
		this.bufferedImage1 = bufferedImage1;
	}

	public Graphics2D getGraphics2D() {
		return graphics2D;
	}

	public void setGraphics2D(Graphics2D graphics2D) {
		this.graphics2D = graphics2D;
	}

	public Color colorInUse;
	private String schemaInUse;

	private Point prevPoint, currentPoint;

	private String text;
	private RMIServerInterface server;

	private BufferedImage bufferedImage1;
	public Graphics2D graphics2D;

	public void point() {
		schemaInUse = "point";
	}

	public void line() {
		schemaInUse = "line";
	}

	public void rect() {
		schemaInUse = "rect";
	}

	public void circle() {
		schemaInUse = "circle";
	}

	public void oval() {
		schemaInUse = "oval";
	}

	public void text() {
		schemaInUse = "text";
	}

	public void eraser() {
		schemaInUse = "eraser";
	}
 
	public Canvas(String s, RMIServerInterface rmiServer, boolean isAdmin) {
		this.isAdmin = isAdmin;
		this.colorInUse = Color.black;
		this.schemaInUse = "point";
		this.text = "";
		this.userName = s;
	    this.server = rmiServer;

		addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent event) {
				currentPoint = event.getPoint();
				PatternDesign design = new PatternDesign();

				if (graphics2D != null) {
					switch (schemaInUse) {
						case "point", "line" -> design.addLine(prevPoint, currentPoint);
						case "eraser" -> {
							graphics2D.setPaint(colorInUse);
							graphics2D.setStroke(new BasicStroke(1.0f));
						}
						case "rect" -> design.addRectangle(prevPoint, currentPoint);
						case "circle" -> design.addCircle(prevPoint, currentPoint);
						case "oval" -> design.addOval(prevPoint, currentPoint);
						case "text" -> {
							text = JOptionPane.showInputDialog("Input text:");
							if (text != null) {
							} else text = "";
							eliminate();
							paintPre();
							graphics2D.setFont(new Font("American Typewriter", Font.PLAIN, 14));
							graphics2D.drawString(text, currentPoint.x, currentPoint.y);
							graphics2D.setStroke(new BasicStroke(1.0f));
						}
						default -> throw new IllegalStateException("Unexpected value: " + schemaInUse);
					}
					if (!schemaInUse.equals("text") && !schemaInUse.equals("eraser")) graphics2D.draw(design.getDrawTool());
					repaint();
					prevPoint = currentPoint;
					try {
						server.upload(new PaintingStatus("end", userName, schemaInUse, colorInUse, currentPoint, text));
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
		});

		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				prevPoint = event.getPoint();
				restore();

				try {
					PaintingStatus item = new PaintingStatus("start", userName, schemaInUse, colorInUse, prevPoint, text);
					server.upload(item);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});

	    addMouseMotionListener(new MouseMotionAdapter() {
	    	public void mouseDragged(MouseEvent event) {
		        currentPoint = event.getPoint();
		        PatternDesign patternDesign = new PatternDesign();

		        if (graphics2D != null) {
					switch (schemaInUse) {
						case "point" -> {
							patternDesign.addLine(prevPoint, currentPoint);
							prevPoint = currentPoint;
							try {
								PaintingStatus status;
								status = new PaintingStatus("drawing", userName, schemaInUse, colorInUse, currentPoint, "");
								server.upload(status);
							} catch (RemoteException e1) {
								e1.printStackTrace();
							}
						}
						case "eraser" -> {
							patternDesign.addLine(prevPoint, currentPoint);
							prevPoint = currentPoint;
							graphics2D.setPaint(Color.white);
							graphics2D.setStroke(new BasicStroke(15.0f));
							try {
								PaintingStatus status = new PaintingStatus("drawing", userName, schemaInUse, Color.white, currentPoint, "");
								server.upload(status);
							} catch (RemoteException e1) {
								e1.printStackTrace();
							}
						}
						case "line" -> {
							eliminate();
							paintPre();
							patternDesign.addLine(prevPoint, currentPoint);
						}
						case "rect" -> {
							eliminate();
							paintPre();
							patternDesign.addRectangle(prevPoint, currentPoint);
						}
						case "circle" -> {
							eliminate();
							paintPre();
							patternDesign.addCircle(prevPoint, currentPoint);
						}
						case "oval" -> {
							eliminate();
							paintPre();
							patternDesign.addOval(prevPoint, currentPoint);
						}
						case "text" -> {
							eliminate();
							paintPre();
							graphics2D.setFont(new Font("TimesRoman", Font.PLAIN, 20));
							graphics2D.drawString("Enter text here", currentPoint.x, currentPoint.y);
							patternDesign.addTxt(currentPoint);
							Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{3}, 0);
							graphics2D.setStroke(dashed);
						}
						default -> throw new IllegalStateException("Unexpected value: " + schemaInUse);
					}
		        	graphics2D.draw(patternDesign.getDrawTool());
					repaint();
		        }
	    	}
	    });

	    setDoubleBuffered(false);

	}

	protected void paintComponent(Graphics graphics) {
		if (bufferedImage == null) {
			if (!isAdmin) {
				try {
					byte[] rawImage = server.forwardRealTimePic();
					bufferedImage = ImageIO.read(new ByteArrayInputStream(rawImage));
					graphics2D = (Graphics2D) bufferedImage.getGraphics();
				    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				    graphics2D.setPaint(colorInUse);
				} catch (IOException e) {
					System.err.println("Unable to get image of paintBoard!");
				}
			} else {
			    bufferedImage = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
			    graphics2D = (Graphics2D) bufferedImage.getGraphics();
			    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			    eliminate();
			}
		}
	    graphics.drawImage(bufferedImage, 0, 0, null);
	}

	public void restore() {
		ColorModel model = bufferedImage.getColorModel();
		boolean flag = model.isAlphaPremultiplied();
		WritableRaster copyData = bufferedImage.copyData(null);
		bufferedImage1 = new BufferedImage(model, copyData, flag, null);
	}

	public Color getColorInUse() {
		return colorInUse;
	}
	
	public String getSchemaInUse() {
		return schemaInUse;
	}
	
	public Graphics2D getPic2D() {
		return graphics2D;
	}
	
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	
	public void eliminate() {
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(colorInUse);
		repaint();
	}

	public void paintPre() {
		paint(bufferedImage1);
	}
	
	public void paint(BufferedImage img) {
		graphics2D.drawImage(img, null, 0, 0);
		repaint();
	}

	public void forwardClearBoard() throws RemoteException {
		server.forwardMsg("new");
	}
}

class PaintingStatus extends UnicastRemoteObject implements RMIDataInterface {
	private String paintStatus;
	private String userName;
	private Point point;
	private String text;
	private String schema;
	private Color color;

	public void setPaintStatus(String paintStatus) {
		this.paintStatus = paintStatus;
	}

	public String getSchema() {
		return this.schema;
	}

	public Color color() {
		return this.color;
	}

	public String getPaintStatus() {
		return this.paintStatus;
	}

	public String getUserName() {
		return this.userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Point point() {
		return this.point;
	}

	public String text() {
		return this.text;
	}

	public PaintingStatus(String paintStatus, String userName, String schema, Color color, Point point, String text) throws RemoteException {
		this.paintStatus = paintStatus;
		this.userName = userName;
		this.schema = schema;
		this.color = color;
		this.point = point;
		this.text = text;
	}

}
