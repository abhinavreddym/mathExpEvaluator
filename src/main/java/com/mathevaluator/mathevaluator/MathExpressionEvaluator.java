package com.mathevaluator.mathevaluator;

/**
 * This is the main evaluator class which evaluates the mathematical expression and returns the result
 * Supported operators include /, *, +, -, (),
 * Decimal quantities are also supported
 * @author abhinav
 *
 */
public class MathExpressionEvaluator {

	private MathExpressionEvaluator() {

	}

	private static class SingletonHelper {
		private final static MathExpressionEvaluator INSTANCE = new MathExpressionEvaluator();
	}

	public static MathExpressionEvaluator getInstance() {
		return SingletonHelper.INSTANCE;
	}

	public double evaluateExp(String exp) throws Exception {
		//Removing white spaces if any
		exp = exp.replaceAll("\\s", "");
		double value = 0;
		String number = "";
		boolean hasNumber = false;

		for (int i = 0; i < exp.length(); i++) {
			final char c = exp.charAt(i);

			switch (c) {
			case '*':
				if (hasNumber) {
					final Double numb = Double.valueOf(number);
					//rather than using sub string for further evaluation, 
					// getting a substring which included only operations which take precedence over * like ()
					// For Eg. consider the expression 15*3-4. Here, value = 15 * evaluateExp("3-4");
					// substring should return 3 and not return the result of 3-4
					final String newExp = getNextExp(exp.substring(i + 1, exp.length()));
					value = numb * evaluateExp(newExp);
					i = i + newExp.length();
					hasNumber = false;
					number = "";
				} else {
					final String newExp = getNextExp(exp.substring(i + 1, exp.length()));
					value = value * evaluateExp(newExp);
					i = i + newExp.length();
				}
				break;
			case '+':

				if (hasNumber) {
					final Double numb = Double.valueOf(number);
					final String newExp = exp.substring(i + 1, exp.length());
					value = numb + evaluateExp(newExp);
					i = i + newExp.length();
					hasNumber = false;
					number = "";
				} else {
					final String newExp = exp.substring(i + 1, exp.length());
					value = value + evaluateExp(newExp);
					i = i + newExp.length();
				}
				break;

			case '-':

				if (hasNumber) {
					final Double numb = Double.valueOf(number);
					final String newExp = getNextExpForSubtraction(exp.substring(i + 1, exp.length()));
					value = numb - evaluateExp(newExp);
					i = i + newExp.length();
					hasNumber = false;
					number = "";
				} else {
					final String newExp = getNextExpForSubtraction(exp.substring(i + 1, exp.length()));
					value = value - evaluateExp(newExp);
					i = i + newExp.length();
				}
				break;
			case '/':

				if (hasNumber) {
					final Double numb = Double.valueOf(number);
					//rather than using sub string for further evaluation, 
					// getting a substring which included only operations which take precedence over * like ()
					// For Eg. consider the expression 15/3-4. Here, value = 15 / evaluateExp("3-4");
					// substring should return 3 and not return the result of 3-4
					final String newExp = getNextExp(exp.substring(i + 1, exp.length()));
					value = numb / evaluateExp(newExp);
					i = i + newExp.length();
					hasNumber = false;
					number = "";
				} else {
					final String newExp = getNextExp(exp.substring(i + 1, exp.length()));
					value = value / evaluateExp(newExp);
					i = i + newExp.length();
				}
				break;

			case '0':
				hasNumber = true;
				number = number + c;
				if (i == (exp.length() - 1)) {
					value = Double.valueOf(number);
					number = "";
					hasNumber = false;
				}

				break;
			case '1':
				hasNumber = true;
				number = number + c;
				if (i == (exp.length() - 1)) {
					value = Double.valueOf(number);
					number = "";
					hasNumber = false;
				}
				break;
			case '2':
				hasNumber = true;
				number = number + c;
				if (i == (exp.length() - 1)) {
					value = Double.valueOf(number);
					number = "";
					hasNumber = false;
				}
				break;
			case '3':
				hasNumber = true;
				number = number + c;
				if (i == (exp.length() - 1)) {
					value = Double.valueOf(number);
					number = "";
					hasNumber = false;
				}

				break;
			case '4':
				hasNumber = true;
				number = number + c;
				if (i == (exp.length() - 1)) {
					value = Double.valueOf(number);
					number = "";
					hasNumber = false;
				}
				break;
			case '5':
				hasNumber = true;
				number = number + c;
				if (i == (exp.length() - 1)) {
					value = Double.valueOf(number);
					number = "";
					hasNumber = false;
				}
				break;
			case '6':
				hasNumber = true;
				number = number + c;
				if (i == (exp.length() - 1)) {
					value = Double.valueOf(number);
					number = "";
					hasNumber = false;
				}
				break;
			case '7':
				hasNumber = true;
				number = number + c;
				if (i == (exp.length() - 1)) {
					value = Double.valueOf(number);
					number = "";
					hasNumber = false;
				}

				break;
			case '8':
				hasNumber = true;
				number = number + c;
				if (i == (exp.length() - 1)) {
					value = Double.valueOf(number);
					number = "";
					hasNumber = false;
				}
				break;
			case '9':
				hasNumber = true;
				number = number + c;
				if (i == (exp.length() - 1)) {
					value = Double.valueOf(number);
					number = "";
					hasNumber = false;
				}

				break;

			case '.':
				if (hasNumber && (number.length() > 0)) {
					number = number + c;
				}
				break;

			case '(':
				final String newExp = exp.substring(i + 1, getBracketCloseIndex(exp, i));
				value = evaluateExp(newExp);
				i = i + newExp.length() + 1;
				break;
			}
		}
		return value;
	}

