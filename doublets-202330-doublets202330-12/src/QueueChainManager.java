import java.util.LinkedList;

public class QueueChainManager extends ChainManager{

	public LinkedList<Chain> list;
	
	public QueueChainManager() {
		this.list = new LinkedList<Chain>();
	}

	@Override
	public void add(Chain chain) {
		// Adds the chain to the queue
		this.list.add(chain);
		super.updateMax(this.list.size());
	}

	@Override
	public Chain next() {
		// Returns next chain in the queue,
		// Returns null if it's empty
		if (this.list.isEmpty()) {
			return null;
		}
		Chain temp = this.list.getFirst();
		this.list.removeFirst();
		super.incrementNumNexts();
		return temp;
	}

	@Override
	public boolean isEmpty() {
		// If the queue is empty
		return this.list.isEmpty();
	}
	
	public String toString() {
		return "";
	}
}
