package treeLogic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tree.InputValidator;

public class InputValidatorTest {
	
	private static final String validString = "1,2,3,4,,5,6,7";
	private static final String invalidNegativeString1 = "1,2,3,4,5,6,7-";
	private static final String invalidNegativeString2 = "1,2,3,4-5,6,7";
	private static final String validNegativeString = "1,-2,3,4,5,6,7";
	private static final String invalidInput = "abecalkj";
	private static final String invalidCharacters = "a,d,c,d,e,f";
	private static final String invalidLeadingCharacter = ",2,3,4,5,6";

	
	@Test
	public void testValidStrings(){
		assertTrue(InputValidator.isValidBinaryTreeString(validString));
		assertTrue(InputValidator.isValidBinaryTreeString(validNegativeString));
	}
	
	@Test
	public void testInvalidStrings(){
		assertFalse(InputValidator.isValidBinaryTreeString(invalidInput));
		assertFalse(InputValidator.isValidBinaryTreeString(invalidNegativeString1));
		assertFalse(InputValidator.isValidBinaryTreeString(invalidNegativeString2));
		assertFalse(InputValidator.isValidBinaryTreeString(invalidNegativeString2));
	}
}
