package com.binarysearchtree;

public class BinarySearchTree <Type extends Comparable> {
	INode<Type> root;
	
	public void add(Type key) {
		INode<Type> newNode = new INode<Type>(key);
		if(isEmpty()) root = newNode;
		else addBelowNode(root, newNode);
	}
	
	private void addBelowNode(INode currentNode, INode newNode) {
		if(currentNode.key.compareTo(newNode.key) < 0) 
			// new node key is greater than current node key so add new node to right side of current node
			if(currentNode.right == null) currentNode.right = newNode; // current nodes right tree is empty 
			else addBelowNode(currentNode.right, newNode); // current nodes right tree is not empty so add new node to below right node
		else if(currentNode.key.compareTo(newNode.key) > 0) 
			// new node key is less than current node key so add new node to left side of current node
			if(currentNode.left == null) currentNode.left = newNode; // current nodes left tree is empty
			else addBelowNode(currentNode.left, newNode); // current nodes left tree is not empty so add new node to below left node
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
	private Type[] properlyAddToArray(Type[] arr, Type key) {
		for (int index = 0; index < arr.length; index++)
			if(arr[index] == null) {
				arr[index] = key;
				break;
			}
		return arr;
	}
	
	public Type[] preOrder(Type[] result) {
		return preOrderTraversel(root, result);
	}
	
	public Type[] inOrder(Type[] result) {
		return inOrderTraversel(root, result);
	}
	
	public Type[] postOrder(Type[] result) {
		return postOrderTraversel(root, result);
	}
	
	private Type[] preOrderTraversel(INode<Type> currentNode, Type[] result) {
		if(currentNode != null) properlyAddToArray(result, currentNode.key);
		if(currentNode.left != null) result = preOrderTraversel(currentNode.left, result);
		if(currentNode.right != null) result = preOrderTraversel(currentNode.right, result);
		return result;
	}
	
	private Type[] inOrderTraversel(INode<Type> currentNode, Type[] result) {
		if(currentNode.left != null) result = inOrderTraversel(currentNode.left, result);
		if(currentNode != null) properlyAddToArray(result, currentNode.key);
		if(currentNode.right != null) result = inOrderTraversel(currentNode.right, result);
		return result;
	}
	
	private Type[] postOrderTraversel(INode<Type> currentNode, Type[] result) {
		if(currentNode.left != null) result = postOrderTraversel(currentNode.left, result);
		if(currentNode.right != null) result = postOrderTraversel(currentNode.right, result);
		if(currentNode != null) properlyAddToArray(result, currentNode.key);
		return result;
	}
	
	public int size() {
		int count = 0;
		if(root != null) count += sizeOfSubtree(root);
		return count;
	}
	
	private int sizeOfSubtree(INode<Type> currentNode) {
		int count = 0;
		if(currentNode != null) {
			count += 1;
			count += sizeOfSubtree(currentNode.left);
			count += sizeOfSubtree(currentNode.right);
		}
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to binary search tree program.");
		BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>();
		binarySearchTree.add(56);
		binarySearchTree.add(30);
		binarySearchTree.add(70);
		System.out.print("Pre Order - ");
		boolean first = true;
		for(Integer key: binarySearchTree.preOrder(new Integer[binarySearchTree.size()]))
			if(first) {
				System.out.print(key);
				first = !first;
			}
			else System.out.print(", "+key);
		first = true;
		System.out.println("");
		System.out.print("In Order - ");
		for(Integer key: binarySearchTree.inOrder(new Integer[binarySearchTree.size()]))
			if(first) {
				System.out.print(key);
				first = !first;
			}
			else System.out.print(", "+key);
		first = true;
		System.out.println("");
		System.out.print("Post Order - ");
		for(Integer key: binarySearchTree.postOrder(new Integer[binarySearchTree.size()]))
			if(first) {
				System.out.print(key);
				first = !first;
			}
			else System.out.print(", "+key);
	}

}
