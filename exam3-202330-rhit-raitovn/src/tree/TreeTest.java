package tree;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Test;

public class TreeTest {

	private static int points = 0;
	
	private void loadUpBst(BinarySearchTree bst1, Integer a1[]) {
		for (int k = 0; k < a1.length; k++) {
			bst1.insert(a1[k]);
		} 	
	}
		
	@Test
	public void testSimpleDegenerateCases() {
		BinarySearchTree t = new BinarySearchTree();
		assertEquals(0, t.numHeightDepthMatch());
		points += 1;
		//    1
		//     \
		//      2
		//       \
		//        3
		t.insert(1);
		assertEquals(1, t.numHeightDepthMatch());
		points += 1;
		t.insert(2);
		assertEquals(0, t.numHeightDepthMatch());
		points += 1;
		t.insert(3);
		assertEquals(1, t.numHeightDepthMatch());
		points += 1;
		t.insert(4);
		assertEquals(0, t.numHeightDepthMatch());
		t.insert(5);
		assertEquals(1, t.numHeightDepthMatch());
		points += 1;
		t.insert(6);
		assertEquals(0, t.numHeightDepthMatch());
		t.insert(7);
		assertEquals(1, t.numHeightDepthMatch());
		points += 1;
		
	} 
	
	
	@Test
	public void testFullTree1() {
		BinarySearchTree t = new BinarySearchTree();
		//      	  20
		//         /      \
		//        /        \
		//      10          30
		//     /  \        /  \
		//    5    15     25   35
		t.insert(20);		
		t.insert(10);		
		t.insert(30);
		assertEquals(0, t.numHeightDepthMatch());
		points += 2;
		t.insert(5);		
		assertEquals(1, t.numHeightDepthMatch());
		t.insert(15);		
		assertEquals(1, t.numHeightDepthMatch());
		t.insert(25);
		assertEquals(2, t.numHeightDepthMatch());
		t.insert(35);
		assertEquals(2, t.numHeightDepthMatch());
		points += 3;
		
		//      	  20
		//         /      \
		//        /        \
		//      10          30
		//     /  \        /  \
		//    5    15     25   35
		//   / \  / \    / \   / \
		//  1  7 12 17  22 28 33 39
		
		t.insert(39);
		assertEquals(1, t.numHeightDepthMatch());
		t.insert(28);		
		assertEquals(1, t.numHeightDepthMatch());
		t.insert(33);		
		assertEquals(1, t.numHeightDepthMatch());
		t.insert(22);		
		assertEquals(1, t.numHeightDepthMatch());
		t.insert(17);		
		assertEquals(0, t.numHeightDepthMatch());
		t.insert(1);		
		assertEquals(0, t.numHeightDepthMatch());
		t.insert(7);		
		assertEquals(0, t.numHeightDepthMatch());
		t.insert(12);		
		assertEquals(0, t.numHeightDepthMatch());
		points += 2;
		
	} 
	
	@Test
	public void testFullTree2() {
		BinarySearchTree t = new BinarySearchTree();
		//      	          20
		//              /             \
		//            /                 \
		//         10                     30
		//       /     \              /        \
		//      5       15           25         35
		//    /   \    /   \        /   \      /   \
		//   1    7   12    17     22   28    33    39
		//  / \  / \  / \   / \   / \   / \   / \   / \
		// 0  2 6  8 11 13 16 18 21 23 26 29 31 34 38 40
		
		final Integer treeVals[] = { 20, 10, 30, 5, 15, 25, 35, 1, 7, 12, 17, 22, 28, 33, 39 }; // Same as Trees1 final example
		this.loadUpBst(t, treeVals);
		
		t.insert(6);
		assertEquals(1, t.numHeightDepthMatch());
		t.insert(11);
		assertEquals(2, t.numHeightDepthMatch());
		t.insert(26);
		assertEquals(3, t.numHeightDepthMatch());
		t.insert(34);
		assertEquals(4, t.numHeightDepthMatch());
		points += 2;
		
		final Integer bottomRow[] = {0, 2, 8, 13, 16, 18, 21, 23, 29, 31, 38, 40}; // Rest of the bottom row
		this.loadUpBst(t, bottomRow);
		assertEquals(4, t.numHeightDepthMatch());
		points += 1; 
	} 
	
	@Test
	public void testNonFullTreesOfVaryingHeights() {
		BinarySearchTree t = new BinarySearchTree();
		//          10
		//        /    \
		//       /      \
		//      /        \
		//     5          20
		//    / \        / 
		//   1   7      15
		//        \    /  
		//         8  12
		final Integer allBut12[] = {10, 5, 20, 1, 7, 15, 8}; // Everything above except 12
		this.loadUpBst(t, allBut12);
		
		assertEquals(1, t.numHeightDepthMatch());
		t.insert(12);
		assertEquals(0, t.numHeightDepthMatch());
		points += 2;
		
		//          10
		//        /     \
		//       /       \
		//      /         \
		//     5           20
		//    / \        /    \
		//   1   7      15     24
		//        \    /  \    / \
		//         8  12   17 22  26
		//		       \           \
		//	     		13		    30
		//		     			     \
		//			     			  36
		//				     		   \
		//					     	    40
		t.insert(13);
		assertEquals(1, t.numHeightDepthMatch());
		t.insert(17);
		t.insert(24);
		t.insert(22);
		t.insert(26);
		assertEquals(1, t.numHeightDepthMatch());
		t.insert(30);
		assertEquals(2, t.numHeightDepthMatch());
		t.insert(36);
		assertEquals(1, t.numHeightDepthMatch());
		t.insert(40);
		assertEquals(2, t.numHeightDepthMatch());
		
		points += 2;
	} 
	



	@AfterClass
	public static void displayPoints() {
		System.out.printf("***  numHeightDepthMatch unit test points:        " + "%2d / 20\n", points);
		System.out.printf("     numHeightDepthMatch efficiency test points:  ?? / 15\n");
		System.out.printf("   The 15 additional efficiency points will be checked by your instructor.\n\n");
	}
}