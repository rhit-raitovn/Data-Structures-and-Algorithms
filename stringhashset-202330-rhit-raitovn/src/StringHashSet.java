import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * A hash set implementation for Strings. Cannot insert null into the set. Other
 * requirements are given with each method.
 *
 * @author Matt Boutell and Naziia Raitova. Created April 29, 2023.
 */
public class StringHashSet implements Iterable<String> {

	// The initial size of the internal array.
	private static final int DEFAULT_CAPACITY = 5;
	private static final int HC = Integer.MAX_VALUE + 1;

	private int size;
	private int capacity;
	private Node[] arrOfNodes;

	/**
	 * A LinkedList<String> designed for the StringHashSet program
	 */
	static class Node implements Iterable<String> {
		private String data;
		private Node next;

		public Node() {
		}

		public Node(String data) {
			this.data = data;
		}

		/**
		 * Adds a new item to the LinkedList
		 * @param item
		 * @return true if the addition was successful
		 */
		public boolean add(String item) {
			// making a new node from the data
			Node node = new Node(this.data);
			if (this.data != null) {
				node.next = this.next;
				this.next = node;
			}
			this.data = item;
			return true;
		}

		/**
		 * Removes an item from the LinkedList
		 * @param item 
		 * @return true if the removal was successful
		 */
		public boolean remove(String item) {
			// making sure we're deleting the right node
			if (this.data.equals(item)) {
				if (this.next == null) {
					this.data = null;
					this.next = null;
				} else {
					this.data = this.next.data;
					this.next = this.next.next;
				}
				return true;
			} 
			
			else {
				// using recursion to remove the 
				if (this.next != null) {
					if (this.next.data.equals(item)) {
						this.next = this.next.next;
						return true;
					}
					else return this.next.remove(item);
				}
			}
			return false;
		}

		/**
		 * Returns true if the LL has an item
		 * @param item 
		 * @return true if the item exists in LL
		 */
		public boolean get(String item) {
			if (this.data == null) 
				return false;
			if (this.data.equals(item)) 
				return true;
			if (this.next == null)
				return false;
			return this.next.get(item);
		}

		/**
		 * Wraps around and puts the item to the front
		 * 
		 * @param item 
		 * @return true if we added the item successfully
		 */
		public boolean wrapAround(String item) {
			return this.remove(item) && this.add(item);
		}

		public Iterator<String> iterator() {
			return new NodeIterator(this);
		}

		private class NodeIterator implements Iterator<String> {
			Node node;

			NodeIterator(Node node) {
				this.node = node;
			}

			@Override
			public boolean hasNext() {
				return this.node != null;
			}

			@Override
			public String next() throws NoSuchElementException {
				if (!this.hasNext()) 
					throw new NoSuchElementException();
				String data = this.node.data;
				this.node = this.node.next;
				return data;
			}
		}
	}

