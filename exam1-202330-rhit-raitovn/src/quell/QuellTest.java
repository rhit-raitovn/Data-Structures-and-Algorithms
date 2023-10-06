package quell;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Test;

public class QuellTest {

	private static int points = 0;
	
	@SuppressWarnings("unchecked")
	private Quell<Integer> quellExampleInteger() {
		Quell<Integer> quellInt = new Quell<Integer>(null);
		LinkedList<Integer> listToAdd = new LinkedList<Integer>();
		
		listToAdd.add(1);
		listToAdd.add(2);
		listToAdd.add(3);
		quellInt.enqueue((LinkedList<Integer>) listToAdd.clone());
		listToAdd.clear();
		listToAdd.add(4);
		listToAdd.add(5);
		listToAdd.add(6);
		listToAdd.add(7);
		quellInt.enqueue((LinkedList<Integer>) listToAdd.clone());
		listToAdd.clear();
		listToAdd.add(8);
		quellInt.enqueue((LinkedList<Integer>) listToAdd.clone());
		listToAdd.clear();
		quellInt.enqueue((LinkedList<Integer>) listToAdd.clone());
		listToAdd.add(9);
		listToAdd.add(10);
		quellInt.enqueue((LinkedList<Integer>) listToAdd.clone());

		// quellInt.toString() would return: "[[1, 2, 3], [4, 5, 6, 7], [8], [], [9, 10]]"
		return quellInt;
	}
	
	@SuppressWarnings("unchecked")
	private Quell<String> quellExampleString() {
		Quell<String> quellStr = new Quell<String>(null);
		LinkedList<String> listToAdd = new LinkedList<String>();
		
		listToAdd.add("A");
		listToAdd.add("B");
		listToAdd.add("C");
		quellStr.enqueue((LinkedList<String>) listToAdd.clone());
		listToAdd.clear();
		listToAdd.add("D");
		quellStr.enqueue((LinkedList<String>) listToAdd.clone());
		listToAdd.clear();
		listToAdd.add("E");
		listToAdd.add("F");
		listToAdd.add("G");
		listToAdd.add("H");
		listToAdd.add("I");
		listToAdd.add("J");
		listToAdd.add("K");
		quellStr.enqueue((LinkedList<String>) listToAdd.clone());

		// quellStr.toString() would return: "[[A, B, C], [D], [E, F, G, H, I, J, K]]"
		return quellStr;
	}
	
	@Test
	public void testGetSpecificList() {
		Quell<Integer> quellInt = quellExampleInteger();
		assertEquals("[1, 2, 3]", quellInt.getSpecificList(0).toString());
		assertEquals("[[1, 2, 3], [4, 5, 6, 7], [8], [], [9, 10]]", quellInt.toString());
		points += 1;
		
		assertEquals("[4, 5, 6, 7]", quellInt.getSpecificList(1).toString());
		assertEquals("[[1, 2, 3], [4, 5, 6, 7], [8], [], [9, 10]]", quellInt.toString());
		
		assertEquals("[8]", quellInt.getSpecificList(2).toString());
		assertEquals("[[1, 2, 3], [4, 5, 6, 7], [8], [], [9, 10]]", quellInt.toString());
		
		assertEquals("[]", quellInt.getSpecificList(3).toString());
		assertEquals("[[1, 2, 3], [4, 5, 6, 7], [8], [], [9, 10]]", quellInt.toString());
		
		assertEquals("[9, 10]", quellInt.getSpecificList(4).toString());
		assertEquals("[[1, 2, 3], [4, 5, 6, 7], [8], [], [9, 10]]", quellInt.toString());
		points += 5;
	}
	
	@Test
	public void testDequeue() {
		Quell<Integer> quellInt = quellExampleInteger();
		
		assertEquals("[1, 2, 3]", quellInt.dequeue().toString());
		assertEquals("[[4, 5, 6, 7], [8], [], [9, 10]]", quellInt.toString());
		
		assertEquals("[4, 5, 6, 7]", quellInt.dequeue().toString());
		assertEquals("[[8], [], [9, 10]]", quellInt.toString());
		
		assertEquals("[8]", quellInt.dequeue().toString());
		assertEquals("[[], [9, 10]]", quellInt.toString());
		
		assertEquals("[]", quellInt.dequeue().toString());
		assertEquals("[[9, 10]]", quellInt.toString());
		
		assertEquals("[9, 10]", quellInt.dequeue().toString());
		assertEquals("[]", quellInt.toString());
		
		points += 6;
	}
	
