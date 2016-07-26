package huffmanEncoding;

public class HuffHeap {

	private HuffNode[] heap;
	private int size, maxSize;

	private static final int FRONT = 1;

	public HuffHeap(int maxSize) {
		this.maxSize = maxSize;
		this.size = 0;
		heap = new HuffNode[this.maxSize + 1];
		heap[0] = new HuffNode("_", Integer.MIN_VALUE);
	}

	public void add(HuffNode newNode) {
		heap[++size] = newNode;

		int current = size;

		while (heap[current].getFreq() < heap[parent(current)].getFreq()) {
			swap(current, parent(current));
			current = parent(current);
		}
	}

	public HuffNode remove() {
		HuffNode popped = heap[FRONT];
		heap[FRONT] = heap[size--];
		restructure(FRONT);
		return popped;
	}

	private void restructure(int pos) {
		if (!isLeaf(pos)) {
			if (heap[pos].getFreq() > heap[leftChild(pos)].getFreq()
					|| heap[pos].getFreq() > heap[rightChild(pos)].getFreq()) {
				if (heap[leftChild(pos)].getFreq() < heap[rightChild(pos)].getFreq()) {
					swap(pos, leftChild(pos));
					restructure(leftChild(pos));
				} else {
					swap(pos, rightChild(pos));
					restructure(rightChild(pos));
				}
			}
		}
	}

	private void swap(int fpos, int spos) {
		HuffNode tmp;
		tmp = heap[fpos];
		heap[fpos] = heap[spos];
		heap[spos] = tmp;
	}

	private int parent(int pos) {
		return pos / 2;
	}

	private int leftChild(int pos) {
		return (2 * pos);
	}

	private int rightChild(int pos) {
		return (2 * pos) + 1;
	}

	private boolean isLeaf(int pos) {
		if (pos >= (size / 2) && pos <= size) {
			return true;
		}
		return false;
	}

	public String toString() {
		String out = "";
		for (int i = 1; i <= size / 2; i++)
			out += " PARENT: " + heap[i] + " LEFT CHILD: " + heap[2 * i] + " RIGHT CHILD: " + heap[2 * i + 1] + "\n";
		return out;
	}
}