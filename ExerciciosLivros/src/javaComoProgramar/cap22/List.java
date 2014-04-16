package javaComoProgramar.cap22;

class ListNode<T>
{
	T data;
	ListNode<T> nextNode;

	// constructor creates a ListNode that refers to object
	ListNode(T object)
	{
		this(object, null);
	} // end ListNode one-argument constructor
	
	ListNode(T object, ListNode<T> node)
	{
		data = object;
		nextNode = node;
	} // end ListNode two-argument constructor

	// return reference to data in node
	T getData()
	{
		return data; // return item in this node
	} // end method getData

	// return reference to next node in list
	ListNode<T> getNext()
	{
		return nextNode; // get next node
	} // end method getNext
} // end class ListNode< T >

// class List definition
public class List<T>
{
	private ListNode<T> firstNode;
	private ListNode<T> lastNode;
	private String name; // string like "list" used in printing

	// constructor creates empty List with "list" as the name
	public List()
	{
		this("list");
	} // end List no-argument constructor

	// constructor creates an empty List with a name
	public List(String listName)
	{
		name = listName;
		firstNode = lastNode = null;
	} // end List one-argument constructor

	// insert item at front of List
	public void insertAtFront(T insertItem)
	{
		if(isEmpty()) // firstNode and lastNode refer to same object
			firstNode = lastNode = new ListNode<T>(insertItem);
		else
			// firstNode refers to new node
			firstNode = new ListNode<T>(insertItem, firstNode);
	} // end method insertAtFront

	// insert item at end of List
	public void insertAtBack(T insertItem)
	{
		if(isEmpty()) // firstNode and lastNode refer to same object
			firstNode = lastNode = new ListNode<T>(insertItem);
		else
			// lastNode's nextNode refers to new node
			lastNode = lastNode.nextNode = new ListNode<T>(insertItem);
	} // end method insertAtBack

	// remove first node from List
	public T removeFromFront() throws EmptyListException
	{
		if(isEmpty()) // throw exception if List is empty
			throw new EmptyListException(name);

		T removedItem = firstNode.data; // retrieve data being removed

		// update references firstNode and lastNode
		if(firstNode == lastNode)
			firstNode = lastNode = null;
		else
			firstNode = firstNode.nextNode;

		return removedItem; // return removed node data
	} // end method removeFromFront

	// remove last node from List
	public T removeFromBack() throws EmptyListException
	{
		if(isEmpty()) // throw exception if List is empty
			throw new EmptyListException(name);

		T removedItem = lastNode.data; // retrieve data being removed

		// update references firstNode and lastNode
		if(firstNode == lastNode)
			firstNode = lastNode = null;
		else
		// locate new last node
		{
			ListNode<T> current = firstNode;

			// loop while current node does not refer to lastNode
			while(current.nextNode != lastNode)
				current = current.nextNode;

			lastNode = current; // current is new lastNode
			current.nextNode = null;
		} // end else

		return removedItem; // return removed node data
	} // end method removeFromBack

	public T search(T key)
	{
		return search(key, firstNode);
	}
	
	private T search(T key, ListNode<T> node)
	{
		T value = null;
		if(key.equals(node.data))
			value = node.data;
		else if(node.nextNode != null)
			value = search(key, node.nextNode);

		return value;
	}
	
	// determine whether list is empty
	public boolean isEmpty()
	{
		return firstNode == null; // return true if list is empty
	} // end method isEmpty

	public static <T> List<T> reverse(List<T> list)
	{
		List<T> listReverse = new List<T>();

		while(!list.isEmpty())
			listReverse.insertAtBack(list.removeFromBack());

		return listReverse;
	}

	// output list contents
	public void print()
	{
		if(isEmpty())
		{
			System.out.printf("Empty %s\n", name);
			return;
		} // end if

		System.out.printf("The %s is: ", name);
		ListNode<T> current = firstNode;

		// while not at end of list, output current node's data
		while(current != null)
		{
			System.out.printf("%s ", current.data);
			current = current.nextNode;
		} // end while

		System.out.println();
	} // end method print

	public void printListBackward()
	{
		if(isEmpty())
		{
			System.out.printf("Empty %s\n", name);
			return;
		} // end if

		System.out.printf("The %s in backward is: ", name);
		ListNode<T> current = firstNode;
		String listBackward = "";
		
		while(current != null)
		{
			listBackward = current.data + " " + listBackward;
			current = current.nextNode;
		}
		
		System.out.print(listBackward);
		System.out.println();
	}
} // end class List< T >

/**************************************************************************
 * (C) Copyright 1992-2010 by Deitel & Associates, Inc. and * Pearson Education,
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this
 * book have used their * best efforts in preparing the book. These efforts
 * include the * development, research, and testing of the theories and programs
 * * to determine their effectiveness. The authors and publisher make * no
 * warranty of any kind, expressed or implied, with regard to these * programs
 * or to the documentation contained in these books. The authors * and publisher
 * shall not be liable in any event for incidental or * consequential damages in
 * connection with, or arising out of, the * furnishing, performance, or use of
 * these programs. *
 *************************************************************************/
