package huffmanEncoding;

public class HuffNode {

	private String ID;
	private int freq;
	private HuffNode left, right;

	public HuffNode(String id, int count) {
		ID = id;
		freq = count;
		left = null;
		right = null;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public int getFreq() {
		return freq;
	}

	public HuffNode getLeft() {
		return left;
	}

	public void setLeft(HuffNode left) {
		this.left = left;
	}

	public HuffNode getRight() {
		return right;
	}

	public void setRight(HuffNode right) {
		this.right = right;
	}
}