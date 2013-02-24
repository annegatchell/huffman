package src.test;

import org.junit.*;
import static org.junit.Assert.*;
import org.junit.Assert;
import src.main.Node;
import src.main.MinPriorityQueue;
import java.util.Arrays;


public class MinPriorityQueueTest {
    MinPriorityQueue<Node> pq;
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
        pq = new MinPriorityQueue<Node>(4);
        a100 = new Node('a', 100, null, null);
        b9 = new Node('b', 9, null, null);
        c101 = new Node('c', 101, null, null);
        d7 = new Node('d', 7, null, null);
    }

    /**
     * Tears down the test fixture. 
     * (Called after every test case method.)
     */
    @After
    public void tearDown() {
        
    }
    // @Test
    // public void testParent(){
    //     assertEquals(0, pq.heapParent(1));
    //     assertEquals(0, pq.heapParent(2));
    //     assertEquals(1, pq.heapParent(3));
    //     assertEquals(1, pq.heapParent(4));
    // }

    // @Test
    // public void  testLeft(){
    //     assertEquals(1, pq.heapLeft(0));
    //     assertEquals(3, pq.heapLeft(1));
    // }

    // @Test
    // public void  testRight(){
    //     assertEquals(2, pq.heapRight(0));
    //     assertEquals(4, pq.heapRight(1));
    // }

    
    @Test
    public void testInsert() {
        System.out.println(a100);
        pq.insert(a100);
        Node[] array = new Node[4];
        array[0] = a100;
        String expected = "size = 1 " + Arrays.toString(array)+ "";
        String pqString = pq.toString();
        System.out.println(Arrays.toString(array));
        System.out.println("expected "+ expected);
        System.out.println("pqString "+ pqString);
        assertEquals(expected, pqString);
        assertEquals(1, pq.getSize());
        pq.insert(b9);
        array[1] = a100;
        array[0] = b9;
        expected = "size = 2 " + Arrays.toString(array); 
        pqString = pq.toString();
        System.out.println("expected "+ expected);
        System.out.println("pqString "+ pqString);
        assertEquals(expected, pqString);
        assertEquals(2, pq.getSize());
    }

    @Test
    public void testMinimum(){
        pq.insert(a100);
        assertEquals("min should be a100",a100, pq.minimum());
        pq.insert(b9);
        assertEquals("Min should be b9", b9, pq.minimum());
        pq.insert(c101);
        assertEquals("Min should be b9", b9, pq.minimum());
        pq.insert(d7);
        assertEquals("Min should be d7",d7, pq.minimum());
    }
    @Test
    public void testExtractMinimum(){
        Node value;
        pq.insert(a100);
        value = pq.extractMinimum();
        assertEquals(a100, value);
        assertEquals(0, pq.getSize());
        pq.insert(a100);
        pq.insert(b9);
        value = pq.extractMinimum();
        assertEquals(1, pq.getSize());
        assertEquals(b9, value);
        value = pq.extractMinimum();
        assertEquals(a100, value);
        assertEquals(0, pq.getSize());
        pq.insert(a100);
        pq.insert(b9);
        pq.insert(c101);
        value = pq.extractMinimum();
        assertEquals(b9, value);
        pq.insert(d7);
        value = pq.extractMinimum();
        assertEquals(d7, value);
    }


    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("src.test.ElementTest");
    }

}