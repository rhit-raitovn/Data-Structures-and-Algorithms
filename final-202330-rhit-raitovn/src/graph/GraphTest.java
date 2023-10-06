 package graph;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Test;

public class GraphTest {

	private static int graphALPoints = 0;
	private static int graphAMPoints = 0;
	private static Set<String> simplevilleExample;
	private static Set<String> middletonExample;
	private static Set<String> threelandExample;
	private static Set<String> complicanapolisExample;

	
	private Graph<String> buildSimplevilleExample(boolean adjList) {
		// Smaller version of the example in the document
		// Only includes A, B, C, and E
		Graph<String> g;
		simplevilleExample = new HashSet<String>();
		

		simplevilleExample.add("AA");
		simplevilleExample.add("BB");
		simplevilleExample.add("CC");
		simplevilleExample.add("DD");
		simplevilleExample.add("EE");
		
		if (adjList) {
			g = new AdjacencyListGraph<String>(simplevilleExample);
		} else {
			g = new AdjacencyMatrixGraph<String>(simplevilleExample);
		}
		
		g.addEdge("AA", "BB");
		g.addEdge("BB", "AA");
		g.addEdge("BB", "DD");
		g.addEdge("DD", "BB");
		g.addEdge("DD", "EE");
		g.addEdge("EE", "DD");
		g.addEdge("EE", "CC");
		g.addEdge("CC", "AA");
		return g;
	}
	
	@Test
	public void testSimplevilleExample1_AL() {
		Graph<String> g = buildSimplevilleExample(true);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("EE","AA");
		assertEquals(1, longDelays.size());
		assertTrue(longDelays.contains("CC"));
		graphALPoints += 1;

	}
	
	@Test
	public void testSimplevilleExample1_AM() {
		Graph<String> g = buildSimplevilleExample(false);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("EE","AA");
		assertEquals(1, longDelays.size());
		assertTrue(longDelays.contains("CC"));
		graphAMPoints += 1;
	}
	
	@Test
	public void testSimplevilleExample2_AL() {
		Graph<String> g = buildSimplevilleExample(true);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("EE","BB");
		assertEquals(1, longDelays.size());
		assertTrue(longDelays.contains("DD"));
		graphALPoints += 1;
	}
	
	@Test
	public void testSimplevilleExample2_AM() {
		Graph<String> g = buildSimplevilleExample(false);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("EE","BB");
		assertEquals(1, longDelays.size());
		assertTrue(longDelays.contains("DD"));		
		graphAMPoints += 1;
	}
	
	@Test
	public void testSimplevilleExample3_AL() {
		Graph<String> g = buildSimplevilleExample(true);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("DD","AA");
		assertEquals(1, longDelays.size());
		assertTrue(longDelays.contains("BB"));
		
		//Repeat of testSimplevilleExample1 to check if the graph remains the same
		longDelays = g.longestDelays("EE","AA");
		assertEquals(1, longDelays.size());
		assertTrue(longDelays.contains("CC"));

		//Repeat of testSimplevilleExample2 to check if the graph remains the same
		longDelays = g.longestDelays("EE","BB");
		assertEquals(1, longDelays.size());
		assertTrue(longDelays.contains("DD"));
		graphALPoints += 1;
	}
	
	@Test
	public void testSimplevilleExample3_AM() {
		Graph<String> g = buildSimplevilleExample(false);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("DD","AA");
		assertEquals(1, longDelays.size());
		assertTrue(longDelays.contains("BB"));
		
		//Repeat of testSimplevilleExample1 to check if the graph remains the same
		longDelays = g.longestDelays("EE","AA");
		assertEquals(1, longDelays.size());
		assertTrue(longDelays.contains("CC"));

		//Repeat of testSimplevilleExample2 to check if the graph remains the same
		longDelays = g.longestDelays("EE","BB");
		assertEquals(1, longDelays.size());
		assertTrue(longDelays.contains("DD"));
		graphAMPoints += 1;
	}
	

	private Graph<String> buildMiddletonExample(boolean adjList) {
		// This is the example in the specification. Labels are strings. 
		Graph<String> g;
		middletonExample = new HashSet<String>();
		

		middletonExample.add("O");
		middletonExample.add("P");
		middletonExample.add("Q");
		middletonExample.add("R");
		middletonExample.add("S");
		middletonExample.add("T");
		middletonExample.add("U");
		middletonExample.add("V");
		middletonExample.add("W");
		middletonExample.add("X");
		middletonExample.add("Y");
		
		if (adjList) {
			g = new AdjacencyListGraph<String>(middletonExample);
		} else {
			g = new AdjacencyMatrixGraph<String>(middletonExample);
		}
		
		g.addEdge("O", "P");
		g.addEdge("P", "O");
		g.addEdge("P", "S");
		g.addEdge("S", "P");
		g.addEdge("S", "U");
		g.addEdge("U", "S");
		g.addEdge("U", "W");
		g.addEdge("W", "U");
		g.addEdge("W", "Y");
		g.addEdge("Y", "W");
		g.addEdge("Y", "X");
		g.addEdge("X", "Y");
		g.addEdge("X", "V");
		g.addEdge("V", "X");
		g.addEdge("V", "T");
		g.addEdge("T", "V");
		g.addEdge("T", "Q");
		g.addEdge("Q", "T");
		g.addEdge("Q", "O");
		g.addEdge("O", "Q");
		g.addEdge("P", "Q");
		g.addEdge("Q", "P");
		g.addEdge("U", "T");
		g.addEdge("T", "P");
		g.addEdge("V", "S");
		g.addEdge("W", "X");
		g.addEdge("X", "W");
		g.addEdge("V", "R");
		g.addEdge("R", "O");
		
		return g;
	}
	
