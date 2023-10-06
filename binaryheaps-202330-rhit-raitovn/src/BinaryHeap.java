import java.lang.reflect.Array;

/**
 * Binary Heaps.
 * 
 * @author Naziia Raitova. Created May 15, 2023.
 */
public class BinaryHeap<T extends Comparable<? super T>> {
	public static int initialCapacity = 8;

	public T[] array;
	public Class<?> classType;
	public int size;
	public int capacity;
	public int resizeCount;
	public int swapCount;
	
	public BinaryHeap(Class<?> class1) {
		this.capacity = BinaryHeap.initialCapacity;
		this.classType = class1;
		this.array = (T[]) Array.newInstance(this.classType, this.capacity + 1);
	}

	public T deleteMin() {
		if (this.size == 0) return null;
		T item = this.array[1];
		int index = 1;
		T last = this.array[this.size];
		this.array[index] = last;
		this.array[this.size] = null;
		while (index <= (this.size-1) / 2) {
			T left = this.array[index * 2];
			T right = this.array[index * 2 + 1];
			if (right == null) {
				if (last.compareTo(left) > 0) {
					this.array[index] = left;
					this.array[index * 2] = last;
					this.swapCount ++;
				}
				break;
			}
			if (last.compareTo(left) < 0 && last.compareTo(right) < 0) break;
			else if (left.compareTo(right) < 0) {
				this.array[index] = left;
				this.array[index * 2] = last;
				this.swapCount ++;
				index *= 2;
			} else {
				this.array[index] = right;
				this.array[index * 2 + 1] = last;
				this.swapCount ++;
				index = index * 2 + 1;
			}
		}
		this.size--;
		return item;
	}

	/**
	 * Add a new item to the heap. Automatically gets put in the correct location.
	 * @param item the new item being added to the heap
	 */
	public void insert(T item) {
		if (this.size >= this.capacity) {
			this.capacity *= 2;
			T[] array = (T[]) Array.newInstance(this.classType, this.capacity + 1);
			System.arraycopy(this.array, 1, array, 1, this.size);
			this.resizeCount++;
			this.array = array;
		}
		int index = this.size + 1;
		this.array[index] = item;
		this.size++;
		while (index > 1 && this.array[index / 2].compareTo(item) > 0) {
			this.array[index] = this.array[index / 2];
			this.array[index / 2] = item;
			this.swapCount++;
			index /= 2;
		}
	}


	public String toString() {
		String output = "null" + (this.size != 0 ? ", " : "");
		for (int i = 0; i < this.size; i++) output += this.array[i + 1] + (i + 1 != this.size ? ", " : "");
		return "[" + output + "]";
	}

	public static void sort(Comparable[] array) {
		int size = array.length;
		for (int index = size / 2 - 1; index >= 0; index--) BinaryHeap.percolateDown(array, index, size);
		for (int position = size - 1; position >= 0; position--) {
			Comparable swap = array[position];
			array[position] = array[0]; array[0] = swap;
			BinaryHeap.percolateDown(array, 0, --size);
		}
	}

	private static void percolateDown(Comparable[] array, int index, int size) {
		while (index <= size / 2 - 1) {
			Comparable item = array[index];
			Comparable left = 2 * (index + 1) - 1 < size ? array[2 * (index + 1) - 1] : null;
			Comparable right = 2 * (index + 1) < size ? array[2 * (index + 1)] : null;
			if (right == null) {
				if (item.compareTo(left) < 0) {
					array[index] = left;
					array[2 * (index + 1) - 1] = item;
				} break;
			} else if (item.compareTo(left) > 0 && item.compareTo(right) > 0) break;
			else if (left.compareTo(right) > 0) {
				array[index] = left;
				array[2 * (index + 1) - 1] = item;
				index = 2 * (index + 1) - 1;
			} else {
				array[index] = right;
				array[2 * (index + 1)] = item;
				index = 2 * (index + 1);
			}
		}
	}

	public static void sort(Comparable[] array, Class<?> classType) {
		BinaryHeap.sort(array);
	}

}