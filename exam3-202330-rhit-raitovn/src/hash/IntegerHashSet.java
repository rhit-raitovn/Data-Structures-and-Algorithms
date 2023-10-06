package hash;

import java.util.Arrays;

/**
 * 
 * A simplified-for-exam-purposes hash set implementation for Integers 
 * using linear probing and no array resizing/rehashing. Cannot insert
 * null into the set. Other requirements are given with each method.
 * 
 */
public class IntegerHashSet {
	private int mSize;
	private IntegerAndFlag[] mArray;
	private int mCapacity;
	
	private class IntegerAndFlag {
		//Do not modify. Use this class in your solution
		public Integer data;
		public boolean deleted;
		
		IntegerAndFlag(Integer value) {
			data = value;
			deleted = false;
		}
		
		@Override
		public String toString() {
			if (this.data == null) 
				return "(null-" + this.deleted + ")";
			return "(" + this.data.toString() + "-" + this.deleted + ")";
		}
	}

	/**
	 * Creates a Hash Set with the given capacity.
	 */
	public IntegerHashSet(int initialCapacity) {
		mCapacity = initialCapacity;
		mArray = new IntegerAndFlag[mCapacity];
		//Initialize each cell with an empty IntegerAndFlag
		for (int i = 0; i < initialCapacity; i++) { 
			mArray[i] = new IntegerAndFlag(null);
		}
		mSize = 0;
	}
	
	/**
	 * See Document for more details.
	 */
	public boolean remove(Integer item) {
		int index = indexOf(item);
		if (!contains(item))
			return false;
		mArray[index].data = null;
		mArray[index].deleted = true;
		return true;
	}
	
	public int indexOf(Integer item) {
		int index = 0;
		for (IntegerAndFlag i : mArray) {
			if (i.data != null)
				if (i.data.equals(item))
					return index;
			index++;
		}
		return -1;
	}
	
	/**
	 * See Document for more details.
	 */
	public boolean contains(Integer item) {
		if (indexOf(item) == -1)
			return false;
		
		return true;
	}
	
	/**
	 * Adds a new node if it is not there already. Do linear-probing to resolve
	 * collisions. No re-hashing, so if the array fills, this will give an infinite loop
	 * @param item
	 * @return true if the item was successfully added (that is, if that hash table
	 *         was modified as a result of this call), false otherwise.
	 */
	public boolean add(Integer item) {
		// Note that you may NOT modify this method. Required for the tests to work.
		int index = item % mCapacity;
		while (mArray[index % mCapacity].data != null) {
			index++;
		}
		mArray[index % mCapacity] = new IntegerAndFlag(item);
		mSize++;
		return true;
	}

	// You may use these methods in your implementation of contains and remove.
	// However, there are no test cases that check these remain valid after remove.
	// You are responsible for ensuring they are correct.
	public int size() {
		return mSize;
	}

	// You may use these methods in your implementation of contains and remove.
	// However, there are no test cases that check these remain valid after remove.
	// You are responsible for ensuring they are correct.
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public String toString() {
		return Arrays.toString(mArray);
	}

}
