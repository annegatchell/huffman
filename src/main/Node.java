package src.main;

public class Node implements Comparable<Node>{
	private char c;
	private int frequency;
	private final Node left, right;

	public Node(char ch, int freq, Node l, Node r){
		c = ch;
		frequency = freq;
		if(freq < 0){
			throw new IllegalArgumentException(ch + 
					"should not have a negative frequency. Setting to 0");
		} 
		left = l;
		right = r;
	}

	public boolean isLeaf(){
		return(left == null && right == null);
	}

	public int compareTo(Node n){
		return frequency - n.getFrequency();
	}

	public int getFrequency(){
		return frequency;
	}

}