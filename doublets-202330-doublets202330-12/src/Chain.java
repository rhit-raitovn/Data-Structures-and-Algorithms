import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Chain implements Iterable<String>{

	private Set<String> words;
	private String last;
	
	public Chain() {
		words = new LinkedHashSet<String>();
	}
	
	public boolean contains(String word) {
		return words.contains(word);
	}
	
	public int length() {
		return words.size();
	}
	
	public String getLast() {
		return last;
	}
	
	public Chain addLast(String word) {
		Chain longerChain = new Chain();
		
		longerChain.words = new LinkedHashSet<String>(this.words);
		longerChain.last = word;
		longerChain.words.add(word);
		
		return longerChain;
	}

	@Override
	public Iterator<String> iterator() {
		return words.iterator();
	}
	
	public String toString() {
		String str = "";
		for (String w : words) {
			str += w;
		}
		return str;
		
	}

}
