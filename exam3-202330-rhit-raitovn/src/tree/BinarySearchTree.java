package tree;

/**
 * An implementation of a binary search tree, containing Integer data. 
 */


public class BinarySearchTree {	
	private Node root;
	private final Node NULL_NODE = new Node(null);

	public BinarySearchTree() {
		root = NULL_NODE;
	}
	
	/**
	 * 
	 * See the document for details.
	 * 
	 */
	public int numHeightDepthMatch() {
		if (this.root == NULL_NODE)
			return 0;
		// TODO: Write this method.
		return this.root.numHeightDepthMatch(-1).match;		
	}

	// The next methods are used by the unit tests
	public void insert(Integer e) {
		root = root.insert(e);
	}
	
	public class HeightAndDepth {
		int height;
		int depth;
		int match;
		int distance;
		
		public HeightAndDepth(int h, int d, int m) {
			this.height = h;
			this.depth = d;
			this.match = m;
		}
	}

	@Override
	public String toString() {
		return root.toString();
	}
	
	// ----------------------------
	// /////////////// Node
	// ----------------------------
	public class Node {
		public Integer data;
		public Node left;
		public Node right;

		public Node(Integer element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}
		
		public HeightAndDepth numHeightDepthMatch(int depth) {
			if (this == NULL_NODE) {
				return new HeightAndDepth(-1,-1,0);
			}
			
			depth++;
			HeightAndDepth lefty = this.left.numHeightDepthMatch(depth);
			HeightAndDepth righty = this.right.numHeightDepthMatch(depth);
			
			int height = Math.max(lefty.height, righty.height) + 1;
			int match = lefty.match + righty.match;
			if (height == depth)
				match++;
			
			// TODO Auto-generated method stub
			return new HeightAndDepth(height, depth, match);
		}

		// The next method is used by the unit tests
		public Node insert(Integer e) {
			if (this == NULL_NODE) {
				return new Node(e);
			} else if (e.compareTo(this.data) < 0) {
				left = left.insert(e);
			} else if (e.compareTo(this.data) > 0) {
				right = right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}
		
		@Override
		// You can use this to see in-order traversal 
		public String toString() {
			if (this == NULL_NODE) {
				return "";
			}
			return left.toString() + this.data.toString() + right.toString();
		}
		
	}
		
}