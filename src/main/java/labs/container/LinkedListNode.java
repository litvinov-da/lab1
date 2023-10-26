package labs.container;

class LinkedListNode<T> {
	public LinkedListNode<T> previous;
	public LinkedListNode<T> next;
	public T data;
	// todo: write builder
	LinkedListNode(T data)
	{
		previous = null;
		next = null;
		this.data = data;
	}
}