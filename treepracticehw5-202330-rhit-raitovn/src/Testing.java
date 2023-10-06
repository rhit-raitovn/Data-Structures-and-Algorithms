import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;


/**
 * @author Matt Boutell
 */
public class Testing {

	private static int totalPoints = 0;
	private static int sumOfHeightsPoints = 0;
	private static int isHeightBalancedPoints = 0;
	private static int makeTreePoints = 0;

	@Test
	public void testSumOfHeights() {
		BinarySearchTree b = new BinarySearchTree();
		assertEquals(0, b.getSumOfHeights());
		b.insert(20);
		assertEquals(0, b.getSumOfHeights());
		b.insert(10);
		assertEquals(1, b.getSumOfHeights());
		b.insert(30);
		assertEquals(1, b.getSumOfHeights());
		sumOfHeightsPoints += 1;
		b.insert(40);
		assertEquals(3, b.getSumOfHeights());
		sumOfHeightsPoints += 1;
		b.insert(38);
		assertEquals(6, b.getSumOfHeights());
		sumOfHeightsPoints += 1;
		b.insert(5);
		assertEquals(7, b.getSumOfHeights());
		b.insert(15);
		assertEquals(7, b.getSumOfHeights());
		b.insert(25);
		assertEquals(7, b.getSumOfHeights());
		b.insert(50);
		assertEquals(7, b.getSumOfHeights());
		b.insert(2);
		assertEquals(9, b.getSumOfHeights());
		b.insert(22);
		assertEquals(10, b.getSumOfHeights());
		sumOfHeightsPoints += 2;
	}

	@Test
	public void testIsHB() {
		BinarySearchTree b = new BinarySearchTree();
		assertTrue(b.isHeightBalanced());
		isHeightBalancedPoints += 1;
		b.insert(30);
		assertTrue(b.isHeightBalanced());
		isHeightBalancedPoints += 1;
		b.insert(20);
		assertTrue(b.isHeightBalanced());
		b.insert(10);
		assertFalse(b.isHeightBalanced());
		isHeightBalancedPoints += 1;
		b.insert(40);
		assertTrue(b.isHeightBalanced());
		b.insert(50);
		assertTrue(b.isHeightBalanced());
		b.insert(5);
		assertFalse(b.isHeightBalanced());
		b.insert(25);
		assertTrue(b.isHeightBalanced());
		b.insert(60);
		assertFalse(b.isHeightBalanced());
		isHeightBalancedPoints += 2;
	}
	
	@Test
	public void testFullTreeWithDepthEmpty() {
		BinarySearchTree b = new BinarySearchTree(-1);
		assertEquals("", b.toStructuredString());
		makeTreePoints += 2;
	}

	@Test
	public void testFullTreeWithDepthRootOnly() {
		BinarySearchTree b = new BinarySearchTree(0);
		assertEquals("[0]", b.toStructuredString());
		makeTreePoints += 2;
	}

	@Test
	public void testFullTreeWithDepthHeightOfOne() {
		BinarySearchTree b = new BinarySearchTree(1);
		assertEquals("[[1]0[1]]", b.toStructuredString());
		makeTreePoints += 2;
	}

	@Test
	public void testFullTreeWithDepthHeightOfTwo() {
		BinarySearchTree b = new BinarySearchTree(2);
		assertEquals("[[[2]1[2]]0[[2]1[2]]]", b.toStructuredString());
		makeTreePoints += 2;
	}

	@Test
	public void testFullTreeWithDepthHeightOfThree() {
		BinarySearchTree b = new BinarySearchTree(3);
		assertEquals("[[[[3]2[3]]1[[3]2[3]]]0[[[3]2[3]]1[[3]2[3]]]]",
				b.toStructuredString());
		makeTreePoints += 2;
	}

	@AfterClass
	public static void displayPoints() {
		System.out.printf("1. sumHeights points: %d/5\n", sumOfHeightsPoints);
		System.out.printf("2. isHeightBalanced points: %d/5]\n", isHeightBalancedPoints);
		System.out.printf("3. makeFullTree points: %d/10]\n", makeTreePoints);
		totalPoints = sumOfHeightsPoints + isHeightBalancedPoints + makeTreePoints;
		System.out.printf("Correctness Points: %d/20\n", totalPoints);
		System.out.printf("Efficiency Points: TBD/5, by us looking at your code.\n");
	}
}