package tree;

/**
 * An implementation of a binary search tree, containing Integer data.
 */
public class BinarySearchTree {

	private BinaryNode root;
	private final BinaryNode NULL_NODE = new BinaryNode(null);

	public BinarySearchTree() {
		root = NULL_NODE;
	}

	/* 
	 * See printout for more details
	 * 
	 */
	public int numLeanOddLeftEvenRight() {
		//TODO: Implement this method.
		HeightAndBalance hb = root.numLeanOddLeftEvenRight(0);
		return hb.count;
	}

	// The next methods are used by the unit tests
	public void insert(Integer e) {
		root = root.insert(e);
	}

	// You can use this to see in-order traversal
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		root.toString(sb);
		return sb.toString();
	}
	
	public class HeightAndBalance {
		int height;
		int balance;
		int count;
		
		public HeightAndBalance(int h, int b, int c) {
			this.height = h;
			this.balance = b;
			this.count = c;
		}
	}

	// ----------------------------
	// /////////////// BinaryNode
	// ----------------------------
	public class BinaryNode {
		public Integer data;
		public BinaryNode left;
		public BinaryNode right;

		public BinaryNode(Integer element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		/* Add any additional methods here */
		
		public HeightAndBalance numLeanOddLeftEvenRight(int count) {
			if (this == NULL_NODE) 
				return new HeightAndBalance(-1,0, count);

			HeightAndBalance lefty = this.left.numLeanOddLeftEvenRight(count);
			HeightAndBalance righty = this.right.numLeanOddLeftEvenRight(count);
			
			int height = Math.max(lefty.height, righty.height) + 1;
			int balance = 0;
			if (lefty.height > righty.height && this.data % 2 != 0) {
				balance = -1; count++;
			}
			else if (lefty.height < righty.height && this.data % 2 == 0) {
				balance = 1; count++;
			}
			return new HeightAndBalance(height, balance, righty.count + lefty.count + count);
		}

		// The rest of the methods are used by the unit tests and for debugging
		public BinaryNode insert(Integer e) {
			if (this == NULL_NODE) {
				return new BinaryNode(e);
			} else if (e < data) {
				left = left.insert(e);
			} else if (e > data) {
				right = right.insert(e);
			} else {
				// do nothing
			}
			return this;
		}

		public void toString(StringBuilder sb) {
			if (this == NULL_NODE) {
				return;
			}
			left.toString(sb);
			sb.append(this.data.toString());
			right.toString(sb);
		}

	}
}