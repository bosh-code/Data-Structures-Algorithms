package josephusQueue;

import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class JosephusQueue {
	
	private final Deque<Integer> elements;
	private int gap;
	
	public JosephusQueue(Deque<Integer> queue) {
		elements = queue;
	}
	
	public static void main(String... args) {
		Deque<Integer> deque;
		JosephusQueue queue;
		int numElements;
		try (Scanner scan = new Scanner(System.in, StandardCharsets.UTF_8)) {
			deque = new ArrayDeque<>();
			queue = new JosephusQueue(deque);
			
			System.out.print("Enter the number of elements: ");
			numElements = scan.nextInt();
			scan.nextLine();
			
			System.out.print("Enter the gap between elements: ");
			queue.gap = scan.nextInt();
			scan.nextLine();
		}
		
		for (int i = 0; i < numElements; i++) {
			deque.add(i);
		}
		
		System.out.println("Time Start!");
		long startTime = System.currentTimeMillis();
		System.out.println("Removal order: " + queue.removeElements());
		long timeTaken = System.currentTimeMillis() - startTime;
		
		System.out.println("Elapsed time (in ms): " + timeTaken);
	}
	
	public String removeElements() {
		StringBuilder removeOrder = new StringBuilder(elements.size());
		int count = 1;
		while (!elements.isEmpty()) {
			if (count % gap == 0) {
				removeOrder.append(elements.getFirst()).append(" ");
				elements.removeFirst();
			} else {
				Integer hoppedElement = elements.getFirst();
				elements.removeFirst();
				elements.add(hoppedElement);
			}
			count++;
			
		}
		return removeOrder.toString();
	}
}
