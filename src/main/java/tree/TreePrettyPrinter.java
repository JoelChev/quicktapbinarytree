package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/*
 * Code slightly modified from the source here:
 * http://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
 */

public class TreePrettyPrinter {
	
	//Add an offset to center the text in the window.
	public static void printTree(Node root, int offset){
		int depth = getDepthOfTree(root);
		//initial offset
		 for(int i=0; i<offset; i++){
	        	System.out.print(" ");
	        }
		printSubTree(Collections.singletonList(root),1, depth, offset);
	}
	
	private static int getDepthOfTree(Node root){
	if(root == null){
		return 0;
	}
		else{
			int leftDepth = getDepthOfTree(root.getLeftChild());
			int rightDepth = getDepthOfTree(root.getRightChild());
			return Math.max(leftDepth, rightDepth)+1;	
		}
	}

    private static void printSubTree(List<Node> nodes, int level, int maxLevel, int offset) {
        if (nodes.isEmpty() ||isAllElementsNull(nodes)){
            return;
        }

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        List<Node> newNodes = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print(node.getValue());
                newNodes.add(node.getLeftChild());
                newNodes.add(node.getRightChild());
            } 
            else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            printWhitespaces(betweenSpaces);
        }
        System.out.println("");
        for(int i=0; i<offset; i++){
        	System.out.print(" ");
        }

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).getLeftChild() != null){
                    System.out.print("/");
                }
                else{
                    printWhitespaces(1);
                }

                printWhitespaces(i + i - 1);

                if (nodes.get(j).getRightChild() != null){
                    System.out.print("\\");
                }
                else {
                    printWhitespaces(1);
                }
                
                printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
            for(int j=0; j<offset; j++){
            	System.out.print(" ");
            }
        }

        printSubTree(newNodes, level + 1, maxLevel, offset);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++){
            System.out.print(" ");
        }
    }


    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}

