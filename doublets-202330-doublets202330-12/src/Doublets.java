import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author <<TODO>>
 */
public class Doublets {
	private LinksInterface links;
	
	public Doublets(LinksInterface links) {
		this.links = links;
	}

	public static void main(String[] args) throws FileNotFoundException {
		// Scanning the use input;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Doublets, a game of \"verbal torture.\"");
		
		// Starting and ending words
		String start = "";
		String end = "";
		Chain chain = new Chain();
		Links link = new Links("english.cleaned.all.35.txt");
		
		// Creating doublets
		Doublets doublets = new Doublets(link);
		ChainManager manager = null;
		while (true) {
			// Getting starting and ending words from the user
			System.out.print("Enter starting word: ");
			start = scanner.next();
			System.out.print("Enter ending word: ");
			end = scanner.next();
			
			// If the word is not valid, ask again
			if (link.getCandidates(start) == null)
				System.out.println("The word " + start + " is not valid. Please try again.");
			else if (link.getCandidates(end) == null)
				System.out.println("The word " + end + " is not valid. Please try again.");
			
			// The user chooses the chain manager
			else {
				System.out.print("Enter chain manager (s: stack, q: queue, p: priority queue, x: exit): ");
				String chainManager = "";
				while ( chainManager != null) {
					if (chainManager.equals("x"))
						break;
					else if (chainManager.equals("s")) {
						manager = new StackChainManager();
						break;
					}
					else if (chainManager.equals("p")) {
						manager = new PriorityQueueChainManager(end);
						break;
					}
					else {
						manager = new QueueChainManager();
						break;
					}
				}
				
				// Finding the doublet chain between the starting and ending words
				chain = doublets.findChain(start, end, manager);
				
				if (chain != null) {
					System.out.println("Chain: " + chain);
					System.out.println("Length: " + chain.length());
					System.out.println("Candidates: " + link.getCandidates(start).size());
					System.out.println("Max size: " + manager.maxSize());
				} else
					System.out.println("No doublet chain exists from " + start + " to " + end + ".");
				}
		}
		
		System.out.println("Goodbye!");
	}

	public Chain findChain(String start, String end, ChainManager manager) {
		
		// Return null of the length of the start and end words is different
		if (start.length() != end.length()) 
			return null;
		
		// If start or end word isn't in the dictionary, return null
		if(links.getCandidates(start) == null || links.getCandidates(end) == null)
			return null;
			
		// Making a set of the words that have already been checked
		HashSet<String> checkedWords = new HashSet<String>();
		manager.add(new Chain().addLast(start));
		checkedWords.add(start);
		
		// Setting a max iterations
		int iterations = 1000;
		for (int i = 0; i < iterations; i++) { 
				
			int chains = manager.maxSize();
			
			// Run through the current list of chains
			for (int c = 0; c < chains; c++) {
				
				Chain currentChain = manager.next();
					
				// Break if either the chain or the list is NullNode
				if (currentChain == null) 
					break;
				
				// Getting the list of candidates
				String word = currentChain.getLast();
				Set<String> words = links.getCandidates(word);
				// If the word is a NullNode continue the loop with other words
				if (words == null) 
					continue;
					
				// Add the word to the list of the checked words
				checkedWords.add(word);
				for (String w : words) {
					Chain chain = currentChain.addLast(w);
						
					// Return if the word is the one we're searching for
					if (w.equals(end)) return chain;
						
					// Otherwise, keep looking
					if (checkedWords.contains(w) == false)
						manager.add(currentChain.addLast(w));
				}
			}
		}
		return null;
	}

}
