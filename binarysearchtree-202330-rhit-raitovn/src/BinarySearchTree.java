import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * 
 * Implementation of most of the Set interface operations using a Binary Search Tree
 *
 * @author Matt Boutell and <<< Naziia Raitova >>>.
 * @param <T>
 */

public class BinarySearchTree<T> implements Iterable<T>{

	private Node root;

	// You will like NULL NODEs (a common software design pattern) once you get used to them.
	private final Node NULL_NODE = new Node();
	public boolean madeChanges;

	public BinarySearchTree() {
		root = NULL_NODE;
		madeChanges = false;
	}

	/**
	 * returns the size of the tree
	 * 
	 */
	public int size() {
		return root.size();
	}

	/**
	 * returns the height of the tree
	 * 
	 */
	public int height() {
		return root.height();
	}

	// For manual tests only
	void setRoot(Node n) {
		this.root = n;
	}
	
	public void printPreOrder() {
		root.printPreOrder();
	}
	
	public void printInOrder() {
		root.printInOrder();
	}
	
	public void printPostOrder() {
		root.printPostOrder();
	}
	
	public boolean insert(T x) {
		this.madeChanges = true;
		BooleanContainer bc = new BooleanContainer(true);
		root = root.insert(x, bc);
		return bc.value;
		
	}
	
	// Not private, since we need access for manual testing.
	class Node {
		private T data;
		private Node left;
		private Node right;
		private BooleanContainer containsItem = new BooleanContainer(true);

		public Node() {
			this.data = null;
			this.left = null;
			this.right = null;
		}
		
		/**
		 * adding nodes
		 * 
		 */
		public Node insert(T x, BooleanContainer bc) {
			if (x == null) 
				throw new IllegalArgumentException();
			// if the tree is empty, return a new node
			if (this == NULL_NODE) 
				return new Node(x);
			// going left or right, depending on the item's value
			else if ((Integer) x < (Integer) this.data) 
				left = left.insert(x, bc);
			else if ((Integer) x > (Integer) this.data) 
				right = right.insert(x, bc);
			else   // x== data
				bc.value = false;
			return this;
		}

		public void printPreOrder() {
			if (this == NULL_NODE) return;
			System.out.println(this.data.toString());
			left.printPreOrder();
			right.printPreOrder();
		}

		public void printInOrder() {
			if (this == NULL_NODE) return;
			left.printInOrder();
			System.out.println(this.data.toString());
			right.printInOrder();
		}

		public void printPostOrder() {
			if (this == NULL_NODE) return;
			left.printPostOrder();
			right.printPostOrder();
			System.out.println(this.data.toString());
		}

		public int size() {
			if (this == NULL_NODE) {
				return 0;
			}
			return (1 + left.size() + right.size());
		}

		public int height() {
			if (this == NULL_NODE) {
				return -1;
			}
			return (1 + Math.max(left.height(), right.height()));
		}

		public Node(T element) {
			this.data = element;
			this.left = NULL_NODE;
			this.right = NULL_NODE;
		}

		public T getData() {
			return this.data;
		}

		public Node getLeft() {
			return this.left;
		}


		public Node getRight() {
			return this.right;
		}

		// For manual testing
		public void setLeft(Node left) {
			this.left = left;
		}
		
		public void setRight(Node right) {
			this.right = right;
		}

		public boolean contains(int i) {
			if (this == NULL_NODE) 
				return false;
			if (this.data.equals(i))
				return true;
			return (this.left.contains(i) || this.right.contains(i));
		}
		
		// turning the tree into an ArrayList
		public ArrayList<Object> toArrayList() {
			// tree ArrayList
			ArrayList<Object> al = new ArrayList<Object>();
			// subtree ArrayList
			ArrayList<Object> smallAL = new ArrayList<Object>();
			if (this != NULL_NODE) {
				if (this.left != NULL_NODE) {
					smallAL = (ArrayList<Object>) this.left.toArrayList();
					al.addAll(smallAL);
				} 
				al.add(this.data);
				if (this.right != NULL_NODE) {
					smallAL = (ArrayList<Object>) this.right.toArrayList();
					al.addAll(smallAL);
				} 
			}
			return al;
		}
		
		// recursive method to remove a node from a tree
		public Node remove(T item) {
			if (item == null) {
				throw new IllegalArgumentException();
			}
			if (this == NULL_NODE) {
				return this;	
			// removing the node from the left or right subtree, depending on the data
			} else if ((Integer) item < (Integer) this.data) {
				this.left = this.left.remove(item);
				return this;
			} else if ((Integer) item > (Integer) this.data ) {
				this.right = this.right.remove(item);
				return this;
			// found the item
			} else {
				// if the node has one child
				if (this.left == NULL_NODE) {
					BinarySearchTree.this.madeChanges = true;
					return this.right;
				}
				if(this.right == NULL_NODE) {
					BinarySearchTree.this.madeChanges = true;
					return this.left;
				}
				// if the node has two children
				Node next = this.left.minNode();
				this.data = next.data;
				this.left = this.left.remove(next.data);
				BinarySearchTree.this.madeChanges = true;
				return this;
			}
		}
		
		// returns the smallest value in the sub tree 
		private Node minNode() {
			if(this.right == NULL_NODE) {
				return this;
			}
			return this.right.minNode();
		}
	}
	
	/**
	 * returns true if tree is empty
	 * 
	 */
	public boolean isEmpty() {
		return this.root == NULL_NODE;
	}

