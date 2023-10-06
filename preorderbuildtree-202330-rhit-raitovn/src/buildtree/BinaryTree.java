package buildtree;

/**
 * 
 * @author Matt Boutell and <<<Naziia Raitova>>>.
 * @param <T>
 */

public class BinaryTree {

	private Node root;
	private final Node NULL_NODE = new Node(null);

	public BinaryTree() {
		root = NULL_NODE;
	}

	/**
	 * Constructs a tree (any tree of characters, not just a BST) with the given
	 * values and number of children, given in a pre-order traversal order. See
	 * the HW spec for more details.
	 * 
	 * @param chars
	 *            One char per node.
	 * @param children
	 *            L,R, 2, or 0.
	 */
	public BinaryTree(String chars, String children) {
		// DONE: Implement this constructor. You may not add any other fields to
		// the BinaryTree class, but you may add local variables and helper
		// methods if you like.
		
		// if either of the strings is empty
		if (chars == "" || children == "") {
			this.root = NULL_NODE;
			return;
		}
		
		// creating a tree starting from the root
		this.root = new Node(chars.charAt(0), chars, children, new Index());

	}
	
	// helper class, stores indexes
	public class Index {
		int index;
	}

	/**
	 * In-order traversal of the characters
	 */
	@Override
	public String toString() {
		return root.toString();
	}

	/**
	 * @return A string showing an in-order traversal of nodes with extra
	 *         brackets so that the structure of the tree can be determined.
	 */
	public String toStructuredString() {
		return root.toStructuredString();
	}

	// /////////////// binary Node
	public class Node {

		public Character data;
		public Node left;
		public Node right;

		public Node(Character element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		// making a tree recursively
		public Node(char data, String chars, String children, Index index) {
			this.data = data;
			
			// 'L', 'R', '2', or '0'
			char numOfChildren = children.charAt(index.index);
			
			// making sure the index is less than children's length
			if (index.index >= children.length())
				return;
			
			// if the node has no children, left and right nodes are null nodes
			if (numOfChildren == '0') {
				this.left = NULL_NODE;
				this.right = NULL_NODE;
			}
			
			// if the node has a left child or 2 children, make the left node the root and increase the index 
			if (numOfChildren == 'L' || numOfChildren == '2') {
				index.index++;
				this.left = new Node(chars.charAt(index.index), chars, children, index);
			} else 
				this.left = NULL_NODE;
			
			// if the node has a right child or 2 children, make the right node the root and increase the index 
			if (numOfChildren == 'R' || numOfChildren == '2') {
				index.index++;
				this.right = new Node(chars.charAt(index.index), chars, children, index);
			} else
				this.right = NULL_NODE;
		}

		@Override
		public String toString() {
			if (this == NULL_NODE) {
				return "";
			}
			return left.toString() + data + right.toString();
		}

		public String toStructuredString() {
			if (this == NULL_NODE) {
				return "";
			}
			return "(" + left.toStructuredString() + this.data
					+ right.toStructuredString() + ")";
		}

	}
}