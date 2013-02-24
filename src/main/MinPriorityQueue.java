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

	public MinPriorityQueue(K[] input){
		buildMinHeap(input);
	}

	public MinPriorityQueue(int maxSize){
		pqueue = (K[]) new Comparable[maxSize];
		this.maxSize = maxSize;
	}

	private int heapParent(int i){
		return (i-1)/2;
	}

	private int heapLeft(int i){
		return 2*i + 1;
	}

	private int heapRight(int i){
		return 2*i + 2;
	}

	private void exchange(int i, int j){
		K temp = pqueue[i];
		pqueue[i] = pqueue[j];
		pqueue[j] = temp;
	}

	private void minHeapify(int i){
		int smallest;
		int l = heapLeft(i);
		int r = heapRight(i);
		if(l < size && pqueue[l].compareTo(pqueue[i]) < 0){
			smallest = l;
		}else smallest = i;
		if(r < size && pqueue[r].compareTo(pqueue[smallest]) < 0){
			smallest = r;
		}
		if(smallest != i){
			exchange(i, smallest);
			minHeapify(smallest);
		}
	}

	private void buildMinHeap(K[] input){
		size = input.length;
		maxSize = size;
		pqueue = input;
		for(int i = size/2; i >= 0; i--){
			minHeapify(i);
		}
	}

	private void heapDecreaseKey(int i, K key){
		// if(key.compareTo(pqueue[i]) > 0){
		// 	throw new IllegalArgumentException("The new key is larger than the current key");
		// }
		pqueue[i] = key;
		while(i > 0 && pqueue[heapParent(i)].compareTo(pqueue[i]) > 0){
			exchange(i, heapParent(i));
			i = heapParent(i);
		}
	}

	public boolean isEmpty(){
		return size == 0;
	}

	public void insert(K item){
		if(size == maxSize){
			throw new BufferOverflowException();
		}
		size = size+1;
		heapDecreaseKey(size-1, item);

	}

	public K minimum(){
		if(size > 0){
			return pqueue[0]; 
		}
		else {return null;}
	}

	public K extractMinimum(){
		if(size < 1){
			throw new NoSuchElementException("This queue was empty");
		}
		K min = pqueue[0];
		pqueue[0] = pqueue[size-1];
		size--;
		minHeapify(0);
		return min;
	}

	public int getSize(){
		return size;
	}

	public String toString(){
		String retval = "size = "+size +" "+Arrays.toString(pqueue);
		return retval;
	}

}