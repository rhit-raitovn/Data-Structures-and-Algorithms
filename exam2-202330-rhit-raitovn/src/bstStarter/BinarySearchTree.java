package bstStarter;

import java.util.ArrayList;

import BinarySearchTree.HeightAndSum;


/**
 *
 * Exam 2. Tree methods.
 * 
 * @author <<YOUR NAME HERE>>
 */

/*
 * TODO: Directions: Implement the methods below. See the paper for details.
 */
public class BinarySearchTree {

	Node root;

	// The -123 is arbitrary any value would be fine since we never refer to it.
	final Node NULL_NODE = new Node(-123);

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	
	/**
	 * 
	 * See the written document for more details.
	 * 
	 */
	public int numLeavesBelowDepth(int depth) {
		// TODO: Write this.
		return root.numLeavesBelowDepth(depth);
	}
	
	/**
	 *  
	 * See the written document for more details.
	 * 
	 */
	int numEmptyChildrenOnPath(Integer target)
	{
		return this.root.numEmptyChildrenOnPath(target);
	}
	
	/**
	 *  
	 * See the written document for more details.
	 * 
	 */
	public void createBalance() {
		this.root.createBalance();
	}



	// The next methods are used by the unit tests
	public void insert(Integer e) {
		this.root = this.root.insert(e);
	}

	@Override
	public String toString() {
		return toInorderList().toString();
	}

	ArrayList<Integer> toInorderList() {
		ArrayList<Integer> list = new ArrayList<>();
		this.root.toInorderList(list);
		return list;
	}


	/**
	 * Feel free to call from tests to use to verify the shapes of your trees while
	 * debugging. Just remove the calls you are done so the output isn't cluttered.
	 * 
	 * @return A string showing a traversal of the nodes where children are indented
	 *         so that the structure of the tree can be determined.
	 * 
	 */
	public String toIndentString() {
		return root.toIndentString("");
	}
	
	/**
	 * @return A string showing an in-order traversal of nodes with extra
	 *         brackets so that the structure of the tree can be determined.
	 */
	public String toStructuredString() {
		return root.toStructuredString();
	}
	
	public class Depth {
		int fullDepth;
		int depth;
		public int numOfLeaves;
		
		public Depth(int depth, int numOfLeaves) {
			this.depth = depth;
//			this.fullDepth = fullDepth;
			this.numOfLeaves = 0;
		}
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

		public void createBalance() {
			if (this == NULL_NODE)
				return;
			if (this.hasChildren() == 2) {
				left.createBalance();
				right.createBalance();
			}
			else if (this.hasChildren() == 1) {
				if (this.left == NULL_NODE) {
					this.right.createBalance();
					this.insert(this.data+(this.data-this.right.data));
				} else if (this.right == NULL_NODE) {
					this.left.createBalance();
					this.insert(this.data+(this.data-this.left.data));
				}
			} 
					
			
		}

		public int numEmptyChildrenOnPath(Integer target) {
			if (this == NULL_NODE || this.hasChildren() == 0)
				return 2;
			if (this.data == target) 
				return 2-this.hasChildren();
			if (target.compareTo(this.data) < 0) {
				if (this.left != NULL_NODE)
					return left.numEmptyChildrenOnPath(target) + 2-this.hasChildren();
			}
			else if (target.compareTo(this.data) > 0) {
				if (this.right != NULL_NODE)
					return right.numEmptyChildrenOnPath(target) + 2-this.hasChildren();
			}
			return 1;
		}

		public int numLeavesBelowDepth(int depth) {
			if (depth <= 0)
				return root.depth();
	
//			left.numLeavesBelowDepth(depth - 1);
//			right.numLeavesBelowDepth(depth - 1);
			
			if (!(left.depth() >= 2 && right.depth() >= 2)) {
				return left.hasChildren() + right.hasChildren();
			}
	
			return left.numLeavesBelowDepth(depth - 1) + right.numLeavesBelowDepth(depth - 1);
		}
		
//		public Depth numLeavesBelowDepth(int depth) {
//			if (this == NULL_NODE) {
//				return new Depth(0, 0);
//			} 
//			if (depth == depth())
//				return new Depth(depth, right.hasChildren() + left.hasChildren());
//			
//			Depth leftLeaves = left.numLeavesBelowDepth();
//			Depth rightLeaves = right.numLeavesBelowDepth(depth);
//				
//			int newDepth = Math.max(leftLeaves.depth, rightLeaves.depth) + 1;
//			int leaves = leftLeaves.numOfLeaves + rightLeaves.numOfLeaves;
//			
//			return new Depth(newDepth, leaves);
//		}
		
		public int depth() {
			if (this == NULL_NODE) {
				return -1;
			}			
			return Math.max(left.depth(), right.depth()) + 1;
		}
		
		public int hasChildren() {
			if (this == NULL_NODE)
				return 0;
			else if (right == NULL_NODE && left == NULL_NODE)
				return 0;
			else if (right != NULL_NODE && left != NULL_NODE)
				return 2;
			return 1;
			
		}


		// -------------------------------------------------------------
		// Below used by unit tests

		public void toInorderList(ArrayList<Integer> list) {
			if (this == NULL_NODE) {
				return;
			}
			this.left.toInorderList(list);
			list.add(this.data);
			this.right.toInorderList(list);
		}
		
		public String toStructuredString() {
			if (this == NULL_NODE) {
				return "";
			}
			return "(" + left.toStructuredString() + this.data
					+ right.toStructuredString() + ")";
		}
		

		// -------------------------------------------------------------

		public Node insert(Integer e) {
			if (this == NULL_NODE) {
				return new Node(e);
			} else if (e.compareTo(this.data) < 0) {
				this.left = this.left.insert(e);
			} else if (e.compareTo(this.data) > 0) {
				this.right = this.right.insert(e);
			} else {
				// do nothing - do not insert a duplicate
			}
			return this;
		}

		public String toIndentString(String indent) {
			if (this == NULL_NODE) {
				return indent + "NULL\n";
			}
			String myInfo = indent + String.format("%d\n", this.data);
			return myInfo + this.left.toIndentString(indent + "  ") + this.right.toIndentString(indent + "  ");
		}
		

	}
}