	@Test
	public void testMiddletonExample1_AL() {
		Graph<String> g = buildMiddletonExample(true);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("X", "O");
		assertEquals(1, longDelays.size());
		assertTrue(longDelays.contains("V"));
		graphALPoints += 1;
	}
	
	@Test
	public void testMiddletonExample1_AM() {
		Graph<String> g = buildMiddletonExample(false);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("X", "O");
		assertEquals(1, longDelays.size());
		assertTrue(longDelays.contains("V"));
		graphAMPoints += 1;
	}
	
	@Test
	public void testMiddletonExample2_AL() {
		Graph<String> g = buildMiddletonExample(true);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("Y", "O");
		assertEquals(3, longDelays.size());
		assertTrue(longDelays.contains("R"));
		assertTrue(longDelays.contains("V"));
		assertTrue(longDelays.contains("X"));
		graphALPoints += 1;
	}
	
	@Test
	public void testMiddletonExample2_AM() {
		Graph<String> g = buildMiddletonExample(false);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("Y", "O");
		assertEquals(3, longDelays.size());
		assertTrue(longDelays.contains("R"));
		assertTrue(longDelays.contains("V"));
		assertTrue(longDelays.contains("X"));
		graphAMPoints += 1;
	}
	
	@Test
	public void testMiddletonExample3_AL() {
		Graph<String> g = buildMiddletonExample(true);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("O", "Y");
		assertEquals(4, longDelays.size());
		//Regardless of which way your program found was slow, anything along that chain
		//	will cause problems.
		assertTrue(longDelays.contains("P") || longDelays.contains("Q"));
		assertTrue(longDelays.contains("S") || longDelays.contains("T"));
		assertTrue(longDelays.contains("U") || longDelays.contains("V"));
		assertTrue(longDelays.contains("W") || longDelays.contains("X"));
		graphALPoints += 1;
	}
	
	@Test
	public void testMiddletonExample3_AM() {
		Graph<String> g = buildMiddletonExample(false);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("O", "Y");
		assertEquals(4, longDelays.size());
		assertTrue(longDelays.contains("P") || longDelays.contains("Q"));
		assertTrue(longDelays.contains("S") || longDelays.contains("T"));
		assertTrue(longDelays.contains("U") || longDelays.contains("V"));
		assertTrue(longDelays.contains("W") || longDelays.contains("X"));
		graphAMPoints += 1;
	}
	
	@Test
	public void testMiddletonExample4_AL() {
		Graph<String> g = buildMiddletonExample(true);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("W", "Q");
		assertEquals(2, longDelays.size());
		assertTrue(longDelays.contains("U"));
		assertTrue(longDelays.contains("T"));
		graphALPoints += 1;
	}
	
	@Test
	public void testMiddletonExample4_AM() {
		Graph<String> g = buildMiddletonExample(false);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("W", "Q");
		assertEquals(2, longDelays.size());
		assertTrue(longDelays.contains("U"));
		assertTrue(longDelays.contains("T"));
		graphAMPoints += 1;
	}
	
	@Test
	public void testMiddletonExample5_AL() {
		Graph<String> g = buildMiddletonExample(true);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("W", "P");
		assertEquals(1, longDelays.size());
		assertTrue(longDelays.contains("U"));
		graphALPoints += 1;
	}
	
	@Test
	public void testMiddletonExample5_AM() {
		Graph<String> g = buildMiddletonExample(false);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("W", "P");
		assertEquals(1, longDelays.size());
		assertTrue(longDelays.contains("U"));
		graphAMPoints += 1;
	}
	

