
/**
 * More Binary Tree practice problems. This problem creates BSTs of type
 * Integer: 1. Neither problem makes use of the BST ordering property; I just
 * found insert() to be a convenient way to build trees for testing. 2. I used
 * Integer instead of T since the makeTree method sets the data value of each
 * node to be a depth, which is an Integer.
 * 
 * @author Matt Boutell and <<<Naziia Raitova>>>.
 * @param <T>
 */

/*
 * TODO: 0 You are to implement the methods below. Use recursion!
 */
public class BinarySearchTree {

	private Node root;

	private final Node NULL_NODE = new Node(null);

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	public int getSumOfHeights() {
		// DONE. 1 Write this.
		// Can you do it in O(n) time instead of O(n log n) by avoiding repeated
		// calls to height()?
		return root.getHeightAndSumOfHeights().sum;
	}
	
	// Created a class to store the height of the tree and the sum of heights
	public class HeightAndSum {
		int height;
		int sum;
		
		public HeightAndSum(int height, int sum) {
			this.height = height;
			this.sum = sum;
			
		}
	}
	
	// Created a class to store the height of the tree and whether it's height balanced
	public class HeightAndBalance {
		int height;
		boolean isBalanced;
		
		public HeightAndBalance(int height, boolean b) {
			this.height = height;
			this.isBalanced = b;
			
		}
	}

	public boolean isHeightBalanced() {
		// TODO: 2 Write this.
		// Can you do it in O(n) time instead of O(n log n) by avoiding repeated
		// calls to height()?
		return root.isHeightBalanced().isBalanced;
	}

	/**
	 * This constructor creates a full tree of Integers, where the value of each
	 * node is just the depth of that node in the tree.
	 * 
	 * @param maxDepth The depth of the leaves in the tree.
	 */
	public BinarySearchTree(int maxDepth) {
		// TODO: 3 Write this.
		// Hint: You may find it easier if your recursive helper method is
		// outside of the BinaryNode class.
		
		// Creating a one-node-tree if the depth is 0
		if (maxDepth == 0) {
			root = new Node(maxDepth);
		}
		// Creating a null node if the depth is -1
		if (maxDepth == -1) {
			this.root = NULL_NODE;
		} else {
			this.root = new Node(0);
			this.root.createTree(maxDepth, 1);
		}
		
	}

	// These are here for testing.
	public void insert(Integer e) {
		root = root.insert(e);
	}

	/**
	 * @return A string showing an in-order traversal of nodes with extra brackets
	 *         so that the structure of the tree can be determined.
	 */
	public String toStructuredString() {
		return root.toStructuredString();
	}

	// /////////////// BinaryNode
	public class Node {

		public Integer data;
		public Node left;
		public Node right;

		public Node(Integer element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		// Creating a tree by recursing and adding 1 to our current depth
		public void createTree(int maxDepth, int current) {
			if (current != maxDepth + 1) {
				left = new Node(current);
				right = new Node(current);
				left.createTree(maxDepth, current + 1);
				right.createTree(maxDepth, current + 1);
			}
		}

		public HeightAndSum getHeightAndSumOfHeights() {
			if (this == NULL_NODE) {
				return new HeightAndSum(-1,0);
			}
			HeightAndSum leftHeightAndSum = left.getHeightAndSumOfHeights();
			HeightAndSum rightHeightAndSum = right.getHeightAndSumOfHeights();
				
			int height = Math.max(leftHeightAndSum.height, rightHeightAndSum.height) + 1;
			int sum = leftHeightAndSum.sum + rightHeightAndSum.sum + height;
			return new HeightAndSum(height,sum);
		}
		
		public HeightAndBalance isHeightBalanced() {
			if (this == NULL_NODE) 
				return new HeightAndBalance(-1, true);
			
			HeightAndBalance leftHeightAndBalance = left.isHeightBalanced();
			HeightAndBalance rightHeightAndBalance = right.isHeightBalanced();
			
			int height = Math.max(leftHeightAndBalance.height, rightHeightAndBalance.height) + 1;	
			// checking if the root of particular subtree is balanced
			boolean isBalanced = Math.abs(leftHeightAndBalance.height - rightHeightAndBalance.height) <= 1 
					&& leftHeightAndBalance.isBalanced
		            && rightHeightAndBalance.isBalanced;
			
			return new HeightAndBalance(height, isBalanced);
		}

		public Node insert(Integer e) {
			if (this == NULL_NODE) {
				return new Node(e);
			} else if (e.compareTo(data) < 0) {
				left = left.insert(e);
			} else if (e.compareTo(data) > 0) {
				right = right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}

		public String toStructuredString() {
			if (this == NULL_NODE) {
				return "";
			}
			return "[" + left.toStructuredString() + this.data + right.toStructuredString() + "]";
		}
	}
}