package linkedDeque;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @param <E> the type for the LinkedDeque
 *
 * @author ryanbosher 1374823
 */
public final class LinkedDeque<E> implements DequeADT<E> {
	private int size;
	private Node<E> front;
	private Node<E> rear;
	
	/**
	 * Default constructor
	 */
	public LinkedDeque() {
		front = null;
		rear = null;
	}
	
	/**
	 * constructor should not take a boolean parameter
	 *
	 * @param collection an existing collection to copy to a new LinkedDeque
	 * @param atRear     boolean flag to fill queue from front or rear
	 */
	public LinkedDeque(Collection<? extends E> collection, boolean atRear) {
		if (collection.isEmpty()) {
			throw new NoSuchElementException("Attempting to instatiate with an empty collection");
		} else {
			if (atRear) {
				for (E element : collection) {
					enqueueRear(element);
				}
			} else {
				for (E element : collection) {
					enqueueFront(element);
				}
			}
		}
	}
	
	/**
	 * @param element to add to rear
	 *
	 * @throws NoSuchElementException New element is null
	 */
	@Override
	public void enqueueRear(E element) {
		Node<E> newNode = new Node<>(element);
		if (newNode.equals(null)) {
			throw new NoSuchElementException("New element is null");
		} else {
			// Deque is empty, inserting and assiging to both ends
			if (rear == null) {
				rear = newNode;
				front = rear;
			} else {
				newNode.setPrev(rear);
				rear.setNext(newNode);
				rear = newNode;
			}
			++size;
		}
	}
	
	/**
	 * @param element to add to front
	 *
	 * @throws NoSuchElementException New element is null
	 */
	@Override
	public void enqueueFront(E element) {
		Node<E> returnNode = null;
		Node<E> newNode = new Node<>(element);
		if (newNode.equals(null)) {
			throw new NoSuchElementException("New element is null");
		} else {
			// Deque is empty, inserting and assiging to both ends
			if (front == null) {
				front = newNode;
				rear = front;
			} else {
				newNode.setNext(front);
				front.setPrev(newNode);
				front = newNode;
			}
			++size;
		}
	}
	
	/**
	 * @return and remove the last element
	 *
	 * @throws NoSuchElementException Attempting to deqeueu null element from rear
	 */
	@Override
	public E dequeueRear() {
		if (isEmpty()) {
			throw new NoSuchElementException("Attempting to deqeueu null element from rear");
		}
		E returnElement = rear.getElement();
		rear = rear.getPrev();
		
		if (rear == null) {
			front = null;
		} else {
			rear.setNext(null);
		}
		
		return returnElement;
	}
	
	/**
	 * @return and remove the first element
	 *
	 * @throws NoSuchElementException Attempting to deqeueu null element from front
	 */
	@Override
	public E dequeueFront() {
		if (isEmpty()) {
			throw new NoSuchElementException("Attempting to deqeueu null element from front");
		}
		E returnElement = front.getElement();
		front = front.getNext();
		
		if (front == null) {
			rear = null;
		} else {
			front.setPrev(null);
		}
		--size;
		return returnElement;
	}
	
	/**
	 * @return without removing the first element
	 *
	 * @throws NoSuchElementException Attempting to poll null element from front
	 */
	@Override
	public E first() {
		if (isEmpty()) {
			throw new NoSuchElementException("Attempting to poll null element from front");
		}
		return front.getElement();
	}
	
	/**
	 * @return without removing the last element
	 *
	 * @throws NoSuchElementException Attempting to poll null element from rear
	 */
	@Override
	public E last() {
		if (isEmpty()) {
			throw new NoSuchElementException("Attempting to poll null element from rear");
		}
		return rear.getElement();
	}
	
	/**
	 * If rear is empty, check front too using && operatrion order.
	 *
	 * @return whether the LinkedDeque is empty or not
	 */
	@Override
	public boolean isEmpty() {
		return rear == null && front == null;
	}
	
	/**
	 * @return the size as an int
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * TODO: Use iterator?
	 * Clears the LinkedDeque by setting the rear to null then running through all elements until the first is null
	 */
	@Override
	public void clear() {
		rear = null;
		while (front != null) {
			front = front.getNext();
			--size;
		}
	}
	
	/**
	 * @return a new LinkedDeque.LinkedDequeIterator
	 */
	@Override
	public Iterator<E> iterator() {
		return new LinkedDequeIterator<>(this);
	}
	
	/**
	 * @return the LinkedDeque elements enclosed by []
	 */
	@Override
	public String toString() {
		StringBuilder returnString = new StringBuilder(size).append("[");
		Iterator<E> iterator = iterator();
		while (iterator.hasNext()) {
			returnString.append(iterator.next());
			if (iterator.hasNext()) {
				returnString.append(",");
			}
		}
		returnString.append("]");
		return returnString.toString();
	}
	
	/**
	 * @param <E> the type of element to be iterated
	 *
	 * @author ryanbosher 1374823
	 */
	private static final class LinkedDequeIterator<E> implements Iterator<E> {
		private Node<E> nextNode;
		private int remaining;
		
		/**
		 * @param linkedDeque the object to iterate
		 */
		LinkedDequeIterator(LinkedDeque<E> linkedDeque) {
			remaining = linkedDeque.size();
			nextNode = linkedDeque.front;
		}
		
		/**
		 * @return whether the nextNode is null
		 */
		@Override
		public boolean hasNext() {
			return nextNode != null;
		}
		
		/**
		 * @return the next element
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException("Nothing to iterate to");
			}
			E returnElement = nextNode.getElement();
			nextNode = nextNode.getNext();
			--remaining;
			return returnElement;
		}
	}
	
	/**
	 * @param <E> the type of element referenced by the Node
	 *
	 * @author ryanbosher 1374823
	 */
	private static final class Node<E> {
		private E element;
		private Node<E> next;
		private Node<E> prev;
		
		/**
		 * Default constructor
		 *
		 * @param element the element to be stored in the node
		 */
		private Node(E element) {
			this.element = element;
			next = null;
			prev = null;
		}
		
		/**
		 * Override Constructor for single linking
		 *
		 * @param element the element to be stored in the node
		 * @param next    reference to the next node
		 */
		private Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
			prev = null;
		}
		
		/**
		 * Override Constructor for doubly linking
		 *
		 * @param element the element to be stored in the node
		 * @param next    reference to the next node
		 * @param prev    reference to the previous node
		 */
		private Node(E element, Node<E> next, Node<E> prev) {
			this.element = element;
			this.next = next;
			this.prev = prev;
		}
		
		/**
		 * @return the node's element
		 */
		public E getElement() {
			return element;
		}
		
		/**
		 * @param element the element for the ndoe
		 */
		public void setElement(E element) {
			this.element = element;
		}
		
		/**
		 * @return a reference to the next element
		 */
		public Node<E> getNext() {
			return next;
		}
		
		/**
		 * @param next the reference to be set for the next node
		 */
		public void setNext(Node<E> next) {
			this.next = next;
		}
		
		/**
		 * @return a reference to the previous element
		 */
		public Node<E> getPrev() {
			return prev;
		}
		
		/**
		 * @param prev a reference to set the previous element
		 */
		public void setPrev(Node<E> prev) {
			this.prev = prev;
		}
	}
}
