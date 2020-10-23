package essentials;

import java.util.*;

import com.google.gson.GsonBuilder;

public class Chain {
	//Adding each block to an ArrayList 
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	
	//Difficulty level to mine a block
	public static int difficulty=5;
	
	//Driver method
	public static void main(String[] args) {
		
		//Genesis Block
		
		blockchain.add(new Block("Hi My name is Saurabh Maurya", "0"));
		System.out.println("Trying to Mine block 1... ");
		blockchain.get(0).mineBlock(difficulty);
		
		//Block 1
		
		blockchain.add(new Block("Studied in KEC", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Trying to Mine block 2... ");
		blockchain.get(1).mineBlock(difficulty);
		
		//Block 2
		
		blockchain.add(new Block("coding for blockchain", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Trying to Mine block 3... ");
		blockchain.get(2).mineBlock(difficulty);
		
		//To check the integrity of the chain i.e the chain is not tampered
		
		System.out.println("\nBlockchain is Valid: " + isChainValid());
		
		//To visualise the chain in a JSON format
		
		String blockjson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe block chain: ");
		System.out.print(blockjson);
	}
	
	// To check the integrity of the chain
	
	public static Boolean isChainValid() {
		Block currentBlock;
		Block preBlock;
		
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');
		
		for (int i = 1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			preBlock = blockchain.get(i - 1);
			// compare registered hash and calculated hash:
			if (!currentBlock.hash.equals(currentBlock.calculateHash())) {
				System.out.println("Current Hashes not equal");
				return false;
			}
			// compare previous hash and registered previous hash
			if (!preBlock.hash.equals(currentBlock.preHash)) {
				System.out.println("Previous Hashes not equal");
				return false;
			}
			//check if hash is solved
			if(!currentBlock.hash.substring( 0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
	
	//For adding new block later
	public static void addBlock(Block newBlock) {
		newBlock.mineBlock(difficulty);
		blockchain.add(newBlock);
	}
}
