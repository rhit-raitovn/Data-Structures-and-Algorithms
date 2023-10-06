import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.swing.text.html.HTMLDocument.Iterator;


/**
 * This program runs various sorts and gathers timing information on them.
 *
 * @author <<Naziia Raitova>>
 *         Created May 18, 2023.
 */
public class SortRunner {
	private static Random rand = new Random(); // uses a fixed seed for debugging. Remove the parameter later.
	
	/**
	 * Starts here.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// array size must be an int. You will need to use something much larger
		int size = 1000*1000; 
		
		// Each integer will have the range from [0, maxValue). If this is significantly higher than size, you
		// will have small likelihood of getting duplicates.
		int maxValue = Integer.MAX_VALUE; 
		
		// Test 1: Array of random values.
		int[] randomIntArray = getRandomIntArray(size, maxValue);
		runAllSortsForOneArray(randomIntArray);
		
		// DONE: Tests 2-4
		// Generate the three other types of arrays (shuffled, almost sorted, almost reverse sorted)
		// and run the sorts on those as well.

		int[] shuffledIntArray = getUniqueElementArray(size);
		runAllSortsForOneArray(shuffledIntArray);

		int[] almostSortedIntArray = getAlmostSortedIntArray(size);
		runAllSortsForOneArray(almostSortedIntArray);

		int[] almostSortedReverseIntArray = getAlmostSortedReverseIntArray(size);
		runAllSortsForOneArray(almostSortedReverseIntArray);
	}

	/**
	 * 
	 * Runs all the specified sorts on the given array and outputs timing results on each.
	 *
	 * @param array
	 */
	private static void runAllSortsForOneArray(int[] array) {
		long startTime, elapsedTime; 
		boolean isSorted = false;

		// DONE: Read this.
		// We prepare the arrays. This can take as long as needed to shuffle items, convert
		// back and forth from ints to Integers and vice-versa, since you aren't timing this 
		// part. You are just timing the sort itself.
		
		int[] sortedIntsUsingDefaultSort = array.clone();
		Integer[] sortedIntegersUsingDefaultSort = copyToIntegerArray(array);
		Integer[] sortedIntegersUsingHeapSort = sortedIntegersUsingDefaultSort.clone();
		Integer[] sortedIntegersUsingTreeSort = sortedIntegersUsingDefaultSort.clone();
		int[] sortedIntsUsingQuickSort = array.clone();

		int size = array.length;
		
		// What is the default sort for ints? Read the javadoc.
		startTime = System.currentTimeMillis();  
		Arrays.sort(sortedIntsUsingDefaultSort); 
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntsUsingDefaultSort);
		displayResults("int", "the default sort", elapsedTime, size, isSorted);
		
		// What is the default sort for Integers (which are objects that wrap ints)?
		startTime = System.currentTimeMillis();  
		Arrays.sort(sortedIntegersUsingDefaultSort); 
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntegersUsingDefaultSort);
		displayResults("Integer", "the default sort", elapsedTime, size, isSorted);

		// Sort using the following methods, and time and verify each like done above. 
		// DONE: a simple sort that uses a TreeSet but handles a few duplicates gracefully. 
		
		startTime = System.currentTimeMillis();
		TreeSetSort.sort(sortedIntegersUsingTreeSort);
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntegersUsingTreeSort);
		displayResults("Integer", "TreeSetSort", elapsedTime, size, isSorted);
		
		// DONE: your implementation of quick sort. I suggest putting this in a static method in a Quicksort class.
		
		startTime = System.currentTimeMillis();
		QuickSort.sort(sortedIntsUsingQuickSort);
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntsUsingQuickSort);
		displayResults("int", "QuickSort", elapsedTime, size, isSorted);
		
		// DONE: your BinaryHeap sort. You can put this sort in a static method in another class. 
		
		startTime = System.currentTimeMillis();
		BinaryHeap.sort(sortedIntegersUsingHeapSort);
		elapsedTime = (System.currentTimeMillis() - startTime);
		isSorted = verifySort(sortedIntegersUsingHeapSort);
		displayResults("Integers", "HeapSort", elapsedTime, size, isSorted);
		
	}
	

	private static void displayResults(String typeName, String sortName, long elapsedTime, int size,  boolean isSorted) {
		if (isSorted) {
			System.out.printf("Sorted %.1e %ss using %s in %d milliseconds\n", (double)size, typeName, sortName, elapsedTime);
		} else {
			System.out.println("ARRAY NOT SORTED");
		}
	}
	
	/**
	 * Checks in O(n) time if this array is sorted.
	 *
	 * @param a An array to check to see if it is sorted.
	 */
	private static boolean verifySort(int[] a) {
		int previous = Integer.MIN_VALUE;
		for (int elem : a) {
			if (elem < previous) 
				return false;
			previous = elem;
		}
		return true;
	}

	/**
	 * Checks in O(n) time if this array is sorted.
	 *
	 * @param a An array to check to see if it is sorted.
	 */
	private static boolean verifySort(Integer[] a) {
		Integer previous =  null;
		for (int elem : a) {
			if (previous != null && elem < previous) 
				return false;
			previous = elem;
		}
		return true;
	}

	/**
	 * Copies from an int array to an Integer array.
	 *
	 * @param randomIntArray
	 * @return A clone of the primitive int array, but with Integer objects.
	 */
	private static Integer[] copyToIntegerArray(int[] ints) {
		Integer[] integers = new Integer[ints.length];
		for (int i = 0; i < ints.length; i++) {
			integers[i] = ints[i];
		}
		return integers;
	}

	/**
	 * Creates and returns an array of random ints of the given size.
	 *
	 * @return An array of random ints.
	 */
	private static int[] getRandomIntArray(int size, int maxValue) {
		int[] a = new int[size];
		for (int i = 0; i < size; i++) {
			a[i] = rand.nextInt(maxValue);
		}
		return a;
	}

	/**
	 * Creates a shuffled random array.
	 *
	 * @param size
	 * @return An array of the ints from 0 to size-1, all shuffled
	 */
	private static int[] getUniqueElementArray(int size) {
        int[] array = new int[size];
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        java.util.Iterator<Integer> iterator = list.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            array[index++] = iterator.next();
        }
        return array;
    }
	
	/**
	 * Creates and returns an array with ints 0 to size-1 in order with size/100 swaps
	 *
	 * @param size the size of the array
	 * @return An array of the ints from 0 to size-1, with a few swaps
	 */
	private static int[] getAlmostSortedIntArray(int size) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = i;
		}
		return swapping(array);
	}

	/**
	 * Creates and returns an array with ints size-1 to 0 in order with size/100 swaps
	 *
	 * @param size the size of the array
	 * @return An array of the ints from size-1 to 0, with a few swaps
	 */
	private static int[] getAlmostSortedReverseIntArray(int size) {
		int[] array = new int[size];
		for (int i = 0; i < size; i++) {
			array[i] = size - i - 1;
		}
		return swapping(array);
	}

	/**
	 * Swaps elements in the array
	 *
	 * @param a the array
	 * @return array
	 */	
	private static int[] swapping(int[] a) {
        Random rand = new Random();
        int numSwaps = a.length / 100;
        for (int r = 0; r < numSwaps; r++) {
            int index1 = rand.nextInt(a.length);
            int index2 = rand.nextInt(a.length);
            while (index1 == index2) {
            	index2 = rand.nextInt(a.length);
            }
            int item = a[index1];
            a[index1] = a[index2];
            a[index2] = item;
        }
        return a;
    }
	
}
