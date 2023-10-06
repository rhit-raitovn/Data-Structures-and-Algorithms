import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

/**
 * Tests binary heaps.
 * 
 * @author Matt Boutell. Created May 7, 2013.
 * @author Nate Chenette. Edited 2019, 2020.
 */
public class BinaryHeapTest {

	/**
	 * Simple test method for insert, delete, toString, and sort. Enforces the
	 * method signatures.
	 */
	@Test
	public void testSimple() {
		// DONE: Implement the BinaryHeap class according to the spec.

		/*
		 * CONSIDER: Since we're implementing the heap's internal storage
		 * using a primitive array (and not an ArrayList), like we did on
		 * GrowableCircularArrayQueue from the StacksAndQueues assignment,
		 * we need to pass in to the constructor a parameter that tells what
		 * class it is.
		 */
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Integer.class);
		
		// deleteMin returns null if it has no elements.
		assertNull(heap.deleteMin());
		heap.insert(21);
		assertEquals("[null, 21]", heap.toString());
		assertEquals(Integer.valueOf(21), heap.deleteMin());
		heap.insert(30);
		heap.insert(15);
		heap.insert(45);
		assertEquals(Integer.valueOf(15), heap.deleteMin());
		assertEquals(Integer.valueOf(30), heap.deleteMin());
		assertEquals(Integer.valueOf(45), heap.deleteMin());
		assertEquals("[null]", heap.toString());
		String[] csLegends = new String[] { "Edsger Dijkstra", "Grace Hopper", "Donald Knuth", "Ada Lovelace",
				"Claude Shannon", "Alan Turing" };
		BinaryHeap.sort(csLegends, String.class);
		assertEquals("[Ada Lovelace, Alan Turing, Claude Shannon, Donald Knuth, Edsger Dijkstra, Grace Hopper]",
				Arrays.toString(csLegends));
	}

	// TODO: Add unit tests for each method until you are satisfied your code is
	// correct. I will test your code with more complex tests. Since this
	// assignment is short, I'd like you to give the tests some thought, rather
	// than just always relying on someone else's tests. Professional developers
	// usually write their own unit tests.
	
	public void myTest() {
		BinaryHeap<Integer> heap = new BinaryHeap<Integer>(Integer.class);
		
		
		String[] names = new String[] { "Elena", "Catherine", "David", "Ben", "Frank", "Adam"};
		BinaryHeap.sort(names, String.class);
		assertEquals("[Adam, Ben, Catherine, David, Elena, Frank]",
				Arrays.toString(names));
	}
}
