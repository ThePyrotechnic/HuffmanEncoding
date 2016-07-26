package tree;

public class Node<T> {
	
	private T value;
	private Node<T> left, right;
	
	public Node() {
		this(null,null,null);
	}
	public Node(T val) {
		this(val,null,null);
	}
	public Node(T val, Node<T> l, Node<T> r) {
		value = val;
		left = l;
		right = r;
	}
	
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}
	public Node<T> getLeft() {
		return left;
	}
	public void setLeft(Node<T> left) {
		this.left = left;
	}
	public Node<T> getRight() {
		return right;
	}
	public void setRight(Node<T> right) {
		this.right = right;
	}	
}
