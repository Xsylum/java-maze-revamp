public class List<T> {
	
	private class Node<E> {
		
		private Node previous;
		private Node next;
		private E value;
		
		private Node(Node previous, Node next, E value) {
			this.previous = previous;
			this.next = next;
			this.value = value;
		}
	}
	
	private Node<T> head;
	private int length;
	
	public List() {
		head = new Node<T>(null, null, null);
		length = 0;
	}
	
	public void addFront(T value) {
		Node<T> node = new Node<T>(head, head.next, value);
		
		head.next.previous = node;
		head.next = node;
		
		length++;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
		Node pointer = head;
		s.append("(");
		
		while (pointer.next.value != null) {
			s.append(pointer.next.value);
			s.append(",");
			pointer = pointer.next;
		}
		
		s.append(")");
		return s.toString();
		
	}
}