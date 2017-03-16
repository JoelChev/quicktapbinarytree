package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TreeParser {
	
	/*
	 * This method is used to parse the in-memory binary tree for a given integer value.
	 * 
	 * @param: int value - The desired integer value to find in a given binary tree.
	 * @param: Node root - A pointer to the root node of a binary tree.
	 * 
	 * @return: A String message indicating at which levels in a binary tree a given integer value is.
	 * If the integer value does not exist in the tree, the message "Levels: None" is returned.
	 */
	public static String parse(int value, Node root){
		StringBuffer levelsFoundBuffer = new StringBuffer();
		levelsFoundBuffer.append("Levels: ");
		
		//Use a hash set as there could be duplicates of an integer at a given level.
		Set<Integer>levelsFound = new HashSet<Integer>();
		recursionTraversal(value, root, 1, levelsFound);
		
		//Turn it into an ArrayList so it can be sorted and prettily output as a list.
		List<Integer> sortedLevelsFound = new ArrayList<>(levelsFound);
		Collections.sort(sortedLevelsFound);;
		for(Integer level:sortedLevelsFound){
			levelsFoundBuffer.append(level + ", ");
		}
		
		String levelsFoundMessage = levelsFoundBuffer.toString();
		//Want to remove the last extra comma at the end.
		if(levelsFoundMessage.length() > 8){
			return levelsFoundMessage.substring(0, levelsFoundMessage.length()-2);
		}
		else{
			return levelsFoundMessage+"None";
		}
		
	}
	
	//Helper method to traverse the tree recursively to seach for the values.
	private static void recursionTraversal(int value, Node root, int level, Set<Integer> levelsFound){
		if(root.getValue()== value){
			levelsFound.add(level);
		}
		if(root.getLeftChild()!=null){
			recursionTraversal(value, root.getLeftChild(), level+1, levelsFound);
		}
		if(root.getRightChild()!=null){
			recursionTraversal(value,root.getRightChild(), level+1, levelsFound);
		}
	}
	

}
