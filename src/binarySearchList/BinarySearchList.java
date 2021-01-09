package binarySearchList;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class BinarySearchList {
	public static final int MAX = 30;
	private static final boolean VERBOSE_ = false;
	private final List<Integer> integerLinkedList = new LinkedList<>();
	private int numComparisons;
	private int numIterations;
	
	
	public BinarySearchList(int numElements) {
		for (int index = 1; index <= numElements; index++) {
			integerLinkedList.add(index);
		}
	}
	
	/**
	 * TODO: Introduce VERBOSE_ args
	 *
	 * @param args unsued
	 *
	 * @throws InterruptedException if the search is inerupted
	 */
	public static void main(String... args) throws InterruptedException {
		for (int i = 1; i <= MAX; i++) {
			BinarySearchList binarySearchList = new BinarySearchList(MAX);
			System.out.println(binarySearchList);
			binarySearchList.search(i);
			System.out.println("Target was = " + i + "\n");
			Thread.sleep(100);
		}
		
		BinarySearchList binarySearchList = new BinarySearchList(10);
		System.out.println(binarySearchList);
		int targetInt = 6;
		binarySearchList.search(targetInt);
		System.out.println("Target was = " + targetInt + "\n");
		
	}
	
	public void search(int target) {
		int index = search(target, 0, integerLinkedList.size());
		
		if (index < 0) {
			System.out.println("Could not find target");
		} else {
			System.out.println("Found " + target + " at: " + index + "\nTook " + numComparisons + " comparisons and " + numIterations + " goes through the list.");
		}
	}
	
	public int search(int target, int start, int end) {
		int result;
		if (start > end) {
			result = -start - 1;
		} else {
			numComparisons++;
			int middleIndex = (start + end) / 2;
			int element = getMiddleElement(middleIndex);
			int comparison = target - element;
			if (comparison == 0) {
				result = middleIndex;
			} else if (comparison < 0) {
				result = search(target, start, middleIndex);
			} else {
				result = search(target, middleIndex + 1, end);
			}
		}
		return result;
	}
	
	private int getMiddleElement(int middleInt) {
		ListIterator<Integer> integerListIterator = integerLinkedList.listIterator();
		int element = 0;
		for (int i = 0; i <= middleInt; i++) {
			numIterations++;
			element = integerListIterator.next();
		}
		return element;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder(MAX);
		if (VERBOSE_) {
			for (Integer i : integerLinkedList) {
				stringBuilder.append(i).append(", ");
			}
		} else {
			stringBuilder.append("BinarySearchList{" + "integerLinkedList=").append(integerLinkedList).append(", numComparisons=").append(numComparisons).append(", numIterations=").append(numIterations).append('}');
			
		}
		return stringBuilder.toString();
	}
}
