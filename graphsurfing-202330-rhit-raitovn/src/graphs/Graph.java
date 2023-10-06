package graphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;

/**
 * Abstract class to represent the Graph ADT. It is assumed that every vertex contains some 
 * data of type T, which serves as the identity of that node and provides access to it.
 * 
 * @author Nate Chenette
 *
 * @param <T>
 */
public abstract class Graph<T> {
	
	/**
	 * Returns the number of vertices in the graph.
	 * @return
	 */
	public abstract int size();
	

	/**
	 * Returns the number of edges in the graph.
	 * @return
	 */
	public abstract int numEdges();

	
	/**
	 * Adds a directed edge from the vertex containing "from" to the vertex containing "to". 
	 * @param from
	 * @param to
	 * @return true if the add is successful, false if the edge is already in the graph.
	 * @throws NoSuchElementException if either key is not found in the graph
	 */
	public abstract boolean addEdge(T from, T to);

	
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
	 * Removes an edge from the graph.
	 * @param from
	 * @param to
	 * @return true if the remove is successful, false if the edge is not in the graph.
	 * @throws NoSuchElementException if either key is not found in the graph
	 */
	public abstract boolean removeEdge(T from, T to) throws NoSuchElementException;
	
	/**
	 * Computes out-degree of a vertex.
	 * @param key
	 * @return the number of successors of the vertex containing key
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract int outDegree(T key) throws NoSuchElementException;

	
	/**
	 * Computes in-degree of a vertex.
	 * @param key
	 * @return the number of predecessors of the vertex containing key
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract int inDegree(T key) throws NoSuchElementException;
	
	
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
	public abstract Set<T> successorSet(T key) throws NoSuchElementException;
	
	/**
	 * Returns a Set of keys that are predecessors of the given key.
	 * @param key
	 * @return
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public abstract Set<T> predecessorSet(T key) throws NoSuchElementException;
	
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
	
	/**
	 * Finds the strongly-connected component of the provided key.
	 * @param key
	 * @return a set containing all data in the strongly connected component of the vertex
	 * containing key 
	 * @throws NoSuchElementException if the key is not found in the graph
	 */
	public Set<T> stronglyConnectedComponent(T key) {		
		Set<T> successors;
		HashSet<T> fVertices = new HashSet<T>();
		LinkedList<T> myList = new LinkedList<T>();
		myList.add(key);
		while (!myList.isEmpty()) {
			// getting the vertex from the end of the LL
			T curVertex = myList.get(myList.size()-1);
			myList.remove(myList.size()-1);
			
			// make sure the vertex isn't already in the tried set
			if (fVertices.contains(curVertex))
				continue;
			// put successors to the list and then add the vertex to the set
			successors = successorSet(curVertex);
			fVertices.add(curVertex);
			myList.addAll(successors);
		}
		// repeating the process with predecessors
		Set<T> predecessors;
		HashSet<T> bVertices = new HashSet<T>();
		myList.add(key);
		while (!myList.isEmpty()) {
			T curVertex = myList.get(myList.size()-1);
			myList.remove(myList.size()-1);
			if (bVertices.contains(curVertex))
				continue;
			predecessors = predecessorSet(curVertex);
			bVertices.add(curVertex);
			myList.addAll(predecessors);
		}
		HashSet<T> result = new HashSet<T>();
		for (T item : fVertices) {
			if (bVertices.contains(item))
				result.add(item);
		}
		return result;
	}
	
	/**
	 * Searches for the shortest path between start and end points in the graph.
	 * @param start
	 * @param end
	 * @return a list of data, starting with start and ending with end, that gives the path through
	 * the graph, or null if no such path is found.  
	 * @throws NoSuchElementException if either key is not found in the graph
	 */
	public List<T> shortestPath(T startLabel, T endLabel) {
		
		Queue<List<T>> myQueue = new LinkedList<List<T>>();
		List<T> first = new ArrayList<T>();
		first.add(startLabel);
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
			if (curVertex.equals(endLabel)) {
				return myList;
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
		
}

