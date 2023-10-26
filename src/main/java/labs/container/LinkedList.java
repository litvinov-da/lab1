package labs.container;

import java.util.Iterator;
import java.util.function.Predicate;

public class LinkedList<E> implements Iterable<E> {

	private LinkedListNode<E> first;
	private LinkedListNode<E> last;
	
	LinkedList()
	{
		first = null;
		last = null;
	}

	LinkedList(Iterable<? extends E> iterable)
	{
		Iterator<? extends E> iter = iterable.iterator();
		while(iter.hasNext()) {
			E elem = iter.next();
			append(elem);
		}
	}
	
	public void append(E data)
	{
		LinkedListNode<E> node = new LinkedListNode<>(data);
		if(isEmpty()) {
			first = node;
			last = node;
		} else {
			node.previous = last;
			last = node;
		}
	}
	
	public void appendAll(LinkedList<? extends E> other)
	{
		Iterator<? extends E> iter = other.iterator();
		while(iter.hasNext()) {
			append(iter.next());
		}
	}
	
	// TODO: write push method
	public void clear()
	{
		first = null;
		last = null;
	}
	
	public boolean remove(Object obj)
	{
		if(isEmpty()) {
			return false;
		}
		LinkedListNode<E> temp = first;
		while(temp.next != null) {
			if(temp == obj) {
				if(temp == first) {
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
			} // TODO: rewrite using remove
			temp = temp.next;
		}
		return false;
	}
	// TODO: doc out remove only the first occurred element
	public boolean removeIf(Predicate<? super E> filter)
	{
		Iterator<E> iter = this.iterator();
		while(iter.hasNext()) {
			E elem = iter.next();
			if(filter.test(elem)) {
				iter.remove(); // TODO: handle if iter is first or last or smth like that
				// maybe extract this shit to special function
				return true;
			}
		}
		return false;
	}
	
	public E getFirst()
	{
		LinkedListNode<E> oldFirst = first;
		first = first.next; // TODO: extract shit of working with nodes to static shit in linkedlistnode
		first.previous = null;
		oldFirst.next = null;
		return oldFirst.data;
	}
	
	public E getLast()
	{
		LinkedListNode<E> oldLast = last;
		last = last.previous;
		last.next = null;
		oldLast.previous = null;
		return oldLast.data;
	}
	
	public E getAt(int index)
	{
		// TODO: write check if index is negative
		Iterator<E> iter = this.iterator();
		int count = -1;
		while(iter.hasNext()) {
			E elem = iter.next();
			count++;
			if(count == index) {
				return elem;
			}
		}
		throw new IndexOutOfBoundsException();
	}
	
	public boolean isEmpty()
	{
		return true;
	}
	
	@Override
	public Iterator<E> iterator()
	{
		return new LinkedListIterator<>(first);
	}
	
	public int size()
	{
		int size = 0;
		Iterator<E> iter = this.iterator();
		while(iter.hasNext()) {
			size++;
		}
		return size;
	}
	
	
	
}