	@Test
	public void testDequeueAndSize() {
		Quell<Integer> quellInt = quellExampleInteger();
		
		assertEquals(5, quellInt.queueSize());
		
		assertEquals("[1, 2, 3]", quellInt.dequeue().toString());
		assertEquals("[[4, 5, 6, 7], [8], [], [9, 10]]", quellInt.toString());
		assertEquals(4, quellInt.queueSize());		
		
		assertEquals("[4, 5, 6, 7]", quellInt.dequeue().toString());
		assertEquals("[[8], [], [9, 10]]", quellInt.toString());
		assertEquals(3, quellInt.queueSize());
		
		assertEquals("[8]", quellInt.dequeue().toString());
		assertEquals("[[], [9, 10]]", quellInt.toString());
		assertEquals(2, quellInt.queueSize());
		
		assertEquals("[]", quellInt.dequeue().toString());
		assertEquals("[[9, 10]]", quellInt.toString());
		assertEquals(1, quellInt.queueSize());
		
		assertEquals("[9, 10]", quellInt.dequeue().toString());
		assertEquals("[]", quellInt.toString());
		assertEquals(0, quellInt.queueSize());
		
		points += 6;
	}
	
	@Test
	public void testNumElements() {
		Quell<Integer> quellInt = quellExampleInteger();
		assertEquals(10, quellInt.numElements());
		assertEquals("[[1, 2, 3], [4, 5, 6, 7], [8], [], [9, 10]]", quellInt.toString());
		
		points += 2;
		
		Quell<String> quellStr = quellExampleString();
		assertEquals(11, quellStr.numElements());
		assertEquals("[[A, B, C], [D], [E, F, G, H, I, J, K]]", quellStr.toString());
		
		points += 2;
	}
	
	@Test
	public void testNumInList() {
		Quell<Integer> quellInt = quellExampleInteger();
		assertEquals(3, quellInt.numInList(0));
		assertEquals("[[1, 2, 3], [4, 5, 6, 7], [8], [], [9, 10]]", quellInt.toString());
		
		quellInt = quellExampleInteger(); //resetting it
		assertEquals(4, quellInt.numInList(1));
		assertEquals("[[1, 2, 3], [4, 5, 6, 7], [8], [], [9, 10]]", quellInt.toString());
		
		quellInt = quellExampleInteger(); //resetting it
		assertEquals(1, quellInt.numInList(2));
		assertEquals("[[1, 2, 3], [4, 5, 6, 7], [8], [], [9, 10]]", quellInt.toString());
		
		quellInt = quellExampleInteger(); //resetting it
		assertEquals(0, quellInt.numInList(3));
		assertEquals("[[1, 2, 3], [4, 5, 6, 7], [8], [], [9, 10]]", quellInt.toString());
		
		quellInt = quellExampleInteger(); //resetting it
		assertEquals(2, quellInt.numInList(4));
		assertEquals("[[1, 2, 3], [4, 5, 6, 7], [8], [], [9, 10]]", quellInt.toString());
		
		points += 4;
	}
	
	@Test
	public void testElementPeek() {
		Quell<String> quellStr = quellExampleString();
		assertEquals("A", quellStr.elementPeek(0));
		assertEquals("[[A, B, C], [D], [E, F, G, H, I, J, K]]", quellStr.toString());
		
		assertEquals("D", quellStr.elementPeek(1));
		assertEquals("[[A, B, C], [D], [E, F, G, H, I, J, K]]", quellStr.toString());
		
		assertEquals("E", quellStr.elementPeek(2));
		assertEquals("[[A, B, C], [D], [E, F, G, H, I, J, K]]", quellStr.toString());
		
		points += 2;
		
		Quell<Integer> quellInt = quellExampleInteger();
		assertEquals(null, quellInt.elementPeek(3));
		assertEquals("[[1, 2, 3], [4, 5, 6, 7], [8], [], [9, 10]]", quellInt.toString());
		
		points += 1;
	}
	
