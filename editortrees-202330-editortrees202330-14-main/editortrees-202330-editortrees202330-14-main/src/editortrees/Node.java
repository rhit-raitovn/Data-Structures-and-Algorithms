package editortrees;

import java.util.ArrayList;

import editortrees.EditTree.LengthContainer;

/**
 * A node in a height-balanced binary tree with rank. Except for the NULL_NODE,
 * one node cannot belong to two different trees.
 * 
 * @author <<Ruth Hammond, Naziia Raitova>>
 */
public class Node {

	enum Code {
		SAME, LEFT, RIGHT;

		// Used in the displayer and debug string
		public String toString() {
			switch (this) {
			case LEFT:
				return "/";
			case SAME:
				return "=";
			case RIGHT:
				return "\\";
			default:
				throw new IllegalStateException();
			}
		}
	}

	// The fields would normally be private, but for the purposes of this class,
	// we want to be able to test the results of the algorithms in addition to the
	// "publicly visible" effects
	
	char data;
	Node left, right; // subtrees
	int rank; // inorder position of this node within its own subtree.
	Code balance;
	DisplayableNodeWrapper displayableNodeWrapper;
	public int totalRotations;
	
	
	// Feel free to add other fields that you find useful.
	// You probably want a NULL_NODE, but you can comment it out if you decide
	// otherwise.
	// The NULL_NODE uses the "null character", \0, as it's data and null children,
	// but they could be anything since you shouldn't ever actually refer to them in
	// your code.
	static final Node NULL_NODE = new Node('\0', null, null);
	// Node parent; You may want parent, but think twice: keeping it up-to-date
	// takes effort too, maybe more than it's worth.

	// constructor that sets the left and right subtrees
	public Node(char data, Node left, Node right) {
		this.data = data;
		this.left = left;
		this.right = right;
		this.rank = 0;
		displayableNodeWrapper = new DisplayableNodeWrapper(this);
	}

	public Node(char data) {
		// Make a leaf
		this(data, NULL_NODE, NULL_NODE);
		this.balance = Code.SAME;
	}

	// Provided to you to enable testing, please don't change.
	int slowHeight() {
		if (this == NULL_NODE) {
			return -1;
		}
		return Math.max(left.slowHeight(), right.slowHeight()) + 1;
	}

	// Provided to you to enable testing, please don't change.
	public int slowSize() {
		if (this == NULL_NODE) {
			return 0;
		}
		return left.slowSize() + right.slowSize() + 1;
	}

	public String toString() {		
		if (this == NULL_NODE)
			return "";		
		return this.left.toString() + this.data + this.right.toString();
	}
	
	public NodeInfo add(char ch, int pos) {
		NodeInfo res = addHelper(ch, pos, new NodeInfo(true));
		return res;
	}
	
	/**
	 * Helper add method that uses NodeInfo helper class
	 * 
	 * @param ch  character to add
	 * @param pos character added in this in-order position
	 * 
	 * @return NodeInfo (balanced tree)
	 */
	public NodeInfo addHelper(char ch, int pos, NodeInfo info) {
		if (this == NULL_NODE) {
			return new NodeInfo(true, new Node(ch));
		}
		
		// if position is smaller than the root's rank then we traverse to the left
		if (pos <= this.rank) {
			this.rank++;
			// building the left tree and setting info direction to left
			this.left = this.left.addHelper(ch, pos, info).newRoot;
			pos--;
			info.direction = NodeInfo.Direction.LEFT;
			
		// if position is greater then we traverse to the right
		} else {
			// since the right node's rank will be smaller we have to adjust the position
			pos = pos-this.rank-1;
			// building the right tree and setting info direction to right
			this.right = this.right.addHelper(ch, pos , info).newRoot;
			info.direction = NodeInfo.Direction.RIGHT;
		}
		
		info.newRoot = this;
		return balanceAfterInsert(info);
	}
	
