package linkedDeque;

import java.util.Iterator;

/**
 * @author sehall
 */
interface DequeADT<E> {
	void enqueueRear(E element);
	
	void enqueueFront(E element);
	
	E dequeueRear();
	
	E dequeueFront();
	
	E first();
	
	E last();
	
	boolean isEmpty();
	
	int size();
	
	void clear();
	
	Iterator<E> iterator();
}
