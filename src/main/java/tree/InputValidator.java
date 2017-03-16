package tree;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
	
	private static final String NEGATIVE_SIGN_REGEX = "[0-9](-)";
			
	private static final String LEADING_NUMBER_CSV_INTEGER_INPUT_REGEX = "^-?[0-9][-(0-9)+,]*$";
	
	
	/*
	 * 
	 * Some logic is added here to ensure that the input string has a leading digit, follows the convention of digits and commas,
	 * and that there are no trailing negative signs, i.e. there is nothing of the form "4,3,4-," or "3,2,3-3," in the input String.
	 * 
	 * @param: String input - The String to parse in order to construct a binary tree.
	 * @return: boolean of whether the String is valid or not with the REGEX validation.
	 */
    public static boolean isValidBinaryTreeString (String input) {
    	Pattern p = Pattern.compile(NEGATIVE_SIGN_REGEX);
    	Matcher matcher = p.matcher(input);
    	
    	return !matcher.find() && input.matches(LEADING_NUMBER_CSV_INTEGER_INPUT_REGEX);
    }

}
