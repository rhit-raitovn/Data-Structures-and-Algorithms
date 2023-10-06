package quell;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import quack.Quack.Node;


public class Quell<T extends Comparable<T>> {
	private Queue<LinkedList<T>> quell;
	private ArrayList<LinkedList<T>> helper;

	public Quell(T obj) {
		this.quell = new LinkedList<LinkedList<T>>();
		this.helper = new ArrayList<LinkedList<T>>();
	}

	@Override
	public String toString() {
		// Don't change. We give this to make unit tests easier to write and understand.
		if (quell.isEmpty()) 
			return "[]";
		return quell.toString();
	}

	public void enqueue(LinkedList<T> list) {
		// Don't change. We give this so the unit tests will work.
		quell.add(list);
	}
	
	public LinkedList<T> getSpecificList(int queueIndex) {		
		transfer();
		LinkedList<T> ret = helper.get(queueIndex);
		this.quell.addAll(helper);
		this.helper = new ArrayList<LinkedList<T>>();
		return ret;
	}

	public LinkedList<T> dequeue() {
		transfer();
		LinkedList<T> remove = this.helper.get(0);
		this.helper.remove(0);
		this.quell = new LinkedList<LinkedList<T>>();
		this.quell.addAll(helper);
		this.helper = new ArrayList<LinkedList<T>>();
		return remove;
	}

	public int queueSize() {
		int count = 0;
		transfer();
		for(LinkedList<T> l : this.helper) {
			count++;
		}
		this.quell.addAll(helper);
		this.helper = new ArrayList<LinkedList<T>>();
		return count;
	}

	public int numElements() {
		int count = 0;
		transfer();
		for(LinkedList<T> l : this.helper) {
			for(T t : l) {
				count++;
			}
		}
		this.quell.addAll(helper);
		this.helper = new ArrayList<LinkedList<T>>();
		return count;
	}

	public int numInList(int queueIndex) {
		int count = 0;
		transfer();
		LinkedList<T> l = this.helper.get(queueIndex);
		for(T t : l) {
			count++;
		}
		this.quell.addAll(helper);
		this.helper = new ArrayList<LinkedList<T>>();
		return count;
	}

	public T elementPeek(int queueIndex) {
		transfer();
		T ret = null;
		try {
			ret = this.helper.get(queueIndex).get(0);
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		this.quell.addAll(helper);
		this.helper = new ArrayList<LinkedList<T>>();
		return ret;
	}

	public T elementRemove(int queueIndex, int listIndex) {
		transfer();
		T ret = null;
		ret = this.helper.get(queueIndex).remove(listIndex);
		
		this.quell.addAll(helper);
		this.helper = new ArrayList<LinkedList<T>>();
		return ret;
	}

	public void merge(int queueIndexFrom, int queueIndexTo) {
		//TODO: Write this method
				
	}
	
	private void transfer() {
		LinkedList<T>element = new LinkedList<T>();
		int size = this.quell.size();
		for(int i = 0; i < size; i++) {
			element = this.quell.remove();
			this.helper.add(element);
		}
//		return helper;
	}

}
