package heap;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.Test;


// Test class
public class HeapTest {

	private static int heapPoints = 0;
	
	
	@Test
	public void testEmpty() {
		Heap heap = new Heap(new int[]{9999}, 0); // empty tree; 9999 in position 0 since we don't care about it.
		assertEquals("[0]", Arrays.toString(heap.calculateHeapRank()));
		heapPoints += 1;
	}
	
	@Test
	public void testOneElement() {
		
		Heap heap = new Heap(new int[]{9999, 17}, 1); 
		assertEquals("[0, 0]", Arrays.toString(heap.calculateHeapRank())); 
		heapPoints += 1;
		//FIXME: *****Does the extra zero in front bother you?
	}
	
	@Test
	public void testHeightOfOne() {
		
		Heap heap = new Heap(new int[]{9999, 17, 50, 45}, 3); 
		assertEquals("[0, 1, 0, 0]", Arrays.toString(heap.calculateHeapRank()));
		heapPoints += 1;
	}
	
	@Test
	public void testFiveElements() {
		
		Heap heap = new Heap(new int[]{9999, 11, 20, 30, 40, 50}, 5);
		assertEquals("[0, 3, 1, 0, 0, 0]", Arrays.toString(heap.calculateHeapRank()));
		heapPoints += 2;
	}
	
	@Test
	public void testSixRandomElements() {
		
		Heap heap = new Heap(new int[]{9999, 12, 32, 21, 44, 55, 29}, 6);
		assertEquals("[0, 3, 1, 1, 0, 0, 0]", Arrays.toString(heap.calculateHeapRank()));
		heapPoints += 2;
	}
	
	
	@Test
	public void testNineElements() {
		
		Heap heap = new Heap(new int[]{9999, 1, 2, 3, 4, 5, 6, 7, 8, 9}, 9);
		assertEquals("[0, 5, 3, 1, 1, 0, 0, 0, 0, 0]", Arrays.toString(heap.calculateHeapRank()));
		heapPoints += 2;
	}
		
	@Test
	public void test18Elements() {
		
		Heap heap = new Heap(new int[]{9999, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18}, 18);
		assertEquals("[0, 10, 6, 3, 3, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0]", Arrays.toString(heap.calculateHeapRank()));
		heapPoints += 3;
	}
		
	@Test
	public void test24ElementsRandomOrder() {
		
		Heap heap = new Heap(new int[]{9999, 10, 15, 12, 18, 20, 19, 16, 30, 26, 25, 28, 40, 45, 21, 23, 35, 32, 80, 27, 26, 35, 35, 35, 42}, 24);
		assertEquals("[0, 15, 7, 4, 3, 3, 2, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]", Arrays.toString(heap.calculateHeapRank()));
		heapPoints += 3;
	}	
	
	@AfterClass
	public static void displayPoints() {
		System.out.println("----------------------------------------------------------------------");
		System.out.printf("2. %2d/15  heap points\n", heapPoints);
	}
}
