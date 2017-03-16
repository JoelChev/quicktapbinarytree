package treeLogic;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import tree.Node;
import tree.TreeParser;

public class TreeParserTest {
	
	private Node balancedTree;
	private Node doubleDigitTree;
	private Node negativeDigitTree;
	private Node maxAndMinDigitTree;
	private Node nearlyEmptyTree;
	private Node multipleValueTree;
	
	private static final String LEVELS_STRING = "Levels: ";
	
	@Before
	public void setup(){
		setupBalancedTree();
		setupDoubleDigitsTree();
		setupNegativeDigitsTree();
		setupMinAndMaxTree();
		setupNearlyEmptyTree();
		setupMultipleValueTree();
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
	
	private void setupMultipleValueTree(){
		multipleValueTree = new Node(1);
		Node leftChild = new Node(1);
		Node rightChild = new Node(1);
		Node rightRightChild = new Node(1);
		multipleValueTree.setLeftChild(leftChild);
		multipleValueTree.setRightChild(rightChild);
		rightChild.setRightChild(rightRightChild);
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
	public void testIntegerPresentOnce(){
		String test = TreeParser.parse(1, balancedTree);
		assertEquals(test, LEVELS_STRING+ "1");
	}
	
	@Test
	public void testDoubleDigitIntegerPresent(){
		String test = TreeParser.parse(22, doubleDigitTree);
		assertEquals(test, LEVELS_STRING + "2");
	}
	
	@Test
	public void testIntegerNotPresent(){
		String test = TreeParser.parse(85, balancedTree);
		assertEquals(test, LEVELS_STRING + "None");
	}
	
	@Test
	public void testIntegerPresentMultipleTimes(){
		String test = TreeParser.parse(1, multipleValueTree);
		assertEquals(test, LEVELS_STRING + "1, 2, 3");
	}
	
	@Test
	public void testMaxAndMinInteger(){
		String test1 = TreeParser.parse(Integer.MAX_VALUE, maxAndMinDigitTree);
		String test2 = TreeParser.parse(Integer.MIN_VALUE, maxAndMinDigitTree);
		assertEquals(test1, LEVELS_STRING + "1");
		assertEquals(test2, LEVELS_STRING + "2");
	}
	
	@Test
	public void testNegativeInteger(){
		String test = TreeParser.parse(-1, negativeDigitTree);
		assertEquals(test, LEVELS_STRING + "1");
	}
	
	@Test
	public void testZero(){
		String test = TreeParser.parse(0, negativeDigitTree);
		assertEquals(test, LEVELS_STRING + "2");
	}
	

}
