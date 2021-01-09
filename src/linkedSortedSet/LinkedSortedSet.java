package linkedSortedSet;

import java.util.Collection;
import java.util.Set;

public class LinkedSortedSet<E extends Comparable<E>> extends LinkedSet<E> {
	
	public LinkedSortedSet(Collection<? extends E> collection) {
		super(collection);
	}
	
	public LinkedSortedSet() {
	}
	
	public static void main(String... args) {
		Set<String> testSet = new LinkedSortedSet<>();
		testSet.add("Bacon");
		testSet.add("Orange");
		testSet.add("Banana");
		testSet.add("Banana");
		testSet.add("Apple");
		testSet.add("Mandarin");
		
		System.out.println(testSet);
	}
	
	@Override
	public boolean add(E o) {
		boolean result = true;
		Node<E> newNode = new Node<E>(o);
		
		if (contains(o)) {
			result = false;
		} else if (firstNode == null || firstNode.element.compareTo(newNode.element) > 0) {
			newNode.next = firstNode;
			firstNode = newNode;
		} else {
			Node<E> currentNode = firstNode;
			boolean finished = false;
			
			while (currentNode.next != null) {
				if (newNode.element.compareTo(currentNode.next.element) < 0) {
					newNode.next = currentNode.next;
					currentNode.next = newNode;
					numElements++;
					finished = true;
					
					break;
				}
				currentNode = currentNode.next;
			}
			if (!finished) {
				currentNode.next = newNode;
				numElements++;
			}
		}
		
		return result;
	}
}