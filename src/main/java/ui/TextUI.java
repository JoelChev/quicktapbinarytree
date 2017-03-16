package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import tree.InputValidator;
import tree.Node;
import tree.TreePrettyPrinter;
import tree.TreeConstructor;
import tree.TreeParser;

public class TextUI {
	
	public static void launchTextUI(Scanner scanner){
		while(1>0){
    		System.out.println("");
	    	System.out.println("Please enter a comma separated binary tree to parse.");
	    	System.out.println("E.g. 7,3,4,5,6,,7,9,,8");
	    	System.out.println("");
	    	
	    	String binaryTree = scanner.nextLine();
	    	if(InputValidator.isValidBinaryTreeString(binaryTree)){
	        	Node root = TreeConstructor.constructTree(binaryTree);
	        	System.out.println("Tree constructed:");
	        	System.out.println("");
	        	System.out.println("");
	        	System.out.println("");
	        	TreePrettyPrinter.printTree(root, 0);
	        	while(true){
		        	System.out.println("Please enter an integer to find in the tree.");
		        	try{
		        		int desiredInt = scanner.nextInt();
		        		scanner.nextLine();
		        		System.out.println("");
			        	System.out.println(TreeParser.parse(desiredInt, root));
			        	break;
		        	}
		        	catch(InputMismatchException e){
		        		scanner.nextLine();
		        		System.out.println("Integer not formatted correctly. Please try again.");
		        		System.out.println("");
		        	}
	        	}
	    	}
	    	else{
	    		System.out.println("Binary tree string is not formatted properly!");
	    		System.out.println("");
	    		System.out.println("Please try again.");
	    	}
	    	System.out.println("");
	    	System.out.println("");
	    	System.out.println("Construct another tree? (y/n)");
	    	System.out.println("");
	    	System.out.print("");
	    	char c = scanner.nextLine().charAt(0);
	    	if(c == 'y'){
	    		continue;
	    	}
	    	else if(c == 'n'){
	    		break;
	    	}
	    	
    	}
	}

}
