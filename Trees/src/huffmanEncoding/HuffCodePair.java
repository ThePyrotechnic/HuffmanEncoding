package huffmanEncoding;

public class HuffCodePair implements Comparable<HuffCodePair>{
	private String ID;
	private String code;

	public HuffCodePair(String ID, String code) {
		this.ID = ID;
		this.code = code;
	}

	public String getID() {
		return ID;
	}

	public String getCode() {
		return code;
	}

	@Override
	public int compareTo(HuffCodePair arg0) {
		return this.ID.compareTo(arg0.getID());
	}
}