	/**
	 * Creates a Hash Set with the default capacity.
	 */
	public StringHashSet() {
		// Recall that using this as a method calls another constructor
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Creates a Hash Set with the given capacity.
	 */
	public StringHashSet(int initialCapacity) {
		initialize(initialCapacity);
	}

	private void initialize(int initialCapacity) {
		this.size = 0;
		this.capacity = initialCapacity;
		this.arrOfNodes = new Node[this.capacity];
	}

	/**
	 * Calculates the hash code for Strings, using the x=31*x + y pattern.
	 * Follow the specification in the String.hashCode() method in the Java API.
	 * Note that the hashcode can overflow the max integer, so it can be
	 * negative. Later, in another method, you'll need to account for a negative
	 * hashcode by adding Integer.MAX_VALUE + 1 before you mod by the capacity
	 * (table size) to get the index.
	 *
	 * This method is NOT the place to calculate the index though.
	 *
	 * @param item
	 * @return The hash code for this String
	 */
	public static int stringHashCode(String item) {
		int hashCode = 0;
		for (char c : item.toCharArray()) {
			hashCode = hashCode * 31 + c;
		}
		return hashCode;
	}

	/**
	 * Adds a new node if it is not there already. If there is a collision, then
	 * add a new node to the -front- of the linked list.
	 *
	 * Must operate in amortized O(1) time, assuming a good hashcode function.
	 *
	 * If the number of nodes in the hash table would be over double the
	 * capacity (that is, lambda > 2) as a result of adding this item, then
	 * first double the capacity and then rehash all the current items into the
	 * double-size table.
	 *
	 * @param item
	 * @return true if the item was successfully added (that is, if that hash
	 *         table was modified as a result of this call), false otherwise.
	 */
	public boolean add(String item) {
		int hashCode = StringHashSet.stringHashCode(item);
		
		if (hashCode < 0) 
			hashCode += Integer.MAX_VALUE + 1;
		int index = hashCode % this.capacity;
		
		if (this.arrOfNodes[index] == null) 
			this.arrOfNodes[index] = new Node();
		
		// helped by Ben 230TA
		// if the item does not exist in the array
		if (this.arrOfNodes[index].get(item)) {
			return false;
		}
		if (this.size >= this.capacity * 2) {
			StringHashSet hashSet = new StringHashSet(this.capacity * 2);
			for (int i = 0; i < this.capacity; i++) {
				if (this.arrOfNodes[i] != null) {
					for (String s : this.arrOfNodes[i]) {
						hashSet.add(s);
					}
				}
			}
			this.arrOfNodes = hashSet.arrOfNodes;
			this.capacity *= 2;
			index = hashCode % this.capacity;
			if (this.arrOfNodes[index] == null) 
				this.arrOfNodes[index] = new Node();
		}
		this.arrOfNodes[index].add(item);
		this.size++;
		return true;

	}

	/**
	 * Prints an array value on each line. Each line will be an array index
	 * followed by a colon and a list of Node data values, ending in null. For
	 * example, inserting the strings in the testAddSmallCollisions example
	 * gives "0: shalom hola null 1 bonjour null 2 caio hello null 3 null 4 hi
	 * null". Use a StringBuilder, so you can build the string in O(n) time.
	 * (Repeatedly concatenating n strings onto a growing string gives O(n^2)
	 * time)
	 *
	 * @return A slightly-formatted string, mostly used for debugging
	 */
	public String toRawString() {

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < this.capacity; i++) {
			builder.append(i);//append syntax
			builder.append(':');
			if (this.arrOfNodes[i] != null) {
				Node current = this.arrOfNodes[i];
				while (current != null) {
					builder.append(' ');
					if (current.data != null)
						builder.append(current.data);
					current = current.next;
				}
			}
			builder.append(' ');
			builder.append("null");
			builder.append("\n");
		}
		return builder.toString();
	}

	/**
	 *
	 * Checks if the given item is in the hash table.
	 *
	 * Must operate in O(1) time, assuming a good hashcode function.
	 *
	 * @param item
	 * @return True if and only if the item is in the hash table.
	 */
	public boolean contains(String item) {
		int hashCode = StringHashSet.stringHashCode(item);
		if (hashCode < 0) 
			hashCode += HC;
		int index = hashCode % this.capacity;
		if (this.arrOfNodes[index] != null)
			return this.arrOfNodes[index].wrapAround(item);
		return false;
	}

	/**
	 * Returns the number of items added to the hash table. Must operate in O(1)
	 * time.
	 *
	 * @return The number of items in the hash table.
	 */
	public int size() {
		return this.size;
	}

	/**
	 * @return True iff the hash table contains no items.
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Removes all the items from the hash table. Resets the capacity to the
	 * DEFAULT_CAPACITY
	 */
	public void clear() {
		initialize(DEFAULT_CAPACITY);
	}

	/**
	 * Removes the given item from the hash table if it is there. You do NOT
	 * need to resize down if the load factor decreases below the threshold.
	 *
	 * @param item
	 * @return True If the item was in the hash table (or equivalently, if the
	 *         table changed as a result).
	 */
	public boolean remove(String item) {
		int hashCode = StringHashSet.stringHashCode(item);
		if (hashCode < 0) 
			hashCode += HC;
		int index = hashCode % this.capacity;
		boolean removed = false;
		if (this.arrOfNodes[index] != null)
			removed = this.arrOfNodes[index].remove(item);
		if (removed) {
			this.size--;
		}
		return removed;
	}

	/**
	 * Adds all the items from the given collection to the hash table.
	 *
	 * @param collection
	 * @return True if the hash table is modified in any way.
	 */
	public boolean addAll(Collection<String> collection) {
		boolean added = false;
		for (String item : collection) 
			added = this.add(item);
		return added;
	}

	/**
	 *
	 * Challenge Feature: Returns an iterator over the set. Return the items in
	 * any order that you can do efficiently. Should throw a
	 * NoSuchElementException if there are no more items and next() is called.
	 * Should throw a ConcurrentModificationException if next() is called and
	 * the set has been mutated since the iterator was created.
	 *
	 * @return an iterator.
	 */
	public Iterator<String> iterator() {
		return null;
	}

	// Challenge Feature: If you have an iterator, this is easy. Use a
	// StringBuilder, so you can build the string in O(n) time. (Repeatedly
	// concatenating n strings onto a string gives O(n^2) time)
	// Format it like any other Collection's toString (like [a, b, c])
	@Override
	public String toString() {
		return null;
	}

}