	/**
	 * Sets right balance codes to each node of the tree
	 * Rotates the tree if the tree is unbalanced
	 * 
	 * @param NodeInfo (the tree that needs to be balanced)
	 * @return NodeInfo (balanced tree)
	 */
	public NodeInfo balanceAfterInsert(NodeInfo info) {
		// stop balancing when the code is =
		if (!info.continueBalance) 
			return info;
		
		// keep balancing 
		// go to the direction we added a new node
		
		// if we added the node to the left
		if (info.direction == NodeInfo.Direction.LEFT) {
			// if the tree was balanced, now the balance code is left
			if (this.balance == Code.SAME) {
				this.balance = Code.LEFT;
				info.continueBalance = true;			
			}
			// if the balance code is right, now the tree is balanced
			else if (this.balance == Code.RIGHT) {
				this.balance = Code.SAME;
				info.continueBalance = false;
			}
			// if the balance code is left, we rotate the tree to the right
			else {
				// if the balance code of the left node is also left, we use 
				// Single Right Rotation
				if (this.left.balance == Code.LEFT) {
					info.updateRotations(1);
					Node result = singleRightRotation(this, this.left);
					info.continueBalance = false;
					info.newRoot = result;
				}
				// if the balance code of the left node is right, we use 
				// Double Right Rotation
				else if (this.left.balance == Code.RIGHT) {
					info.updateRotations(2); 
					Node result = doubleRightRotation(this, this.left, this.left.right);
					info.newRoot = result;
					info.continueBalance = false;
				}
			}
		}
		
		// if we added the node to the right
		else if (info.direction == NodeInfo.Direction.RIGHT) {
			// if the tree was balanced, now the balance code is right
			if (this.balance == Code.SAME) {
				this.balance = Code.RIGHT;
				info.continueBalance = true;
			}
			// if the balance code was left, now the tree is balanced
			else if (this.balance == Code.LEFT) {
				this.balance = Code.SAME;
				info.continueBalance = false;
			}
			// if the balance code is right, we rotate the tree to the left
			else {
				// if the balance code of the right node is also right, we use 
				// Single Left Rotation
				if (this.right.balance == Code.RIGHT) {
					info.updateRotations(1);
					Node result = singleLeftRotation(this, this.right);
					info.newRoot = result;
					info.continueBalance = false;
				} 
				// if the balance code of the right node is left, we use 
				// Double Left Rotation
				else if (this.right.balance == Code.LEFT) {
					info.updateRotations(2);
					Node result = doubleLeftRotation(this, this.right, this.right.left);
					info.newRoot = result;
					info.continueBalance = false;
				}
			}
		}
		
		// stop balancing of the balance code of the root is same
		if (this.balance == Code.SAME)
			info.continueBalance = false;

		return info;
	}
	
	public Node delete(int pos, NodeInfo info) {
		
		if(pos == rank) {
			// set the deleted character
			info.charToBeDeleted = (info.charToBeDeleted == '\0') ? this.data : info.charToBeDeleted;
			
			// deleting a leaf (no children)
			if (!this.hasLeft() && !this.hasRight()) {
				return NULL_NODE;
			} 
			
			// deleting a a node with one child
			else if(this.hasLeft() && !this.hasRight()) {
				return this.left;
			}
			
			else if(this.hasRight() && !this.hasLeft()) {
				return this.right;
			}
			
			 // deleting a node with two children
			
			else {
				// finding the in-order successor of the current node which is the 
				// left-most child of the current right-child
				Node theLastLeft = this.right;
				if (theLastLeft != NULL_NODE) {
					while (theLastLeft.hasLeft()) {
						theLastLeft = theLastLeft.left;
					}
				}
				
				// we change the data of the current root to the data of the current in-order successor
				// instead of trying to move the tree
				this.data = theLastLeft.data;
				this.right = this.right.delete(0, info);
				info.direction = NodeInfo.Direction.RIGHT;
				info.newRoot = this;
				balanceAfterDeletion(info);
				return info.newRoot;
			}
		}
		
		// if position is smaller than the root's rank then we traverse to the left
		else if (pos < this.rank) {
			// building the left tree and setting info direction to left
			this.left = this.left.delete(pos, info);
//			pos--;
			this.rank--;
			info.direction = NodeInfo.Direction.LEFT;
			info.newRoot = this;
			
		// if position is greater then we traverse to the right
		} else {
			// since the right node's rank will be smaller we have to adjust the position
			pos = pos-this.rank-1;
			// building the right tree and setting info direction to right
			this.right = this.right.delete(pos , info);
			info.direction = NodeInfo.Direction.RIGHT;
			info.newRoot = this;
		}
		
		return balanceAfterDeletion(info).newRoot;
	}
	
