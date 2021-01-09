package linkedSortedSet;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedSet<E> extends AbstractSet<E> {
	protected int numElements;
	protected Node<E> firstNode;
	
	// constructor for creating a new set which
	// initially holds the elements in the collection c
	public LinkedSet(Collection<? extends E> c) {
		this();
		for (E element : c) {
			add(element);
		}
	}
	
	// default constructor that creates a new set
	// that is initially empty
	public LinkedSet() {
		numElements = 0;
		firstNode = null;
	}
	
	// returns an iterator for iterating through the elements in the set
	@Override
	public Iterator<E> iterator() {
		return new LinkedIterator<E>(firstNode);
	}
	
	// returns the number of elements in the set
	@Override
	public int size() {
		return numElements;
	}
	
	// adds the element to the set provided that it
	// is not already in the set, and returns
	// true if the set did not already contain the element
	@Override
	public boolean add(E o) {
		if (!contains(o)) {
			Node<E> newNode = new Node<E>(o);
			// add the new node to the front of the list
			newNode.next = firstNode;
			firstNode = newNode;
			numElements++;
			return true;
		} else
			return false;
	}
	
	// remove the element from the set and returns true if the
	// element was in the set
	@Override
	public boolean remove(Object o) {  // search for the node of the element o in the set
		boolean found = false;
		if (firstNode != null) {  // check if the element is first in list
			if (firstNode.element == null && o == null || firstNode.element != null && firstNode.element.equals(o)) {
				found = true;
				firstNode = firstNode.next;
				numElements--;
			} else {  // check the other nodes in the list
				Node<E> previous = firstNode;
				Node<E> current = firstNode.next;
				while (current != null && !found) {
					if (current.element == null && o == null || current.element != null && current.element.equals(o)) {
						found = true;
						previous.next = current.next;
						numElements--;
					} else {
						previous = current;
						current = current.next;
					}
				}
			}
		}
		return found;
	}
	
	// removes all elements from the set
	@Override
	public void clear() {
		firstNode = null;
		numElements = 0;
	}
	
	// inner class which represents a node in a singly-linked list
	protected static class Node<E> {
		public E element;
		public Node<E> next;
		
		public Node(E element) {
			this.element = element;
			next = null;
		}
	}
	
	// inner class which represents an iterator for a singly-linked list
	private static class LinkedIterator<E> implements Iterator<E> {
		private Node<E> nextNode; // next node to use for the iterator
		
		// constructor which accepts a reference to first node in list
		// and prepares an iterator which will iterate through the
		// entire linked list
		public LinkedIterator(Node<E> firstNode) {
			nextNode = firstNode; // start with first node in list
		}
		
		// returns whether there is still another element
		@Override
		public boolean hasNext() {
			return nextNode != null;
		}
		
		// returns the next element or throws a NoSuchElementException
		// it there are no further elements
		@Override
		public E next() throws NoSuchElementException {
			if (!hasNext())
				throw new NoSuchElementException();
			E element = nextNode.element;
			nextNode = nextNode.next;
			return element;
		}
		
		// remove method not supported by this iterator
		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
	}
}
