package src.test;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Assert;
import src.main.Node;
import src.main.Huffman;
import java.lang.Exception;

public class HuffmanTest {
    Node a100;
    Node b9;
    Node c101;
    Node d7;
    Huffman h;

    /**
     * Sets up the test fixture. 
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {
       
    }

    /**
     * Tears down the test fixture. 
     * (Called after every test case method.)
     */
    @After
    public void tearDown() {
        
    }

    @Test
    public void testGetFrequncies(){
       //System.out.println((int) 'a');
        h = new Huffman((int)'a', 4);
        h.encode("aaabbc");
        int[] expected = {3,2,1,0};
        assertArrayEquals(expected, h.getFrequencies());
        h = new Huffman((int)'a', 4);
        h.encode("aaaaaaaaaaabbbbbbbbbbccccccccddddddd");
        int[] expected2 = {11, 10, 8, 7};
        assertArrayEquals(expected2, h.getFrequencies());
    }

    @Test
    public void testBuildTree(){
        //System.out.println((char) 0 +" "+ (int) ((char) -1));

        h = new Huffman((int)'a', 4);
        h.encode("aaaaaaaaaaabbbbbbbbbbccccccccddddddd");
        Node a11 = new Node('a', 11, null, null);
        Node b10 = new Node('b', 10, null, null);
        Node c8 = new Node('c', 8, null, null);
        Node d7 = new Node('d', 7, null, null);
        Node dc = new Node('.', 15, d7, c8);
        Node ba = new Node('.', 21, b10, a11);
        Node expected = new Node('.', 36, dc, ba);
        System.out.println("Expected: "+expected);
        System.out.println("Tree    : "+h.getTree());
        assertEquals(expected.toString(), h.getTree().toString());
    }

    @Test
    public void testGeneratedCodeTable(){
        h = new Huffman((int)'a', 4);
        h.encode("aaaaaaaaaaabbbbbbbbbbccccccccddddddd");
        String[] expected = {"11","10","01","00"};
        assertArrayEquals(expected, h.getCodeTable());
    }

    @Test
    public void testCodedString(){
        h = new Huffman((int)'a', 4);
        h.encode("aaaaaaaaaaabbbbbbbbbbccccccccddddddd");
        String a = "1111111111111111111111";
        String b = "10101010101010101010";
        String c = "0101010101010101";
        String d = "00000000000000";
        String expected = a+b+c+d;
        assertEquals(expected, h.getEncodedString());
    }

    // @Test
    // public void testAllSameFreqs() {
    //     String input = "abcd";
    //     Huffman h = new Huffman(4, input);
    //     Node trie = h.encode(input);
    //     String output = h.getCompressed();
    //     //assertEquals("output should be 'test'", "tes", output);

    // }

    // @Test
    // public void testNotAllLettersUsed(){
    //     String input = "abd";
    //     Huffman h = new Huffman(4);
    //     Node trie = h.encode(input);
    //     String output = h.getCompressed();
    //     //assertEquals("output should be 'test'", "test", output);

    // }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("src.test.ElementTest");
    }

}