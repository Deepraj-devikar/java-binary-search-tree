package com.binarysearchtree;

public class INode <Type extends Comparable> {
	Type key;
	INode<Type> left;
	INode<Type> right;
	
	public INode(Type key) {
		this.key = key;
	}
}
