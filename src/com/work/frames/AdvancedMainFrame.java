package com.work.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.Border;

import com.fazecast.jSerialComm.SerialPort;
import com.work.control.Control;
import com.work.listeningcom.ListeningCom;

@SuppressWarnings("serial")
public class AdvancedMainFrame extends JFrame {

	private static JTextArea jTextArea;
	public  JTextArea jTextArea1 = new JTextArea("***Start working***");	
	private static JComboBox<String> jComboBox;
	private static ListeningCom listeningCom;
	private static JTextField textToSend;
	private static JButton send;
	private static JButton start;
	private static JButton end;
	private static JButton disconnect;
	private static JButton refresh;
	private static JButton connect;
	private static JButton delete;
	private static JButton showFirst;
	private static JButton showSecond;
	private static JButton showThird;
	private static JButton showFourth;
	private static JButton showFifth;
	private static JButton showSixth;
	private static JButton showSeventh;
	private static JButton showEigth;
	private static JButton showECG;

	private JMenuItem jMenuItem;
	private JMenuItem jMenuSave;
	private JMenuBar jMenuBar;
	private JMenu jMenu;
	private byte[] bytesToSend = {85,1,2,5,1,9,-86}; //start PM transmit
	private byte[] bytesToStop = {85,1,1,2,4,(byte) 170}; //stop PM transmit

	private DrawFrame drawFrame;
	private ShowECG ecg;



