import java.util.Stack;

public class StackChainManager extends ChainManager{
	public Stack<Chain> list;
	
	public StackChainManager() {
		this.list = new Stack<Chain>();
	}

	@Override
	public void add(Chain chain) {
		// Adds chain to the stack
		this.list.push(chain);
		super.updateMax(this.list.size());
	}

	@Override
	public Chain next() {
		// Returns next chain in the stack
		// Returns null if the stack is empty
		if (this.list.isEmpty()) {
			return null;
		}
		Chain temp = this.list.peek();
		this.list.pop();
		super.incrementNumNexts();
		return temp;
	}

	@Override
	public boolean isEmpty() {
		// If the stack is empty
		return this.list.isEmpty();
	}
}
