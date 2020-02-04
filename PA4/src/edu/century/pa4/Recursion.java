package edu.century.pa4;


public class Recursion {
	public static void main(String[] args) {
		Recursion test = new Recursion();
		test.recursionOne(2);
		System.out.print("\n");
		System.out.println(test.recursionTwo(5));
	}

	/**
	 *@Specifications: converts a number to binary.
	 *@Param: num-the number converted to a binary
	 *@Precondition: must be positive 
	 *@Postcondition: prints out the binary of the number.
	 *@Throws:
	 */
public static void recursionOne(int num) {
	if (num==0) {
		return;
	}
	else {
		recursionOne(num/2);
	}
	if ((num%2)==0) {
		System.out.print(0);
	}
	if ((num%2)==1) {
		System.out.print(1);
	}
}

/**
 *@Specifications: Will print out a pyramid of *
 *@Param: rows
 *@Precondition: must be positive
 *@Postcondition: prints out *
 *@Throws:
 */
public static int recursionTwo(int rows) {
	int count=0;
	if (rows<1) {
		System.out.print("Under 1 unit");
		return 0;
	}
	if (rows==1) {
		System.out.println("*");
	}
	else {
		recursionTwo(rows-1);
		count+=helper(rows);
	}
	return count+1;
}

/**
 *@Specifications:
 *@Param: Rows-the rows left in recursion two function
 *@Precondition: must be positive
 *@Postcondition: will print out the number of needed *.
 */
public static int helper(int rows) {
	for (int count=0;count<rows;count++) {
		System.out.print("*");
	}
	System.out.print("\n");
	return rows;
}
}