package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;

public class AdjacencyListGraph<T> extends Graph<T> {
	Map<T,Vertex> keyToVertex;
	
	private class Vertex {
		T key;
		List<Vertex> successors;
		List<Vertex> predecessors;
		
		Vertex(T key) {
			this.key = key;
			this.successors = new ArrayList<Vertex>();
			this.predecessors = new ArrayList<Vertex>();
		}
	}
	
	AdjacencyListGraph(Set<T> keys) {
		this.keyToVertex = new HashMap<T,Vertex>();
		for (T key : keys) {
			Vertex v = new Vertex(key);
			this.keyToVertex.put(key, v);
		}
	}

	@Override
	public int size() {
		int size = this.keyToVertex.size();
		return size;
	}

	@Override
	public int numEdges() {
		int num = 0;
		for (Vertex v : this.keyToVertex.values()) {
			num += v.predecessors.size();
		}
		return num;
	}

	@Override
	public boolean addEdge(T from, T to) {
		if (!this.keyToVertex.containsKey(from) || !this.keyToVertex.containsKey(to))
			throw new NoSuchElementException();
		Vertex fromV = this.keyToVertex.get(from);
		Vertex toV = this.keyToVertex.get(to);
		if (fromV.successors.contains(toV) || toV.predecessors.contains(fromV))
			return false;
		fromV.successors.add(toV);
		toV.predecessors.add(fromV);
		return true;
	}

	@Override
	public boolean hasVertex(T key) {
		return this.keyToVertex.get(key) != null;
	}

	@Override
	public boolean hasEdge(T from, T to) throws NoSuchElementException {
		if (!this.keyToVertex.containsKey(from) || !this.keyToVertex.containsKey(to))
			throw new NoSuchElementException();
		Vertex fromV = this.keyToVertex.get(from);
		Vertex toV = this.keyToVertex.get(to);
		if (fromV.successors.contains(toV) || toV.predecessors.contains(fromV))
			return true;
		return false;
	}

	@Override
	public boolean removeEdge(T from, T to) throws NoSuchElementException {
		if (!this.keyToVertex.containsKey(from) || !this.keyToVertex.containsKey(to))
			throw new NoSuchElementException();
		Vertex fromV = this.keyToVertex.get(from);
		Vertex toV = this.keyToVertex.get(to);
		if (!fromV.successors.contains(toV) || !toV.predecessors.contains(fromV))
			return false;
		fromV.successors.remove(toV);
		toV.predecessors.remove(fromV);
		return true;
	}

	@Override
	public int outDegree(T key) {
		if (!this.keyToVertex.containsKey(key))
			throw new NoSuchElementException();
		return this.keyToVertex.get(key).successors.size();
	}

	@Override
	public int inDegree(T key) {
		if (!this.keyToVertex.containsKey(key))
			throw new NoSuchElementException();
		int count = 0;
		Vertex vertex = this.keyToVertex.get(key);
		return vertex.predecessors.size();
	}

	@Override
	public Set<T> keySet() {
		return this.keyToVertex.keySet();
	}

	@Override
	public Set<T> successorSet(T key) {
		if (!this.keyToVertex.containsKey(key))
			throw new NoSuchElementException();
		List<Vertex> list = this.keyToVertex.get(key).successors;
		Set set = new HashSet<T>();
		for (Vertex v : list) {
			set.add(v.key);
		}
		return set;
	}
	
	@Override
	public Set<T> predecessorSet(T key) {
		if (!this.keyToVertex.containsKey(key))
			throw new NoSuchElementException();
		List<Vertex> list = this.keyToVertex.get(key).predecessors;
		Set set = new HashSet<T>();
		for (Vertex v : list) {
			set.add(v.key);
		}
		return set;
	}

	@Override
	public Iterator<T> successorIterator(T key) {
		if (!this.keyToVertex.containsKey(key))
			throw new NoSuchElementException();
		return successorSet(key).iterator();
	}

	@Override
	public Iterator<T> predecessorIterator(T key) {
		if (!this.keyToVertex.containsKey(key))
			throw new NoSuchElementException();
		return predecessorSet(key).iterator();
	}

}
