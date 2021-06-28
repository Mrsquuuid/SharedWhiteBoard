
package Client;
 
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.CENTER;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicReference;

import Remote.RMIServerInterface;
import Remote.RMIClientInterface;
import Remote.RMIDataInterface;
import org.json.simple.JSONObject;

/**
 * @author Yuzhe You (No.1159774)
 */

public class DrawClient extends UnicastRemoteObject implements RMIClientInterface, Remote {

	public DrawClient(Client msgClient) throws RemoteException {
		super();
		this.msgClient = msgClient;
		onlineClient = new DefaultListModel<>();
		isAdmin = false;
		kickedOut = false;
	}

	public JTextArea chatBox;
	private Client msgClient;
	static RMIServerInterface server;
	private boolean isAdmin;
	private boolean kickedOut;
	public static JFrame window;
	private DefaultListModel<String> onlineClient;

	private JButton clearButton;
	private JButton restoreButton;
	private JButton openButton;
	private JButton blackButton;
	private JButton blueButton;
	private JButton greenButton;
	private JButton redButton;
	private JButton orangeButton;
	private JButton yellowButton;
	private JButton cyanButton;
	private JButton pencilButton;
	private JButton lineButton;
	private JButton rectangleButton;
	private Hashtable<String, Point> prevPosition = new Hashtable<>();
	private JScrollPane jsp;
	private JList<String> onlineRecord;
	private JScrollPane onlineUser;
	private JButton circleButton;
	private JButton ovalButton;
	private JButton wordButton;
	private JButton rubberButton;
	private JButton sendMsgBtn;
	private JButton kickOutButton;
	private Canvas canvas;
	private Color colorOwn;
	private String schemaOwn;
	private String clientName;
	public JTextField wordBox;

	public JTextArea getChatBox() {
		return chatBox;
	}

