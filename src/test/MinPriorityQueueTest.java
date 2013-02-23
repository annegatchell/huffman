package src.test;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Assert;
import src.main.Node;
import src.main.MinPriorityQueue;


public class MinPriorityQueueTest {
    MinPriorityQueue pq;
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
        pq = new MinPriorityQueue(4);
        a100 = new Node('a', 100, null, null);
        b9 = new Node('b', 9, null, null);
        c101 = new Node('c', 101, null, null);
    }

    /**
     * Tears down the test fixture. 
     * (Called after every test case method.)
     */
    @After
    public void tearDown() {
        
    }


    
    @Test
    public void testInsert() {
        pq.insert(a100);
        Node[] array = new Node[4];
        array[0] = a100;
        assertArrayEquals(array, pq.getCopyOfCurrentArrayValues());
        pq.insert(b9);
        array[1] = a100;
        array[0] = b9;
        assertArrayEquals(array, pq.getCopyOfCurrentArrayValues());
    }

    @Test
    public void testMinimum(){
        pq.insert(a100);
        assertEquals(a100, pq.minimum());
        pq.insert(b9);
        assertEquals(b9, pq.minimum());
        pq.insert(c101);
        assertEquals(b9, pq.minimum());
        pq.insert(d7);
        assertEquals(d7, pq.minimum());
    }
    @Test
    public void testExtractMinimum(){
        pq.insert(a100);
        assertEquals(a100, pq.extractMinimum());
        pq.insert(b9);
        assertEquals(b9, pq.minimum());
        pq.insert(c101);
        assertEquals(b9, pq.minimum());
        pq.insert(d7);
        assertEquals(d7, pq.minimum());
    }


    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("src.test.ElementTest");
    }

}