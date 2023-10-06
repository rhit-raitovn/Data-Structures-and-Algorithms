package bstStarter;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;




public class Testing {

	private static int numLeavesBelowDepthPoints = 0;
	private static int numEmptyChildrenOnPathPoints = 0;
	private static int createBalancePoints = 0;
	

	// *************************************************************
	// JUnit helper operations
	// *************************************************************
	
	private void loadUpBst(BinarySearchTree bst1, Integer a1[]) {
		for (int k = 0; k < a1.length; k++) {
			bst1.insert(a1[k]);
		} // end for		
	} // loadUpBst
	
	
	// -------------------------------------------------------------

	
	@Test
	public void testNumLeavesBelowDepthTest1() {
		BinarySearchTree b = new BinarySearchTree();
		final Integer treeVals[] = { 10, 5, 30, 35 }; // Simple
		this.loadUpBst(b, treeVals);
				
		assertEquals(2, b.numLeavesBelowDepth(-1));
		assertEquals(2, b.numLeavesBelowDepth(0));
		
		numLeavesBelowDepthPoints += 3;
		
		assertEquals(1, b.numLeavesBelowDepth(1));
		assertEquals(0, b.numLeavesBelowDepth(2));
		assertEquals(0, b.numLeavesBelowDepth(3));
		
		numLeavesBelowDepthPoints += 3;
	} 
	
	@Test
	public void testNumLeavesBelowDepthTest2() {
		BinarySearchTree b = new BinarySearchTree();
		final Integer treeVals[] = { 34, 22, 62, 13, 46, 87, 5, 35, 67, 93, 1, 9, 64, 79, 7, 86 }; // Example Tree 
		this.loadUpBst(b, treeVals);
		
		assertEquals(6, b.numLeavesBelowDepth(-1));
		
		numLeavesBelowDepthPoints += 2;
		
		assertEquals(6, b.numLeavesBelowDepth(0));
		assertEquals(6, b.numLeavesBelowDepth(1));
		assertEquals(6, b.numLeavesBelowDepth(2));
				
		numLeavesBelowDepthPoints += 2;
		
		assertEquals(4, b.numLeavesBelowDepth(3));
		
		numLeavesBelowDepthPoints += 2;
		
		assertEquals(2, b.numLeavesBelowDepth(4));
		
		numLeavesBelowDepthPoints += 2;
		
		assertEquals(0, b.numLeavesBelowDepth(5));
		assertEquals(0, b.numLeavesBelowDepth(6));
		assertEquals(0, b.numLeavesBelowDepth(7));
		assertEquals(0, b.numLeavesBelowDepth(8));
		
		numLeavesBelowDepthPoints += 2;
	} 
	
	@Test
	public void testNumEmptyChildrenOnPathTest1() {
		
		BinarySearchTree b = new BinarySearchTree();
		b.insert(10);
		assertEquals(2, b.numEmptyChildrenOnPath(10));
		
		numEmptyChildrenOnPathPoints += 1;
		
		assertEquals(2, b.numEmptyChildrenOnPath(5));
		assertEquals(2, b.numEmptyChildrenOnPath(15));
		
		numEmptyChildrenOnPathPoints += 1;
	}
	
	@Test
	public void testNumEmptyChildrenOnPathTest2() {
		BinarySearchTree b = new BinarySearchTree();
		final Integer treeVals[] = { 10, 5, 30, 35 }; // Simple
		this.loadUpBst(b, treeVals);
		
		//Finding a specific node that's in the tree
		assertEquals(0, b.numEmptyChildrenOnPath(10));
		assertEquals(2, b.numEmptyChildrenOnPath(5));
		assertEquals(1, b.numEmptyChildrenOnPath(30));
		assertEquals(3, b.numEmptyChildrenOnPath(35));
		
		numEmptyChildrenOnPathPoints += 2;
		
		//Looking for nodes that aren't in the tree
		assertEquals(2, b.numEmptyChildrenOnPath(1));
		assertEquals(2, b.numEmptyChildrenOnPath(6));
		assertEquals(1, b.numEmptyChildrenOnPath(20));
		assertEquals(3, b.numEmptyChildrenOnPath(31));
		assertEquals(3, b.numEmptyChildrenOnPath(36));
				
		numEmptyChildrenOnPathPoints += 2;
		

	}
	
