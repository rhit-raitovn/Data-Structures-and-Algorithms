import java.util.ArrayList;

/**
 * Binary Tree practice problems
 * 
 * @author Matt Boutell and <<<YOUR NAME HERE>>>. 2014.
 * @param <T>
 */

/*
 * DONE: 0 You are to implement the four methods below. I took most of them from
 * a CSSE230 exam given in a prior term. These can all be solved by recursion -
 * I encourage you to do so too, since most students find practicing recursion
 * to be more useful.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

	private Node root;

	private final Node NULL_NODE = new Node(null);

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	/**
	 * This method countPositivess the number of occurrences of positive Integers in the
	 * tree that is of type Integer. Hint: You may assume this tree contains
	 * integers, so may use a cast.
	 * 
	 * @return The number of positive integers in the tree.
	 */
	
	public int countPositives() {
		if(this.root != NULL_NODE)
			return this.root.countPositives();  
		return 0;
	}
	/**
	 * Recall that the depth of a node is number of edges in a path from this
	 * node to the root. Returns the depth of the given item in the tree. If the
	 * item isn't in the tree, then it returns -1.
	 * 
	 * @param item
	 * @return The depth, or -1 if it isn't in the tree.
	 */
	public int getDepth(T item) {
		if (this.root != NULL_NODE) {
			int res = this.root.getDepth(item, new BooleanContainer(false)) - 1;   // Carl Blome (TA) helped debug this part
			if (this.root.bc.value == false) return -1;                            //If the item isn't in the tree, then it returns -1.
			return res;     
		}
		if (this.root.bc.value == false) return -1;                                
		return 0;
	}

	/**
	 * This method visits each node of the BST in pre-order and determines the
	 * number of children of each node. It produces a string of those numbers.
	 * If the tree is empty, an empty string is to be returned. For the
	 * following tree, the method returns the string: "2200110"
	 * 
	 * 10 5 15 2 7 18 10
	 * 
	 * @return A string representing the number of children of each node when
	 *         the nodes are visited in pre-order.
	 */

	public String numChildrenOfEachNode() {
		if(this.root != NULL_NODE)
			return this.root.numChildrenOfEachNode();
		return "";
	}

	/**
	 * This method determines if a BST forms a zig-zag pattern. By this we mean
	 * that each node has exactly one child, except for the leaf. In addition,
	 * the nodes alternate between being a left and a right child. An empty tree
	 * or a tree consisting of just the root are both said to form a zigzag
	 * pattern. For example, if you insert the elements 10, 5, 9, 6, 7 into a
	 * BST in that order. , you will get a zig-zag.
	 * 
	 * @return True if the tree forms a zigzag and false otherwise.
	 */
	public boolean isZigZag() {
		if(this.root != NULL_NODE)
			return this.root.isZigZag(2);        // call the function and give any number for direction for now
		return true;                             // if the root is a null node, return true
	}

	public void insert(T e) {
		root = root.insert(e);
	}

	// /////////////// BinaryNode
	public class Node {

		public T element;
		public Node left;
		public Node right;
		private BooleanContainer bc = new BooleanContainer(false);

		public Node(T element) {
			this.element = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public boolean isZigZag(int direction) {                    // got help from Dr.Krohn
			if (this.left == NULL_NODE && this.right == NULL_NODE)  // if both of the nodes are null nodes
				return true;                                        // stop the recursion and return true
			if (this.right == NULL_NODE)                            // otherwise, keep going 
				if (direction != -1)                                // if the direction is to the right
					return this.left.isZigZag(-1);                  // return with the direction to the left
			if (this.left == NULL_NODE)
				if (direction != 1)                                 // if the direction is to the left
					return this.right.isZigZag(1);                  // return with the direction to the right 
			return false;                                           
		}

		public int countPositives() {
			int count = 0;
			if ((Integer) this.element > 0)                  // if the item is a positive number
				count++;
			if(this.left != NULL_NODE)
				count += this.left.countPositives();         // recursing through the whole tree
			if(this.right != NULL_NODE)
				count += this.right.countPositives();
			return count;
		}

		public String numChildrenOfEachNode() {
			String result = "";
			if (this.left != NULL_NODE && this.right != NULL_NODE)      // if the node has two children
				result += "2";
			else if (this.left != NULL_NODE || this.right != NULL_NODE) // if the node has one child
				result += "1";
			else                                                          // if the node has no children
				result += "0";

			if (this.left != NULL_NODE)
				result += this.left.numChildrenOfEachNode();             // recursing though the whole tree

			if (this.right != NULL_NODE)
				result += this.right.numChildrenOfEachNode();
			
			return result;
			
		}

		public int getDepth(T item, BooleanContainer bc) {
			int depth = 0;
			if (this == NULL_NODE)
				return 0;
			int d = item.compareTo(this.element);       // the direction
			if (d == 0)  {                              // the node with the right item
				this.bc.value = true;                   // the item is in the tree!
				return 1;
			}
				                        
			else if (d > 0)
				depth = 1 + this.right.getDepth(item, bc);  // the item is greater, so we should go right
			else
				depth = 1 + this.left.getDepth(item, bc);   // the item is smaller, so we should go left
			
			return depth;                               
		}
		
		public Node insert(T e) {
			if (this == NULL_NODE) {
				return new Node(e);
			} else if (e.compareTo(element) < 0) {
				left = left.insert(e);
			} else if (e.compareTo(element) > 0) {
				right = right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}
	}
}

class BooleanContainer {
	public boolean value;
	
	public BooleanContainer(boolean value) {
		this.value = value;
	}
}