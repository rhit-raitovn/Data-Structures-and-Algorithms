package queues;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;
import java.util.Stack;

public class QueueFromStacks<T> implements SimpleQueue{

	private Stack<T> stackA;
	private Stack<T> stackB;

	public QueueFromStacks() {
		this.stackA = new Stack<T>();
		this.stackB = new Stack<T>();

	}
	
	public String toString() {
		if (this.isEmpty()) 
			return "[]";
		String str = "[";
		
		transferStack(stackA, stackB);
		
		// while transferring back to A, each element is appended to a String
		while(!this.stackB.isEmpty()) {
			T item = this.stackB.pop();
			this.stackA.push(item);
			str += item.toString() + ", ";
		}
		
		String var = str.substring(0, str.length()-2)+']';
		return var;
	}
	
	@Override
	public void clear() {
		this.stackA = new Stack<T>();
		this.stackB = new Stack<T>();
	}

	@Override
	public void enqueue(Object item) {
		// last element in StackA is the first element in the queue
		stackA.push((T) item);
		
	}

	@Override
	public Object dequeue() throws NoSuchElementException {
		if(stackA.isEmpty()) 
			throw new NoSuchElementException();
		// transfers every element to StackB 
		// top element in Stack B is the first element in the queue
		transferStack(stackA, stackB);
		Object item = stackB.pop();
		transferStack(stackB, stackA);
		
		return item;
	}

	@Override
	public Object peek() throws NoSuchElementException {
		if(stackA.isEmpty()) 
			throw new NoSuchElementException();
		// order in Stack A is backward, need to transfer to 
		// Stack B first to get right order
		transferStack(stackA, stackB);
	
		T item = stackB.peek();
		
		transferStack(stackB, stackA);
		return item;
		
	}

	@Override
	public boolean isEmpty() {
		return stackA.isEmpty();
	}

	@Override
	public int size() {
		transferStack(stackA, stackB);
		// keeps track of elements in Stack while transferring
		int res = transferStack(stackB, stackA);
		return res;
	}

	@Override
	public boolean contains(Object item) {
		Boolean result = false;
		// empties out stack A and checks to see if item matches
		// before pushing to Stack B
		while(!this.stackA.isEmpty()) {
			T item1 = this.stackA.pop();
			this.stackB.push(item1);
			if(item.equals(item1)) {
				result = true;
			}
			
		}
		// transfers back
		transferStack(stackB, stackA);
		return result;
	}

	// pushes everything from Stack1 to Stack 2, 
	// returns size of stack
	private int transferStack(Stack<T> stack1, Stack<T> stack2) {
		int count = 0;
		while(!stack1.isEmpty()) {
			stack2.push(stack1.pop());
			count++;
		}
		return count;
	}
}
