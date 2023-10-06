
public class HW2 {
	
	public static boolean problem6(int[] array) { // This is a binary search
	    int low = 0;                              // the first element
	    int high = array.length - 1;              // the last element
	    while (low <= high) {
	        int middle = (low + high) / 2;        // the middle element of the array
	        if (array[middle] == middle)          // checking if a[i] = i
	            return true;                      // return if it's a "right" array
	        else if (array[middle] < middle)      
	            low = middle++;
	        else
	            high = middle--;
	    }
	    return false;                             // it's not a "right" array
	}

	public static void main(String[] args) {     // checking
		int[] arr = {0,1,2,3,4,5,6};
		int[] arr1 = {1,2,3,4,5,6,7,9};
		System.out.println(problem6(arr));      // true
		System.out.println(problem6(arr1));     // false
	}                                            // run time O(N) -- one loop 

}
