package heap;

import java.util.ArrayList;

public class Heap {

	// Note:
	// For this exam question, we are setting the INITIAL_CAPACITY large
	// so that we never have to grow the array
	// All JUnit tests are written to not violate the heap's array capacity
	private static final int INITIAL_CAPACITY = 100;
	
	public int[] heap;
	public int heapSize;
	
	public Heap(int[] initialMinHeapData, int initialHeapSize) {
		this.heap = new int[INITIAL_CAPACITY];
		this.heapSize = initialHeapSize;
		
		// Note: we are observing the convention of having the heap begin at index = 1
		for (int k = 1; k <= initialHeapSize; k++) {
			this.heap[k] = initialMinHeapData[k];
		}
	}
	
	/*
	 * See written document for more details
	 */
	public int[] calculateHeapRank() {
		
		ArrayList<Integer> array= new ArrayList<Integer>();
		int[] arr = new int[this.heapSize];
		int rank = this.heapSize/2;
		int size = this.heapSize + 0;
		
		int index = 1;
		int adder = 1;
		int times = 1;
		arr[1] = rank + 1;
		int middle = size;
		for (int t = middle; t < this.heapSize; t++) {
			arr[t] = 0;
		}
		while (index < 4) {

			middle = middle/2;
			for (int t = middle/2 + 1; t < middle; t++) {
				arr[t] = adder;
			}
			middle = middle/2;
			adder = adder+2;
			index++;
		}
		return arr;
	}	
		
	
	// The following method(s) are required for tests to run correctly and should not be changed.
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (this.heapSize == 0) {
			return "[]";
		} else {
			sb.append("[");
			for (int k = 1; k <= this.heapSize; k++) {
				sb.append(this.heap[k]);
				sb.append(", ");
			}
			sb.delete(sb.length()-2, sb.length());
			sb.append("]");
			return sb.toString();
		}
	}

}