	public NodeInfo balanceAfterDeletion(NodeInfo info) {
		// stop balancing when the code is =
		if (!info.continueBalance) 
			return info;
		
		if (!this.hasLeft() && !this.hasRight()) {
			this.balance = Code.SAME;
			return info;
		}
		
		// if we deleted the right node
		if (info.direction == NodeInfo.Direction.RIGHT) {
			
			// if the tree was balanced, now the balance code is left & we finish balancing
			if (this.balance == Code.SAME) {
				this.balance = Code.LEFT;
				info.continueBalance = false;
			}
			// if the balance code is right, now the tree is balanced
			else if (this.balance == Code.RIGHT) {
				this.balance = Code.SAME;
			}
			// Balance code is LEFT
			else {
				if (this.left.balance == Code.RIGHT) {
					info.updateRotations(2);
					info.newRoot = this.doubleRightRotation(this, this.left, this.left.right);
					return info;
				} else {
					info.updateRotations(1);
					info.newRoot = this.deleteSingleRightRotation(info);
					return info;
				}
			}
		}
		
		// if we deleted the left node
		else if (info.direction == NodeInfo.Direction.LEFT) {
			// if the tree was balanced, now the balance code is right
			if (this.balance == Code.SAME) {
				this.balance = Code.RIGHT;
				info.continueBalance = false;
			}
			// if the balance code was left, now the tree is balanced
			else if (this.balance == Code.LEFT) {
				this.balance = Code.SAME;
			}
			// if the balance code is right, we rotate the tree to the left
			else {
				// if the balance code of the right node is also right, we use 
				// Single Left Rotation
				if (this.right.balance == Code.LEFT) {
					info.updateRotations(2);
					info.newRoot = doubleLeftRotation(this, this.right, this.right.left);
					return info;
				} else {
					info.updateRotations(1);
					info.newRoot = this.deleteSingleLeftRotation(info);
					return info;
				}
			}
		} 
		
		// if we deleted the root (don't have the direction) 
		return info;
	}

	/**
	 * INNER HELPER CLASS
	 * 
	 * direction - stores direction (where we added the node)
	 * continueBalance - false if we the tree is balanced
	 * totalRotations - the number of total rotations
	 * newRoot - new root after rotation
	 * 
	 */
	public static class NodeInfo {
		Direction direction;
		boolean continueBalance;
		public int totalRotations;
		Node newRoot;
		public char charToBeDeleted;
		
		enum Direction {
			LEFT, RIGHT;
		}
		
		// constructors
		public NodeInfo(boolean cb) {
			this.continueBalance = cb;
			this.direction = Direction.RIGHT;
		}
		
		public NodeInfo(boolean cb, Node n) {
			this.continueBalance = cb;
			this.newRoot = n;
		}
		
		public NodeInfo(boolean cb, Node n, char ch, Direction d) {
			this.continueBalance = cb;
			this.newRoot = n;
			this.charToBeDeleted = ch;
			this.direction = d;
		}
		
		public NodeInfo(Node n) {
			this.newRoot = n;
		}
		
		/**
		 * updates the total number of rotations
		 * 
		 * @param n = 1 if it's a single rotation
		 *        n = 2 if it's a double rotation
		 */
		public void updateRotations(int n) {
			this.totalRotations = this.totalRotations + n;
		}
		
	}

