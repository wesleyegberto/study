package javaComoProgramar.cap22;

class TreeNode<T extends Comparable<T>> {
	TreeNode<T> leftNode;
	T data;
	TreeNode<T> rightNode;

	public TreeNode(T nodeData) {
		data = nodeData;
		leftNode = rightNode = null;
	}

	public void insert(T insertValue) {
		if(insertValue.compareTo(data) < 0) {
			if(leftNode == null)
				leftNode = new TreeNode<T>(insertValue);
			else
				leftNode.insert(insertValue);
		} else if(insertValue.compareTo(data) > 0) {
			if(rightNode == null)
				rightNode = new TreeNode<T>(insertValue);
			else
				rightNode.insert(insertValue);
		}
	}

}

public class Tree<T extends Comparable<T>> {
	private TreeNode<T> root;

	public Tree() {
		root = null;
	}

	public void insertNode(T insertValue) {
		if(root == null)
			root = new TreeNode<T>(insertValue);
		else
			root.insert(insertValue);
	}

	public boolean deleteNode(T key) {
		if(root == null)
			return false;
		else if(root.data.compareTo(key) == 0) {
			if(root.leftNode != null && root.rightNode == null)
				root = root.leftNode;
			else if(root.leftNode == null && root.rightNode != null)
				root = root.rightNode;
			else if(root.leftNode != null && root.rightNode != null)
				root = swampUniqueByLess(root);
			else
				root = null;

			return true;
		} else if(root.data.compareTo(key) > 0 && root.leftNode != null) {
			return deleteNode(key, root, root.leftNode);
		} else if(root.data.compareTo(key) < 0 && root.rightNode != null) {
			return deleteNode(key, root, root.rightNode);
		} else
			return false;
	}

	private boolean deleteNode(T key, TreeNode<T> fatherCurrentNode, TreeNode<T> currentNode) {
		TreeNode<T> swamp = currentNode;

		if(currentNode.data.compareTo(key) == 0) {
			if(currentNode.leftNode == null && currentNode.rightNode == null) {
				if(fatherCurrentNode.leftNode == currentNode)
					fatherCurrentNode.leftNode = null;
				else if(fatherCurrentNode.rightNode == currentNode)
					fatherCurrentNode.rightNode = null;
			} else if(currentNode.leftNode != null && currentNode.rightNode == null) {
				if(fatherCurrentNode.leftNode == currentNode)
					fatherCurrentNode.leftNode = swamp.leftNode;
				else if(fatherCurrentNode.rightNode == currentNode)
					fatherCurrentNode.rightNode = swamp.leftNode;
			} else if(currentNode.leftNode == null && currentNode.rightNode != null) {
				if(fatherCurrentNode.leftNode == currentNode)
					fatherCurrentNode.leftNode = swamp.rightNode;
				else if(fatherCurrentNode.rightNode == currentNode)
					fatherCurrentNode.rightNode = swamp.rightNode;
			} else if(currentNode.leftNode != null && currentNode.rightNode != null)
				swampFatherNodeByLess(fatherCurrentNode, currentNode);

			return true;
		} else if(currentNode.data.compareTo(key) > 0 && currentNode.leftNode != null) {
			return deleteNode(key, currentNode, currentNode.leftNode);
		} else if(currentNode.data.compareTo(key) < 0 && currentNode.rightNode != null) {
			return deleteNode(key, currentNode, currentNode.rightNode);
		} else
			return false;
	}

	private void swampFatherNodeByLess(TreeNode<T> fatherCurrentNode, TreeNode<T> currentNode) {
		if(currentNode == null)
			return;

		TreeNode<T> swampNode = currentNode; // Contém a referência que será
												// removida
		TreeNode<T> nextNode = currentNode.leftNode; // Contém a referência que
														// será trocada
		TreeNode<T> backNode = fatherCurrentNode; // Armazenará o nó anterior ao
													// nextNode para receber o
													// filho a esqueda do mesmo
													// caso exista

		while(nextNode.rightNode != null) {
			backNode = nextNode;
			nextNode = nextNode.rightNode;
		}

		if(nextNode.leftNode == null)
			backNode.rightNode = null;
		else if(nextNode.leftNode != null)
			backNode.rightNode = nextNode.leftNode;

		if(swampNode.leftNode != nextNode)
			nextNode.leftNode = swampNode.leftNode;

		nextNode.rightNode = swampNode.rightNode;

		if(fatherCurrentNode.leftNode == swampNode)
			fatherCurrentNode.leftNode = nextNode;
		else
			fatherCurrentNode.rightNode = nextNode;
	}

