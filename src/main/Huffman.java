/*
*	This is a Huffman Encoder implementation
*	The encoder is intitialized with the unicode integer representation
*	of the first letter of the alphabet for the string being encoded.
*	A default value is 0. To use the entire 16-UTF library, intialize
*	with 0 as the first character and 0x5555 as the alphabet size.
*	For an ASCII representation, use 256 as the alphabet size.
*	
*	Then call encode() for the string that you want to encode.
*	The frequencies of letters are calculated, then the letters
*	are added to a MinPriorityQueue if they have a frequency greater
*	than 0. This is because 0 frequencies should not be in the 
*	Huffman Tree. Then, they are extracted two
*	at a time, merged into a combined node, and reinserted. This
* 	is repeated until there is one node in the queue, which is the
*	entire tree.
*
*	The Nodes used have a character, a frequency and left and right
*	Nodes. This allows for the structure of the Huffman tree.
*
*	Once the tree is generated, a table of endcodings is generated
*	by traversing the tree for each alphabet letter that is in the 
*	original string.
*
*	Then, the encoded string is generated using the table for outputting.
*	In this implementation, the endcoding is a string representation,
*	since it does not actually output a binary file. 
*	Thus, the length of the string generated is equal to the number
*	of bits in the encoding.
*
*	To output an actual binary file, a buffering system would need to
*	be added, but it would not alter the Huffman Code.
*	
*	encode() also outputs an ASCII tree picture.
*
*	FOR EXPERIMENTS:
*	For the runtime experiments, the function encodeWithPremadeFreq()
*	method is used. This function bypasses the generation of the 
*	frequency table, as well as the generation and printing of the
*	encoded string. 
*	This is appropriate, because the number of operations that matter
*	are those between these two steps: generating a Huffman Encoding
*	from the frequencies in a given string.
*
*	The variable numOps keeps track of how many operations are used.
*	It also adds in the number of ops that MinPriorityQueue uses.
*	
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

public class Huffman{
	private int sizeOfAlphabet = 256; //ASCII
	private int actualSizeOfAlphabet;
	private Node tree;
	private String original;
	private String codeTable[];
	private int frequencies[];
	private int firstChar;
	private String codedString;
	private int numOps = 0;


	public Huffman(int first, int sizeOfAlpha, String input){
		sizeOfAlphabet = sizeOfAlpha;
		original = input;
		frequencies = new int[sizeOfAlphabet];
		codeTable = new String[sizeOfAlphabet];
		firstChar = first;
	}

	public Huffman(int first, int sizeOfAlpha){
		sizeOfAlphabet = sizeOfAlpha;
		frequencies = new int[sizeOfAlphabet];
		codeTable = new String[sizeOfAlphabet];
		firstChar = first;
	}

	//Returns
	public Node encode(String input){
		System.out.println(input);
		original = input;
		//Tabulate frequencies of characters
		calculateFrequencies();
		//Create min priority queue
		MinPriorityQueue<Node> pq = 
			new MinPriorityQueue<Node>(sizeOfAlphabet);
		generateFreqNodesAndAddToMinPQ(pq);
		//Set the actual size of the alphabet(no 0 frequencies)
		actualSizeOfAlphabet = pq.getSize();
		//Generate the tree
		buildTree(pq);
		numOps += pq.getNumOps();
		//Generate the table of codes
		generateCodeTable(tree);
		//Encode the message
		generateEncodedString();
		//Print string
		System.out.println(codedString);
		//Print tree
		printTree("",tree);
		return tree;
	}

	public Node encodeWithPremadeFreq(int[] freq){
		frequencies = freq;
		//Create min priority queue
		MinPriorityQueue<Node> pq = 
			new MinPriorityQueue<Node>(sizeOfAlphabet);
		generateFreqNodesAndAddToMinPQ(pq);
		actualSizeOfAlphabet = pq.getSize();
		//Generate the tree
		buildTree(pq);
		numOps += pq.getNumOps();
		//Generate the table of codes
		generateCodeTable(tree);
		//Print tree
		//printTree("",tree);
		return tree;
	}

	private void generateEncodedString(){
		StringBuilder br = new StringBuilder();
		int index;
		for(int i = 0; i < original.length(); i++){
			index = (int)original.charAt(i) - firstChar;
			br.append(codeTable[index]);
		}
		codedString = br.toString();
	}

	private void generateCodeTable(Node root){
		generateCodeTable(root, "");
	}

	private void generateCodeTable(Node x, String s){
		if(x.isLeaf()){
			numOps++; //test
			codeTable[(int)x.getChar() - firstChar] = s;
			numOps++; //subtraction
			numOps++; //assignment
			return;
		}
		generateCodeTable(x.getLeftNode(), s + '0');
		generateCodeTable(x.getRightNode(), s+ '1');
	}

	private void buildTree(MinPriorityQueue<Node> pq){
		Node x, y, z;
		for(int i = 0; i < actualSizeOfAlphabet-1; i++){
			x = pq.extractMinimum();
			y = pq.extractMinimum();
			z = new Node('.', x.getFrequency()+y.getFrequency(), x, y);
			numOps++; //addition
			pq.insert(z);
		}
		tree = pq.extractMinimum();
	}

	private void generateFreqNodesAndAddToMinPQ(MinPriorityQueue<Node> pq){
		char c;
		for(int i = 0; i < sizeOfAlphabet; i++){
			if(frequencies[i] > 0){
				pq.insert(new Node((char)(i+firstChar), 
					frequencies[i], null, null));
			}
		}
	}

	private void calculateFrequencies(){
		int index;
		for(int i = 0; i < original.length(); i++){
			index = ((int) original.charAt(i)) - firstChar;
			frequencies[index]++;
		}
	}

	private int calculateOutPutSize(){
		return -1;	
	}

	public int getCodedStringLength(){
		return codedString.length();
	}

	public int[] getFrequencies(){
		return frequencies;
	}
	public void printCodeTable(){
		System.out.println("Frequencies:");
		for(int i = 0; i < sizeOfAlphabet;i++){
			if(frequencies[i] > 0){
				System.out.println(((char) i)+"\t"+frequencies[i]);
			}
		}
	}
	public Node getTree(){
		return tree;
	}
	public String[] getCodeTable(){
		return codeTable;
	}
	public String getEncodedString(){
		return codedString;
	}
	public void printTree(String prefix, Node n){
		System.out.println(prefix+"|-"+n.getCharAndFreq());
		if(n.hasLeft()){
			printTree(prefix + "|   ", n.getLeftNode());
		}
		if(n.hasRight()){
			printTree(prefix + "|   ", n.getRightNode());
		}
	}
	public int getNumOps(){
		return numOps;
	}
}