	/**
	 * Makes a string of elements and ranks
	 * 
	 * @return The string of elements and ranks, given in an PRE-ORDER traversal of
	 *         the tree.
	 */
	public String toRankString() {
		String result = "";
		
		if (this == NULL_NODE)
			return "";
		result += this.data + "" + this.rank + ", ";
		if (this.left != NULL_NODE) {
			result += this.left.toRankString();
		}
		if (this.right != NULL_NODE) {
			result += this.right.toRankString();
		}
		
		return result;
	}

	/**
	 * Gets the character from the given position
	 * 
	 * @param pos position in the tree
	 * @return the character at that position
	 * 
	 */
	public char get(int pos) {
		// if position equals the root's rank
		if (pos == this.rank)
			return this.data;
		
		// if position is smaller than the root's rank, we go to the left
		if (pos < this.rank) {
			if (this.left != NULL_NODE) {
				return this.left.get(pos);
			}
		
		// if position is greater than the root's rank, we go to the right
		} else {
			if (this.right != NULL_NODE) {
				return this.right.get(pos-(this.rank+1));
			}
		}
		return 'c';
	}
	
	public String get(int Lpos, int Rpos, StringBuilder sb) {
		if (this == NULL_NODE)
			return "";
		
		// using in-order traversal:
		// left
		if (Lpos < this.rank) {
			this.left.get(Lpos, Math.min(this.rank - 1, Rpos), sb);
		}
		// root
		if (Lpos <= this.rank && this.rank <= Rpos) {
			sb.append(this.data);
		}
		//right
		if (Rpos > this.rank) {
			this.right.get(Math.min(Lpos - this.rank - 1, 0), Rpos - this.rank - 1, sb);
		}
		
		return sb.toString();
	}

	/**
	 * Calculates the size and 
	 * returns true iff for every node in the tree,
	 * the node's rank equals the size of the left subtree.
	 * 
	 * @return True iff each node's rank correctly equals its left subtree's size.
	 * 
	 */
	public SizeBoolean ranksMatchLeftSubtreeSize() {
		if(this == NULL_NODE) {
			return new SizeBoolean(0, true);
		}
		SizeBoolean lefty = left.ranksMatchLeftSubtreeSize();
		SizeBoolean righty = right.ranksMatchLeftSubtreeSize();
		
		// calculate the size of the left subtree
		int size = lefty.size + righty.size + 1;
		if (!(lefty.match && righty.match)) {
			return new SizeBoolean(size,false);
		}
		// if the size of the left subtree = the root's rank
		boolean match = (lefty.size == this.rank);
		
		return new SizeBoolean(size, match);
	}

	/**
	 * INNER HELPER CLASS
	 * 
	 * A class to store the size of the tree 
	 * and whether the size of the left subtree match the root's rank
	 * 
	 */
	public class SizeBoolean {
		public int size = 0;
		public boolean match = false;
		
		public SizeBoolean(int s, boolean m) {
			this.size = s;
			this.match = m;
		}
	}
	
	/**
	 * Calculates the height and returns true iff for every node in the tree, 
	 * the node's balance code is correct based on its childrens' heights.
	 * 
	 * @return True iff each node's balance code is correct.
	 * 
	 */
	public HeightAndBalance balanceCodeAreCorrect() {
		if (this == NULL_NODE) {
			return new HeightAndBalance(-1, true);
		}
	
		HeightAndBalance lefty = left.balanceCodeAreCorrect();
		HeightAndBalance righty = right.balanceCodeAreCorrect();
		
		// calculate the height of the subtree
		int height = Math.max(lefty.height, righty.height) + 1;
		
		// checking if the balances are correct
		boolean isCorrect = lefty.balanceCodesAreCorrect && righty.balanceCodesAreCorrect;
		if (!((lefty.height > righty.height && this.balance == Code.LEFT) 
				|| (lefty.height < righty.height && this.balance == Code.RIGHT)
				|| (lefty.height == righty.height && this.balance == Code.SAME)))
			isCorrect = false;
			

		return new HeightAndBalance(height, isCorrect);
	}
	
