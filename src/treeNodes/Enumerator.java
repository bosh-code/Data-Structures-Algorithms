package treeNodes;

import java.util.Enumeration;
import java.util.Iterator;

class Enumerator<E> implements Enumeration<E> {
	private final Iterator<E> iterator;
	
	Enumerator(Iterator<E> iterator) {
		this.iterator = iterator;
	}
	
	@Override
	public boolean hasMoreElements() {
		return iterator.hasNext();
	}
	
	@Override
	public E nextElement() {
		return iterator.next();
	}
}
