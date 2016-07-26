package huffmanEncoding;

import java.util.ArrayList;
import java.util.Scanner;

public class HuffmanTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		System.out.println("Bits: " + input.getBytes().length * 8);

		HuffHeap heap = new HuffHeap(input.length());
		ArrayList<HuffNode> nodeList = new ArrayList<HuffNode>();

		char[] charListPrim = input.toCharArray();
		ArrayList<Character> charList = new ArrayList<Character>();
		for (char cur : charListPrim)
			charList.add(cur);

		selectionSort(charList);

		for (int a = 1; a < charList.size();)
			if (charList.get(a) == charList.get(a - 1))
				charList.remove(a);
			else
				a++;

		for (Character cur : charList) {
			int count = input.length() - input.replace(cur.toString(), "").length();
			heap.add(new HuffNode(cur.toString(), count));
			nodeList.add(new HuffNode(cur.toString(), count));
		}

		sort(nodeList);

		int amt = nodeList.size();
		for (int a = 0; a < amt; a++) {
			if (a != amt - 1) {
				String name = nodeList.get(0).getID() + nodeList.get(1).getID();
				int count = nodeList.get(0).getFreq() + nodeList.get(1).getFreq();
				HuffNode parent = new HuffNode(name, count);

				parent.setLeft(nodeList.get(0));
				parent.setRight(nodeList.get(1));
				nodeList.remove(0);
				nodeList.remove(0);
				nodeList.add(parent);
				sort(nodeList);
			}
		}

		ArrayList<HuffCodePair> codePairs = traverse(nodeList.get(0));

		String codedInput = "";
		for (Character cur : input.toCharArray())
			codedInput += getCode(cur, codePairs);
		float compressionAmt = 100 - ((float) codedInput.length() / ((float) (input.getBytes().length * 8)) * 100);

		System.out.println("Output: " + codedInput + "\nBits: " + codedInput.length() + "\nCompression amount: "
				+ compressionAmt + "%");

		System.out.println("Decoded binary: "+decode(codedInput, codePairs));

		scan.close();
	}

	private static void selectionSort(ArrayList<Character> l) {
		for (int a = 0; a < l.size() - 1; a++)
			for (int b = a + 1; b < l.size(); b++)
				if (l.get(a) >= l.get(b)) {
					char temp = l.get(a);
					l.set(a, l.get(b));
					l.set(b, temp);
				}
	}

	private static void sort(ArrayList<HuffNode> l) {
		for (int a = 0; a < l.size() - 1; a++)
			for (int b = a + 1; b < l.size(); b++)
				if (l.get(a).getFreq() >= l.get(b).getFreq()) {
					HuffNode temp = l.get(a);
					l.set(a, l.get(b));
					l.set(b, temp);
				}
	}

	private static ArrayList<HuffCodePair> traverse(HuffNode node) {
		ArrayList<HuffCodePair> codePairs = new ArrayList<HuffCodePair>();
		String code = "";
		traverse(node, code, codePairs);
		return codePairs;
	}

	private static void traverse(HuffNode node, String code, ArrayList<HuffCodePair> codePairs) {
		if (node.getLeft() == null && node.getRight() == null)
			codePairs.add(new HuffCodePair(node.getID(), code));
		if (node.getLeft() != null)
			traverse(node.getLeft(), code + "1", codePairs);
		if (node.getRight() != null)
			traverse(node.getRight(), code + "0", codePairs);
	}

	private static String getCode(Character cur, ArrayList<HuffCodePair> codeList) {
		for (int a = 0; a < codeList.size(); a++) {
			if (cur.toString().equals(codeList.get(a).getID()))
				return codeList.get(a).getCode();
		}
		return "";
	}

	private static String decode(String input, ArrayList<HuffCodePair> codeList) {
		String result = "";
		char[] inputArr = input.toCharArray();


		for (int b = 0; b < input.length();) {
			boolean found = false;
			String cur = "" + inputArr[b];
			while (!found) {
				if (linearSearch(cur, codeList)) {
					result += extract(cur,codeList);
					found = true;
					b++;
				} else
					cur += inputArr[++b];
			}
		}
		return result;
	}

	private static boolean linearSearch(String query, ArrayList<HuffCodePair> codeList) {
		for (int a = 0; a < codeList.size(); a++)
			if (codeList.get(a).getCode().equals(query))
				return true;
		return false;
	}
	private static String extract(String cur, ArrayList<HuffCodePair> codeList) {
		String result = "";
		for (int a = 0; a < codeList.size(); a++)
			if (codeList.get(a).getCode().equals(cur))
				result = codeList.get(a).getID();
		return result;
	}
}
