package essentials;

import java.util.Date;

public class Block {
	public String hash;
	public String preHash;
	private String data;
	private long timestamp;
	private int nonce;

	public Block(String data, String preHash) {
		this.data = data;
		this.preHash = preHash;
		this.timestamp = new Date().getTime();
		this.hash = calculateHash();
	}

	public String calculateHash() {
		String calculatedhash = Hash.hashfun(preHash + Long.toString(timestamp)+ Integer.toString(nonce) + data);
		return calculatedhash;
	}

	public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); 
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		System.out.println("BlockMined!!! \n" + hash + "\n\n\n");
	}

}
