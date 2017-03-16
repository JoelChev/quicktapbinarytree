package treeLogic;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import tree.Node;
import tree.TreeConstructor;


public class TreeConstructorTest {
	
	private Node balancedTree;
	private Node doubleDigitTree;
	private Node negativeDigitTree;
	private Node maxAndMinDigitTree;
	private Node nearlyEmptyTree;
	
	private static final String balancedTreeString = "1,2,3";
	private static final String doubleDigitsTreeString = "1,22,3,7";
	private static final String negativeDigitsTreeString = "-1,0,18,4";
	private static final String maxAndMinDigitsTreeString = "2147483647,-2147483648,33";
	private static final String nearlyEmptyTreeString = "1,2,3,,,,4";
	
	@Before
	public void setup(){
		setupBalancedTree();
		setupDoubleDigitsTree();
		setupNegativeDigitsTree();
		setupMinAndMaxTree();
		setupNearlyEmptyTree();
	}
	
	private void setupBalancedTree(){
		balancedTree = new Node(1);
		Node leftChild = new Node(2);
		Node rightChild = new Node(3);
		balancedTree.setLeftChild(leftChild);
		balancedTree.setRightChild(rightChild);
	}
	
	private void setupDoubleDigitsTree(){
		doubleDigitTree = new Node(1);
		Node leftChild = new Node(22);
		Node rightChild = new Node(3);
		Node leftLeftChild = new Node(7);
		doubleDigitTree.setLeftChild(leftChild);
		doubleDigitTree.setRightChild(rightChild);
		leftChild.setLeftChild(leftLeftChild);
	}
	
	private void setupNegativeDigitsTree(){
		negativeDigitTree = new Node(-1);
		Node leftChild = new Node(0);
		Node rightChild = new Node(18);
		Node leftLeftChild = new Node(4);
		negativeDigitTree.setLeftChild(leftChild);
		negativeDigitTree.setRightChild(rightChild);
		leftChild.setLeftChild(leftLeftChild);
	}
	
	private void setupMinAndMaxTree(){
			maxAndMinDigitTree = new Node(Integer.MAX_VALUE);
			Node leftChild = new Node(Integer.MIN_VALUE);
			Node rightChild = new Node(33);
			maxAndMinDigitTree.setLeftChild(leftChild);
			maxAndMinDigitTree.setRightChild(rightChild);
	}
	
	private void setupNearlyEmptyTree(){
		nearlyEmptyTree = new Node(1);
		Node leftChild = new Node(2);
		Node rightChild = new Node(3);
		Node rightRightChild = new Node(4);
		nearlyEmptyTree.setLeftChild(leftChild);
		nearlyEmptyTree.setRightChild(rightChild);
		rightChild.setRightChild(rightRightChild);
	}
	
	@Test
	public void testBalancedTree(){
		Node test = TreeConstructor.constructTree(balancedTreeString);
		assertTrue(testTreeEquality(balancedTree, test));
	}
	
	@Test
	public void testDoubleDigits(){
		Node test = TreeConstructor.constructTree(doubleDigitsTreeString);
		assertTrue(testTreeEquality(doubleDigitTree, test));
	}
	
	@Test
	public void testNegativeDigits(){
		Node test = TreeConstructor.constructTree(negativeDigitsTreeString);
		assertTrue(testTreeEquality(negativeDigitTree, test));
	}
	
	@Test
	public void testIntMaxAndMin(){
		Node test = TreeConstructor.constructTree(maxAndMinDigitsTreeString);
		assertTrue(testTreeEquality(maxAndMinDigitTree, test));
	}
	
	@Test
	public void testNearlyEmptyLevel(){
		Node test = TreeConstructor.constructTree(nearlyEmptyTreeString);
		assertTrue(testTreeEquality(nearlyEmptyTree, test));
	}
	
	@Test
	public void testNullAndEmptyInputs(){
		Node null1 = TreeConstructor.constructTree(null);
		Node null2 = TreeConstructor.constructTree("");
		assertNull(null1);
		assertNull(null2);
	}
	
	private boolean testTreeEquality(Node expected, Node actual){
		if(expected ==null && actual ==null){
			return true;
		}
		else if(!expected.equals(actual)){
			return false;
		}
		return (testTreeEquality(expected.getLeftChild(), actual.getLeftChild()) && 
				testTreeEquality(expected.getRightChild(), actual.getRightChild()));
			
	}

}
