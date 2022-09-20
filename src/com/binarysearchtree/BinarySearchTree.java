package com.binarysearchtree;

public class BinarySearchTree <Type extends Comparable> {
	INode<Type> root;
	
	/**
	 * add key value to binary search tree by wrapping key into the INode 
	 * first it will check binary search tree is empty or not if it is empty 
	 * then new node will add to root node as first node into the binary search tree
	 * otherwise it will add new node below root node 
	 * 
	 *  now it will check new node key is less than root node key or greater than root node key 
	 *  	if it is less than root node key then will add new node to left tree of root node
	 *  	if it is greater than root node key then will add new node to right tree of root node
	 *  
	 * @param key
	 */
	public void add(Type key) {
		INode<Type> newNode = new INode<Type>(key);
		if(isEmpty()) root = newNode;
		else addBelowNode(root, newNode);
	}
	
	/**
	 * Will recursively check for the correct position new node and add new node to that correct position of node
	 * 
	 * now it will check new node key is less than current node key or greater than current node key 
	 *  	if it is less than current node key then will add new node to left tree of current node
	 *  	if it is greater than current node key then will add new node to right tree of current node
	 *  
	 * @param current node
	 * @param new node
	 */
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
	
	/**
	 * Search node with the key given is present in binary search tree or not
	 * 
	 * @param key
	 * @return key found in tree then true else false
	 */
	public boolean search(Type key) {
		if(root == null) return false;
		else return searchKeyInSubTree(root, key);
	}
	
	/**
	 * recursively check key is found in subtree or not 
	 * if found then return true if not found then at last return false
	 * 
	 * @param currentNode
	 * @param key
	 * @return key found in subtree then true else false
	 */
	private boolean searchKeyInSubTree(INode<Type> currentNode, Type key) {
		if(currentNode == null) return false;
		if(currentNode.key == key) return true;
		if(searchKeyInSubTree(currentNode.left, key)) return true;
		return searchKeyInSubTree(currentNode.right, key);
	}
	
	/**
	 * to correctly add new value to given array by checking where we got null then their add new value to the array
	 * 
	 * @param arr
	 * @param key
	 * @return array of keys
	 */
	private Type[] properlyAddToArray(Type[] arr, Type key) {
		for (int index = 0; index < arr.length; index++)
			if(arr[index] == null) {
				arr[index] = key;
				break;
			}
		return arr;
	}
	
	/**
	 * pre-order sequence of binary search tree with pre-order traversal
	 * 
	 * @param result
	 * @return result by adding every nodes values to result 
	 */
	public Type[] preOrder(Type[] result) {
		return preOrderTraversel(root, result);
	}
	
	/**
	 * in-order sequence of binary search tree with in-order traversal
	 * 
	 * @param result
	 * @return result by adding every nodes values to result 
	 */
	public Type[] inOrder(Type[] result) {
		return inOrderTraversel(root, result);
	}
	
	/**
	 * post-order sequence of binary search tree with post-order traversal
	 * 
	 * @param result
	 * @return result by adding every nodes values to result 
	 */
	public Type[] postOrder(Type[] result) {
		return postOrderTraversel(root, result);
	}
	
	/**
	 * recursively arrange all nodes values to result array according to pre-order-traversal manner
	 * 
	 * @param currentNode
	 * @param result
	 * @return result by adding every nodes values to result 
	 */
	private Type[] preOrderTraversel(INode<Type> currentNode, Type[] result) {
		if(currentNode != null) properlyAddToArray(result, currentNode.key);
		if(currentNode.left != null) result = preOrderTraversel(currentNode.left, result);
		if(currentNode.right != null) result = preOrderTraversel(currentNode.right, result);
		return result;
	}
	
	/**
	 * recursively arrange all nodes values to result array according to in-order-traversal manner
	 * 
	 * @param currentNode
	 * @param result
	 * @return result by adding every nodes values to result 
	 */
	private Type[] inOrderTraversel(INode<Type> currentNode, Type[] result) {
		if(currentNode.left != null) result = inOrderTraversel(currentNode.left, result);
		if(currentNode != null) properlyAddToArray(result, currentNode.key);
		if(currentNode.right != null) result = inOrderTraversel(currentNode.right, result);
		return result;
	}
	
	/**
	 * recursively arrange all nodes values to result array according to post-order-traversal manner
	 * 
	 * @param currentNode
	 * @param result
	 * @return result by adding every nodes values to result 
	 */
	private Type[] postOrderTraversel(INode<Type> currentNode, Type[] result) {
		if(currentNode.left != null) result = postOrderTraversel(currentNode.left, result);
		if(currentNode.right != null) result = postOrderTraversel(currentNode.right, result);
		if(currentNode != null) properlyAddToArray(result, currentNode.key);
		return result;
	}
	
	/**
	 * count of nodes in binary search tree
	 * 
	 * @return count of nodes in binary search tree
	 */
	public int size() {
		int count = 0;
		if(root != null) count += sizeOfSubtree(root);
		return count;
	}
	
	/**
	 * recursively calculate size of subtries
	 * 
	 * @param currentNode
	 * @return size of subtree
	 */
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
		binarySearchTree.add(60);
		binarySearchTree.add(40);
		binarySearchTree.add(95);
		binarySearchTree.add(22);
		binarySearchTree.add(11);
		binarySearchTree.add(65);
		binarySearchTree.add(16);
		binarySearchTree.add(63);
		binarySearchTree.add(67);
		binarySearchTree.add(3);
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
		System.out.println("");
		if(binarySearchTree.search(63)) {
			System.out.println("63 found in binary search tree");
		} else {
			System.out.println("63 not found in binary search tree");
		}
	}

}
