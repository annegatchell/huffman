package src.test;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Assert;
import src.main.Node;
import src.main.Huffman;
import java.lang.Exception;

public class NodeTest {
    Node a100;
    Node b9;
    Node c101;
    Node d7;

    /**
     * Sets up the test fixture. 
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {
        a100 = new Node('a', 100, null, null);
        c101 = new Node('c', 101, a100, b9);
        b9 = new Node('b', 9, null, null);
        d7 = new Node('b', 9, null, null);
       
    }

    /**
     * Tears down the test fixture. 
     * (Called after every test case method.)
     */
    @After
    public void tearDown() {
        
    }

    @Test
    public void testBuildTrie() {
        
    }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("src.test.ElementTest");
    }

}