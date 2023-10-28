package labs.container;

import java.util.Iterator;
import java.util.function.Predicate;

/**
 * The class for storing data in two-linked list
 * 
 * @param <E> Type of the data stored in the collection
 * 
 * Collection has next functionality: 
 * 	Adding to the collection
 * 		@see #add(Object)
 *  	@see #addAll(LinkedList)
 *  	@see #addFirst(Object)
 *  Clearing and removing:
 *  	@see #clear()
 *  	@see #remove(Object)
 *  	@see #removeIf(Predicate)
 *  Getting the elements:
 *  	@see #getAt(int)
 *  	@see #getFirst()
 *  	@see #getLast()
 *  Getting some other information:
 *  	@see #size()
 *  	@see #isEmpty()
 *  Getting iterator:
 *  	@see #iterator()
 */
public class LinkedList<E> implements Iterable<E> {
	/**
	 * First node of the list
	 */
	private LinkedListNode<E> first;
	/**
	 * Last node of the list
	 */
	private LinkedListNode<E> last;

	/**
	 * Constructor
	 * 
	 * Initialize empty collection
	 */
	LinkedList() {
		first = null;
		last = null;
	}

	/**
	 * Constructor that cope data from another iterable object
	 * @param iterable Iterable object
	 */
	LinkedList(Iterable<? extends E> iterable) {
		Iterator<? extends E> iter = iterable.iterator();
		while (iter.hasNext()) {
			E elem = iter.next();
			add(elem);
		}
	}

	/**
	 * Add data to the end of the list
	 * @param data Data 
	 */
	public void add(E data) {
		LinkedListNode<E> node = new LinkedListNode<>(data);
		if (isEmpty()) {
			first = node;
			last = node;
		} else {
			node.previous = last;
			last.next = node;
			last = node;
		}
	}

	/**
	 * Add all data from another LinkedList
	 * @param other Another LinkedList
	 */
	public void addAll(LinkedList<? extends E> other) {
		Iterator<? extends E> iter = other.iterator();
		while (iter.hasNext()) {
			add(iter.next());
		}
	}

	/**
	 * Add data to the top of the list (as the first element, as a head)
	 * @param data
	 */
	public void addFirst(E data) {
		LinkedListNode<E> node = new LinkedListNode<>(data);
		if (isEmpty()) {
			first = node;
			last = node;
		} else {
			node.next = first;
			first.previous = node;
			first = node;
		}
	}

	/**
	 * Clear the entire collection
	 */
	public void clear() {
		first = null;
		last = null;
	}

	/**
	 * Remove object
	 * @param obj Object to be removed from collection
	 * @return true, if was removed
	 */
	public boolean remove(final Object obj) {
		return removeIf(new Predicate<Object>() {
			@Override
			public boolean test(Object t) {
				return t == obj;
			}
		});
	}

	/**
	 * Remove the first element that correspond the filter
	 * @param filter Given filter
	 * @return true, if was removed
	 */
	public boolean removeIf(Predicate<? super E> filter) {
		if (isEmpty()) {
			return false;
		}
		LinkedListNode<E> temp = first;
		while (temp != null) {
			if (filter.test(temp.data)) {
				if (temp == first) {
					first = temp.next;
					temp.next = null;
					first.previous = null;
				} else if (temp == last) {
					last = temp.previous;
					temp.previous = null;
					last.next = null;
				} else {
					LinkedListNode<E> prev = temp.previous;
					LinkedListNode<E> next = temp.next;
					next.previous = prev;
					prev.next = next;
					temp.previous = null;
					temp.next = null;
				}
				return true;
			}
			temp = temp.next;
		}
		return false;
	}

	/**
	 * Get the first element (the head)
	 * 
	 * Delete this element from collection
	 * @return The link to the first element
	 */
	public E getFirst() {
		LinkedListNode<E> oldFirst = first;
		first = first.next;
		first.previous = null;
		oldFirst.next = null;
		return oldFirst.data;
	}

	/**
	 * Get the last element (the tail)
	 * 
	 * Delete this element from collection
	 * @return The link to the last element
	 */
	public E getLast() {
		LinkedListNode<E> oldLast = last;
		last = last.previous;
		last.next = null;
		oldLast.previous = null;
		return oldLast.data;
	}

	/**
	 * Get the element by the index
	 * 
	 * Delete this element from collection

	 * @param index Index of the element
	 * @return The link to the wanted element
	 * @throws IndexOutOfBoundsException
	 */
	public E getAt(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}

		Iterator<E> iter = this.iterator();
		// count = -1 because of the while in while we first of all get iter.next()
		// and for more readability in the top of the while we also increase count
		// so that the initial value must be -1 for the first iterator count variable to
		// be equal to 0
		int count = -1;
		while (iter.hasNext()) {
			E elem = iter.next();
			count++;
			if (count == index) {
				remove(elem);
				return elem;
			}
		}
		throw new IndexOutOfBoundsException();
	}

	/**
	 * Check if the collection is empty
	 * @return true, if empty
	 */
	public boolean isEmpty() {
		return (first == null || last == null);
	}

	/**
	 * Get iterator of the collection
	 * @see LinkedListIterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator<>(first);
	}

	/**
	 * Return size of the collection
	 * @return Integer size
	 */
	public int size() {
		int size = 0;
		Iterator<E> iter = this.iterator();
		while (iter.hasNext()) {
			size++;
		}
		return size;
	}

	/**
	 * Represent collection as a string
	 */
	@Override
	public String toString() {
		String str = "LinkedList object that holds next data: ";
		for (E elem : this) {
			str += elem.toString() + " ";
		}
		return str;
	}

}