	@Test
	public void testNumEmptyChildrenOnPathTest3() {
		BinarySearchTree b = new BinarySearchTree();
		final Integer treeVals[] = { 34, 22, 62, 13, 46, 87, 5, 35, 67, 93, 1, 9, 64, 79, 7, 86 }; // Example Tree 
		this.loadUpBst(b, treeVals);
		
		//Finding a specific node that's in the tree
		assertEquals(0, b.numEmptyChildrenOnPath(34));
		assertEquals(0, b.numEmptyChildrenOnPath(62));
		assertEquals(0, b.numEmptyChildrenOnPath(87));
		assertEquals(0, b.numEmptyChildrenOnPath(67));
		
		numEmptyChildrenOnPathPoints += 1;
		
		assertEquals(1, b.numEmptyChildrenOnPath(22));
		assertEquals(2, b.numEmptyChildrenOnPath(13));
		
		
		assertEquals(1, b.numEmptyChildrenOnPath(46));
		assertEquals(3, b.numEmptyChildrenOnPath(35));
		assertEquals(2, b.numEmptyChildrenOnPath(64));
		assertEquals(1, b.numEmptyChildrenOnPath(79));
		
		numEmptyChildrenOnPathPoints += 1;
		
		assertEquals(2, b.numEmptyChildrenOnPath(5));
		assertEquals(4, b.numEmptyChildrenOnPath(1));
		assertEquals(3, b.numEmptyChildrenOnPath(9));
		assertEquals(5, b.numEmptyChildrenOnPath(7));
		
		assertEquals(2, b.numEmptyChildrenOnPath(93));
		assertEquals(3, b.numEmptyChildrenOnPath(86));
		
		numEmptyChildrenOnPathPoints += 1;
		
		
	}
	
	@Test
	public void testNumEmptyChildrenOnPathTest4() {
		BinarySearchTree b = new BinarySearchTree();
		final Integer treeVals[] = { 34, 22, 62, 13, 46, 87, 5, 35, 67, 93, 1, 9, 64, 79, 7, 86 }; // Example Tree 
		this.loadUpBst(b, treeVals);
		
		//Looking for nodes that aren't in the tree
		assertEquals(1, b.numEmptyChildrenOnPath(23));
		assertEquals(2, b.numEmptyChildrenOnPath(14));
		
		numEmptyChildrenOnPathPoints += 1;
		
		assertEquals(1, b.numEmptyChildrenOnPath(47));
		assertEquals(3, b.numEmptyChildrenOnPath(36));
		
		assertEquals(2, b.numEmptyChildrenOnPath(94));
		assertEquals(2, b.numEmptyChildrenOnPath(92));
		
		assertEquals(2, b.numEmptyChildrenOnPath(63));
		assertEquals(2, b.numEmptyChildrenOnPath(65));
		
		assertEquals(1, b.numEmptyChildrenOnPath(78));
		assertEquals(3, b.numEmptyChildrenOnPath(85));
		
		numEmptyChildrenOnPathPoints += 2;
	}
	
