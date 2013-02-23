package src.test;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Assert;
import src.main.Node;
import java.lang.Exception;

public class NodeTest {
    Node a100;
    Node b9;
    Node c101;

    /**
     * Sets up the test fixture. 
     * (Called before every test case method.)
     */
    @Before
    public void setUp() {
        a100 = new Node('a', 100, null, null);
        b9 = new Node('b', 9, null, null);
        c101 = new Node('c', 101, a100, b9);
       
    }

    /**
     * Tears down the test fixture. 
     * (Called after every test case method.)
     */
    @After
    public void tearDown() {
        
    }

    @Test (expected = IllegalArgumentException.class)
    public void testNegativeInstantion(){
        Node dneg = new Node('d', -4, null, null);
    }
    
    @Test
    public void testComparison() {
        assertEquals("Should be more a's than b's", 91, a100.compareTo(b9));
        assertEquals("Should be fewer b's than a's", -91, b9.compareTo(a100));
    }

    @Test
    public void testIsLeaf(){
        assertTrue("Is leaf", a100.isLeaf());
        assertFalse("Not leaf", c101.isLeaf());
    }

    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("src.test.ElementTest");
    }

}