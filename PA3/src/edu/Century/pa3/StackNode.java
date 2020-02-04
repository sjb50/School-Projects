package edu.Century.pa3;

public class StackNode<E> {
	private E data;
	private StackNode<E> link;

	/**
	 * creates the a node and a null link for the stackNode
	 * @param data
	 */
	public StackNode(E data) {
		this.data=data;
		link = null;
	}
	
	 /*
	  * Returns data.
	  */
	public E getData() {
		return data;
	}

	/**
	 *@Specifications: changes the daga of a node
	 *@Param: E data
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 *@Specifications: Returns the link of the data for this nose.
	 *@Postcondition: returns link.
	 */
	public StackNode<E> getLink() {
		return link;
	}

	/**
	 *@Specifications: sets the link of the data
	 *@Postcondition: sets link to <E>link.
	 */
	public void setLink(StackNode<E> link) {
		this.link = link;
	}
}
