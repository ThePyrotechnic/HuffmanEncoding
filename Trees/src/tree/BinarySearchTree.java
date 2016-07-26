package tree;

public class BinarySearchTree<T extends Comparable<T>> {
	
	private Node<T> head;
	
	public BinarySearchTree() {
		this(null);
	}
	public BinarySearchTree(T val) {
		head = new Node<T>(val);
	}
	
	public boolean add(T val) {
		Node<T> newNode = new Node<T>(val);
		if(head.getValue() == null) {
			head = newNode;
			return true;
		}
		else
			return add(newNode,head);
	}
	private boolean add(Node<T> newNode, Node<T> curNode) 
	{
		if (newNode.getValue().compareTo(curNode.getValue()) == 0)
			return true;
		else if (newNode.getValue().compareTo(curNode.getValue()) < 0) 
		{
			if(curNode.getLeft() == null) 
			{
				curNode.setLeft(newNode);
				return true;
			}
			else return add(newNode,curNode.getLeft());
		}
		else
		{
			if(curNode.getRight() == null) 
			{
				curNode.setRight(newNode);
				return true;
			}
			else return add(newNode,curNode.getRight());
		}
	}
	
	public boolean search(T val) 
	{
		if(head.getValue() == null)
			return false;
		else
			return search(val, head);
	}
	private boolean search(T val, Node<T> curNode)
	{
		if(val.compareTo(curNode.getValue()) == 0)
			return true;
		else if(val.compareTo(curNode.getValue()) < 0)
			if(curNode.getLeft() == null)
				return false;
			else return search(val, curNode.getLeft());
		else
			if(curNode.getRight() == null)
				return false;
			else return search(val, curNode.getRight());
	}
	
	public void delete(T val)
	{
		head = delete(val, head);
	}
	private Node<T> delete(T val, Node<T> curNode)
	{
		if(val.compareTo(curNode.getValue()) == 0)
		{
			if(curNode.getLeft() == null && curNode.getRight() == null)
				return null;
			if(curNode.getLeft() == null)
				return curNode.getRight();
			if(curNode.getRight() == null)
				return curNode.getLeft();
			
			Node<T> temp = smallest(curNode.getRight());
			curNode.setValue(temp.getValue());
			curNode.setRight(delete(temp.getValue(),curNode.getRight()));
			return curNode;
		}
		else if(val.compareTo(curNode.getValue()) < 0)
		{
			curNode.setLeft(delete(val, curNode.getLeft() ));
			return curNode;
		}
		else 
		{
			curNode.setRight(delete(val, curNode.getRight() ));
			return curNode;
		}
	}
	private Node<T> smallest(Node<T> curNode) 
	{
		if(curNode.getLeft() == null)
			return curNode;
		else 
			return smallest(curNode.getLeft());
	}
}