	private TreeNode<T> swampUniqueByLess(TreeNode<T> swamp) {
		TreeNode<T> nextNode = swamp.leftNode; // Contém a referência que será
												// trocada
		TreeNode<T> backNode = swamp; // Armazenará o nó anterior ao nextNode
										// para receber o filho a esqueda do
										// mesmo caso exista

		while(nextNode.rightNode != null) {
			backNode = nextNode;
			nextNode = nextNode.rightNode;
		}

		nextNode.rightNode = swamp.rightNode;

		if(swamp.leftNode != nextNode)
			nextNode.leftNode = swamp.leftNode;

		if(swamp != backNode)
			backNode.rightNode = null;

		return nextNode;
	}

	public TreeNode<T> contains(T key) {
		return search(key, root);
	}

	private TreeNode<T> search(T key, TreeNode<T> currentNode) {
		TreeNode<T> resp;

		if(currentNode == null)
			return null;

		if(currentNode.data.compareTo(key) == 0)
			return currentNode;

		resp = search(key, currentNode.rightNode);
		if(resp != null)
			return resp;

		resp = search(key, currentNode.leftNode);
		if(resp != null)
			return resp;

		return null;
	}

	public int getDepth() {
		return getDepth(root, 1);
	}

	private int getDepth(TreeNode<T> node, int level) {
		int depthLeft = 0, depthRight = 0;

		if(node.leftNode != null || node.rightNode != null)
			++level;
		if(node.leftNode != null) {
			depthLeft = getDepth(node.leftNode, level);
		}
		if(node.rightNode != null) {
			depthRight = getDepth(node.rightNode, level);
		}

		if(depthLeft > depthRight)
			return depthLeft;
		else if(depthRight > depthLeft)
			return depthRight;
		else if(depthRight == depthLeft && depthRight != 0 && depthLeft != 0)
			return depthLeft;
		else
			return level;
	}

	public void preorderTraversal() {
		preorderHelper(root);
	} // end method preorderTraversal

	private void preorderHelper(TreeNode<T> node) {
		if(node == null)
			return;

		System.out.printf("%s ", node.data); // output node data
		preorderHelper(node.leftNode); // traverse left subtree
		preorderHelper(node.rightNode); // traverse right subtree
	} // end method preorderHelper

	public void inorderTraversal() {
		inorderHelper(root);
	} // end method inorderTraversal

	private void inorderHelper(TreeNode<T> node) {
		if(node == null)
			return;

		inorderHelper(node.leftNode); // traverse left subtree
		System.out.printf("%s ", node.data); // output node data
		inorderHelper(node.rightNode); // traverse right subtree
	} // end method inorderHelper

	public void postorderTraversal() {
		postorderHelper(root);
	} // end method postorderTraversal

	private void postorderHelper(TreeNode<T> node) {
		if(node == null)
			return;

		postorderHelper(node.leftNode);
		postorderHelper(node.rightNode);
		System.out.printf("%s ", node.data);
	}

	public void levelOrder() {
		System.out.print(root.data + " ");
		levelOrderHelper(root);
	}

	private void levelOrderHelper(TreeNode<T> node) {
		if(node == null)
			return;

		if(node.leftNode != null)
			System.out.print(node.leftNode.data + " ");

		if(node.rightNode != null)
			System.out.print(node.rightNode.data + " ");

		levelOrderHelper(node.leftNode);
		levelOrderHelper(node.rightNode);
	}

	public void outputTree() {
		outputTreeHelper(root, 0, 5);
	}

	private void outputTreeHelper(TreeNode<T> node, int totalSpaces, int spaces) {
		while(node != null) {
			totalSpaces += spaces;
			outputTreeHelper(node.rightNode, totalSpaces, spaces);

			for(int i = 0; i < totalSpaces; i++)
				System.out.print(" ");

			System.out.println(node.data);
			node = node.leftNode;
		}
	}
}
