package labs.container;

import java.util.Iterator;

class LinkedListIterator<E> implements Iterator<E> {
	private LinkedListNode<E> current;
	
	LinkedListIterator(LinkedListNode<E> current)
	{
		this.current = current;
	}
	

	@Override
	public boolean hasNext() {
		return current != null && current.next != null;
	}

	@Override
	public E next() {
		E data = current.data;
		current = current.next;
		return data;
	}
	
	@Override
	public void remove() {
		if(current.previous == null) {
			current.next.previous = null;
		} else if (current.next == null) {
			current.previous.next = null;
		} else {
			LinkedListNode<E> prev = current.previous;
			LinkedListNode<E> next = current.next;
			next.previous = prev;
			prev.next = next;
		}
		current.next = null;
		current.previous = null;
	}
}