	/**
	 * INNER HELPER CLASS
	 * 
	 * A class to store the height of the tree 
	 * and whether the nodes have the right balance codes
	 * 
	 */
	class HeightAndBalance {
		int height;
		boolean balanceCodesAreCorrect;
		
		public HeightAndBalance(int h, boolean bc) {
			this.height = h;
			this.balanceCodesAreCorrect = bc;
		}
	}

	/**
	 * Makes a string of elements and ranks with balance codes too
	 * 
	 * @return The string of elements and ranks, given in an pre-order traversal of
	 *         the tree.
	 */
	public String toDebugString() {
		String result = "";
		
		if (this == NULL_NODE)
			return "";
		result += this.data + "" + this.rank + "" + this.balance.toString() + ", ";
		if (this.left != NULL_NODE) {
			result += this.left.toDebugString();
		}
		if (this.right != NULL_NODE) {
			result += this.right.toDebugString();
		}
		
		return result;
	}
	
	
	// use for displaying the tree
	public boolean hasLeft() {
		return this.left != NULL_NODE;
	}

	public boolean hasRight() {
		return this.right != NULL_NODE;
	}

	public boolean hasParent() {
		return false;
	}

//	public Node getParent() {
//		return NULL_NODE;
//	}
	
	// ROTATION METHODS
	
	// Single Left Rotation
	//
	//		     A                           B
	//		    / \                         /  \
	//		  T1   B          --->         A   T3 
	//		      / \                     / \
	//		     T2  T3                  T1  T2
	
	public Node singleLeftRotation(Node A, Node B) {
		Node t1 = A.left;
		Node t2 = B.left;
		Node t3 = B.right;
		
		int currentArank = A.rank;
		int currentBrank = B.rank;
		
		A.right = t2;
		B.left = A;
		B.right = t3;
		A.left = t1;
		
		A.rank = currentArank;
		B.rank = currentArank + currentBrank + 1;
		
		A.balance = Code.SAME;
		B.balance = Code.SAME;		
		
		return B;
	}
	
	public Node deleteSingleLeftRotation(NodeInfo info) {
		boolean tmp = this.right.balance == Code.SAME;
		Node result = singleLeftRotation(this, this.right);
		if (tmp) {
			result.balance = Code.LEFT;
			result.left.balance = Code.RIGHT;
			info.continueBalance = false;
		}
		return result;
	}
	
	// Single Right Rotation
	//
	//			     A                           B
	//			    / \                         / \
	//			   B  T3          ---->        T1  A 
	//			  / \                             / \
	//			 T1  T2                          T2 T3
	
	public Node singleRightRotation(Node A, Node B) {
		Node t1 = B.left;
		Node t2 = B.right;
		Node t3 = A.right;
		
		int currentArank = A.rank;
		int currentBrank = B.rank;
		
		A.right = t3;
		A.left = t2;
		B.left = t1;
		B.right = A;
		
		A.rank = currentArank - currentBrank - 1;
		B.rank = currentBrank;
		
		A.balance = Code.SAME;
		B.balance = Code.SAME;		
		
		return B;
	}
	
	public Node deleteSingleRightRotation(NodeInfo info) {
		boolean tmp = this.left.balance == Code.SAME;
		Node result = this.singleRightRotation(this, this.left);
		if (tmp) {
			result.balance = Code.RIGHT;
			result.right.balance = Code.LEFT;
			info.continueBalance = false;
		}
		return result;
	}
	
	
	// Double Left Rotation
	//
	//				     A                           C
	//				    / \                         / \
	//				   /   B          ---->        /   \ 
	//				  /   / \                     A     B
	//				 /   C   \                   / \   / \
	//				/   / \   \                 /  T2 T3  \
	//			   T1  T2 T3  T4               T1         T4
	
