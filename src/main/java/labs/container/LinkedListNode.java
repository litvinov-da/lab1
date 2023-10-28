package labs.container;

/**
 * Node of the Linked List collection @see LinkedList For iterating you should
 * see the implemented iterator @see LinkedListIterator
 * 
 * @param <T> Type of the data which the node has
 */
class LinkedListNode<T> {
	/**
	 * Previous node
	 * 
	 * Can be null
	 */
	public LinkedListNode<T> previous;
	/**
	 * Next node
	 * 
	 * Can be null
	 */
	public LinkedListNode<T> next;
	/**
	 * The data stored in the node
	 */
	public T data;

	/**
	 * Useful constructor
	 * 
	 * Initialize default node with the data only. All links are null
	 * 
	 * @param data The data that should be stored
	 */
	LinkedListNode(T data) {
		previous = null;
		next = null;
		this.data = data;
	}
}