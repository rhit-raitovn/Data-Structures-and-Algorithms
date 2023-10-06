import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Links implements LinksInterface{
	
	private HashMap<String, HashSet<String>> map;
	private HashSet<String> dictionary;
	
	public Links(String filename) throws FileNotFoundException {
		map = new HashMap<String, HashSet<String>>();
		dictionary = new HashSet<String>();
		
		Scanner scanner = new Scanner(new File(filename));
		
		while (scanner.hasNext()) {
			dictionary.add(scanner.next());
		}
		scanner.close();
		
		for (String word : dictionary) {
			addNeighbors(word);
		}
	}

	private void addNeighbors(String word) {
		HashSet<String> neighbors = new HashSet<String>();
		
		for (int k = 0; k < word.length(); k ++) {
			addNeighborsAtPosition(k, word, neighbors);
		}
		
//		if (!neighbors.isEmpty()) {
			map.put(word, neighbors);
//		}	
	}

	private void addNeighborsAtPosition(int k, String word, HashSet<String> neighbors) {
		String front = word.substring(0, k);
		String back = word.substring(k+1);
		
		for (char ch = 'a'; ch <= 'z'; ch++) {
			if (ch == word.charAt(k)) {
				continue;
			}
			
			String possibleWord = front + ch + back;
			
			if (dictionary.contains(possibleWord))
				neighbors.add(possibleWord);
		}
		
	}

	@Override
	public Set<String> getCandidates(String word) {
		Set<String> candidates = map.get(word);
		
//		if (candidates == null || candidates.size() == 0) {
//			return new HashSet<String>();
//		}
		
		return candidates;
	}

	@Override
	public boolean exists(String word) {
		return map.containsKey(word);
	}

}
