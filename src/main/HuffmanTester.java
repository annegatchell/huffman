package src.main;

public class HuffmanTester{
	String test = "This sentence contains three a's, three c's, two d's, twenty-six e's, five f's, three g's, eight h's, thirteen i's, two l's, sixteen n's, nine o's, six r's, twenty-seven s's, twenty-two t's, two u's, five v's, eight w's, four x's, five y's, and only one z.";
	// String frost = "The Road Not Taken by Robert Frost ";
	// 	frost += "Two roads diverged in a yellow wood, ";
	// 	frost +="And sorry I could not travel both And be one traveler, long I stood ";
	// 	frost +="And looked down one as far as I could To where it bent in the undergrowth; ";
	// 	frost +="Then took the other, as just as fair, ";
	// 	frost +="And having perhaps the better claim, ";
	// 	frost +="Because it was grassy and wanted wear; ";
	// 	frost +="Though as for that the passing there Had worn them really about the same, ";
	// 	frost +="And both that morning equally lay ";
	// 	frost +="In leaves no step had trodden black. ";
	// 	frost +="Oh, I kept the first for another day! ";
	// 	frost +="Yet knowing how way leads on to way, I doubted if I should ever come back. ";
	// 	frost +="I shall be telling this with a sigh Somewhere ages and ages hence: ";
	// 	frost +="Two roads diverged in a wood, and I- I took the one less traveled by, ";
	// 	frost +="And that has made all the difference.";

	public static void main(String[] args){
		HuffmanTester t = new HuffmanTester();
		t.testString(t.test);		

	}

	public void testString(String test){
		Huffman h = new Huffman(0, 256);
		h.encode(test);
	}

	// public void testRobertFrost(){
	// 	Huffman h = new Huffman(0,)
	// }


}