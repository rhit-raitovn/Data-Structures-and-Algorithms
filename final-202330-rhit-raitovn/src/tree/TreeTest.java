package tree;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class TreeTest {
	private static int treePoints = 0;
	
	
	
	@Test
	public void testNumLeanOddLeftEvenRightN01() {
		// Empty Tree
		BinarySearchTree t = new BinarySearchTree();
		assertEquals(0, t.numLeanOddLeftEvenRight());
		
		t.insert(81);
		assertEquals(0, t.numLeanOddLeftEvenRight());		
		treePoints += 1;
	}
	
	//FIXME: Renumber the tests
	
		@Test
	public void testNumLeanOddLeftEvenRightN02() {
		//     81
		//    /
		//   20
		BinarySearchTree t = new BinarySearchTree();
		t.insert(81);
		t.insert(20);
		assertEquals(1, t.numLeanOddLeftEvenRight());
		treePoints += 1;
	}
	
	@Test
	public void testNumLeanOddLeftEvenRightN03() {
		//     81
		//       \
		//       101
		BinarySearchTree t = new BinarySearchTree();
		t.insert(81);
		t.insert(101);
		assertEquals(0, t.numLeanOddLeftEvenRight());
		treePoints += 1;
	}
	
	@Test
	public void testNumLeanOddLeftEvenRightN04() {
		//     70
		//       \
		//       101
		BinarySearchTree t = new BinarySearchTree();
		t.insert(70);
		t.insert(101);
		assertEquals(1, t.numLeanOddLeftEvenRight());
		treePoints += 1;
	}
	
	@Test
	public void testNumLeanOddLeftEvenRightN05() {
		//     70
		//    /
		//   20
		BinarySearchTree t = new BinarySearchTree();
		t.insert(70);
		t.insert(20);
		assertEquals(0, t.numLeanOddLeftEvenRight());
		treePoints += 1;
	}
	
	@Test
	public void testNumLeanOddLeftEvenRightN06() {
		//     81
		//    /  \
		//   5   101
		BinarySearchTree t = new BinarySearchTree();
		t.insert(81);
		t.insert(5);
		t.insert(101);
		assertEquals(0, t.numLeanOddLeftEvenRight());
		
		//     70
		//    /  \
		//   5   101
		BinarySearchTree t2 = new BinarySearchTree();
		t.insert(70);
		t.insert(5);
		t.insert(101);
		assertEquals(0, t2.numLeanOddLeftEvenRight());
		
		treePoints += 1;
	}
	
	@Test
	public void testNumLeanOddLeftEvenRightN07() {
		//     70
		//    /  \
		//   5   102
		//         \
		//         105
		BinarySearchTree t = new BinarySearchTree();
		t.insert(70);
		t.insert(5);
		t.insert(102);
		t.insert(105);
		assertEquals(2, t.numLeanOddLeftEvenRight());
		treePoints += 1;
	}
	
	@Test
	public void testNumLeanOddLeftEvenRightN08() {
		//      81
		//    /    \
		//   6     102
		//    \    /
		//    19  99
		BinarySearchTree t = new BinarySearchTree();
		t.insert(81);
		t.insert(6);
		t.insert(102);
		t.insert(19);
		t.insert(99);		
		assertEquals(1, t.numLeanOddLeftEvenRight());
		treePoints += 1;
	}
	
	@Test
	public void testNumLeanOddLeftEvenRightN09() {
		//      81
		//    /    \
		//   6     111
		//  / \    /
		// 1  19  99
		BinarySearchTree t = new BinarySearchTree();
		t.insert(81);
		t.insert(6);
		t.insert(111);
		t.insert(1);
		t.insert(19);
		t.insert(104);			
		assertEquals(1, t.numLeanOddLeftEvenRight());
		treePoints += 1;
	}
	
	@Test
	public void testNumLeanOddLeftEvenRightN10() {
		//     81 
		//    /  \  
		//   4   100
		//    \    \
		//    18   105
		//      \ 
		//      21
		BinarySearchTree t = new BinarySearchTree();
		t.insert(81);
		t.insert(4);
		t.insert(100);
		t.insert(18);
		t.insert(105);
		t.insert(21);
		assertEquals(4, t.numLeanOddLeftEvenRight());
		treePoints += 1;
	}
	
	@Test
	public void testNumLeanOddLeftEvenRightN11() {
		//               27             
		//            /     \            
		//          21       55        
		//         /       /    \      
		//       16      40      62   
		//      /       /  \    /  \  
		//     8      32    42 57   63
		//                            \
		//                             65
		BinarySearchTree t = new BinarySearchTree();
		t.insert(27);
		t.insert(21);
		t.insert(55);
		t.insert(16);
		t.insert(40);
		t.insert(8);
		assertEquals(3, t.numLeanOddLeftEvenRight());
		t.insert(62);
		assertEquals(2, t.numLeanOddLeftEvenRight());
		t.insert(42);
		assertEquals(3, t.numLeanOddLeftEvenRight());
		t.insert(32);
		assertEquals(2, t.numLeanOddLeftEvenRight());
		t.insert(57);
		t.insert(63);
		t.insert(65);
		assertEquals(2, t.numLeanOddLeftEvenRight());
		treePoints += 1;
	}
	
	@Test
	public void testNumLeanOddLeftEvenRightN12() {
		//               20             
		//            /     \            
		//          10       31        
		//         /  \     /  \      
		//        5   14  25    40   
		//           /   /     /  \  
		//          11  22    36   46
		//                        /  \
		//                       41   50
		BinarySearchTree t = new BinarySearchTree();
		t.insert(20);
		t.insert(10);
		t.insert(31);
		t.insert(25);
		assertEquals(2, t.numLeanOddLeftEvenRight());
		t.insert(14);
		assertEquals(2, t.numLeanOddLeftEvenRight());
		t.insert(22);
		assertEquals(4, t.numLeanOddLeftEvenRight());
		t.insert(5);
		assertEquals(3, t.numLeanOddLeftEvenRight());
		t.insert(40);
		assertEquals(3, t.numLeanOddLeftEvenRight());
		t.insert(46);
		assertEquals(3, t.numLeanOddLeftEvenRight());
		t.insert(36);
		assertEquals(2, t.numLeanOddLeftEvenRight());
		t.insert(41);
		assertEquals(3, t.numLeanOddLeftEvenRight());
		t.insert(50);
		assertEquals(3, t.numLeanOddLeftEvenRight());
		t.insert(11);
		assertEquals(4, t.numLeanOddLeftEvenRight());
		treePoints += 2;
	}
	
	@Test
	public void testNumLeanOddLeftEvenRightN13() {
		//  2
		//   \
		//    99
		//   /
		//  4
		//   \
		//    97
		//   /
		//  6
		//   \
		//    95
		//   /
		//  8
		//   \
		//    93
		//   /
		//  10
		//   \
		//    91
		//   /
		//  12
		BinarySearchTree t = new BinarySearchTree();
		t.insert(2);
		t.insert(99);
		t.insert(4);
		t.insert(97);
		assertEquals(3, t.numLeanOddLeftEvenRight());
		t.insert(6);
		assertEquals(4, t.numLeanOddLeftEvenRight());
		t.insert(95);
		assertEquals(5, t.numLeanOddLeftEvenRight());
		t.insert(8);
		assertEquals(6, t.numLeanOddLeftEvenRight());
		t.insert(93);
		assertEquals(7, t.numLeanOddLeftEvenRight());
		t.insert(10);
		assertEquals(8, t.numLeanOddLeftEvenRight());
		t.insert(91);
		assertEquals(9, t.numLeanOddLeftEvenRight());
		t.insert(12);
		assertEquals(10, t.numLeanOddLeftEvenRight());
		treePoints += 2;
	}

	@AfterClass
	public static void displayPoints() {
		System.out.println("----------------------------------------------------------------------");
		System.out.printf("1. %2d/15  NumLeanOddLeftEvenRight correctness points                     \n", treePoints);
		System.out.printf("    ?/10  NumLeanOddLeftEvenRight efficiency/elegance points (manually graded) \n");
	}
}
