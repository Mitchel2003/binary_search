package main;

import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class SearchBinary extends Box {
	private static Box boxCarton = new Box();
	private static String queryInput=".*[a-zA-Z].*";//regularExpression

	public static void main(String[] args) {
		logic();
	}

	private static void logic() {
		Scanner qwerty=new Scanner(System.in);
		int way = 0;

		do {
			print("					   ---Welcome---		\n"
					+ "\n	   Please entry the list of numbers"
					+ "\n" 
					+ "\n			  		ads:"
					+ "\n			   -only nums '+'"
					+ "\n			   -no reply nums");

			do {
				print("					  --add--"
					+ "\n"
					+ "			 entry 'X' for next step");
				String value=qwerty.next();
				way=createList(value);
	
			}while(way!=0);
			
		} while (way!=0);

		qwerty.close();
	}

//--------------------------------------------------methods and logic--------------------------------------------------

	private static int createList(String value) {
		int way=checkInput(value);

		if(way==1) {
			print("Sorry, pleasy try again (caracter strange)");
			return 1;
		}
		if(way==2) {
			print("Date is incorrect, try again");
			return 2;
		}
		if(way==3) {
			boxCarton.addToList(Integer.parseInt(value));
			return 3;
		}
		if(way==4) {
			inputNumExtra();
			return 0;
		}
		
		print("...Good bay...");
		return 0;

	}
	private static void inputNumExtra() {
		if(boxCarton.getBox()==null) {
			print("...List empty...");
			return;
		}
		if(boxCarton.getBox().size()<2) {
			print("...List to small...");
			return;
		}
		
		print("Now entry the number especial for try sums");
		Scanner qwerty=new Scanner(System.in);
		String value = qwerty.next();
		
		if(tryParseInt(value)==Integer.MIN_VALUE) {
			print("Caracter incorrect, please try again");
			qwerty.close();
			return;
		}

		boxCarton.validateReply();//method for quit numbers replied
		boxCarton.addOrganization();//method for organize of ascendent form
		boxCarton.deletRedundancy(Integer.parseInt(value));//method for eliminate numbers greater than user input 
		
		qwerty.close();
		wait(3);
		Brain brain=new Brain (boxCarton.getBox(),Integer.parseInt(value));
		print("list: "+brain.getList()
				+"\n"
				+ "Operation completed");
	}
	
//--------------------------------------------------tools--------------------------------------------------	

	private static int checkInput(String value) {
		
		if(value.equalsIgnoreCase("q")) {
			return 0;
		}
		if(value.equalsIgnoreCase("x")) {
			return 4;
		}
		if(tryParseInt(value)!=Integer.MIN_VALUE) {
			return 3;
		}
		if(value.matches(queryInput)) {
			return 2;
		}
		
		return 1;
	}
	private static int tryParseInt(String value) {
		try {
			return Integer.parseInt(value);
		}catch(NumberFormatException e) {
			return Integer.MIN_VALUE;
		}
	}
	private static void print(String text) {
		String addressImag = "../imag/info.png";
		ImageIcon imag = new ImageIcon(SearchBinary.class.getResource(addressImag));

		JOptionPane.showMessageDialog(null,text,"Information", JOptionPane.INFORMATION_MESSAGE,imag);
	}
	private static void wait(int value) {
		try {
			for(int i=0;i<value;i++) {
				Thread.sleep(1000);
				System.out.print(" . ");
			}
			System.out.println("\n");
		}catch(Exception e) {
			print("error: "+e);
		}
	}
}
