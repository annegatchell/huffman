/*
*	This is a test for encoding the poem The Road Not Taken
*	by Robert Frost using my Huffman Encoder implementation
*	 
*	It encodes the poem, and the following infomation is printed
*	to stdout:
*	-the input poem string
*	-the encoded binary string
*	-an ASCII representation of the Huffman Tree
*	-length of original string
*	-length of code 
*	-frequencie table
*	
*	Author:
*	Anne Gatchell annegatchell@gmail.com
*
*	Date:
*	23 February 2013
*
*/

package src.main;

public class HuffmanRobertFrost{
	String frost = "the road not taken by robert frost two roads diverged in a yellow wood, and sorry i could not travel both and be one traveler, long i stood and looked down one as far as i could to where it bent in the undergrowth; then took the other, as just as fair, and having perhaps the better claim, because it was grassy and wanted wear; though as for that the passing there had worn them really about the same, and both that morning equally lay in leaves no step had trodden black. oh, i kept the first for another day! yet knowing how way leads on to way, i doubted if i should ever come back. i shall be telling this with a sigh somewhere ages and ages hence: two roads diverged in a wood, and i- i took the one less traveled by, and that has made all the difference.";

	public static void main(String[] args){
		HuffmanRobertFrost t = new HuffmanRobertFrost();	
		t.testRobertFrost();
	}
	public void testRobertFrost(){
		Huffman h = new Huffman(0,256);
		h.encode(frost);
		System.out.println("Length of original string: "+frost.length());
		System.out.println("Length of code: "+h.getCodedStringLength());
		h.printCodeTable();
	}

}