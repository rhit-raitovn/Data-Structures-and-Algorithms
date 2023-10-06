package priorityQueue;
import java.util.ArrayList;

/**
 * An implementation of the Priority Queue interface using an array list.
 * 
 * @author Matt Boutell and <<TODO: Naziia Raitova>>>. Created Mar 29, 2014.
 * 
 * @param <T>
 *            Generic type of PQ elements
 */
public class ArrayListMinPQ<T extends Comparable<T>> {
	// Requirement: The methods below must use the items list.
	// You may not add other fields to this ArrayListMinPQ class, 
	// but you may add local variables to the methods. 
	private ArrayList<T> items;

	public ArrayListMinPQ() {
		this.items = new ArrayList<T>();
	}

	public T findMin() {
		// This is also known as peekMin
		// TODO: implement
		if (this.items.size() == 0) 
			return null;
		T min = this.items.get(0);
		for (T t : this.items) {
			if (t.compareTo(min) < 1) 
				min = t;
		}
		return min;
	}

	public T deleteMin() {
		if (this.items.size() == 0) 
			return null;
		T min = this.items.get(0);
		for (T t : this.items) {
			if (t.compareTo(min) < 1) 
				min = t;
		}
		int i = this.items.indexOf(min);
		this.items.remove(i);
		return min;
	}

	public void insert(T item) {
		this.items.add(item);   // done
	}

	public int size() {
		return this.items.size(); // done
	}

	public boolean isEmpty() {
		return this.items.isEmpty(); // done
	}

	public void clear() {
		this.items.clear(); // done
	}
}
