package labs.container;

import java.util.Iterator;
import java.util.function.Predicate;

public class LinkedList<E> implements Iterable<E> {

	private LinkedListNode<E> first;
	private LinkedListNode<E> last;

	LinkedList() {
		first = null;
		last = null;
	}

	LinkedList(Iterable<? extends E> iterable) {
		Iterator<? extends E> iter = iterable.iterator();
		while (iter.hasNext()) {
			E elem = iter.next();
			add(elem);
		}
	}

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

	public void addAll(LinkedList<? extends E> other) {
		Iterator<? extends E> iter = other.iterator();
		while (iter.hasNext()) {
			add(iter.next());
		}
	}

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

	public void clear() {
		first = null;
		last = null;
	}

	public boolean remove(final Object obj) {
		return removeIf(new Predicate<Object>() {
			@Override
			public boolean test(Object t) {
				return t == obj;
			}
		});
	}

	// TODO: doc out remove only the first occurred element
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

	public E getFirst() {
		LinkedListNode<E> oldFirst = first;
		first = first.next;
		first.previous = null;
		oldFirst.next = null;
		return oldFirst.data;
	}

	public E getLast() {
		LinkedListNode<E> oldLast = last;
		last = last.previous;
		last.next = null;
		oldLast.previous = null;
		return oldLast.data;
	}

	public E getAt(int index) {
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}

		Iterator<E> iter = this.iterator();
		// count = -1 because of the while in while we first of all get iter.next()
		// and for more readability in the top of the while we also increase count
		// so that the initial value must be -1 for the first iterator count variable to be equal to 0
		int count = -1;
		while (iter.hasNext()) {
			E elem = iter.next();
			count++;
			if (count == index) {
				return elem;
			}
		}
		throw new IndexOutOfBoundsException();
	}

	public boolean isEmpty() {
		return (first == null || last == null);
	}

	@Override
	public Iterator<E> iterator() {
		return new LinkedListIterator<>(first);
	}

	public int size() {
		int size = 0;
		Iterator<E> iter = this.iterator();
		while (iter.hasNext()) {
			size++;
		}
		return size;
	}

	@Override
	public String toString() {
		String str = "LinkedList object that holds next data: ";
		for (E elem : this) {
			str += elem.toString() + " ";
		}
		return str;
	}

}
