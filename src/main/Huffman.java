package src.main;

public class Huffman{
	private int sizeOfAlphabet = 256; //ASCII
	private int actualSizeOfAlphabet;
	private Node tree;
	private String original;
	private boolean codeTable[];
	private int frequncies[];
	private int firstChar;


	public Huffman(int first, int sizeOfAlpha, String input){
		sizeOfAlphabet = sizeOfAlpha;
		original = input;
		codeTable = new boolean[sizeOfAlphabet];
		frequncies = new int[sizeOfAlphabet];
		firstChar = first;
	}

	public Huffman(int first, int sizeOfAlpha){
		sizeOfAlphabet = sizeOfAlpha;
		codeTable = new boolean[sizeOfAlphabet];
		frequncies = new int[sizeOfAlphabet];
		firstChar = first;
	}

	//Returns
	public Node encode(String input){
		original = input;
		//Tabulate frequencies of characters
		calculateFrequencies();
		//Create min priority queue
		MinPriorityQueue<Node> pq = new MinPriorityQueue<Node>(sizeOfAlphabet);
		generateFreqNodesAndAddToMinPQ(pq);
		//System.out.println(pq);
		actualSizeOfAlphabet = pq.getSize();
		//Generate the tree
		buildTree(pq);
		//tree = new Node('a',100, null, null);
		return tree;
	}

	private void buildTree(MinPriorityQueue<Node> pq){
		Node x, y, z;
		for(int i = 0; i < actualSizeOfAlphabet-1; i++){
			x = pq.extractMinimum();
			y = pq.extractMinimum();
			z = new Node('.', x.getFrequency()+y.getFrequency(), x, y);
			pq.insert(z);
		}
		tree = pq.extractMinimum();
		System.out.println("\n"+tree);
	}

	private void generateFreqNodesAndAddToMinPQ(MinPriorityQueue<Node> pq){
		char c;
		for(int i = 0; i < sizeOfAlphabet; i++){
			if(frequncies[i] > 0){
				pq.insert(new Node((char)(i+firstChar), frequncies[i], null, null));
			}
		}
	}

	private void calculateFrequencies(){
		int index;
		for(int i = 0; i < original.length(); i++){
			index = ((int) original.charAt(i)) - firstChar;
			//System.out.println("charat(i) " + (int)original.charAt(i) + 
			//	" index " +index+" firstChar "+firstChar);
			frequncies[index]++;
		}
	}

	private int calculateOutPutSize(){
		return -1;	
	}

	public void getCompressed(){
	}

	public int[] getFrequencies(){
		return frequncies;
	}
	public Node getTree(){
		return tree;
	}
	private void printTree(){

	}
}