	@Test
	public void testElementRemove() {
		Quell<String> quellStr = quellExampleString();
		assertEquals("D", quellStr.elementRemove(1, 0));
		assertEquals("[[A, B, C], [], [E, F, G, H, I, J, K]]", quellStr.toString());
		
		quellStr = quellExampleString(); //resetting it
		assertEquals("F", quellStr.elementRemove(2, 1));
		assertEquals("[[A, B, C], [D], [E, G, H, I, J, K]]", quellStr.toString());
		
		quellStr = quellExampleString(); //resetting it
		assertEquals("C", quellStr.elementRemove(0, 2));
		assertEquals("[[A, B], [D], [E, F, G, H, I, J, K]]", quellStr.toString());
		
		points += 3;
		
		quellStr = quellExampleString(); //resetting it
		assertEquals("E", quellStr.elementRemove(2, 0));
		assertEquals("[[A, B, C], [D], [F, G, H, I, J, K]]", quellStr.toString());
		assertEquals("F", quellStr.elementRemove(2, 0));
		assertEquals("[[A, B, C], [D], [G, H, I, J, K]]", quellStr.toString());
		assertEquals("G", quellStr.elementRemove(2, 0));
		assertEquals("[[A, B, C], [D], [H, I, J, K]]", quellStr.toString());
		
		quellStr = quellExampleString(); //resetting it
		assertEquals("H", quellStr.elementRemove(2, 3));
		assertEquals("[[A, B, C], [D], [E, F, G, I, J, K]]", quellStr.toString());
		assertEquals("I", quellStr.elementRemove(2, 3));
		assertEquals("[[A, B, C], [D], [E, F, G, J, K]]", quellStr.toString());
		assertEquals("F", quellStr.elementRemove(2, 1));
		assertEquals("[[A, B, C], [D], [E, G, J, K]]", quellStr.toString());
		assertEquals("G", quellStr.elementRemove(2, 1));
		assertEquals("[[A, B, C], [D], [E, J, K]]", quellStr.toString());
		assertEquals("J", quellStr.elementRemove(2, 1));
		assertEquals("[[A, B, C], [D], [E, K]]", quellStr.toString());
		assertEquals("K", quellStr.elementRemove(2, 1));
		assertEquals("[[A, B, C], [D], [E]]", quellStr.toString());
		assertEquals("E", quellStr.elementRemove(2, 0));
		assertEquals("[[A, B, C], [D], []]", quellStr.toString());
		assertEquals("D", quellStr.elementRemove(1, 0));
		assertEquals("[[A, B, C], [], []]", quellStr.toString());
		assertEquals("C", quellStr.elementRemove(0, 2));
		assertEquals("[[A, B], [], []]", quellStr.toString());
		assertEquals("B", quellStr.elementRemove(0, 1));
		assertEquals("[[A], [], []]", quellStr.toString());
		assertEquals("A", quellStr.elementRemove(0, 0));
		assertEquals("[[], [], []]", quellStr.toString());
		
		points += 2;
		
	}
	
	@Test
	public void testMergeSimple() {
		Quell<Integer> quellInt = quellExampleInteger();
		
		quellInt.merge(1, 0);
		assertEquals("[[1, 2, 3, 4, 5, 6, 7], [8], [], [9, 10]]", quellInt.toString());
		
		quellInt = quellExampleInteger(); //resetting it
		quellInt.merge(2, 0);
		assertEquals("[[1, 2, 3, 8], [4, 5, 6, 7], [], [9, 10]]", quellInt.toString());
		
		quellInt = quellExampleInteger(); //resetting it
		quellInt.merge(4, 0);
		assertEquals("[[1, 2, 3, 9, 10], [4, 5, 6, 7], [8], []]", quellInt.toString());
				
		points += 2;
	}
	
	@Test
	public void testMergeEmpty() {
		Quell<Integer> quellInt = quellExampleInteger();
		
		//[[1, 2, 3], [4, 5, 6, 7], [8], [], [9, 10]]
		
		quellInt.merge(3, 0);
		assertEquals("[[1, 2, 3], [4, 5, 6, 7], [8], [9, 10]]", quellInt.toString());
		
		quellInt = quellExampleInteger(); //resetting it
		quellInt.merge(0, 3);
		assertEquals("[[4, 5, 6, 7], [8], [1, 2, 3], [9, 10]]", quellInt.toString());
		
		points += 1;
	}
	
	@Test
	public void testMergeMultipleTimes() {
		Quell<Integer> quellInt = quellExampleInteger();
		
		quellInt.merge(1, 0);
		assertEquals("[[1, 2, 3, 4, 5, 6, 7], [8], [], [9, 10]]", quellInt.toString());
		
		quellInt.merge(1, 0);
		assertEquals("[[1, 2, 3, 4, 5, 6, 7, 8], [], [9, 10]]", quellInt.toString());
		
		quellInt.merge(2, 0);
		assertEquals("[[1, 2, 3, 4, 5, 6, 7, 8, 9, 10], []]", quellInt.toString());
		
		points += 2;
	}
	
	@Test
	public void testMergeVarious() {
		Quell<Integer> quellInt = quellExampleInteger();
		
		quellInt.merge(1, 2);
		assertEquals("[[1, 2, 3], [8, 4, 5, 6, 7], [], [9, 10]]", quellInt.toString());
		
		quellInt = quellExampleInteger(); //resetting it
		quellInt.merge(4, 0);
		assertEquals("[[1, 2, 3, 9, 10], [4, 5, 6, 7], [8], []]", quellInt.toString());
		quellInt.merge(3, 0);
		assertEquals("[[1, 2, 3, 9, 10], [4, 5, 6, 7], [8]]", quellInt.toString());
		quellInt.merge(1, 2);
		assertEquals("[[1, 2, 3, 9, 10], [8, 4, 5, 6, 7]]", quellInt.toString());
		
		points += 1; 
		
	}
	

	@AfterClass
	public static void showPoints() {
		System.out.printf("QueLL points: %d/40\n", points);
	}
}
