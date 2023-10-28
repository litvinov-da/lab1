package labs.container;

class LinkedListNode<T> {
	public LinkedListNode<T> previous;
	public LinkedListNode<T> next;
	public T data;

	LinkedListNode(T data) {
		previous = null;
		next = null;
		this.data = data;
	}
}