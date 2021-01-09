package selectionSort;

public final class SelectionSort {
	private SelectionSort() {}
	
	public static void main(String... args) {
		int[] ints = {4, 5, 7, 6, 8, 3, 2, 1};
		int minIndex = 0;
		int swapCount = 0;
		
		for (int currentIndex = 0; currentIndex < ints.length - 1; currentIndex++) {
			int currentSmallest = ints[currentIndex];
			
			for (int j = currentIndex + 1; j < ints.length; j++) {
				if (currentSmallest > ints[j]) {
					currentSmallest = ints[j];
					minIndex = j;
				}
			}
			
			System.out.println("Swapping " + ints[currentIndex] + " With " + currentSmallest + " At " + minIndex);
			int temp = ints[currentIndex];
			ints[currentIndex] = currentSmallest;
			
			ints[minIndex] = temp;
			++swapCount;
		}
		
		for (int num : ints) {
			System.out.println(num);
		}
		
		System.out.println("Total swaps: " + swapCount + " // Length: " + ints.length);
	}
	
}
