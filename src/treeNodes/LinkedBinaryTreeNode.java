package treeNodes;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.*;

public class LinkedBinaryTreeNode<E> implements MutableTreeNode {
	private E element;
	private MutableTreeNode parent;
	private MutableTreeNode leftChild;
	private MutableTreeNode rightChild;
	
	public LinkedBinaryTreeNode() {
		this(null);
	}
	
	public LinkedBinaryTreeNode(E element) {
		this.element = element;
		parent = null;
		leftChild = null;
		rightChild = null;
	}
	
	public static void main(String... args) {
		MutableTreeNode root = new LinkedBinaryTreeNode<>("A");
		MutableTreeNode nodeB = new LinkedBinaryTreeNode<>("B");
		MutableTreeNode nodeC = new LinkedBinaryTreeNode<>("C");
		MutableTreeNode nodeD = new LinkedBinaryTreeNode<>("D");
		MutableTreeNode nodeE = new LinkedBinaryTreeNode<>("E");
		MutableTreeNode nodeF = new LinkedBinaryTreeNode<>("F");
		nodeB.insert(nodeD, 0);
		nodeB.insert(nodeE, 1);
		nodeC.insert(nodeF, 0);
		root.insert(nodeB, 0);
		root.insert(nodeC, 1);
		System.out.println("Original Tree: " + root);
		root.remove(nodeC);
		System.out.println("Modified Tree: " + root);
	}
	
	/**
	 * @param childIndex
	 *
	 * @return
	 *
	 * @throws IndexOutOfBoundsException
	 * @throws NoSuchElementException
	 */
	@Override
	public TreeNode getChildAt(int childIndex) {
		TreeNode result;
		if (childIndex == 0 && leftChild != null) {
			result = leftChild;
		} else if (childIndex == 1 && rightChild != null) {
			result = rightChild;
		} else if (childIndex >= 2 || childIndex < 0) {
			throw new IndexOutOfBoundsException("Invalid child index provided");
		} else {
			throw new NoSuchElementException("No element at given index");
		}
		return result;
	}
	
	@Override
	public int getChildCount() {
		int childCount = 0;
		if (leftChild != null) {
			childCount++;
		}
		if (rightChild != null) {
			childCount++;
		}
		return childCount;
	}
	
	@Override
	public TreeNode getParent() {
		return parent;
	}
	
	@Override
	public int getIndex(TreeNode node) {
		int result = -1;
		if (node == null) {
			throw new IllegalArgumentException("oops");
		}
		if (leftChild == node) {
			result = 0;
		} else if (rightChild == node) {
			result = 1;
		}
		if (result == -1) {
			throw new NoSuchElementException("Node '" + node + "' is not a child node.");
		}
		return result;
	}
	
	@Override
	public boolean getAllowsChildren() {
		return true;
	}
	
	@Override
	public boolean isLeaf() {
		return getChildCount() == 0;
	}
	
	@Override
	public Enumeration<MutableTreeNode> children() {
		List<MutableTreeNode> list = new LinkedList<MutableTreeNode>();
		if (leftChild != null) {
			list.add(leftChild);
		}
		if (rightChild != null) {
			list.add(rightChild);
		}
		return (Enumeration<MutableTreeNode>) new Enumerator(list.iterator());
	}
	
	@Override
	public void setParent(MutableTreeNode newParent) {
		parent = newParent;
	}
	
	
	/**
	 * @param child
	 * @param index
	 *
	 * @throws IllegalArgumentException
	 */
	@Override
	public void insert(MutableTreeNode child, int index) {
		if (child == null) {
			throw new IllegalArgumentException("null child");
		}
		
		child.removeFromParent();
		
		if (index == 0 && leftChild == null) {
			leftChild = child;
		} else if (index == 1 && rightChild == null) {
			rightChild = child;
		} else if (index >= 2 || index < 0) {
			throw new IndexOutOfBoundsException("Invalid child index provided");
		} else {
			throw new IndexOutOfBoundsException("There is already an element at index " + index + ".");
		}
		
	}
	
	
	@Override
	public void remove(int index) {
		if (index == 0) {
			remove(leftChild);
		} else if (index == 1) {
			remove(rightChild);
		} else {
			throw new IndexOutOfBoundsException("Invalid child index provided");
		}
	}
	
	
	@Override
	public void remove(MutableTreeNode node) {
		if (Objects.equals(node, leftChild)) {
			node.setParent(null);
			leftChild = null;
		} else if (Objects.equals(node, rightChild)) {
			node.setParent(null);
			rightChild = null;
		}
	}
	
	public E getUserObject() {
		return element;
	}
	
	@Override
	public void setUserObject(Object object) {
		element = (E) object;
	}
	
	@Override
	public void removeFromParent() {
		if (parent != null) {
			parent.remove(this);
			parent = null;
		}
	}
	
	public String toString() {
		String output = String.valueOf(element);
		if (!isLeaf()) {
			output += "[ ";
			output += leftChild + " " + rightChild;
			output += "]";
		}
		return output;
	}
}

