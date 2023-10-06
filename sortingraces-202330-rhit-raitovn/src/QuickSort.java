
class QuickSort {
	/**
	 * Sorts an array of integers using the QuickSort algorithm
	 *
	 * @param array the array to be sorted
	 */
	static void sort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}
	
	private static void quickSort(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int partition = array[start + (end - start) / 2];
        int i = start, j = end;
        while (i <= j) {
            while (array[i] < partition) {
                i++;
            }
            while (array[j] > partition) {
                j--;
            }
            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        quickSort(array, start, j);
        quickSort(array, i, end);
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}