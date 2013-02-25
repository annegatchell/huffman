/*
*	This is a Node class for the construction of a Huffman
*	encoder.
*	It implements Comparable based on the frequencies of two Nodes.
*	
*	Author:
*	Anne Gatchell annegatchell@gmail.com
*
*	Date:
*	23 February 2013
*
*	Sources: CLRS, Sedgewick - Algorithms
*/

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

	@Override
	public String toString(){
		return (c + " " + frequency + " " + left + " " + right);
	}
	public char getChar(){
		return c;
	}
	public Node getLeftNode(){
		return left;
	}
	public Node getRightNode(){
		return right;
	}

}