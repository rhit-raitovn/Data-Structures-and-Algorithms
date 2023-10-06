package list;

import static org.junit.Assert.fail;

/**
 * 
 * @author anderson
 * 
 * @param <T>
 *            Any Comparable type
 * 
 *            A linked list whose elements are kept in sorted order.
 */
public class SortedLinkedList<T extends Comparable<T>> extends
		DoublyLinkedList<T> {

	/**
	 * Create an empty list
	 * 
	 */
	public SortedLinkedList() {
		super();
	}

	/**
	 * Creates a sorted list containing the same elements as the parameter
	 * 
	 * @param list
	 *            the input list
	 */
	public SortedLinkedList(DoublyLinkedList<T> list) {
		super();
		DLLIterator<T> i = list.iterator();
		while (i.hasNext()) {
			this.add(i.next());
		}
	}

	/**
	 * Adds the given element to the list, keeping it sorted.
	 */
	@Override
	public void add(T element) {
		DLLIterator<T> i = this.iterator();
		Node current = this.head.next;
		try {
			if (!i.hasNext()) 
				this.head.addAfter(element);
			else if(element.compareTo(current.data) <= 0) {
				this.head.addAfter(element);
			}
			else {				
				while (current.next!=this.tail) {
						if (element.compareTo(current.next.data) <= 0) {
							break;
						} 	
						current = current.next;
				}	
				current.addAfter(element);
			}
		} catch (UnsupportedOperationException e) {
			throw e;
		}
	}

	@Override
	public void addFirst(T element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addLast(T element) {
		throw new UnsupportedOperationException();
	}
}
