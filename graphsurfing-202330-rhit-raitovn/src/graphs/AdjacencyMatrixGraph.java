package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public class AdjacencyMatrixGraph<T> extends Graph<T> {
	public Map<T,Integer> keyToIndex;
	public List<T> indexToKey;
	public int[][] matrix;
	int edges;
	
	AdjacencyMatrixGraph(Set<T> keys) {
		this.edges = 0;
		int size = keys.size();
		this.keyToIndex = new HashMap<T,Integer>();
		this.indexToKey = new ArrayList<T>();
		this.matrix = new int[size][size];
		Iterator iter = keys.iterator();
		// need to populate keyToIndex and indexToKey with info from keys
		for (int i = 0; i < keys.size(); i++) {
			T key = (T) iter.next();
			this.keyToIndex.put(key, i);
			this.indexToKey.add(key);
		}
	}
	
	@Override
	public int size() {
		int size = this.matrix.length;
		return size;
	}

	@Override
	public int numEdges() {
		return this.edges;
	}

	@Override
	public boolean addEdge(T from, T to) {
		if (!this.keyToIndex.containsKey(from) || !this.keyToIndex.containsKey(to))
			throw new NoSuchElementException(); 
		
		int fromI = this.keyToIndex.get(from);
		int toI = this.keyToIndex.get(to);
		int[] row = this.matrix[fromI];
		if (row[toI] == 1)
			return false;
		row[toI] = 1;
		this.edges++;
		return true;
	}

	@Override
	public boolean hasVertex(T key) { 
		return this.keyToIndex.containsKey(key);
	}

	@Override
	public boolean hasEdge(T from, T to) throws NoSuchElementException {
		if (!this.keyToIndex.containsKey(from) || !this.keyToIndex.containsKey(to))
			throw new NoSuchElementException(); 
		int fromI = this.keyToIndex.get(from);
		int toI = this.keyToIndex.get(to);
		int[] row = this.matrix[fromI];
		if (row[toI] == 1)
			return true;
		return false;
		
	}

	@Override
	public boolean removeEdge(T from, T to) throws NoSuchElementException {
		if (!this.keyToIndex.containsKey(from) || !this.keyToIndex.containsKey(to))
			throw new NoSuchElementException(); 
		
		int fromI = this.keyToIndex.get(from);
		int toI = this.keyToIndex.get(to);
		int[] row = this.matrix[fromI];
		if (row[toI] != 1)
			return false;
		row[toI] = 0;
		this.edges--;
		return true;
	}

	@Override
	public int outDegree(T key) {
		if (!this.keyToIndex.containsKey(key))
			throw new NoSuchElementException(); 
		int count = 0;
		int keyI = this.keyToIndex.get(key);
		int[] row = matrix[keyI];
		for (int i = 0; i < row.length; i++) {
			if (row[i] == 1)
				count++;
		}
		return count;
	}

	@Override
	public int inDegree(T key) {
		if (!this.keyToIndex.containsKey(key))
			throw new NoSuchElementException(); 
		int count = 0;
		int col = this.keyToIndex.get(key);
		for (int row = 0; row < this.matrix.length; row++) {
			if (this.matrix[row][col] == 1)
				count++;
		}
		return count;
	}

	@Override
	public Set<T> keySet() {
		return this.keyToIndex.keySet();
	}

	@Override
	public Set<T> successorSet(T key) {
		if (!this.keyToIndex.containsKey(key))
			throw new NoSuchElementException();		
		Set<T> set = new HashSet<>();
		int row = this.keyToIndex.get(key);
		for (int col = 0; col < this.matrix.length; col++) {
			if (this.matrix[row][col] == 1) 
				set.add(this.indexToKey.get(col));
		}
		return set;
	}

	@Override
	public Set<T> predecessorSet(T key) {
		if (!this.keyToIndex.containsKey(key))
			throw new NoSuchElementException();
		Set<T> set = new HashSet<>();
		int col = this.keyToIndex.get(key);
		for (int row = 0; row < this.matrix.length; row++) {
			if (this.matrix[row][col] == 1) 
				set.add(this.indexToKey.get(row));
		}
		return set;
	}

	@Override
	public Iterator<T> successorIterator(T key) {
		if (!this.keyToIndex.containsKey(key))
			throw new NoSuchElementException(); 
		return new MyIter(this.keyToIndex.get(key), 's');
	}

	@Override
	public Iterator<T> predecessorIterator(T key) {
		if (!this.keyToIndex.containsKey(key))
			throw new NoSuchElementException(); 
		return new MyIter(this.keyToIndex.get(key), 'p');
	}
	
	private class MyIter implements Iterator<T> {
		int from;
		int pos;
		char c;

		MyIter(int from, char c) {
			this.from = from;
			this.c = c;
			this.pos = -1;
		}

		@Override
		public boolean hasNext() {
			for (int t = this.pos + 1; t < matrix.length; t++) {
				if (this.c == 's' &&  matrix[from][t] == 1) 
					return true;
				else if (this.c == 'p' && matrix[t][from] == 1) 
					return true;
			}
			return false;
		}

		@Override
		public T next() {
			for (int t = this.pos + 1; t < matrix.length; t++) {
				if (this.c == 's' &&  matrix[from][t] == 1) {
					this.pos = t; 
					break; 
				}
				else if (this.c == 'p' && matrix[t][from] == 1) {
					this.pos = t; 
					break;
				}
			}
			return indexToKey.get(this.pos);
		}
	}
}
