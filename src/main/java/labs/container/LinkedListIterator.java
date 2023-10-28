package labs.container;

import java.util.Iterator;

class LinkedListIterator<E> implements Iterator<E> {
	private LinkedListNode<E> current;
	private LinkedListNode<E> thrown;

	LinkedListIterator(LinkedListNode<E> current) {
		this.current = current;
	}

	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public E next() {
		E data = current.data;
		thrown = current;
		current = current.next;
		return data;
	}

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