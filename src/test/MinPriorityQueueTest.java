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
    boolean DEBUG;

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
        DEBUG = false;
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
        pq.insert(a100);
        Node[] array = new Node[4];
        array[0] = a100;
        String expected = "size = 1 " + Arrays.toString(array)+ "";
        String pqString = pq.toString();
        if(DEBUG){
            System.out.println(Arrays.toString(array));
            System.out.println("expected "+ expected);
            System.out.println("pqString "+ pqString);
        }
        assertEquals(expected, pqString);
        assertEquals(1, pq.getSize());
        pq.insert(b9);
        array[1] = a100;
        array[0] = b9;
        expected = "size = 2 " + Arrays.toString(array); 
        pqString = pq.toString();
        if(DEBUG){
            System.out.println("expected "+ expected);
            System.out.println("pqString "+ pqString);
        }
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
        assertEquals(3, pq.getSize());
        value = pq.extractMinimum();
        assertEquals(b9, value);
        assertEquals(2, pq.getSize());
        pq.insert(d7);
        assertEquals(3, pq.getSize());
        value = pq.extractMinimum();
        assertEquals(2, pq.getSize());
        assertEquals(d7, value);
        value = pq.extractMinimum();
        assertEquals(1, pq.getSize());
        assertEquals(a100, value);
        value = pq.extractMinimum();
        assertEquals(0, pq.getSize());
        assertEquals(c101, value);
    }

    @Test
    public void testEmpty(){
        assertEquals(true, pq.isEmpty());
        pq.insert(a100);
        assertEquals(false, pq.isEmpty());
        pq.extractMinimum();
        assertEquals(true, pq.isEmpty());
    }

    @Test
    public void testCreatingQueueFromArrayOfNodes(){
        Node[] inputArray = {a100, b9, c101, d7};
        MinPriorityQueue pq_array = new MinPriorityQueue(inputArray);
        Node[] array = {d7, b9, c101, a100};
        String expected = "size = 4 " + Arrays.toString(array)+ "";
        if(DEBUG){
            System.out.println("expected "+ expected);
            System.out.println("pqString "+ pq_array.toString());
        }
        assertEquals(expected, pq_array.toString());
        assertEquals("Min should be d7", d7, pq_array.extractMinimum());
        assertEquals("Min should be b9", b9, pq_array.extractMinimum());
        assertEquals("Min should be a100", a100, pq_array.extractMinimum());
        assertEquals("Min should be c101", c101, pq_array.extractMinimum());
    }


    public static void main(String args[]) {
        org.junit.runner.JUnitCore.main("src.test.ElementTest");
    }

}