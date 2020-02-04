package edu.Century.pa3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author sjb19 Class will read a file and determine if it is balanced.
 * @param <E>
 */
public class SimpleParser<E> {
	static final char OPEN_PARA = '(';
	static final char CLOSE_PARA = ')';
	static final char OPEN_BRACKET = '[';
	static final char CLOSE_BRACKET = ']';
	static final char OPEN_C_BRACKET = '{';
	static final char CLOSE_C_BRACKET = '}';
	static final char OPEN_CARROT = '<';
	static final char CLOSE_CARROT = '>';
	boolean balanced = true;
	private String badLine = "";
	private Stacks open, close;
	private String fileAsString = "";

	/**
	 * Constructor that creats a stack for opening characters and one for closing
	 * charactesr
	 */
	public SimpleParser() {
		open = new Stacks();
		close = new Stacks();
	}

	/**
	 * @Specifications:This Method will same the file as a string and then convert
	 *                      the string to an array of characters/
	 * @Param: File textFile
	 * @Precondition: file must be found
	 * @Postcondition: text from the file will be saved and then made into an array.
	 * @Exceptions: file not found
	 * @Throws:FileNotFoundException
	 */
	public void checkFile(File textFile) throws FileNotFoundException {
		Scanner reader = new Scanner(textFile);
		String lineChecker;
		int lineCounter = 1;
		while (reader.hasNext()) {
			lineChecker = reader.nextLine();
			fileAsString += ("line " + lineCounter + ":" + lineChecker + "\n");
			balancer(lineChecker, lineCounter);
			lineCounter++;
		}
	}

	/**
	 * @Specifications: this will take an array of characters and use stacks to
	 *                  check for balance by the popping the special characterss.
	 * @Param: Char[] copyFile
	 * @Precondition: copiedFile must not be not null
	 * @Postcondition: returns true or false depending on the characters read
	 * @Throws: NullPointerException
	 */
	public boolean balancer(String copiedLine, int line) throws NullPointerException {
		char[] copiedFile = new char[copiedLine.length()];
		copiedFile = copiedLine.toCharArray();
		for (int count = 0; count < copiedLine.length(); count++) {
			switch (copiedFile[count]) {
			case OPEN_PARA:
			case OPEN_CARROT:
			case OPEN_C_BRACKET:
			case OPEN_BRACKET:
				open.push(copiedFile[count]);
				break;
			case CLOSE_PARA:
			case CLOSE_C_BRACKET:
			case CLOSE_CARROT:
			case CLOSE_BRACKET:
				close.push(copiedFile[count]);
				if (open.peak() == (null)) {
					System.out.println("Here");
					close.pop();
					badLine += ("error at line " + line + "here");
					balanced = false;
				} else {
					if (!(checkBalance((char) open.pop(), (char) close.pop()))) {
						badLine += ("error at line " + line + "\n");
						balanced = false;
					}
				}
				break;
			}
		}
		return balanced;
	}

	/**
	 * @Specifications:it will compare two special characters to determine if they
	 *                    match if not it will return false.
	 * @Param: char opener, char closer
	 * @Precondition:
	 * @Postcondition: returns true or false depending on if the characters are
	 *                 compatible
	 */
	public boolean checkBalance(char opener, char closer) {
		switch (opener) {
		case OPEN_PARA:
			if (closer != ')') {
				return false;
			}
			break;
		case OPEN_BRACKET:
			if (closer != ']') {
				return false;
			}
			break;
		case OPEN_CARROT:
			if (closer != '>') {
				return false;
			}
			break;
		case OPEN_C_BRACKET:
			if (closer != '}') {
				return false;
			}
			break;
		}
		return true;
	}

	public String toString() {
		String results = fileAsString;
		if (!(balanced)) {
			results += "File is not balanced\n" + badLine;
			return results;
		}
		return (results+"File is balanced\n");
	}
}