	public AdvancedMainFrame() {
		super("Listen");

		jMenuBar = new JMenuBar();
		setJMenuBar(jMenuBar);

		jMenu = new JMenu("Balls");
		jMenuItem = new JMenuItem("Add Bounce");
		jMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		jMenuSave = new JMenuItem("Save as...");
		jMenuSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

		jMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

			}
		});	

		jMenu.add(jMenuItem);

		jMenuBar.add(jMenu);

		jComboBox = new JComboBox<String>(ListeningCom.getPortNames());
		jComboBox.setMaximumSize(jComboBox.getPreferredSize());


		textToSend = new JTextField(20);
		textToSend.setMaximumSize(textToSend.getPreferredSize());

		jTextArea = new JTextArea("***Start working***");
		jTextArea.setSize(jTextArea.getPreferredSize());
		jTextArea1.setSize(jTextArea.getPreferredSize());

		JButton clear = new JButton("Clear");
		JButton draw = new JButton("Draw");
		disconnect = new JButton("Disconnect");
		disconnect.setEnabled(false);
		connect = new JButton("Connect");
		send = new JButton("Send");
		send.setEnabled(false);
		start = new JButton("Start");
		start.setEnabled(false);
		end = new JButton("End");
		end.setEnabled(false);		
		showFirst = new JButton("showFirst");
		showSecond = new JButton("showSecond");
		showThird = new JButton("showThird");
		showFourth = new JButton("showFourth");
		showFifth = new JButton("showFifth");
		showSixth = new JButton("showSixth");
		showSeventh = new JButton("showSeventh");
		showEigth = new JButton("showEigth");
		showECG = new JButton("Show ECG");
		
		refresh = new JButton("Refresh ports");
		delete = new JButton("Delete");
		delete.setEnabled(false);
		JButton get = new JButton("Get data");
		JPanel main = new JPanel();
		JPanel top = new JPanel();
		JPanel center = new JPanel();		
		JPanel botom = new JPanel();
		JPanel east = new JPanel();
		JPanel west = new JPanel();
		JPanel speed = new JPanel();
		JPanel combo = new JPanel();
		Border speedBorder = BorderFactory.createTitledBorder("Baud Rate");
		Border eastBorder = BorderFactory.createTitledBorder("Controls");
		speed.setBorder(speedBorder);
		east.setBorder(eastBorder);
		ButtonGroup speedGroup = new ButtonGroup();
		JRadioButton rb9600 = new JRadioButton("9600", false);
		speedGroup.add(rb9600);
		JRadioButton rb115200 = new JRadioButton("115200", false);
		speedGroup.add(rb115200);
		JRadioButton rb921600 = new JRadioButton("921600", true);
		speedGroup.add(rb921600);

		JSplitPane jSplitPane = new JSplitPane(
				JSplitPane.VERTICAL_SPLIT,
				top, 
				center);

		jSplitPane.setResizeWeight(0.5);
		jSplitPane.setOneTouchExpandable(true);
		jSplitPane.setContinuousLayout(true);
		jSplitPane.setSize(jSplitPane.getPreferredSize());

		speed.setLayout(new BoxLayout(speed, BoxLayout.Y_AXIS));
		speed.add(rb9600);
		speed.add(rb115200);
		speed.add(rb921600);
		speed.setEnabled(false);

		main.setLayout(new BorderLayout());
		top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
		botom.setLayout(new BoxLayout(botom, BoxLayout.X_AXIS));
		center.setLayout(new BoxLayout(center, BoxLayout.PAGE_AXIS));
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
		combo.setLayout(new BoxLayout(combo, BoxLayout.X_AXIS));

		main.add(top, BorderLayout.NORTH);
		main.add(botom, BorderLayout.SOUTH);
		main.add(center, BorderLayout.CENTER);
		main.add(east, BorderLayout.EAST);
		main.add(west, BorderLayout.WEST);

		center.add(Box.createHorizontalGlue());
		center.add(Box.createVerticalStrut(10));
		center.add(jTextArea);
		center.add(new JScrollPane(jTextArea));
		//center.add(jSplitPane);
		/*center.add(jTextArea1);
		center.add(new JScrollPane(jTextArea1));*/
		center.add(Box.createVerticalStrut(10));
		center.add(Box.createHorizontalGlue());

		top.add(Box.createHorizontalStrut(10));
		top.add(Box.createVerticalGlue());
		top.add(connect);
		top.add(Box.createHorizontalStrut(10));
		top.add(disconnect);

		botom.add(Box.createHorizontalStrut(10));
		botom.add(Box.createVerticalGlue());
		botom.add(textToSend);
		botom.add(Box.createHorizontalStrut(10));
		botom.add(send);
		
		east.add(Box.createVerticalStrut(10));
		east.add(clear);
		east.add(Box.createVerticalStrut(10));
		east.add(jComboBox);
		east.add(Box.createVerticalStrut(10));
		east.add(refresh);
		east.add(Box.createVerticalStrut(10));
		east.add(get);
		east.add(Box.createVerticalStrut(10));
		east.add(draw);
		east.add(Box.createVerticalStrut(10));
		east.add(showECG);
		east.add(Box.createVerticalStrut(10));
		east.add(start);
		east.add(Box.createVerticalStrut(10));
		east.add(end);
		east.add(Box.createVerticalStrut(10));
		east.add(delete);
		east.add(Box.createVerticalStrut(10));
		east.add(speed);
		east.add(Box.createVerticalGlue());
		
		west.add(Box.createVerticalStrut(10));
		west.add(showFirst);
		west.add(Box.createVerticalStrut(10));
		west.add(showSecond);
		west.add(Box.createVerticalStrut(10));
		west.add(showThird);
		west.add(Box.createVerticalStrut(10));
		west.add(showFourth);
		west.add(Box.createVerticalStrut(10));
		west.add(showFifth);
		west.add(Box.createVerticalStrut(10));
		west.add(showSixth);
		west.add(Box.createVerticalStrut(10));
		west.add(showSeventh);
		west.add(Box.createVerticalStrut(10));
		west.add(showEigth);
		west.add(Box.createVerticalGlue());

		this.add(main);
		this.pack();

		/*	ActionListener actionListenerCB = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = (String) comboBox.getSelectedItem();
				serialPort = new SerialPort(s);
			}
		};
		comboBox.addActionListener(actionListener);*/

		jComboBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listeningCom = new ListeningCom((String) jComboBox.getSelectedItem());

			}
		});

		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jTextArea.setText("");
			}
		});


		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jComboBox.removeAllItems();
				String ports[] = ListeningCom.getPortNames();
				for (int i = 0; i < ports.length; i++) {
					jComboBox.addItem(ports[i]);
				}

			}
		});


		disconnect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listeningCom.closePort();
			} 
		});

		connect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					listeningCom.openPort();
				} catch(Exception ex) {
					jTextArea.append("\nSome problem with connection to port " + getJComboBoxItem());
				}

			}
		});

		send.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String getText = textToSend.getText();
				if(getText.equals("")) {

				} else {
					listeningCom.sendString(getText);
					jTextArea.append("\nSended string *****\n" + getText + "\n**********");

					textToSend.setText("");
				}
			} 
		});

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listeningCom.sendStartBytes(bytesToSend);
				textToSend.setText("");
			} 
		});

		end.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listeningCom.sendBytes(bytesToStop);
				listeningCom.sendBytes(bytesToStop);
				listeningCom.sendBytes(bytesToStop);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {

					e1.printStackTrace();
				}
				listeningCom.showList();
				textToSend.setText("");
			} 
		});

		get.addActionListener(new ActionListener() { 

			public void actionPerformed(ActionEvent e) {
				listeningCom.getData();

			}
		});
		
		delete.addActionListener(new ActionListener() { 

			public void actionPerformed(ActionEvent e) {
				listeningCom.delete();
				jTextArea.setText("");

			}
		});

		draw.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				drawFrame = new DrawFrame();


			}
		});
		
		showECG.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ecg = new ShowECG();


			}
		});
		
		showFirst.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listeningCom.showFirst();
			} 
		});
		
		showSecond.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listeningCom.showSecond();
			} 
		});
		
		showThird.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listeningCom.showThird();
			} 
		});
		
		showFourth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listeningCom.showFourth();
			} 
		});
		
		showFifth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listeningCom.showFifth();
			} 
		});
		
		showSixth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listeningCom.showSixth();
			} 
		});
		
		showSeventh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listeningCom.showSeventh();
			} 
		});
		
		showEigth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				listeningCom.showEighth();
			} 
		});
	} // **********END CONSTRUCTOR*********

	public static String getJComboBoxItem() {
		return (String) jComboBox.getSelectedItem();

	}

	public static void addToJTextArea(String string) {
		jTextArea.append("\n" + string);
	}
	
	public static void addToJTextArea(Long time) {
		jTextArea.append("\n" + time);
	}

	public static void setSendActive() {
		send.setEnabled(true);
		start.setEnabled(true);
		end.setEnabled(true);
		delete.setEnabled(true);
		disconnect.setEnabled(true);
	}

	public static void setSendDisactive() {
		send.setEnabled(false);
		start.setEnabled(false);
		end.setEnabled(false);
		delete.setEnabled(false);
		disconnect.setEnabled(false);
	}

	public static void setJComboBoxActive() {
		jComboBox.setEnabled(true);
		refresh.setEnabled(true);
		connect.setEnabled(true);
	}

	public static void setJComboBoxDisactive() {
		jComboBox.setEnabled(false);
		refresh.setEnabled(false);
		connect.setEnabled(false);
	}

}
