import java.lang.reflect.Array;

/**
 * Binary Heaps.
 * 
 * @author Naziia Raitova. Created May 15, 2023.
 */
public class BinaryHeap<T extends Comparable<? super T>> {

	public T[] array;
	public Class<?> classType;
	public int size;
	public int capacity;
	public int resizeCount;
	public int swapCount;
	
	public BinaryHeap(Class<?> class1) {
		this.capacity = 10;
		this.classType = class1;
		this.array = (T[]) Array.newInstance(this.classType, this.capacity + 1);
        this.capacity = 10;
        this.size = 0;
        this.resizeCount = 0;
        this.swapCount = 0;
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
        if (size >= capacity - 1) {
            capacity *= 2;
            T[] newArray = (T[]) Array.newInstance(classType, capacity);
            System.arraycopy(array, 0, newArray, 0, size + 1);
            resizeCount++;
            array = newArray;
        }
        int index = size + 1;
        array[index] = item;
        size++;
        swim(index);
    }

    private void swim(int index) {
        while (index > 1 && array[index / 2].compareTo(array[index]) > 0) {
            swap(index, index / 2);
            index /= 2;
        }
    }

    private void swap(int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        swapCount++;
    }

	public String toString() {
		String result = "null" + (this.size != 0 ? ", " : "");
		for (int i = 0; i < this.size; i++) {
			result += this.array[i + 1] + (i + 1 != this.size ? ", " : "");
		}
		return "[" + result + "]";
	}
	
	public static void sort(Comparable[] array) {
        int size = array.length;

        // Build the initial binary heap
        for (int index = size / 2 - 1; index >= 0; index--) {
        	sortHelper(array, index, size);
        }

        // Perform the sorting
        for (int position = size - 1; position >= 0; position--) {
            Comparable<?> swap = array[position];
            array[position] = array[0];
            array[0] = swap;
            sortHelper(array, 0, position);
        }
    }

    private static void sortHelper(Comparable[] array, int index, int size) {
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;
        int largest = index;

        if (leftChild < size && array[leftChild].compareTo(array[largest]) > 0) {
            largest = leftChild;
        }

        if (rightChild < size && array[rightChild].compareTo(array[largest]) > 0) {
            largest = rightChild;
        }

        if (largest != index) {
            Comparable<?> swap = array[index];
            array[index] = array[largest];
            array[largest] = swap;
            sortHelper(array, largest, size);
        }
    }
}