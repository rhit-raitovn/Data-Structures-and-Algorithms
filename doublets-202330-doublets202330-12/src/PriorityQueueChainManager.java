import java.util.PriorityQueue;

public class PriorityQueueChainManager extends ChainManager{
	
	private String end;
	private PriorityQueue<PriorityQueueChainManager.Entry> chain;
	
	public PriorityQueueChainManager(String end) {
		this.end = end;
		this.chain = new PriorityQueue<Entry>();
	}
	
	@Override
	public void add(Chain chain) {
		// Adds chain to the queue
		Entry en = new Entry(chain);
		this.chain.add(en);
		super.updateMax(this.chain.size());
	}

	@Override
	public Chain next() {
		// If the queue is empty, returns null
		if(this.chain.isEmpty()) {
			return null;
		}
		// Returns next chain
		super.incrementNumNexts();
		Chain temp = this.chain.remove().c;
		return temp;
	}

	@Override
	public boolean isEmpty() {
		// True if the chain is empty
		return this.chain.isEmpty();
	}
	
	public int difference(String cur) {
		// The difference in strings
		int diff = 0;
		for (int i = 0; i < cur.length(); i++) {
			if (cur.charAt(i) != this.end.charAt(i)) {
				diff++;
			}
		}
		return diff;
	}
	
	class Entry implements Comparable<Entry>{
		
		private Chain c;
		private int value;
		
		public Entry (Chain chain) {
			this.c = chain;
			this.value = approximation(this.c);
		}
		
		public int approximation(Chain current) {
			return current.length() + difference(current.getLast());
		}
		
		@Override
		public int compareTo(Entry o) {
			// Comparing values
			int value1 = this.value;
			int value2 = o.value;
			if(value1 == value2)
				return 0;
			else if(value1 > value2)
				return 1;
			else 
				return -1;
		}
		
	}

}
