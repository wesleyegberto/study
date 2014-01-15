package javaComoProgramar.cap22;

public class SortedList<T extends Comparable<T>> {
	private ListNode<T> firstNode;
	private ListNode<T> lastNode;
	private String name;

	public SortedList() {
		this("SortedList");
	}

	public SortedList(String listName) {
		name = listName;
		firstNode = lastNode = null;
	}

	public void insert(T insertItem) {
		ListNode<T> newPos = new ListNode<T>(insertItem);

		if(isEmpty())
			firstNode = lastNode = new ListNode<T>(insertItem);
		else if(firstNode == lastNode) {
			if(firstNode.data.compareTo(insertItem) > 0) {
				newPos.nextNode = firstNode;
				firstNode = newPos;
			} else {
				firstNode.nextNode = lastNode = newPos;
			}
		} else {
			ListNode<T> pos = firstNode;
			ListNode<T> ant = firstNode;

			while(pos != null) {
				if(pos == lastNode) {
					if(pos.data.compareTo(insertItem) > 0) {
						newPos.nextNode = pos;
						ant.nextNode = newPos;
					} else {
						pos.nextNode = lastNode = newPos;
					}
					return;
				} else if(pos.data.compareTo(insertItem) >= 0) {
					if(pos == firstNode) {
						newPos.nextNode = pos;
						firstNode = newPos;
					} else {
						newPos.nextNode = pos;
						ant.nextNode = newPos;
					}
					return;
				}
				ant = pos;
				pos = pos.nextNode;
			}
		}
	}

	public T removeFromFront() throws EmptyListException {
		if(isEmpty())
			throw new EmptyListException(name);

		T removedItem = firstNode.data;

		if(firstNode == lastNode)
			firstNode = lastNode = null;
		else
			firstNode = firstNode.nextNode;

		return removedItem;
	}

	public T removeFromBack() throws EmptyListException {
		if(isEmpty())
			throw new EmptyListException(name);

		T removedItem = lastNode.data;

		if(firstNode == lastNode)
			firstNode = lastNode = null;
		else {
			ListNode<T> current = firstNode;

			while(current.nextNode != lastNode)
				current = current.nextNode;

			lastNode = current;
			current.nextNode = null;
		}

		return removedItem;
	}

	public void merge(SortedList<T> orig) {
		while(!orig.isEmpty())
			insert(orig.removeFromFront());
	}

	public boolean isEmpty() {
		return firstNode == null;
	}

	public void print() {
		if(isEmpty()) {
			System.out.printf("Empty %s\n", name);
			return;
		}

		System.out.printf("The %s is: ", name);
		ListNode<T> current = firstNode;

		while(current != null) {
			System.out.printf("%s ", current.data);
			current = current.nextNode;
		}

		System.out.println();
	}
}
