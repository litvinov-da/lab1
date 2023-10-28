package labs.container;

import java.util.Iterator;

/**
 * Class for iterating Linked List
 * 
 * @see LinkedList
 * @param <E> Generic parameter of the data stored in the given collection
 */
class LinkedListIterator<E> implements Iterator<E> {
	/**
	 * Current node in the list Warning! Can be null
	 */
	private LinkedListNode<E> current;
	/**
	 * The last node that was returned by next() method
	 * 
	 * @see #next()
	 */
	private LinkedListNode<E> thrown;

	/**
	 * Constructor
	 * 
	 * @param current Node treaded as the first node for iteration
	 */
	LinkedListIterator(LinkedListNode<E> current) {
		this.current = current;
	}

	/**
	 * Is there any elements left for iterating
	 * 
	 * Should be used for iteration
	 * 
	 * @return true, if you can use next() method; false, otherwise
	 */
	@Override
	public boolean hasNext() {
		return current != null;
	}

	/**
	 * Return the data of the next iterated element of the given collection
	 * 
	 * Warning! You should always check if there is any element to iterate Through
	 * method @see #hasNext()
	 */
	@Override
	public E next() {
		E data = current.data;
		thrown = current;
		current = current.next;
		return data;
	}

	/**
	 * Remove the last given element
	 * 
	 * Does not handle first/last references in the collection itself
	 */
	@Override
	public void remove() {
		if (thrown.previous == null) {
			thrown.next.previous = null;
		} else if (thrown.next == null) {
			thrown.previous.next = null;
		} else {
			LinkedListNode<E> prev = thrown.previous;
			LinkedListNode<E> next = thrown.next;
			next.previous = prev;
			prev.next = next;
		}
		thrown.next = null;
		thrown.previous = null;
	}
}