	@Test
	public void testCreateBalanceTest1() {
		BinarySearchTree b = new BinarySearchTree();
		b.insert(10);
		b.createBalance(); //Nothing should happen
		assertEquals("(10)", b.toStructuredString());
		
		
		b.insert(5);
		b.createBalance(); //Add 15 to 10's right child
		assertEquals("((5)10(15))", b.toStructuredString());
		
		createBalancePoints += 2;
		
		b = new BinarySearchTree(); //Clear the tree
		b.insert(10);
		b.insert(30);
		b.createBalance();
		assertEquals("((-10)10(30))", b.toStructuredString());
		
		createBalancePoints += 2;
		
		BinarySearchTree b2 = new BinarySearchTree();
		final Integer treeVals[] = { 10, 5, 30, 35 }; // Simple
		this.loadUpBst(b2, treeVals);
		
		b2.createBalance();
		assertEquals("((5)10((25)30(35)))", b2.toStructuredString());
		
		createBalancePoints += 2;
		
		b2.createBalance(); //Nothing should happen
		assertEquals("((5)10((25)30(35)))", b2.toStructuredString());
		
		createBalancePoints += 2;
	}
	
	@Test
	public void testCreateBalanceTest2() {
		BinarySearchTree b = new BinarySearchTree();
		final Integer treeVals[] = { 10, 5, 30, 35 }; // Simple
		this.loadUpBst(b, treeVals);
		
		b.createBalance();
		assertEquals("((5)10((25)30(35)))", b.toStructuredString());
		
		createBalancePoints += 1;
		
		b.createBalance(); //Nothing should happen
		assertEquals("((5)10((25)30(35)))", b.toStructuredString());
		
		createBalancePoints += 1;
		
		BinarySearchTree b2 = new BinarySearchTree();
		final Integer treeVals2[] = { 10, 5, 30, 3}; // Simple
		this.loadUpBst(b2, treeVals2);
		
		b2.createBalance();
		assertEquals("(((3)5(7))10(30))", b2.toStructuredString());
		
		createBalancePoints += 1;
		
		b2.createBalance(); //Nothing should happen
		assertEquals("(((3)5(7))10(30))", b2.toStructuredString());
		
		createBalancePoints += 1;
		
	}
	
	@Test
	public void testCreateBalanceTest3() {
		BinarySearchTree b = new BinarySearchTree();
		final Integer treeVals[] = { 30, 15, 7 }; // Simple Tree 
		this.loadUpBst(b, treeVals);
		
		b.createBalance();
		assertEquals("(((7)15(23))30(45))", b.toStructuredString());
		
		createBalancePoints += 2;
		
		BinarySearchTree b2 = new BinarySearchTree();
		final Integer treeVals2[] = { 0, 15, 20 }; // Simple Tree 
		this.loadUpBst(b2, treeVals2);
		
		b2.createBalance();
		assertEquals("((-15)0((10)15(20)))", b2.toStructuredString());
		
		createBalancePoints += 2;
		
	}
	
	@Test
	public void testCreateBalanceTest4() {
		BinarySearchTree b = new BinarySearchTree();
		final Integer treeVals[] = { 34, 22, 62, 13, 46, 87, 5, 35, 67, 93, 1, 9, 64, 79, 7, 86 }; // Example Tree 
		this.loadUpBst(b, treeVals);
		
		b.createBalance();
		assertEquals("(((((1)5((7)9(11)))13(21))22(31))34(((35)46(57))62(((64)67((72)79(86)))87(93))))", b.toStructuredString());
		
		createBalancePoints += 4;
	}


	@AfterClass
	public static void displayPoints() {
		System.out.printf("%2d/16 numLeavesBelowDepth correctness points\n", Testing.numLeavesBelowDepthPoints);

		System.out.printf("%2d/12 numEmptyChildrenOnPath correctness points\n", Testing.numEmptyChildrenOnPathPoints);

		System.out.printf("%2d/20 createBalance correctness points\n", Testing.createBalancePoints);
		System.out.printf(" ?/ 8 numEmptyChildrenOnPath efficiency will be checked by the instructor\n");
		System.out.printf(" ?/ 4 Overall correctness and elegance will be double checked by the instructor.\n");	
		System.out.printf("      Your implementation will not earn full credit if defects are detected in your code \n");	
		System.out.printf("      even if your code passed all the provided test cases.\n");	
		System.out.printf("      Remember: 'correctness is king'\n");	
	}
}