package queues;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

/**
 * A circular queue that grows as needed.
 * 
 * @author Matt Boutell and <<<Ruth Hammond, Naziia Raitova>>>
 * @param <T>
 */
public class GrowableCircularArrayQueue<T> implements SimpleQueue{
	// DONE: Declare this class to implement SimpleQueue<T>, then add the
	// missing methods (Eclipse will help).
	// DONE: The javadoc for overridden methods is in the SimpleQueue interface.
	// Read it!

	private static final int INITIAL_CAPACITY = 5;

	private T[] array;
	private Class<T> type;
	private int input;
	private int output;
	
	private int count;
	private int[] order;
	private int orderIndex;

	/**
	 * Creates an empty queue with an initial capacity of 5
	 * 
	 * @param type
	 *            So that an array of this type can be constructed.
	 */
	@SuppressWarnings("unchecked")
	public GrowableCircularArrayQueue(Class<T> type) {
		this.type = type;
		// This is a workaround due to a limitation Java has with
		// constructing generic arrays.
		this.array = (T[]) Array.newInstance(this.type, INITIAL_CAPACITY);
		this.order = new int[this.array.length*2];
		this.orderIndex = 0;
		
		this.input = 0;
		this.output = 0;
		this.count = 0;
		
	}

	/**
	 * Displays the contents of the queue in insertion order, with the
	 * most-recently inserted one last, in other words, not wrapped around. Each
	 * adjacent pair will be separated by a comma and a space, and the whole
	 * contents will be bounded by square brackets. See the unit tests for
	 * examples.
	 */
	@Override
	public String toString() {
		int j = this.output; // j is the first element of the queue
		int index = 1; 	// keeps track of the number of items we have indexed through
		if (this.isEmpty()) 
			return "[]";
		String str = "[";
		
		while(index <= this.size()) {
			// if current element is valid -> add to String
			if (j < this.array.length && this.array[j] != null)
				str += this.array[j] + ", ";
//			
			// wraps around
			if(j+1 == this.array.length)
				j=0;
			else if((this.array[j+1] == null && this.array[0] != null))
				j=0;
			else
				j++;
			
			index++;
		}
	
		return str.substring(0, str.length() - 2) + "]";
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		this.array = (T[]) Array.newInstance(this.type, INITIAL_CAPACITY);
		this.count = 0;
		this.input = 0;
		this.output = 0;
	}

	@Override
	public void enqueue(Object item) {
		// if the array is full then we double the capacity of the current array
		if (this.isFull()) {
			int current_capacity = this.array.length;
			@SuppressWarnings("unchecked")
			T[] newArr = (T[]) Array.newInstance(this.type, current_capacity*2);
			
			// copies current elements in order into new array 
			// starting at index 0 of new array
			int numElements = 0;
			int index = this.output;
			while(numElements < this.size()) {
				newArr[numElements] = this.array[index];
				
				if (index >= this.array.length-1) {
					index = 0;
				} else {
					index++;
				}
				numElements++;
			}
				
			this.array = newArr;
			this.input = current_capacity;
			this.output = 0;
		
		}
		
		// wraps around 
		else if (this.input >= this.array.length)
			this.input = 0;
		
		// inputs item into queue
		this.array[this.input] = (T) item;
		this.orderIndex++;
		
		// moves input marker 
		if(!this.isFull() && this.input < this.array.length)
			this.input++;
		
		this.count++;
	}    

	@Override
	public Object dequeue() throws NoSuchElementException {
			if(this.size() == 0)
				throw new NoSuchElementException();
			
			this.count--;

			T res = this.array[this.output];
			this.array[this.output] = null;
			
			// moves output marker
			this.output++;
			return res;
	}

	@Override
	public Object peek() throws NoSuchElementException {
		if(this.size() == 0)
			throw new NoSuchElementException();
		
		return this.array[0];
	}

	@Override
	public boolean isEmpty() {
		return this.input == 0 && this.output == 0;
	}

	@Override
	public int size() {
		return this.count;
	}

	@Override
	public boolean contains(Object item) {
		for (int i = 0; i < this.array.length; i++) 
			if (this.array[i]!= null && this.array[i].equals(item))
				return true;
		return false;
	}
	
	private boolean isFull()  {
		Boolean bool1 = !this.isEmpty();
		Boolean bool2 = this.input == this.array.length;
		Boolean bool3 = this.output == 0;

		// checks if (1) queue is not empty and needs to be doubled
		return ((!this.isEmpty() && this.input == this.array.length && this.output == 0) || (this.count != 0 && this.input == this.output));
	}

}

