package QuickTapSurvey.Programming_Challenge;


import java.util.Scanner;

import ui.SwingUI;
import ui.TextUI;


public class App 

{
    public static void main( String[] args )
    {
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Hello and welcome to the Tree Traversal Interface");
    	System.out.println("In this program, you can determine levels that a given integer is present in a constructed binary tree.");
    	System.out.println("Please select if you would like to use GUI or Text Mode.");
		
		System.out.println("");
		System.out.println("Enter 1 for GUI Mode.");
		System.out.println("Enter 2 for Text Mode.");
		System.out.println("Enter any other key to quit.");
		System.out.println("");
		String input = scanner.nextLine();
		if(input.charAt(0)=='1'){
			SwingUI.launchGUI();
		}
		else if(input.charAt(0) == '2'){
			TextUI.launchTextUI(scanner);
		}
    	

    }
        
    
}