	private int getBracketCloseIndex(String exp, int openBracketIndex) throws Exception {
		int result = 0;
		int count = 0;
		for (int i = openBracketIndex; i < exp.length(); i++) {
			final char c = exp.charAt(i);

			switch (c) {
			case '(':
				result = i;
				count++;
				break;
			case ')':
				result = i;
				count--;
				if (count == 0) {
					return result;
				}
				break;
			default:
				break;
			}

		}
		if (count != 0) {
			throw new Exception("Invalid Expression");
		}

		return result;
	}

	private String getNextExp(String exp) throws Exception {
		String result = "";
		exp = exp.trim().toLowerCase();

		for (int i = 0; i < exp.length(); i++) {
			final char character = exp.charAt(i);

			switch (character) {
			case '*':
				i = exp.length();
				break;
			case '/':
				i = exp.length();
				break;
			case '+':
				i = exp.length();
				break;
			case '-':
				i = exp.length();
				break;
			case '.':
				result = result + character;
				break;
			case '(':
				final String newExp = exp.substring(i, getBracketCloseIndex(exp, i) + 1);
				result = result + newExp;
				i = (i + newExp.length()) - 1;
				break;
			case ')':
				throw new Exception("Invalid Expression");
			case ' ':
				result = result + character;
				break;
			default:
				// assuming its a number
				result = result + character;
				break;
			}
		}
		return result;
	}

	private String getNextExpForSubtraction(String exp) throws Exception {
		String result = "";
		exp = exp.trim().toLowerCase();

		for (int i = 0; i < exp.length(); i++) {
			final char character = exp.charAt(i);

			switch (character) {
			case '*':
				result = result + character;
				break;
			case '/':
				result = result + character;
				break;
			case '+':
				i = exp.length();
				break;
			case '-':
				i = exp.length();
				break;
			case '.':
				result = result + character;
				break;
			case '(':
				final String newExp = exp.substring(i, getBracketCloseIndex(exp, i) + 1);
				result = result + newExp;
				i = (i + newExp.length()) - 1;
				break;
			case ')':
				throw new Exception(" '(' is not finished ");
			case ' ':
				result = result + character;
				break;
			default:
				// Assuming its a number
				result = result + character;
				break;
			}
		}
		return result;
	}

}