	private Graph<String> buildThreelandExample(boolean adjList) {
		Graph<String> g;
		threelandExample = new HashSet<String>();
		
		threelandExample.add("FF");
		threelandExample.add("GG");
		threelandExample.add("HH");
		threelandExample.add("II");
		threelandExample.add("JJ");
		threelandExample.add("KK");
		threelandExample.add("LL");
		threelandExample.add("MM");
		threelandExample.add("NN");
		threelandExample.add("OO");
		threelandExample.add("PP");
		threelandExample.add("QQ");
		threelandExample.add("RR");
		threelandExample.add("SS");
		
		if (adjList) {
			g = new AdjacencyListGraph<String>(threelandExample);
		} else {
			g = new AdjacencyMatrixGraph<String>(threelandExample);
		}
		
		//Left Path
		g.addEdge("FF", "GG");
		g.addEdge("GG", "FF");
		g.addEdge("GG", "JJ");
		g.addEdge("JJ", "GG");
		g.addEdge("JJ", "MM");
		g.addEdge("MM", "JJ");
		g.addEdge("JJ", "NN");
		g.addEdge("NN", "JJ");
		g.addEdge("MM", "QQ");
		g.addEdge("QQ", "MM");
		g.addEdge("NN", "QQ");
		g.addEdge("QQ", "NN");
				
		//Middle Path
		g.addEdge("GG", "II");
		g.addEdge("II", "GG");
		g.addEdge("II", "LL");
		g.addEdge("LL", "II");
		g.addEdge("LL", "PP");
		g.addEdge("PP", "LL");
		g.addEdge("PP", "QQ");
		g.addEdge("QQ", "PP");		
		
		//Right Path
		g.addEdge("FF", "HH");
		g.addEdge("HH", "FF");
		g.addEdge("HH", "KK");
		g.addEdge("KK", "HH");
		g.addEdge("KK", "OO");
		g.addEdge("OO", "KK");
		g.addEdge("OO", "RR");
		g.addEdge("RR", "OO");
		g.addEdge("RR", "SS");
		g.addEdge("SS", "RR");
		g.addEdge("SS", "QQ");
		g.addEdge("QQ", "SS");
		
		return g;
	}
	
	@Test
	public void testThreelandExample1_AL() {
		Graph<String> g = buildThreelandExample(true);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("QQ", "FF");
		assertEquals(1, longDelays.size());
		assertTrue(longDelays.contains("GG"));
		graphALPoints += 1;
	}
	
	@Test
	public void testThreelandExample_AM() {
		Graph<String> g = buildThreelandExample(false);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("QQ", "FF");
		assertEquals(1, longDelays.size());
		assertTrue(longDelays.contains("GG"));
		graphAMPoints += 1;
	}
	
	private Graph<String> buildComplicanapolisExample(boolean adjList) {
		Graph<String> g;
		complicanapolisExample = new HashSet<String>();
		
		complicanapolisExample.add("A");
		complicanapolisExample.add("B");
		complicanapolisExample.add("C");
		complicanapolisExample.add("D");
		complicanapolisExample.add("E");
		complicanapolisExample.add("F");
		complicanapolisExample.add("G");
		complicanapolisExample.add("H");
		complicanapolisExample.add("I");
		complicanapolisExample.add("J");
		complicanapolisExample.add("K");
		complicanapolisExample.add("L");
		
		if (adjList) {
			g = new AdjacencyListGraph<String>(complicanapolisExample);
		} else {
			g = new AdjacencyMatrixGraph<String>(complicanapolisExample);
		}
		
		//Horizontals
		g.addEdge("A", "B");
		g.addEdge("B", "A");
		g.addEdge("B", "C");
		g.addEdge("C", "B");
		g.addEdge("C", "D");
		g.addEdge("D", "C");
		g.addEdge("F", "E");
		g.addEdge("G", "F");
		g.addEdge("G", "H");
		g.addEdge("H", "G");
		g.addEdge("I", "J");
		g.addEdge("J", "I");
		g.addEdge("J", "K");
		g.addEdge("K", "J");
		g.addEdge("K", "L");
		g.addEdge("L", "K");
		
		//Verticals
		g.addEdge("E", "A");
		g.addEdge("F", "B");
		g.addEdge("B", "F");
		g.addEdge("G", "C");
		g.addEdge("C", "G");
		g.addEdge("D", "H");
		g.addEdge("H", "D");
		g.addEdge("E", "I");
		g.addEdge("I", "E");
		g.addEdge("J", "F");
		g.addEdge("K", "G");
		g.addEdge("H", "L");
		g.addEdge("L", "H");
		
		//Diagonals
		g.addEdge("F", "A");
		g.addEdge("D", "G");
		g.addEdge("G", "J");
		g.addEdge("L", "F");
		g.addEdge("J", "E");
		return g;
	}
	
	@Test
	public void testComplicanapolisExample1_AL() {
		Graph<String> g = buildComplicanapolisExample(true);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("D", "E");
		assertEquals(1, longDelays.size());
		assertTrue(longDelays.contains("G"));
		graphALPoints += 1;
	}
	
	@Test
	public void testComplicanapolisExample_AM() {
		Graph<String> g = buildComplicanapolisExample(false);
		Set<String> longDelays;
		
		longDelays = g.longestDelays("D", "E");
		assertEquals(1, longDelays.size());
		assertTrue(longDelays.contains("G"));
		graphAMPoints += 1;
	}

	@AfterClass
	public static void displayPoints() {
		System.out.println("----------------------------------------------------------------------");
		System.out.printf("3. %2d/10  Graph (AL) points        \n", graphALPoints);
		System.out.printf("   %2d/10  Graph (AM) points        \n", graphAMPoints);
	}

}
