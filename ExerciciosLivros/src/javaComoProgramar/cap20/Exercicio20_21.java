package javaComoProgramar.cap20;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Exercicio20_21 {
	public static void main(String[] args) {
		PriorityQueue<Double> queue = new PriorityQueue<Double>(0, new ComparatorMiddle());

		queue.offer(3.2);
		queue.offer(9.8);
		queue.offer(5.4);

		System.out.print("Polling from queue: ");

		while(queue.size() > 0) {
			System.out.printf("%.1f; ", queue.peek());
			queue.poll();
		}
	}

	static class ComparatorMiddle implements Comparator<Double> {
		@Override
		public int compare(Double d1, Double d2) {
			return -(d1.compareTo(d2));
		}

	}
}
