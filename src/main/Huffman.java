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
		//System.out.println(pq);
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
		//System.out.println(pq);
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