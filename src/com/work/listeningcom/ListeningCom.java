 package com.work.listeningcom;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortPacketListener;
import com.work.control.Control;
import com.work.frames.AdvancedMainFrame;

public class ListeningCom implements SerialPortDataListener {

	private SerialPort userPort;
	private InputStream in;
	private Control control = new Control();
	private LinkedList<Byte> bytesRiesived = new LinkedList<>();
	private long start;
	private long stop;
	
	public ListeningCom() {
		
	}

	public ListeningCom(String comPortName) {
		userPort = SerialPort.getCommPort(comPortName);
		userPort.addDataListener(this);
	}

	public void setUserPort(String comPortName) {
		
	}

	public SerialPort getUserPort () {
		return this.userPort;
	}

	public void getData() {

		/*try {
			int i = 0;
			while (true)
			{
				while (userPort.bytesAvailable() == 0) {
					//System.out.println("not avalible");
					Thread.sleep(20);
				}

				if(userPort.bytesAvailable() > 0) {

					byte[] readBuffer = new byte[userPort.bytesAvailable()];
					int numRead = userPort.readBytes(readBuffer, readBuffer.length);
					System.out.println("Read " + numRead + " bytes." + Arrays.toString(readBuffer));
				}
				else {
					System.out.println(userPort.bytesAvailable());
					System.out.println(userPort.isOpen());
				}
				if(i > 100) {
					break;
				} else {
					i++;
				}

			}
		} catch (Exception e) { System.out.println(e);  }
		userPort.closePort();*/

		/*in = userPort.getInputStream();
		for (int j = 0; j < 1000; ++j)
			try {
				System.out.print((char)in.read());
				in.close();
			} catch (IOException e) {

				System.out.println(e);
			} catch(Exception ex) {
				System.out.println(ex);
			}*/


		/*userPort.addDataListener(new SerialPortDataListener() {
			@Override
			public int getListeningEvents() {
				return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
			}
			public void serialEvent(SerialPortEvent event) {
				byte[] newData = event.getReceivedData();
				System.out.println("Received data of size: " + newData.length);
				for (int i = 0; i < newData.length; ++i)
					System.out.print((char)newData[i]);
				System.out.println("\n");
			}
		});*/
	}

	public static String[] getPortNames() {
		SerialPort[] ports = SerialPort.getCommPorts();
		String[] result = new String[ports.length];
		for (int i = 0; i < ports.length; i++) {
			result[i] = ports[i].getSystemPortName();
		}

		return result;
	}

	public void openPort() {
		//SerialPort userPort = SerialPort.getCommPort((String) AdvancedMainFrame.jComboBox.getSelectedItem());
		//Initializing port
		if(userPort.isOpen()) {
			AdvancedMainFrame.addToJTextArea("Port " + AdvancedMainFrame.getJComboBoxItem() +
					" is already opened or not available");

		} else {
			userPort.setBaudRate(921600);
			userPort.setNumDataBits(8);
			userPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
			userPort.setParity(SerialPort.NO_PARITY);
			userPort.openPort();
			if (userPort.isOpen()) {
				AdvancedMainFrame.setSendActive();
				AdvancedMainFrame.setJComboBoxDisactive();
				AdvancedMainFrame.addToJTextArea("Port opened!");
				//timeout not needed for event based reading
				//userPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 100, 0);
			} else {
				AdvancedMainFrame.setSendDisactive();
				AdvancedMainFrame.setJComboBoxActive();
				AdvancedMainFrame.addToJTextArea("Port NOT open!!!!");
			}
		}		

	}

	public void closePort() {
		if(userPort.isOpen()) {
			userPort.closePort();
			AdvancedMainFrame.setSendDisactive();
			AdvancedMainFrame.setJComboBoxActive();
			AdvancedMainFrame.addToJTextArea("Port is close");
		} else {
			AdvancedMainFrame.addToJTextArea("Port " + AdvancedMainFrame.getJComboBoxItem() + 
					" already close");
		}
	}

	public void sendString(String string) {

	}

	public void sendStartBytes(byte[] bytes) {
		userPort.writeBytes(bytes, bytes.length);

	}
	
	public void sendEndBytes(byte[] bytes) {
		userPort.writeBytes(bytes, bytes.length);

	}
	
	public void sendBytes(byte[] bytes) {
		userPort.writeBytes(bytes, bytes.length);

	}

	@Override
	public int getListeningEvents() {
		return userPort.LISTENING_EVENT_DATA_AVAILABLE;
	}

	@Override
	public void serialEvent(SerialPortEvent arg0) {
		while (userPort.bytesAvailable() == 0) {}
			
		if(userPort.bytesAvailable() > 0) {
			
			byte[] readBuffer = new byte[userPort.bytesAvailable()];
			int numRead = userPort.readBytes(readBuffer, readBuffer.length);
			for(int i = 0; i < readBuffer.length; i++) {
				bytesRiesived.add(readBuffer[i]);
			}
			//control.addToList(bytesRiesived);
			
			AdvancedMainFrame.addToJTextArea("Read " + numRead + " bytes." + Arrays.toString(readBuffer));

		}
	}
	
	public void showList() {
		control.addToList(bytesRiesived);
	}
	
	public void showFirst() {
		control.showFirst();
	}
	
	public void showSecond() {
		control.showSecond();
	}
	
	public void showThird() {
		control.showThird();
	}
	
	public void showFourth() {
		control.showFourth();
	}
	
	public void showFifth() {
		control.showFifth();
	}
	
	public void showSixth() {
		control.showSixth();
	}
	
	public void showSeventh() {
		control.showSeventh();
	}
	
	public void showEighth() {
		control.showEighth();
	}
	
	public void delete() {
		bytesRiesived.removeAll(bytesRiesived);
		control.delete();
	}


}