	public void setChatBox(JTextArea chatBox) {
		this.chatBox = chatBox;
	}



	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}

	public void setKickedOut(boolean kickedOut) {
		this.kickedOut = kickedOut;
	}

	public static JFrame getWindow() {
		return window;
	}

	public static void setWindow(JFrame window) {
		DrawClient.window = window;
	}

	public DefaultListModel<String> getOnlineClient() {
		return onlineClient;
	}

	public void setOnlineClient(DefaultListModel<String> onlineClient) {
		this.onlineClient = onlineClient;
	}

	public JButton getClearButton() {
		return clearButton;
	}

	public void setClearButton(JButton clearButton) {
		this.clearButton = clearButton;
	}

	public JButton getRestoreButton() {
		return restoreButton;
	}

	public void setRestoreButton(JButton restoreButton) {
		this.restoreButton = restoreButton;
	}

	public JButton getOpenButton() {
		return openButton;
	}

	public void setOpenButton(JButton openButton) {
		this.openButton = openButton;
	}

	public JButton getBlackButton() {
		return blackButton;
	}

	public void setBlackButton(JButton blackButton) {
		this.blackButton = blackButton;
	}

	public JButton getBlueButton() {
		return blueButton;
	}

	public void setBlueButton(JButton blueButton) {
		this.blueButton = blueButton;
	}

	public JButton getGreenButton() {
		return greenButton;
	}

	public void setGreenButton(JButton greenButton) {
		this.greenButton = greenButton;
	}

	public JButton getRedButton() {
		return redButton;
	}

	public void setRedButton(JButton redButton) {
		this.redButton = redButton;
	}

	public JButton getOrangeButton() {
		return orangeButton;
	}

	public void setOrangeButton(JButton orangeButton) {
		this.orangeButton = orangeButton;
	}

	public JButton getYellowButton() {
		return yellowButton;
	}

	public void setYellowButton(JButton yellowButton) {
		this.yellowButton = yellowButton;
	}

	public JButton getCyanButton() {
		return cyanButton;
	}

	public void setCyanButton(JButton cyanButton) {
		this.cyanButton = cyanButton;
	}

	public JButton getPencilButton() {
		return pencilButton;
	}

	public void setPencilButton(JButton pencilButton) {
		this.pencilButton = pencilButton;
	}

	public JButton getLineButton() {
		return lineButton;
	}

	public void setLineButton(JButton lineButton) {
		this.lineButton = lineButton;
	}

	public JButton getRectangleButton() {
		return rectangleButton;
	}

	public void setRectangleButton(JButton rectangleButton) {
		this.rectangleButton = rectangleButton;
	}

	public JButton getCircleButton() {
		return circleButton;
	}

	public void setCircleButton(JButton circleButton) {
		this.circleButton = circleButton;
	}

	public JButton getOvalButton() {
		return ovalButton;
	}

	public void setOvalButton(JButton ovalButton) {
		this.ovalButton = ovalButton;
	}

	public JButton getWordButton() {
		return wordButton;
	}

	public void setWordButton(JButton wordButton) {
		this.wordButton = wordButton;
	}

	public JButton getRubberButton() {
		return rubberButton;
	}

	public void setRubberButton(JButton rubberButton) {
		this.rubberButton = rubberButton;
	}

	public JButton getSendMsgBtn() {
		return sendMsgBtn;
	}

	public void setSendMsgBtn(JButton sendMsgBtn) {
		this.sendMsgBtn = sendMsgBtn;
	}

	public Color getColorOwn() {
		return colorOwn;
	}

	public void setColorOwn(Color colorOwn) {
		this.colorOwn = colorOwn;
	}

	public String getSchemaOwn() {
		return schemaOwn;
	}

	public void setSchemaOwn(String schemaOwn) {
		this.schemaOwn = schemaOwn;
	}


	public JTextField getWordBox() {
		return wordBox;
	}

	public void setWordBox(JTextField wordBox) {
		this.wordBox = wordBox;
	}

	public Hashtable<String, Point> getPrevPosition() {
		return prevPosition;
	}

	public void setPrevPosition(Hashtable<String, Point> prevPosition) {
		this.prevPosition = prevPosition;
	}

	public JScrollPane getJsp() {
		return jsp;
	}

	public void setJsp(JScrollPane jsp) {
		this.jsp = jsp;
	}

	public ActionListener getActionListener() {
		return actionListener;
	}

	public void setActionListener(ActionListener actionListener) {
		this.actionListener = actionListener;
	}


	public void addUser(String name) {
		this.onlineClient.addElement(name);
	}

	public void kickOut(String name) {
		this.onlineClient.removeElement(name);
	}

	public double response() {
		return System.currentTimeMillis();
	}

	public void assignManager() {
		this.isAdmin = true;
	}

	public boolean isAdmin() {
		return this.isAdmin;
	}

	public boolean isKickedOut() {
		return this.kickedOut;
	}

	public void clearBoard() {
		if (!this.isAdmin)
			this.canvas.eliminate();
	}

	public void forwardMsg(String message) {
		System.out.println("Remote: " + message);
	}

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String name) {
		this.clientName = name;
	}

	public void modifySymbol(String name) {
		this.clientName = name;
		JOptionPane.showMessageDialog(this.window, "Duplicate username: " + name, "Warning", JOptionPane.WARNING_MESSAGE);
	}


	public void notifyKicked() {
		kickedOut = true;
		onlineClient.removeAllElements();
		window.setTitle("You are kicked out from the room!");
		new Thread(() -> JOptionPane.showMessageDialog(window, "You are kicked out from the room!",
				"Warning", JOptionPane.WARNING_MESSAGE)).start();

	}

	public boolean synchronize(RMIDataInterface data) throws RemoteException {

		if (data.getUserName().equals(clientName)) {
			return true;
		}
		PatternDesign patternDesign = new PatternDesign();
		if (data.getPaintStatus().equals("start")) {
			prevPosition.put(data.getUserName(), data.point());
			return true;
		}

		colorOwn = canvas.getColorInUse();
		Point prePoint = (Point) prevPosition.get(data.getUserName());
		canvas.getPic2D().setPaint(data.color());

		if (data.getPaintStatus().equals("drawing")) {
			if (data.getSchema().equals("eraser")) {
				canvas.getPic2D().setStroke(new BasicStroke(15.0f));
			}
			patternDesign.addLine(prePoint, data.point());
			canvas.getPic2D().draw(patternDesign.getDrawTool());
			canvas.repaint();
			prevPosition.put(data.getUserName(), data.point());
			canvas.getPic2D().setPaint(colorOwn);
			return true;
		}

		if (data.getPaintStatus().equals("end")) {
			switch (data.getSchema()) {
				case "point" -> patternDesign.addLine(prePoint, data.point());
				case "line" -> patternDesign.addLine(prePoint, data.point());
				case "eraser" -> canvas.getPic2D().setStroke(new BasicStroke(1.0f));
				case "rect" -> patternDesign.addRectangle(prePoint, data.point());
				case "circle" -> patternDesign.addCircle(prePoint, data.point());
				case "oval" -> patternDesign.addOval(prePoint, data.point());
				case "text" -> {
					canvas.getPic2D().setFont(new Font("TimesRoman", Font.PLAIN, 20));
					canvas.getPic2D().drawString(data.text(), data.point().x, data.point().y);
				}
				default -> throw new IllegalStateException("Unexpected value: " + data.getSchema());
			}
			if (!data.getSchema().equals("text") && data.getSchema().equals("eraser"))
				canvas.getPic2D().draw(patternDesign.getDrawTool());
			canvas.repaint();
			prevPosition.remove(data.getUserName());
			canvas.getPic2D().setPaint(colorOwn);
			return true;
		}
		return false;
	}

	public void shutDownAllClients() {
		kickedOut = true;
		window.setTitle("The manager has quit");
		Thread t = new Thread(() -> {
			JOptionPane.showMessageDialog(window, "The manager has quit." + "\n" +
							"Your application will be closed.",
					"Error", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		});
		t.start();
	}

	public byte[] forwardPic() throws IOException {
		ByteArrayOutputStream stream;
		stream = new ByteArrayOutputStream();
		ImageIO.write(this.canvas.getBufferedImage(), "png", stream);
		return stream.toByteArray();
	}


	private void restore() throws IOException {
		FileDialog fileDialog = new FileDialog(window, "save image", FileDialog.SAVE);
		fileDialog.setVisible(true);
		if (fileDialog.getFile() != null) {
			String filePath = fileDialog.getDirectory();
			String fileName = fileDialog.getFile();
			ImageIO.write(canvas.getBufferedImage(), "png", new File(filePath + fileName + ".png"));
		}
	}

	private void load() throws IOException {
		FileDialog loadAnImage = new FileDialog(window, "load an image", FileDialog.LOAD);
		loadAnImage.setVisible(true);
		if (loadAnImage.getFile() == null) {
			return;
		}
		BufferedImage read = ImageIO.read(new File(loadAnImage.getDirectory() + loadAnImage.getFile()));
		canvas.paint(read);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(read, "png", outputStream);
		server.forwardPicOpened(outputStream.toByteArray());
	}


	public void drawOpenedImage(byte[] rawImage) throws IOException {
		AtomicReference<BufferedImage> imageAtomicReference = new AtomicReference<>(ImageIO.read(new ByteArrayInputStream(rawImage)));
		this.canvas.paint(imageAtomicReference.get());
	}

	public void extra() {

	}

	ActionListener actionListener = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
			LineBorder border = new LineBorder(new Color(240, 240, 240), 2);
			LineBorder lineBorder = new LineBorder(colorOwn, 2);
			if (actionEvent.getSource() == restoreButton) {
				try {
					restore();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (actionEvent.getSource() == openButton) {
				try {
					load();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (actionEvent.getSource() == clearButton) {
				canvas.eliminate();
				if (isAdmin) {
					try {
						canvas.forwardClearBoard();
					} catch (RemoteException remoteException) {
						remoteException.printStackTrace();
					}
				}
			}
			colorOwn = canvas.getColorInUse();
			schemaOwn = canvas.getSchemaInUse();

			if (actionEvent.getSource().equals(blackButton)) {
				canvas.colorInUse = Color.black;
				canvas.graphics2D.setPaint(canvas.colorInUse);
			} else if (actionEvent.getSource().equals(blueButton)) {
				canvas.colorInUse = Color.blue;
				canvas.graphics2D.setPaint(canvas.colorInUse);
			} else if (actionEvent.getSource().equals(orangeButton)) {
				canvas.colorInUse = Color.orange;
				canvas.graphics2D.setPaint(canvas.colorInUse);
			} else if (actionEvent.getSource().equals(yellowButton)) {
				canvas.colorInUse = Color.yellow;
				canvas.graphics2D.setPaint(canvas.colorInUse);
			} else if (actionEvent.getSource() == cyanButton) {
				canvas.colorInUse = Color.cyan;
				canvas.graphics2D.setPaint(canvas.colorInUse);
			} else if (actionEvent.getSource().equals(greenButton)) {
				canvas.colorInUse = Color.green;
				canvas.graphics2D.setPaint(canvas.colorInUse);
			} else if (actionEvent.getSource().equals(redButton)) {
				canvas.colorInUse = Color.red;
				canvas.graphics2D.setPaint(canvas.colorInUse);
			}
			if (actionEvent.getSource() == pencilButton || actionEvent.getSource() == lineButton || actionEvent.getSource() == rectangleButton || actionEvent.getSource() == circleButton || actionEvent.getSource() == ovalButton || actionEvent.getSource() == rubberButton || actionEvent.getSource() == wordButton) {
				pencilButton.setBorder(border);
				lineButton.setBorder(border);
				rectangleButton.setBorder(border);
				circleButton.setBorder(border);
				ovalButton.setBorder(border);
				wordButton.setBorder(border);
				rubberButton.setBorder(border);
				if (actionEvent.getSource().equals(pencilButton)) {
					canvas.point();
					pencilButton.setBorder(lineBorder);
				} else if (actionEvent.getSource().equals(lineButton)) {
					canvas.line();
					lineButton.setBorder(lineBorder);
				} else if (actionEvent.getSource().equals(rectangleButton)) {
					canvas.rect();
					rectangleButton.setBorder(lineBorder);
				} else if (actionEvent.getSource().equals(circleButton)) {
					canvas.circle();
					circleButton.setBorder(lineBorder);
				} else if (actionEvent.getSource().equals(ovalButton)) {
					canvas.oval();
					ovalButton.setBorder(lineBorder);
				} else if (actionEvent.getSource().equals(wordButton)) {
					canvas.text();
					wordButton.setBorder(lineBorder);
				} else if (actionEvent.getSource().equals(rubberButton)) {
					canvas.eraser();
					rubberButton.setBorder(lineBorder);
				}
			}

			if (actionEvent.getSource() == sendMsgBtn) {
				JSONObject msgBody = new JSONObject();
				msgBody.put("category", "groupChat");
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
				Date date = new Date(System.currentTimeMillis());

				msgBody.put("currentTime", formatter.format(date));
				msgBody.put("content", wordBox.getText());
				msgBody.put("username", clientName);
				System.out.println(wordBox.getText());
				try {
					wordBox.setText("");
					msgClient.sendToServer(msgBody);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}

			if (actionEvent.getSource() == blackButton || actionEvent.getSource() == blueButton || actionEvent.getSource() == greenButton || actionEvent.getSource() == redButton
					|| actionEvent.getSource() == orangeButton || actionEvent.getSource() == yellowButton || actionEvent.getSource() == cyanButton) {
				LineBorder lb = new LineBorder(colorOwn, 2);
				if ("point".equals(schemaOwn)) {
					pencilButton.setBorder(lb);
				} else if ("line".equals(schemaOwn)) {
					lineButton.setBorder(lb);
				} else if ("rect".equals(schemaOwn)) {
					rectangleButton.setBorder(lb);
				} else if ("circle".equals(schemaOwn)) {
					circleButton.setBorder(lb);
				} else if ("oval".equals(schemaOwn)) {
					ovalButton.setBorder(lb);
				} else if ("text".equals(schemaOwn)) {
					wordButton.setBorder(lb);
				} else if ("eraser".equals(schemaOwn)) {
					rubberButton.setBorder(lb);
				}
			}
		}
	};


	public void drawBoard(RMIServerInterface server) {
		//build the GUI 
		window = new JFrame("WhiteBoard of "+ clientName);
	    Container pane = window.getContentPane();
	    
	    canvas = new Canvas(clientName, server, isAdmin);
	    
	    blackButton = new JButton("Black");

	    blackButton.setMaximumSize(new Dimension(20, 20));
		blackButton.setForeground(Color.BLACK);
		blueButton = new JButton("Blue");
		blueButton.setForeground(Color.BLUE);

	    greenButton = new JButton("Green");
		greenButton.setForeground(Color.GREEN);

	    redButton = new JButton("Red");
		redButton.setForeground(Color.RED);

	    orangeButton = new JButton("Orange");
	   	orangeButton.setForeground(Color.ORANGE);

	    yellowButton = new JButton("Yellow");
		yellowButton.setForeground(Color.YELLOW);

	    cyanButton = new JButton("Cyan");
		cyanButton.setForeground(Color.CYAN);

		blackButton.addActionListener(actionListener);
		blueButton.addActionListener(actionListener);
		greenButton.addActionListener(actionListener);
		redButton.addActionListener(actionListener);
		orangeButton.addActionListener(actionListener);
		yellowButton.addActionListener(actionListener);
		cyanButton.addActionListener(actionListener);

		wordBox = new JTextField();
		//wordBox.setBounds(295, 283, 200, 26);
		window.getContentPane().add(wordBox);
		wordBox.setColumns(10);

		chatBox= new JTextArea();
		jsp = new JScrollPane(chatBox);
		jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		//frame.getContentPane().add(jsp);
	    
	    LineBorder lb = new LineBorder(Color.black, 2);
	    Icon toolPic = new ImageIcon("src/Client/ToolPic/pen.png");
	    pencilButton = new JButton(toolPic);
	    pencilButton.setToolTipText("Free draw");
	    pencilButton.setBorder(lb);

	    lb = new LineBorder(new Color(238,238,238), 2);
	    toolPic = new ImageIcon("src/Client/ToolPic/line.png");
	    lineButton = new JButton(toolPic);
	    lineButton.setBorder(lb);

	    toolPic = new ImageIcon("src/Client/ToolPic/rectangle.png");
	    rectangleButton = new JButton(toolPic);
	    rectangleButton.setBorder(lb);

	    toolPic = new ImageIcon("src/Client/ToolPic/circle.png");
	    circleButton = new JButton(toolPic);
	    circleButton.setBorder(lb);

	    toolPic = new ImageIcon("src/Client/ToolPic/oval.png");
	    ovalButton = new JButton(toolPic);
	    ovalButton.setBorder(lb);

	    toolPic = new ImageIcon("src/Client/ToolPic/text.png");
	    wordButton = new JButton(toolPic);
	    wordButton.setBorder(lb);

	    toolPic = new ImageIcon("src/Client/ToolPic/eraser.png");
	    rubberButton = new JButton(toolPic);
	    rubberButton.setBorder(lb);

	    
	    clearButton = new JButton("Clear Board");
	    restoreButton = new JButton("Save Image");
	    openButton = new JButton("Load Picture");
	    kickOutButton = new JButton("Remove User");
		openButton = new JButton("Open Image");
		sendMsgBtn = new JButton("send message");
		sendMsgBtn.setBorder(lb);

		pencilButton.addActionListener(actionListener);
		lineButton.addActionListener(actionListener);
		rectangleButton.addActionListener(actionListener);
		circleButton.addActionListener(actionListener);
		ovalButton.addActionListener(actionListener);
		wordButton.addActionListener(actionListener);
		rubberButton.addActionListener(actionListener);
		clearButton.addActionListener(actionListener);
		restoreButton.addActionListener(actionListener);
		openButton.addActionListener(actionListener);
		kickOutButton.addActionListener(actionListener);
		openButton.addActionListener(actionListener);
		sendMsgBtn.addActionListener(actionListener);


		GroupLayout setup = new GroupLayout(pane);
		pane.setLayout(setup);
		setup.setAutoCreateContainerGaps(true);
		setup.setAutoCreateGaps(true);
		setup.setHorizontalGroup(setup.createSequentialGroup()
				.addGroup(setup.createParallelGroup(CENTER)
						.addComponent(pencilButton)
						.addComponent(lineButton)
						.addComponent(rectangleButton)
						.addComponent(circleButton)
						.addComponent(ovalButton)
						.addComponent(wordButton)
						.addComponent(rubberButton)
				)
				.addGroup(setup.createParallelGroup(CENTER)
						.addComponent(canvas)
						.addGroup(setup.createSequentialGroup()
								.addComponent(blackButton)
								.addComponent(redButton)
								.addComponent(orangeButton)
								.addComponent(yellowButton)
								.addComponent(greenButton)
								.addComponent(cyanButton)
								.addComponent(blueButton)
						).addGroup(setup.createParallelGroup()
								.addComponent(jsp)

						)
						.addGroup(setup.createSequentialGroup()
								.addComponent(wordBox)
								.addComponent(sendMsgBtn)


						)

				)
				.addGroup(setup.createParallelGroup(CENTER)
						.addComponent(onlineUser)
						.addComponent(kickOutButton)
						.addComponent(clearButton)
						.addComponent(openButton)
						.addComponent(restoreButton)


				)
		);

		setup.setVerticalGroup(setup.createSequentialGroup()
				.addGroup(setup.createParallelGroup(BASELINE)
						.addGroup(setup.createSequentialGroup()
								.addComponent(pencilButton)
								.addComponent(lineButton)
								.addComponent(rectangleButton)
								.addComponent(circleButton)
								.addComponent(ovalButton)
								.addComponent(wordButton)
								.addComponent(rubberButton)
						)
						.addComponent(canvas)
						.addGroup(setup.createSequentialGroup()
								.addComponent(onlineUser)
								.addComponent(kickOutButton)
								.addComponent(clearButton)
								.addComponent(openButton)
								.addComponent(restoreButton)

						)
				)
				.addGroup(setup.createParallelGroup(BASELINE)
						.addComponent(blackButton)
						.addComponent(redButton)
						.addComponent(orangeButton)
						.addComponent(yellowButton)
						.addComponent(greenButton)
						.addComponent(cyanButton)
						.addComponent(blueButton)
				)
				.addGroup(setup.createParallelGroup(BASELINE)
						.addComponent(jsp)
				)
				.addGroup(setup.createParallelGroup(BASELINE)
						.addComponent(wordBox)
						.addComponent(sendMsgBtn)

				)

		);
		setup.linkSize(SwingConstants.HORIZONTAL, clearButton, restoreButton, openButton);

		onlineRecord = new JList<>(onlineClient);
		onlineUser = new JScrollPane(onlineRecord);
		onlineUser.setMinimumSize(new Dimension(100, 100));

		if (!isAdmin) {
	    	clearButton.setVisible(false);
	    	restoreButton.setVisible(false);
	    	openButton.setVisible(false);
	    	kickOutButton.setVisible(false);
	    } else {
	        onlineRecord.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent event) {
	                @SuppressWarnings("unchecked")
					JList<String> stringJList = (JList<String>)event.getSource();
	                if (event.getClickCount() == 2) {
	                    int i = stringJList.locationToIndex(event.getPoint());
	                    String chosenClient = stringJList.getModel().getElementAt(i);
	                    int dialogResult = JOptionPane.showConfirmDialog (window, "Are you sure to remove " + chosenClient + "?",
	                    		"Warning", JOptionPane.YES_NO_OPTION);
	                    if(dialogResult == JOptionPane.YES_OPTION) {
	                    	try {
								server.removeClient(chosenClient);

								JSONObject removeClient = new JSONObject();
								removeClient.put("category", "remove");
								removeClient.put("userName", chosenClient);
								msgClient.sendToServer(removeClient);

							}
							catch (IOException e) {
								e.printStackTrace();
							}
	                    	catch (Exception e) {
								e.printStackTrace();
							}
						}
	                }
	            }
	        });
        }

		window.setMinimumSize(isAdmin ? new Dimension(760, 550) : new Dimension(740, 550));

	    window.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent windowEvent) {
				if (isAdmin) {
					if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(window,
							"Are you sure to close?",
							"Reminder",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE)) {
						try {
							server.DeleteTogether();
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							System.exit(0);
						}
					}
				} else {
					if (JOptionPane.showConfirmDialog(window,
							"Are you sure to quit?", "Reminder",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						try {
							server.DeleteMyself(clientName);
						} catch (RemoteException e) {
							e.printStackTrace();
						} finally {
							System.exit(0);
						}
					}
				}
			}
	    });

		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.setVisible(true);
		window.setResizable(false);
	}
}
