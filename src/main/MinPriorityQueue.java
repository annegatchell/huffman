/*
*	This is a generic Minimum Priority Queue implementation
*	The objects used must implement Comparable.
*	This is implemented as a Min Heap.
*	If the 
*	
*	Author:
*	Anne Gatchell annegatchell@gmail.com
*
*	Date:
*	23 February 2013
*
*	Sources: CLRS, Sedgewick - Algorithms
*/

//How to deal with duplicates?
package src.main;
import java.lang.IllegalArgumentException;
import java.nio.BufferOverflowException;
import java.util.NoSuchElementException;
import java.util.Arrays;

public class MinPriorityQueue<K extends Comparable<K>>{
	int size = 0;
	int maxSize;
	K[] pqueue;
	int numOps;

	public MinPriorityQueue(K[] input){
		buildMinHeap(input);
	}

	public MinPriorityQueue(int maxSize){
		pqueue = (K[]) new Comparable[maxSize]; 
		numOps++;
		this.maxSize = maxSize; 
		numOps++;
	}

	// public K[] toArray(Class<K> clazz, int size){

	// 	K[] result = (K[])Array.newInstance(clazz,size);
	// 	return result;
	// }

	private int heapParent(int i){
		numOps++; //subtraction
		numOps++; //division
		return (i-1)/2; 
	}

	private int heapLeft(int i){
		numOps++; //multiplication
		numOps++; //addition
		return 2*i + 1; 
	}

	private int heapRight(int i){
		numOps++; //multiplication
		numOps++; //addition
		return 2*i + 2; 
	}

	private void exchange(int i, int j){
		K temp = pqueue[i]; 
		numOps++; //assignment
		pqueue[i] = pqueue[j]; 
		numOps++; //assignment
		pqueue[j] = temp;
		numOps++; //assignment
	}

	private void minHeapify(int i){
		int smallest;
		int l = heapLeft(i);
		numOps++; //assignment
		int r = heapRight(i);
		numOps++; //assignment
		if(l < size && pqueue[l].compareTo(pqueue[i]) < 0){
			smallest = l; 
			numOps++; //assignment
		}else{
			smallest = i;
			numOps++; //assignment
		}
		numOps++; //test(s) for if statement above
		if(r < size && pqueue[r].compareTo(pqueue[smallest]) < 0){
			smallest = r;
			numOps++; //assignment
		}
		numOps++; //tests(s) for if statement above
		if(smallest != i){
			exchange(i, smallest);
			minHeapify(smallest);
		}
		numOps++; //tests(s) for if statement above
	}

	private void buildMinHeap(K[] input){
		size = input.length;
		numOps++; //assignment
		maxSize = size;
		numOps++; //assignment
		pqueue = input;
		numOps++; //assignment
		for(int i = size/2; i >= 0; i--){
			numOps++; //check and increment i in loop
			minHeapify(i);
		}
	}

	private void heapDecreaseKey(int i, K key){
		// if(key.compareTo(pqueue[i]) > 0){
		// 	throw new IllegalArgumentException("The new key is larger than the current key");
		// }
		pqueue[i] = key;
		numOps++; //assignment
		while(i > 0 && pqueue[heapParent(i)].compareTo(pqueue[i]) > 0){
			numOps++; //loop tests
			exchange(i, heapParent(i));
			i = heapParent(i);
			numOps++; //assignment
		}
	}

	public boolean isEmpty(){
		numOps++; //test below
		return size == 0;
	}

	public void insert(K item){
		if(size == maxSize){
			throw new BufferOverflowException();
		}
		numOps++; //test above
		size = size+1;
		numOps++; //increment
		heapDecreaseKey(size-1, item);
	}

	public K minimum(){
		if(size > 0){
			numOps++; //test
			return pqueue[0]; 
		}else{
			numOps++; //test
			return null;
		}
	}

	public K extractMinimum(){
		if(size < 1){
			throw new NoSuchElementException("This queue was empty");
		}
		numOps++; //test
		K min = pqueue[0];
		numOps++; //assignment
		pqueue[0] = pqueue[size-1];
		numOps++; //assignment and subtraction
		numOps++;
		size--;
		numOps++; //decrement
		minHeapify(0);
		return min;
	}

	public int getSize(){
		return size;
	}

	public int getNumOps(){
		return numOps;
	}

	public String toString(){
		String retval = "size = "+size +" "+Arrays.toString(pqueue);
		return retval;
	}

}