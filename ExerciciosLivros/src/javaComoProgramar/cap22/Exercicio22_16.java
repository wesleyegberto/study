package javaComoProgramar.cap22;

public class Exercicio22_16
{
	public static void main(String[] args)
	{
		DuplicateTree<Integer> dT = new DuplicateTree<Integer>();
		
		dT.insertNode(2);
		dT.insertNode(7);
		dT.insertNode(8);
		dT.insertNode(9);
		dT.insertNode(7);
		dT.insertNode(2);
		dT.inorderTraversal();
	}
}

// class TreeNode definition
class DuplicateTreeNode<T extends Comparable<T>>
{
	// package access members
	DuplicateTreeNode<T> leftNode; // left node
	T data; // node value
	DuplicateTreeNode<T> centerNode;
	DuplicateTreeNode<T> rightNode; // right node

	// constructor initializes data and makes this a leaf node
	public DuplicateTreeNode(T nodeData)
	{
		data = nodeData;
		leftNode = rightNode = null; // node has no children
	} // end TreeNode constructor

	// locate insertion point and insert new node; ignore duplicate values
	public void insert(T insertValue)
	{
		// insert in left subtree
		if(insertValue.compareTo(data) < 0)
		{
			// insert new TreeNode
			if(leftNode == null)
				leftNode = new DuplicateTreeNode<T>(insertValue);
			else
				// continue traversing left subtree recursively
				leftNode.insert(insertValue);
		} // end if
		// insert in right subtree
		else if(insertValue.compareTo(data) > 0)
		{
			// insert new TreeNode
			if(rightNode == null)
				rightNode = new DuplicateTreeNode<T>(insertValue);
			else
				// continue traversing right subtree recursively
				rightNode.insert(insertValue);
		} // end else if
		// insert in center subtree
		else if(insertValue.compareTo(data) == 0)
		{
			if(centerNode == null)
				centerNode = new DuplicateTreeNode<T>(insertValue);
			else
				// continue traversing right subtree recursively
				centerNode.insert(insertValue);
		}
	} // end method insert
} // end class TreeNode

// class DuplicateTree definition
class DuplicateTree<T extends Comparable<T>>
{
	private DuplicateTreeNode<T> root;

	// constructor initializes an empty Tree of integers
	public DuplicateTree()
	{
		root = null;
	} // end Tree no-argument constructor

	// insert a new node in the binary search tree
	public void insertNode(T insertValue)
	{
		if(root == null)
			root = new DuplicateTreeNode<T>(insertValue); // create root node
		else
			root.insert(insertValue); // call the insert method
	} // end method insertNode

	// begin preorder traversal
	public void preorderTraversal()
	{
		preorderHelper(root);
	} // end method preorderTraversal

	// recursive method to perform preorder traversal
	private void preorderHelper(DuplicateTreeNode<T> node)
	{
		if(node == null)
			return;

		System.out.printf("%s ", node.data); // output node data
		preorderHelper(node.leftNode); // traverse left subtree
		postorderHelper(node.centerNode); // traverse center subtree
		preorderHelper(node.rightNode); // traverse right subtree
	} // end method preorderHelper

	// begin inorder traversal
	public void inorderTraversal()
	{
		inorderHelper(root);
	} // end method inorderTraversal

	// recursive method to perform inorder traversal
	private void inorderHelper(DuplicateTreeNode<T> node)
	{
		if(node == null)
			return;

		inorderHelper(node.leftNode); // traverse left subtree
		System.out.printf("%s ", node.data); // output node data
		postorderHelper(node.centerNode); // traverse center subtree
		inorderHelper(node.rightNode); // traverse right subtree
	} // end method inorderHelper

	// begin postorder traversal
	public void postorderTraversal()
	{
		postorderHelper(root);
	} // end method postorderTraversal

	// recursive method to perform postorder traversal
	private void postorderHelper(DuplicateTreeNode<T> node)
	{
		if(node == null)
			return;

		postorderHelper(node.leftNode); // traverse left subtree
		postorderHelper(node.centerNode); // traverse center subtree
		postorderHelper(node.rightNode); // traverse right subtree
		System.out.printf("%s ", node.data); // output node data
	} // end method postorderHelper
} // end class Tree