	/**
	 * returns true if tree contains a node with data i
	 * 
	 */
	public boolean contains(int i) {
		return this.root.contains(i);
	}

	/**
	 * returns the tree as an ArrayList
	 * 
	 */
	public ArrayList<Object> toArrayList() {
		return this.root.toArrayList();
	}
	
	/**
	 * returns the tree as an array
	 * 
	 */
	public Object[] toArray() {
		return this.root.toArrayList().toArray(new Integer[this.root.toArrayList().size()]);
	}
	
	/**
	 * returns the tree as a string
	 * 
	 */
	public String toString() {
		return this.toArrayList().toString();
	}
	
	/**
	 * returns the true if we removed a node successfully
	 * 
	 */
	public boolean remove(T item) throws IllegalArgumentException {
		if (item == null) 
			throw new IllegalArgumentException();
		// making sure the item is in the tree
		if(!this.root.contains((Integer)item)) {
			return false;
		}
		if (this.root == NULL_NODE)
			return false;

		this.root = this.root.remove(item);
		// the tree was modified
		this.madeChanges = true;
		return true;			
	}
	
	public Iterator<T> inefficientIterator() {
		return new ArrayListIterator(this);
	}
	
	
	public Iterator iterator() {
		return new InOrderIterator(this.root);
	}

	public Iterator preOrderIterator() {
		return new PreOrderIterator(this.root);
	}

	// DONE: Implement your 3 iterator classes here, plus any other inner helper classes you'd like. 
	
	/**
	 * Lazy tree iterator
	 * 
	 * calls the toArrayList() method and iterates over the list.
	 */
	private class ArrayListIterator implements Iterator<T> {

		private BinarySearchTree bst;
		private ArrayList<T> array;
		private int index = -1;
		
		public ArrayListIterator(BinarySearchTree bst) {
			this.bst = bst;
			this.array = bst.toArrayList();
		}
		
		@Override
		public boolean hasNext() {
			if (index >= this.array.size()-1)
				return false;
			return true;
		}

		@Override
		public T next() {
			if (this.hasNext()) {
				index++;
				return (T) this.array.get(index);
			}
			throw new NoSuchElementException();
		}
	}
	
	/**
	 * Pre-order tree iterator
	 * 
	 */
	class PreOrderIterator implements Iterator<T> {
		
		// using a stack 
		private Stack<T> stack = new Stack<>();
		private T index;
		Node root;
		private boolean nextCalled = false;

		public PreOrderIterator(Node root) {
			BinarySearchTree.this.madeChanges = false;
			this.root = root;
			stackHelper(root);
		}

		@Override
		public boolean hasNext() {
			return !(this.stack.isEmpty());
		}

		@Override
		public T next() throws ConcurrentModificationException {
			// next() has been called
			this.nextCalled = true;
			// if the tree was modified
			if(BinarySearchTree.this.madeChanges == true){
				throw new ConcurrentModificationException();
			}
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			this.index = this.stack.pop();
			return this.index;

		}

		// adding nodes in a stack in pre-order order
		private void stackHelper(Node n) {
			if (n == BinarySearchTree.this.NULL_NODE) {
				return;
			}
			// adding the right first, because we iterate it the last
			if (n.right != BinarySearchTree.this.NULL_NODE) {
				stackHelper(n.right);
			}
			// then adding the left
			if (n.left != BinarySearchTree.this.NULL_NODE) {
				stackHelper(n.left);
			}
			// finally root, because pre-order works as root-left-right
			this.stack.push(n.data);

		}
		
		@Override
		public void remove() {
			// Testing exception throwing when next() has not been called yet
			if (!this.nextCalled) 
				throw new IllegalStateException();
			if (!this.hasNext()) 
				throw new IllegalStateException();
			this.root.remove(this.next());
		}

	}
	
	/**
	 * In-order tree iterator
	 * 
	 */
	private class InOrderIterator implements Iterator {
		
		// making a stack
		private Stack<T> stack = new Stack<>();
		private T indexData;
		private Node root;
		private boolean nextCalled = false;

		public InOrderIterator(Node root) {
			BinarySearchTree.this.madeChanges = false;
			stackHelper(root);
			this.root = root;
		}

		@Override
		public boolean hasNext() {
			return !(this.stack.isEmpty());
		}

		@Override
		public T next() throws ConcurrentModificationException {
			// next() has been called
			this.nextCalled = true;
			// the tree was modified
			if(BinarySearchTree.this.madeChanges == true){
				throw new ConcurrentModificationException();
			}
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			this.indexData = this.stack.pop();
			return this.indexData;
		}

		private void stackHelper(Node n) {
			if (n == BinarySearchTree.this.NULL_NODE) {
				return;
			}
			// adding the right first, because we iterate it the last
			if (n.right != BinarySearchTree.this.NULL_NODE) {
				stackHelper(n.right);
			}
			// then adding the root data
			this.stack.push(n.data);
			// finally the left sub-tree, because in-order works as left-root-right
			if (n.left!= BinarySearchTree.this.NULL_NODE) {
				stackHelper(n.left);
			}
		}
		
		@Override
		public void remove() {
			if (!this.nextCalled) 
				throw new IllegalStateException();
			if (!this.hasNext()) 
				throw new IllegalStateException();
			this.root.remove(this.next());
		}

	}
}

class BooleanContainer {
	public boolean value;
	
	public BooleanContainer(boolean value) {
		this.value = value;
	}
}
