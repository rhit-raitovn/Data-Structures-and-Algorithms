package hash;
import static org.junit.Assert.*;
import org.junit.*;


// Test class
public class IntegerHashSetTest {

	private static int points = 0;
	
	private void loadUpSet(IntegerHashSet set, Integer a1[]) {
		for (int k = 0; k < a1.length; k++) {
			set.add(a1[k]);
		} 		
	}
	
	@BeforeClass public static void displayStart() {
		System.out.println("*** IntegerHashSet Tests ***");
	}
	
	@Test
	public void testRemoveSimpleNoOverlap() {
		IntegerHashSet set = new IntegerHashSet(5);
		set.add(10);
		assertTrue(set.remove(10));
		assertEquals("[(null-true), (null-false), (null-false), (null-false), (null-false)]", set.toString());
		points += 1;
		
		assertFalse(set.remove(10)); //Shouldn't be able to remove again
		assertEquals("[(null-true), (null-false), (null-false), (null-false), (null-false)]", set.toString());
		points += 1;
		
		set = new IntegerHashSet(5); //Resetting
		final Integer itemsToAdd[] = {11, 12, 14};
		this.loadUpSet(set, itemsToAdd);
		assertTrue(set.remove(11));
		assertEquals("[(null-false), (null-true), (12-false), (null-false), (14-false)]", set.toString());
		points += 1;		
	}
	
	@Test
	public void testRemoveSimpleOverlap() {
		IntegerHashSet set = new IntegerHashSet(5);
		final Integer itemsToAdd[] = {10, 15, 20}; //All hash to index 0 (i.e., Overlap)
		this.loadUpSet(set, itemsToAdd);
		assertTrue(set.remove(10));
		assertEquals("[(null-true), (15-false), (20-false), (null-false), (null-false)]", set.toString());
		
		set = new IntegerHashSet(5); //Resetting
		this.loadUpSet(set, itemsToAdd);
		assertTrue(set.remove(15));
		assertEquals("[(10-false), (null-true), (20-false), (null-false), (null-false)]", set.toString());
		points += 2;
		
		//Now there's a gap
		assertTrue(set.remove(20));
		assertEquals("[(10-false), (null-true), (null-true), (null-false), (null-false)]", set.toString());
		points += 1;
	}
	
	@Test
	public void testContainsSimpleNotFullNoOverlap() {
		IntegerHashSet set = new IntegerHashSet(5);
		final Integer itemsToAdd[] = {10, 11, 13};
		this.loadUpSet(set, itemsToAdd);
		
		assertTrue(set.contains(10));
		assertTrue(set.contains(11));
		assertTrue(set.contains(13));
		points += 2;
		
		assertFalse(set.contains(12));
		assertFalse(set.contains(14));
		assertFalse(set.contains(15));
		assertFalse(set.contains(16));
		assertFalse(set.contains(17));
		assertFalse(set.contains(18));
		assertFalse(set.contains(19));
		points += 1;
	}
	
	@Test
	public void testContainsSimpleNotFullNoOverlapWraparound() {
		IntegerHashSet set = new IntegerHashSet(5);
		final Integer itemsToAdd[] = {14, 24, 10};
		this.loadUpSet(set, itemsToAdd);
		
		assertTrue(set.contains(14));
		assertTrue(set.contains(10));
		assertTrue(set.contains(24));
		points += 3;
	}
	
	@Test
	public void testContainsSimpleNotFullOverlap() {
		IntegerHashSet set = new IntegerHashSet(5);
		final Integer itemsToAdd[] = {10, 15, 20};
		this.loadUpSet(set, itemsToAdd);
		
		assertTrue(set.contains(10));
		assertTrue(set.contains(15));
		assertTrue(set.contains(20));
		
		assertFalse(set.contains(11));
		assertFalse(set.contains(12));
		assertFalse(set.contains(25));
		assertFalse(set.contains(16));
		assertFalse(set.contains(17));
		assertFalse(set.contains(18));
		assertFalse(set.contains(19));		
		points += 1;
	}
	
	@Test
	public void testContainsSimpleFull() {
		IntegerHashSet set = new IntegerHashSet(5);
		final Integer itemsToAdd[] = {10, 11, 12, 13, 14};
		this.loadUpSet(set, itemsToAdd);
		
		assertTrue(set.contains(10));
		assertTrue(set.contains(11));
		assertTrue(set.contains(12));
		assertTrue(set.contains(13));
		assertTrue(set.contains(14));	
		
		assertFalse(set.contains(15));
		assertFalse(set.contains(16));
		assertFalse(set.contains(17));
		assertFalse(set.contains(18));
		assertFalse(set.contains(19));
		points += 1;
	}
	

	
	@Test
	public void testContainsAfterRemoveSimple() {
		IntegerHashSet set = new IntegerHashSet(5);
		set.add(10);
		assertTrue(set.contains(10));
		assertTrue(set.remove(10));
		assertFalse(set.contains(10));
		points += 2;
		
		set = new IntegerHashSet(5); //Resetting
		final Integer itemsToAdd[] = {11, 16, 21}; //All hash to index 1 (i.e., Overlap)
		this.loadUpSet(set, itemsToAdd);
		assertTrue(set.contains(11));
		assertTrue(set.contains(16));
		assertTrue(set.contains(21));
		assertTrue(set.remove(11));
		assertTrue(set.contains(16));
		assertTrue(set.contains(21));
		points += 1;
		
		//Now test with a gap
		set = new IntegerHashSet(5); //Resetting
//		final Integer itemsToAdd[] = {11, 16, 21}; //All hash to index 1 (i.e., Overlap)
		this.loadUpSet(set, itemsToAdd);
		assertTrue(set.remove(16));//Creates a gap
		assertTrue(set.contains(11));
		assertFalse(set.contains(16));
		assertTrue(set.contains(21));
		points += 1;
	}
	
	@Test
	public void testContainsEfficiency() {
		int capacity = 1000000;// 10,000,000
		IntegerHashSet set = new IntegerHashSet(capacity); 
		//Filling each index with a value that matches the index
		for (int i = 0; i < capacity; i++) {
			set.add(i);
		}
		
		Long startTimeSearchAll = System.currentTimeMillis();
		assertFalse(set.contains(capacity)); //O(n)
		Long endTimeSearchAll = System.currentTimeMillis() - startTimeSearchAll;
		
		Long startTimeSearchOne = System.currentTimeMillis();
		assertTrue(set.contains(capacity-1)); //O(1)
		Long endTimeSearchOne = System.currentTimeMillis() - startTimeSearchOne;
		
		System.out.println("     IntegerHashSet Contains Efficiency Test");
		System.out.printf("		O(n) Call: %4d ms\n", endTimeSearchAll);
		System.out.printf("		O(1) Call: %4d ms\n", endTimeSearchOne);
		
		assertTrue(endTimeSearchAll > endTimeSearchOne);
		
		points += 2;
	}
	
	
	@AfterClass
	public static void displayPoints() {
		System.out.printf("     IntegerHashSet unit test points:             %2d / 20\n\n", points);
	}
	

	
}
