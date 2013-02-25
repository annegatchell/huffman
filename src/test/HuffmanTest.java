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
        System.out.println((int) 'a');
        h = new Huffman((int)'a', 4);
        h.encode("aaabbc");
        int[] expected = {3,2,1,0};
        assertArrayEquals(expected, h.getFrequencies());
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