package arrayQueue;

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements QueueADT<E> {
	private static final int INIT_SIZE = 0;
	private E firstElement;
	private int firstIndex;
	private int lastIndex;
	private int size;
	private E[] array;
	
	ArrayQueue() {
		size = 0;
		firstIndex = 0;
		lastIndex = 0;
		array = (E[]) new Object[INIT_SIZE];
	}
	
	public static void main(String... args) {
		ArrayQueue<Integer> integerArrayQueue = new ArrayQueue<>();
		integerArrayQueue.enqueue(1);
		integerArrayQueue.enqueue(2);
		integerArrayQueue.enqueue(3);
		integerArrayQueue.enqueue(4);
		integerArrayQueue.enqueue(5);
		System.out.println(integerArrayQueue);
	}
	
	/**
	 * Adds an element to the rear of the Queue
	 *
	 * @param element new element to be added
	 */
	@Override
	public void enqueue(E element) {
		if (array.length == size || array.length <= 0) {
			expandCapacity();
		}
		if (isEmpty()) {
			firstIndex = 0;
			array[firstIndex] = element;
		} else {
			array[lastIndex] = element;
		}
		size++;
		lastIndex = size;
	}
	
	/**
	 * Removes and returns the front element in the queue.
	 *
	 * @return the element from the front of the Queue
	 *
	 * @throws NoSuchElementException if there is no element to return
	 */
	@Override
	public E dequeue() {
		return null;
	}
	
	/**
	 * returns the first element but does not remove it
	 *
	 * @return element the first element in the queue
	 *
	 * @throws NoSuchElementException ignored
	 */
	@Override
	public E first() {
		return array[firstIndex];
	}
	
	/**
	 * @return true if no elements
	 */
	@Override
	public boolean isEmpty() {
		return size <= 0;
	}
	
	/**
	 * @return the number of elements in the queue
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Ripped from ArrayStack
	 */
	private void expandCapacity() {
		E[] largerArray = (E[]) new Object[(size + 1)];
		if (size >= 0) {
			System.arraycopy(array, 0, largerArray, 0, size);
			array = largerArray;
		}
	}
	
	@Override
	public String toString() {
		StringBuilder text = new StringBuilder("[");
		for (E e : array) {
			text.append(e);
			if (e != array[array.length - 1]) {
				text.append(", ");
			}
		}
		text.append("]");
		
		return text.toString();
	}
}
