package edu.Century.pa3;

public class Stacks<E> {
	private StackNode<E> head;
	private int counter;
	
	/**
	 * Creates a stack with a null head.
	 */
	public Stacks() {
		head=null;
		counter = 0;
	}
	
	/**
	 * creats a stack with element as the head.
	 * @param element
	 */
	public Stacks(E element) {
		head = new StackNode(element);
		counter=1;
	}
	
	/**
	 *@Specifications: Adds an element to the top of a stack
	 *@Param: E element 
	 *@Postcondition: element added to the top of the stack
	 */
	public void push(E element) {
		if (head==null) {
			head = new StackNode(element);
			counter++;
		}
		else {
			StackNode info = new StackNode(element);
			info.setLink(head);
			head=info;
			counter++;
		}
	}
	
	/**
	 *@Specifications: Returns the element at the top of the stack
	 *@Precondition: will return null if no element found
	 *@Postcondition: returns head.
	 */
	public StackNode peak() {
		return head;
	}
	
	/**
	 *@Specifications: returns and removed the stop of the stack
	 *@Postcondition: element at the top is removed.
	 */
	public E pop() {
		E element = head.getData();
		head=head.getLink();
		return element;
	}
	
	/** 
	 *@Specifications: returns the amount of items in the stack
	 *@Postcondition: returns counter.
	 */
	public int getCount() {
		return counter;
	}
}
