package arraySorter;

public class ArraySorter<E extends Comparable<E>> {
	
	public static final double TIME = 1000.0;
	private int numComparisons;
	
	public static void main(String... args) {
		ArraySorter<String> sorter = new ArraySorter<>();
		String[] list = {"123", "937", "414", "345", "865", "222", "742", "143", "894", "567"};
		
		System.out.println("Merge Sort");
		double startTime = System.nanoTime() / TIME;
		sorter.mergeSort(list);
		double endTime = System.nanoTime() / TIME;
		sort(sorter, list, startTime, endTime);
		
		System.out.println("\nSelection Sort");
		startTime = System.nanoTime() / TIME;
		sorter.selectionSort(list);
		endTime = System.nanoTime() / TIME;
		sort(sorter, list, startTime, endTime);
		
		System.out.println("\nQuick Sort");
		startTime = System.nanoTime() / TIME;
		sorter.quickSort(list);
		endTime = System.nanoTime() / TIME;
		sort(sorter, list, startTime, endTime);
		
		System.out.println("\nInsertion Sort");
		startTime = System.nanoTime() / TIME;
		sorter.insertionSort(list);
		endTime = System.nanoTime() / TIME;
		sort(sorter, list, startTime, endTime);
		
		System.out.println("\nBubble Sort");
		startTime = System.nanoTime() / TIME;
		sorter.bubbleSort(list);
		endTime = System.nanoTime() / TIME;
		sort(sorter, list, startTime, endTime);
	}
	
	/**
	 * Helper method to print results.
	 * @param array for num compare
	 * @param list the list of nums
	 * @param startTime start
	 * @param endTime end
	 */
	private static void sort(ArraySorter<String> array, String[] list, double startTime, double endTime) {
		for (int i = 0; i < list.length; i++) {
			if (i > 0) {
				System.out.print(", " + list[i]);
			} else {
				System.out.print(list[i]);
			}
		}
		System.out.println("\nNano time elapsed: " + (endTime - startTime) + "\nComparisons: " + array.numComparisons);
	}
	
	@SafeVarargs
	public final void selectionSort(E... list) {
		
		for (int i = 0; i < list.length - 1; i++) {
			int indexMin = i;
			
			for (int j = i + 1; j < list.length; j++) {
				numComparisons++;
				if (list[j].compareTo(list[indexMin]) < 0) {
					indexMin = j;
				}
			}
			
			E temp = list[indexMin];
			list[indexMin] = list[i];
			list[i] = temp;
		}
	}
	
	@SafeVarargs
	public final void insertionSort(E... list) {
		E elementInsert;
		for (int i = 1; i < list.length; i++) {
			elementInsert = list[i];
			numComparisons++;
			int indexInsert = i;
			while (indexInsert > 0 && list[indexInsert - 1].compareTo(elementInsert) > 0) {
				numComparisons++;
				list[indexInsert] = list[indexInsert - 1];
				indexInsert--;
			}
			list[indexInsert] = elementInsert;
		}
	}
	
	@SafeVarargs
	public final void bubbleSort(E... list) {
		for (int i = list.length - 1; i >= 0; i--) {
			numComparisons++;
			for (int j = 0; j < i; j++) {
				numComparisons++;
				if (list[j].compareTo(list[j + 1]) > 0) {
					E temp = list[j + 1];
					list[j + 1] = list[j];
					list[j] = temp;
				}
			}
		}
	}
	
	@SafeVarargs
	public final void quickSort(E... list) {
		quickSortSegment(list, 0, list.length);
	}
	
	private void quickSortSegment(E[] list, int start, int end) {
		if (end - start > 1) {
			int indexPartition = partition(list, start, end);
			quickSortSegment(list, start, indexPartition);
			quickSortSegment(list, indexPartition + 1, end);
		}
	}
	
	private int partition(E[] list, int start, int end) {
		E partitionElement = list[start];
		int leftIndex = start;
		int rightIndex = end - 1;
		
		while (leftIndex < rightIndex) {
			while (list[leftIndex].compareTo(partitionElement) <= 0 && leftIndex < rightIndex) {
				numComparisons++;
				leftIndex++;
			}
			while (list[rightIndex].compareTo(partitionElement) > 0) {
				numComparisons++;
				rightIndex--;
			}
			if (leftIndex < rightIndex) {
				E temp = list[leftIndex];
				list[leftIndex] = list[rightIndex];
				list[rightIndex] = temp;
			}
		}
		list[start] = list[rightIndex];
		list[rightIndex] = partitionElement;
		return rightIndex;
	}
	
	@SafeVarargs
	public final void mergeSort(E... list) {
		mergeSortSegment(list, 0, list.length);
	}
	
	private void mergeSortSegment(E[] list, int start, int end) {
		int numElements = end - start;
		if (numElements > 1) {
			int middle = (start + end) / 2;
			mergeSortSegment(list, start, middle);
			mergeSortSegment(list, middle, end);
			E[] tempList = (E[]) new Comparable[numElements];
			System.arraycopy(list, start, tempList, 0, numElements);
			int indexLeft = 0;
			int indexRight = middle - start;
			for (int i = 0; i < numElements; i++) {
				if (indexLeft < middle - start) {
					if (indexRight < end - start) {
						numComparisons++;
						if (tempList[indexLeft].compareTo(tempList[indexRight]) < 0) {
							list[start + i] = tempList[indexLeft];
							indexLeft++;
						} else {
							list[start + i] = tempList[indexRight];
							indexRight++;
						}
					} else {
						list[start + i] = tempList[indexLeft];
						indexLeft++;
					}
				} else {
					list[start + i] = tempList[indexRight];
					indexRight++;
				}
			}
		}
	}
	
	public int getNumComparisons() {
		return numComparisons;
	}
}