	public Node doubleLeftRotation(Node A, Node B, Node C) {
		 
		Node t1 =  A.left;
		Node t2 = C.left;
		Node t3 = C.right;
		Node t4 = B.right;
		
		int currentArank = A.rank;
		int currentBrank = B.rank;
		int currentCrank = C.rank;
		
		A.right = t2;
		A.left = t1;
		C.right = B;
		C.left = A;
		B.right = t4;
		B.left = t3;
		
		A.rank = currentArank;
		B.rank = currentBrank - (currentCrank + 1);
		C.rank = currentArank + currentCrank + 1;
		
		// if we're adding it to t2
		if (C.balance == Code.RIGHT) {
			A.balance = Code.LEFT;
			B.balance = Code.SAME;
		} 
		// if we're adding it to t3
		else if (C.balance == Code.LEFT) {
			A.balance = Code.SAME;
			B.balance = Code.RIGHT;
		} 
		
		// if A has no children, the balance is =
		if (A.right == NULL_NODE && A.left == NULL_NODE) 
			A.balance = Code.SAME;
			
	    // if B has no children, the balance is =
		if (B.right == NULL_NODE && B.left == NULL_NODE) 
			B.balance = Code.SAME;
		
		C.balance = Code.SAME;
		
		return C;
	}
	
	// Double Right Rotation
	//
	//					     A                           C
	//					    / \                         / \
	//					   B   \          ---->        /   \ 
	//					  / \   \                     B     A
	//					 /   C   \                   / \   / \
	//					/   / \   \                 /  T2 T3  \
	//				   T1  T2 T3  T4               T1         T4
	
	public Node doubleRightRotation(Node A, Node B, Node C) {
		Node t1 =  B.left;
		Node t2 = C.left;
		Node t3 = C.right;
		Node t4 = A.right;
		
		int currentArank = A.rank;
		int currentBrank = B.rank;
		int currentCrank = C.rank;
		
		A.right = t4;
		A.left = t3;
		B.right = t2;
		B.left = t1;
		C.right = A;
		C.left = B;
		
		A.rank = currentArank - (currentBrank + currentCrank) - 2;
		B.rank = currentBrank;
		C.rank = currentBrank + currentCrank + 1;
		
		// if we're adding it to t2
		if (C.balance == Code.LEFT) {
			A.balance = Code.RIGHT;
			B.balance = Code.SAME;
		} 
		// if we're adding it to t3
		else if (C.balance == Code.RIGHT) {
			A.balance = Code.SAME;
			B.balance = Code.LEFT;
		} 
		
		// if A has no children, the balance is =
		if (A.right == NULL_NODE && A.left == NULL_NODE) 
			A.balance = Code.SAME;
			
	    // if B has no children, the balance is =
		if (B.right == NULL_NODE && B.left == NULL_NODE) 
			B.balance = Code.SAME;
		
		C.balance = Code.SAME;
		
		return C;		
	}

	public String getLength(int pos, LengthContainer container) {
		
		String res = "";
		// base case
		if (container.length < 1 | this == NULL_NODE) {
			return "";
		}

		// traverse to the left of the tree
		if (pos < this.rank) {
			res += this.left.getLength(pos, container);
			
		// traverse to the right of the tree
		} else if (pos > this.rank) {
			
			// when we go right, we DON'T want to grab the parent's data
			res += this.right.getLength(pos - this.rank - 1, container);
			return res;
		}
		if (container.length > 0) {
			Node theLastLeft;
			
			if(this.right == NULL_NODE) {
				theLastLeft = NULL_NODE;
			} else {
				theLastLeft = this.right;
				if (theLastLeft != NULL_NODE) {
					while (theLastLeft.hasLeft()) {
						theLastLeft = theLastLeft.left;
					}
				}
				
			container.length--;
			res += String.valueOf(this.data) + this.right.getLength(theLastLeft.rank, container);
		}
		return res;
	}
	
	
	// You will probably want to add more constructors and many other
	// recursive methods here. I added 47 of them - most were tiny helper methods
	// to make the rest of the code easy to understand. My longest method was
	// delete(): 20 lines of code other than } lines. Other than delete() and one of
	// its helpers, the others were less than 10 lines long. Well-named helper
	// methods are more effective than comments in writing clean code.
	
	// TODO: By the end of milestone 1, consider if you want to use the graphical debugger. See
	// the unit test throwing an error and the README.txt file.
}