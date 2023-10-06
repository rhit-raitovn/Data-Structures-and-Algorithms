package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;

public abstract class Graph<T> {
		
	/**
	 * See the printed document for more details.
	 * 
	 */
	Set<T> longestDelays(T start, T end) {
		Queue<List<T>> myQueue = new LinkedList<List<T>>();
		List<T> first = new ArrayList<T>();
		first.add(start);
		myQueue.add(first);
		
		HashSet<T> result = new HashSet<T>();
		while (!myQueue.isEmpty()) {
			// myList is the list from the back of the queue
			List<T> myList = myQueue.poll();
			int index = myList.size() - 1;
			T curVertex = (T) myList.get(index);
			// make sure the vertex isn't already in the set
			if (result.contains(curVertex))
				continue;
			// checking the endLabel
			if (curVertex.equals(end)) {
				return makeSet(myList);
			}
			// adding the removed successors to arrayLists				// adding the arrayLists to the queue
			for (T t : successorSet(curVertex)) {
				List<T> myArray = new ArrayList<T>(myList);
				myArray.add(t);
				myQueue.add(myArray);
			}
			// add the vertex to the result list
			result.add(curVertex);
		}
		return null;
	}
	
	Set<T> makeSet(List<T> list) {
		Set<T> set = new HashSet<T>();
		T startVertex = (T) list.get(0);
		System.out.println("success" + successorSet(startVertex).toString());
		list.remove(0);
		list.remove(list.size()-1);
//		for (int i = 0; i < list.size(); i++) {
//			T curVertex = (T) list.get(i);
//			set.add(curVertex);
//		}
		for (T vertex : list) {
//			if (successorSet(startVertex).contains(vertex))
				set.add(vertex);
		}
		System.out.println(set.toString());
		return set;
	}
	
	boolean samePath(T vertex1, T vertex2) {
		
	}
	
	//FIXME: If we want some automated efficiency checks and don't mind giving away that it must be shortestPath, 
	// 		 we need to stub in shortestPath so we can compare relative to a plain shortest path call -- would have to
	//		 avoid caring about if their shortestPath is efficient or not

	
	/* Selected GraphSurfing Milestone 1 Methods */
	
	/**
	 * Adds a directed edge from the vertex containing "from" to the vertex containing "to". 
	 * @param from
	 * @param to
	 * @return true if the add is successful, false if the edge is already in the graph.
	 * @throws NoSuchElementException if either key is not found in the graph
	 */
	public abstract boolean addEdge(T from, T to) throws NoSuchElementException;

	
	/**
	 * Determines whether a vertex is in the graph.
	 * @param key
	 * @return true if the graph has a vertex containing key.
	 */
	public abstract boolean hasVertex(T key);
	
	
	/**
	 * Determines whether an edge is in the graph.
	 * @param from
	 * @param to
	 * @return true if the directed edge (from, to) is in the graph, otherwise false.
	 * @throws NoSuchElementException if either key is not found in the graph
	 */
	public abstract boolean hasEdge(T from, T to) throws NoSuchElementException;
	
	
	/**
	 * Returns the Set of vertex keys in the graph. 
	 * @return
	 */
	public abstract Set<T> keySet();
	
	/**
	 * Returns a Set of keys that are successors of the given key.
	 * @param key
	 * @return
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public Set<T> successorSet(T key) throws NoSuchElementException {
		if (!this.hasVertex(key)) {
			throw new NoSuchElementException("Could not find vertex containing " + key.toString());
		}
		Iterator<T> succIt = this.successorIterator(key);
		Set<T> toReturn = new HashSet<T>();
		while (succIt.hasNext()) {
			toReturn.add(succIt.next());
		}
		return toReturn;
	}
	
	/**
	 * Returns a Set of keys that are predecessors of the given key.
	 * @param key
	 * @return
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public Set<T> predecessorSet(T key) throws NoSuchElementException {
		if (!this.hasVertex(key)) {
			throw new NoSuchElementException("Could not find vertex containing " + key.toString());
		}
		Iterator<T> predIt = this.predecessorIterator(key);
		Set<T> toReturn = new HashSet<T>();
		while (predIt.hasNext()) {
			toReturn.add(predIt.next());
		}
		return toReturn;
	}
	
	/**
	 * Returns an Iterator that traverses the keys who are successors of the given key.
	 * @param key
	 * @return
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract Iterator<T> successorIterator(T key) throws NoSuchElementException;
	
	/**
	 * Returns an Iterator that traverses the keys who are successors of the given key.
	 * @param key
	 * @return
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract Iterator<T> predecessorIterator(T key) throws NoSuchElementException;
}



