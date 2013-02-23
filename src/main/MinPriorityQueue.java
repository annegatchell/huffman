package src.main;

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

public class MinPriorityQueue<K extends Comparable<K>>{
	int size = 0;
	int maxSize;
	K[] pqueue;

	public MinPriorityQueue(int maxSize){
		pqueue = (K[]) new Comparable[maxSize];
		this.maxSize = maxSize;
	}

	public void insert(K item){

	}

	public K minimum(){
		if(size > 0){
			return pqueue[0]; 
		}
		else return null;
	}

	// public K extractMinimum(){
	// 	if(size < 1){
			
	// 	}
	// }


	//Only for testing purposes
	public K[] getCopyOfCurrentArrayValues(){
		K[] retval = (K[]) new Comparable[maxSize];
		for(int i = 0; i < maxSize; i++){
			retval[i] = pqueue[i];
		}
		return retval;
	}

}