package com.work.control;

import java.util.Arrays;
import java.util.LinkedList;

import com.work.frames.AdvancedMainFrame;
import com.work.frames.ShowECG;
import com.work.listeningcom.ListeningCom;

public class Control extends Thread {

	private LinkedList<Byte> bytesRiesived;
	private LinkedList<Integer> listIntegers = new LinkedList<>();
	private LinkedList<Integer> secondChanel = new LinkedList<>();
	private LinkedList<Integer> thirdChanel = new LinkedList<>();
	private LinkedList<Integer> fourthChanel = new LinkedList<>();
	private LinkedList<Integer> fifthChanel = new LinkedList<>();
	private LinkedList<Integer> sixthChanel = new LinkedList<>();
	private LinkedList<Integer> seventhChanel = new LinkedList<>();
	private LinkedList<Integer> eighthChanel = new LinkedList<>();
	
	public Control() {
		bytesRiesived = new LinkedList<>();
	}

	public void addToList(LinkedList<Byte> addBytes) {
		bytesRiesived.addAll(addBytes);
		//System.out.println("add to list = " + bytesRiesived.toString());
		parseArray(bytesRiesived);
		//parseByTest(bytesRiesived);
		//addToArea(bytesRiesived);

	}

	public void parseArray(byte[] readBuffer) {

	}

	public void parseArray(LinkedList<Byte> bytesRiesived) {
		/*for(int i = 0; i < bytesRiesived.size(); i++) {
			if(bytesRiesived.get(i) == 92) {
				bytesRiesived.remove(i);
			}
			if(bytesRiesived.get(i) == -59) {
				bytesRiesived.remove(i);
				i-=2;

			}
		}*/
		for(int i = 0; i < bytesRiesived.size(); i++) {
			if(bytesRiesived.get(i) == 92 && bytesRiesived.get(i+26) == -59) {

				listIntegers.add(fromBinToInt(dataToBin(bytesRiesived.get(i+1)) + 
						dataToBin(bytesRiesived.get(i+2)) + dataToBin(bytesRiesived.get(i+3))) + 600000);
				secondChanel.add(fromBinToInt(dataToBin(bytesRiesived.get(i+4)) + 
						dataToBin(bytesRiesived.get(i+5)) + dataToBin(bytesRiesived.get(i+6))) + 450000);
				thirdChanel.add(fromBinToInt(dataToBin(bytesRiesived.get(i+7)) + 
						dataToBin(bytesRiesived.get(i+8)) + dataToBin(bytesRiesived.get(i+9))) + 300000);
				fourthChanel.add(fromBinToInt(dataToBin(bytesRiesived.get(i+10)) + 
						dataToBin(bytesRiesived.get(i+11)) + dataToBin(bytesRiesived.get(i+12))) + 150000);
				fifthChanel.add(fromBinToInt(dataToBin(bytesRiesived.get(i+13)) + 
						dataToBin(bytesRiesived.get(i+14)) + dataToBin(bytesRiesived.get(i+15))) + 0);
				sixthChanel.add(fromBinToInt(dataToBin(bytesRiesived.get(i+16)) + 
						dataToBin(bytesRiesived.get(i+17)) + dataToBin(bytesRiesived.get(i+18))) - 150000);
				seventhChanel.add(fromBinToInt(dataToBin(bytesRiesived.get(i+19)) + 
						dataToBin(bytesRiesived.get(i+20)) + dataToBin(bytesRiesived.get(i+21))) - 300000);
				eighthChanel.add(fromBinToInt(dataToBin(bytesRiesived.get(i+22)) + 
						dataToBin(bytesRiesived.get(i+23)) + dataToBin(bytesRiesived.get(i+24))) - 450000);


				i += 26;
			}
		}
		//System.out.println("done!");
		addToArea();
	}

	public void parseByTest(LinkedList<Byte> bytesRiesived) {
		for(int i = 0; i < bytesRiesived.size(); i++) {
			if(bytesRiesived.get(i) == 92) {
				bytesRiesived.remove(i);
			}
			if(bytesRiesived.get(i) == -59) {
				bytesRiesived.remove(i);
				i-=2;

			}
		}
		//addToArea(bytesRiesived);
		//System.out.println("done!");
	}

