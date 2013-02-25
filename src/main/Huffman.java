package src.main;

public class Huffman{
	private int sizeOfAlphabet = 256; //ASCII
	private int actualSizeOfAlphabet;
	private Node tree;
	private String original;
	private String codeTable[];
	private int frequncies[];
	private int firstChar;
	private String codedString;


	public Huffman(int first, int sizeOfAlpha, String input){
		sizeOfAlphabet = sizeOfAlpha;
		original = input;
		frequncies = new int[sizeOfAlphabet];
		codeTable = new String[sizeOfAlphabet];
		firstChar = first;
	}

	public Huffman(int first, int sizeOfAlpha){
		sizeOfAlphabet = sizeOfAlpha;
		frequncies = new int[sizeOfAlphabet];
		codeTable = new String[sizeOfAlphabet];
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
		//Generate the table of codes
		generateCodeTable(tree);
		//Encode the message
		generateEncodedString();
		//Print string
		System.out.println(codedString);
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
			System.out.println("int "+(int)x.getChar()+" "+x.getChar());
			codeTable[(int)x.getChar() - firstChar] = s;
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
	public String[] getCodeTable(){
		return codeTable;
	}
	public String getEncodedString(){
		return codedString;
	}
	public void printTree(){
		
	}
}