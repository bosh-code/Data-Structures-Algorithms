package arrayQueue;

import java.util.NoSuchElementException;

public interface QueueADT<E> {
	void enqueue(E element);
	
	/**
	 * @return the front element and remove it
	 *
	 * @throws NoSuchElementException if it does not exist
	 */
	E dequeue();
	
	/**
	 * @return the first element
	 *
	 * @throws NoSuchElementException if it does not exist
	 */
	E first();
	
	boolean isEmpty();
	
	int size();
}