	private String dataToBin(byte data) {
		String bs = null;
		if(data >= 0) {
			bs = Integer.toBinaryString(data);
			while (bs.length() < 8) {
				bs = "0" + bs;
			}
		}  else {
			bs = Integer.toBinaryString(data);
			bs = bs.substring(24, 32);
		}

		//System.out.println(bs);
		//System.out.println(Integer.parseInt(bs,2));
		return bs;

	}

	public void addToArea() {

		//AdvancedMainFrame.addToJTextArea(listIntegers.toString());

		for(int i: listIntegers) {
			AdvancedMainFrame.addToJTextArea(String.valueOf(i));
		}

		//System.out.println(listIntegers);

	}

	public void addToArea(LinkedList<Byte> bytesRiesived) {

		//AdvancedMainFrame.addToJTextArea(bytesRiesived.toString());

		for(int i: bytesRiesived) {
			AdvancedMainFrame.addToJTextArea(String.valueOf(i));
		}

		//System.out.println(listIntegers);

	}

	private int fromBinToInt(String binaryString) {
		int decDigit = 0;		
		char[] binStringToArray = binaryString.toCharArray(); // Преобразуем строку в массив символов
		//System.out.println(Arrays.toString(binStringToArray));
		if(binStringToArray[0] != '1') {
			int j = 0;
			for(int i = binStringToArray.length-1; i >= 0; i--) {
				decDigit += Character.getNumericValue(binStringToArray[i]) * Math.pow(2, j);
				j++;
			}
		} else {
			for(int i = binStringToArray.length-1; i >= 0; i--) {
				binStringToArray[i] = binStringToArray[i] == '1' ? '0' : '1' ;	

			}
			int j = 0;
			for(int i = binStringToArray.length-1; i >= 0; i--) {
				decDigit += Character.getNumericValue(binStringToArray[i]) * Math.pow(2, j);
				j++;	

			}
			decDigit = (decDigit+1)*(-1);
		}
		//System.out.println(Arrays.toString(binStringToArray));

		//System.out.println(decDigit);
		return decDigit;
	}
	
	public void showFirst() {
		for(int i: listIntegers) {
			AdvancedMainFrame.addToJTextArea(String.valueOf(i));
		}
		ShowECG.showECG(listIntegers);
		ShowECG.showECG(secondChanel);
		ShowECG.showECG(thirdChanel);
		ShowECG.showECG(fourthChanel);
		ShowECG.showECG(fifthChanel);
		ShowECG.showECG(sixthChanel);
		ShowECG.showECG(seventhChanel);
		ShowECG.showECG(eighthChanel);
	}
	
	public void showSecond() {
		for(int i: secondChanel) {
			AdvancedMainFrame.addToJTextArea(String.valueOf(i));
		}
		
	}
	
	public void showThird() {
		for(int i: thirdChanel) {
			AdvancedMainFrame.addToJTextArea(String.valueOf(i));
		}
		
	}
	
	public void showFourth() {
		for(int i: fourthChanel) {
			AdvancedMainFrame.addToJTextArea(String.valueOf(i));
		}
		
	}
	
	public void showFifth() {
		for(int i: fifthChanel) {
			AdvancedMainFrame.addToJTextArea(String.valueOf(i));
		}
		
	}
	
	public void showSixth() {
		for(int i: sixthChanel) {
			AdvancedMainFrame.addToJTextArea(String.valueOf(i));
		}
		
	}
	
	public void showSeventh() {
		for(int i: seventhChanel) {
			AdvancedMainFrame.addToJTextArea(String.valueOf(i));
		}
		
	}
	
	public void showEighth() {
		for(int i: eighthChanel) {
			AdvancedMainFrame.addToJTextArea(String.valueOf(i));
		}
		
	}
	
	public void delete() {
		bytesRiesived.removeAll(bytesRiesived);
		listIntegers.removeAll(listIntegers);
		secondChanel.removeAll(secondChanel);
		thirdChanel.removeAll(thirdChanel);
		fourthChanel.removeAll(fourthChanel);
		fifthChanel.removeAll(fifthChanel);
		sixthChanel.removeAll(sixthChanel);
		seventhChanel.removeAll(seventhChanel);
		eighthChanel.removeAll(eighthChanel);
	}

}
