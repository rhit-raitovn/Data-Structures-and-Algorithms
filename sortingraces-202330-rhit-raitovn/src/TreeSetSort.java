import java.util.HashMap;
import java.util.TreeSet;

class TreeSetSort {
	static void sort(Integer[] array) {
		HashMap<Integer, Integer> map = new HashMap<>();
		TreeSet<Integer> set = new TreeSet<>();
		
		for (Integer elem : array) {
			if (set.add(elem))
				continue;
			if (!map.containsKey(elem)) 
				map.put(elem, 2);
			else 
				map.put(elem, map.remove(elem) + 1);
		}
		int index = 0;
		for (Integer elem : set) {
			try {
				Integer count = map.get(elem);
				for (int i = 0; i < count; i++) {
					array[index] = elem;
					index++;
				}
			} catch (NullPointerException e) {
				array[index] = elem;
				index++;
			}
		}
	}
}




