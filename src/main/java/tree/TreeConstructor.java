package tree;

import java.util.LinkedList;

public class TreeConstructor {
	
	private static final String DIGIT_AND_LETTER_SEPARATOR_REGEX = "(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)";
	
	/*
	 * A method to construct a binary tree in memory from a comma separated String of integers.
	 * 
	 * @param: String treeString - The String that is to be parsed in order to construct a binary tree.
	 * 
	 * NOTE: If there are too many commas at any point in the String for a given level (i.e. something like 1,,,,,,,2,3,4,)
	 * then the tree that is constructed will only reflect what it was able to dequeue during BFS construction. In the above example one would simply
	 * get a root node of 1 for instance.
	 */
	public static Node constructTree(String treeString){
		if(treeString == null || treeString.length() ==0){
			return null;
		}
		
		LinkedList<Node> queue = new LinkedList<>();
		//In order to handle double digit numbers, as well as negatives and properly deal with multiple commas, some String formatting is necessary.
		LinkedList<Object> elements = splitString(treeString);
		int characterPosition=0;
		int nextCharacterPosition=1;
		Integer rootNodeValue = (Integer) elements.getFirst();
		Node root = new Node(rootNodeValue);
		queue.add(root);
			
		//In order to construct the tree, a modified BFS will be used.
		//It is important to note that there are four possibilities for a given node. It either has zero children
		// a left child, a right child or both children.
	    while (!queue.isEmpty() && nextCharacterPosition<elements.size()){
	        Node current = queue.remove();
	        characterPosition++;
	        nextCharacterPosition++;
	        Object currentCharacter = elements.get(characterPosition);
	        Object nextCharacter = elements.get(nextCharacterPosition);
	        //Case 1:Potential only right child case.
	        if(!(currentCharacter instanceof Integer) && !(nextCharacter instanceof Integer)){
	        	if(elements.get(nextCharacterPosition+1) instanceof Integer){
	        		Integer rightChildValue = (Integer) elements.get(nextCharacterPosition+1);
	        		addRightChild(rightChildValue, current, queue);
	        		characterPosition=nextCharacterPosition+1;
	        		nextCharacterPosition=nextCharacterPosition+2;
	        	}
	        	//This implies that there are at least 3 commas in a row, so we need to move forward by one in the LinkedList of elements.
	        	else{
	        		characterPosition=characterPosition+1;
	        		nextCharacterPosition=nextCharacterPosition+1;
	        	}
	        }
	        //Case 2: Potential only left child case.
	        else if((currentCharacter instanceof Character) && (nextCharacter instanceof Integer)){
	        	Integer leftChildValue = (Integer) nextCharacter;
	        	addLeftChild(leftChildValue, current, queue);
	        	//In the event that the next character is the end of the String, that implies that the last node is a left child.
	        	if(nextCharacterPosition+1 == elements.size() || nextCharacterPosition+2 == elements.size()){
	        		break;
	        	}
	        	//Otherwise if the string is longer, there could potentially be a right child to add.
	        	//Case 3: Potential left and right child case.
	        	if((elements.get(nextCharacterPosition+1) instanceof Character) && (elements.get(nextCharacterPosition+2) instanceof Integer)){
	        		Integer rightChildValue = (Integer) elements.get(nextCharacterPosition+2);
	        		addRightChild(rightChildValue, current, queue);
	        		characterPosition++;
	        		nextCharacterPosition++;
	        	}
        		characterPosition=nextCharacterPosition+1;
	        	nextCharacterPosition=nextCharacterPosition+2;
	        }
	        //Otherwise Case 4: No children. Just continue on.
	    }
	    return root;
	}
	
	private static void addLeftChild(Integer value, Node current, LinkedList<Node> queue ){
		Node leftChild = new Node(value);
    	current.setLeftChild(leftChild);
    	leftChild.setParent(current);
    	queue.add(leftChild);
		
	}
	
	private static void addRightChild(Integer value, Node current, LinkedList<Node> queue){
		Node rightChild = new Node(value);
		current.setRightChild(rightChild);
		rightChild.setParent(current);
		queue.add(rightChild);
	}
	
	
	//This important helper method is used to parse the String into a LinkedList of Integers and Characters (commas).
	//Using this, it is possible to deal with integers with multiple digits, as well as negatives. It handles all the logic for this.
	private static LinkedList<Object> splitString(String string){

		//This Array will contain a split version of the input string with both characters and integers.
	    String[] subStringArray = string.split(DIGIT_AND_LETTER_SEPARATOR_REGEX);
	    
	    LinkedList<Object> elements = new LinkedList<>();

	    for(int i = 0; i < subStringArray.length; i++){
	    	try{
	    		//Handle negative numbers: the numbers must be combined with the negative signs.
	    		if(elements.size() >0 && elements.getLast() instanceof Character){
	    			Character lastCharacter = (Character) elements.getLast();
	    			if(lastCharacter == '-'){
	    				elements.removeLast();
	    				subStringArray[i] = "-" + subStringArray[i];
	    			}
	    		}
	    		elements.add(Integer.parseInt(subStringArray[i]));
	    	}
	    	//This implies that one is dealing with a comma, or potentially multiple commas in a row. One must
	    	//parse each comma into a separate node.
		    catch(NumberFormatException e){
		    	char[]chars = subStringArray[i].toCharArray();
		    	for(int j=0; j<chars.length; j++){
		    		elements.add(chars[j]);
		    	}
		    }
		    
		}
	    //Remove all trailing commas and negative signs too.
	    while(elements.getLast() instanceof Character){
	    	elements.removeLast();
	    }
	    return elements;
	}
	
	

}
