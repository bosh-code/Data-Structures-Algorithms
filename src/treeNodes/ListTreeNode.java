package treeNodes;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ListTreeNode<E> implements MutableTreeNode {
	public static final int CAPACITY = 32;
	private final List<MutableTreeNode> children;
	private E element;
	private MutableTreeNode parent;
	
	public ListTreeNode() {
		this(null);
	}
	
	public ListTreeNode(E element) {
		this.element = element;
		parent = null;
		children = new ArrayList<>();
	}
	
	public static void main(String... args) {
		MutableTreeNode root = new ListTreeNode<>("A");
		MutableTreeNode nodeB = new ListTreeNode<>("B");
		MutableTreeNode nodeC = new ListTreeNode<>("C");
		MutableTreeNode nodeD = new ListTreeNode<>("D");
		MutableTreeNode nodeE = new ListTreeNode<>("E");
		MutableTreeNode nodeF = new ListTreeNode<>("F");
		nodeB.insert(nodeD, 0);
		nodeB.insert(nodeE, 1);
		nodeC.insert(nodeF, 0);
		root.insert(nodeB, 0);
		root.insert(nodeC, 1);
		System.out.println("Original Tree: " + root);
		root.remove(nodeC);
		nodeB.insert(nodeC, 1);
		System.out.println("Modified Tree: " + root);
	}
	
	// return the child at specified index
	
	/**
	 * @param childIndex
	 *
	 * @return
	 *
	 * @throws IndexOutOfBoundsException
	 */
	@Override
	public TreeNode getChildAt(int childIndex) {
		return children.get(childIndex);
	}
	
	@Override
	public int getChildCount() {
		return children.size();
	}
	
	@Override
	public TreeNode getParent() {
		return parent;
	}
	
	/**
	 * @param node
	 *
	 * @return
	 *
	 * @throws IllegalArgumentException
	 */
	@Override
	public int getIndex(TreeNode node) {
		if (node == null) {
			throw new IllegalArgumentException("Node is null");
		} else {
			return children.indexOf(node);
		}
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
	public Enumeration<? extends TreeNode> children() {
		return (Enumeration<? extends TreeNode>) new Enumerator(children.iterator());
	}
	
	/**
	 * @param child
	 * @param index
	 *
	 * @throws IllegalArgumentException null child
	 */
	@Override
	public void insert(MutableTreeNode child, int index) {
		if (child == null) {
			throw new IllegalArgumentException("null child");
		}
		child.removeFromParent();
		children.add(index, child);
		child.setParent(this);
	}
	
	@Override
	public void remove(int index) {
		MutableTreeNode child = children.get(index);
		if (child != null) {
			remove(child);
		}
	}
	
	@Override
	public void remove(MutableTreeNode node) {
		if (children.remove(node)) {
			node.setParent(null);
		}
	}
	
	@Override
	public void setParent(MutableTreeNode newParent) {
		removeFromParent();
		parent = newParent;
	}
	
	public E getUserObject() {
		return element;
	}
	
	@Override
	public void setUserObject(Object object) {
		element = (E) object; // unchecked, could throw exception
	}
	
	@Override
	public void removeFromParent() {
		if (parent != null) {
			parent.remove(this);
			parent = null;
		}
	}
	
	public String toString() {
		StringBuilder returnString = new StringBuilder(CAPACITY);
		returnString.append(element);
		if (!isLeaf()) {
			returnString.append("[ ");
			for (MutableTreeNode childNode : children) {
				returnString.append(childNode).append(" ");
			}
			returnString.append("]");
		}
		return returnString.toString();
	}
}