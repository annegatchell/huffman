package src.main;

public class HuffmanTester{
	String test = "This sentence contains three a's, three c's, two d's, twenty-six e's, five f's, three g's, eight h's, thirteen i's, two l's, sixteen n's, nine o's, six r's, twenty-seven s's, twenty-two t's, two u's, five v's, eight w's, four x's, five y's, and only one z.";

	public static void main(String[] args){
		HuffmanTester t = new HuffmanTester();
		t.testString(t.test);		

	}

	public void testString(String test){
		Huffman h = new Huffman(0, 256);
		h.encode(test);
	}

}