/*
*	This is a runtime experiment for a Huffman Encoder
*	It runs an encoding on a randomly generated frequncy table for 
*	alphabet sizes ranging from 10^1 to 10^4, at intervals of
*	2*10^i, 3*10^i, and 7*10^i for good logarithmic plotting.
*	
*	It generates 10 random frequency tables for each alphabet size
*	and encodes each one. It then outputs all 10 operation counts
*	for each alphabet size.
*
*
*	Author:
*	Anne Gatchell annegatchell@gmail.com
*
*	Date:
*	23 February 2013
*
*	Educational Sources: Professor Clauset Analysis of Algorithms Lectures,
*						 CLRS, Sedgewick - Algorithms
*/

package src.main;

import java.util.Random;
import java.lang.Math;

public class Experiment{
	int numTrials;
	int numDataPointsPerPowerOf10;
	AlphaSizeAndRuntime[] alphaSizes;
	int maxUnicodeChars = 0xFFFF; //65,535 chars
	
	public Experiment(int numTries, int numDataPts){
		numTrials = numTries;
		numDataPointsPerPowerOf10 = numDataPts;
		int expMax = (int) Math.log10(maxUnicodeChars);
		int numDataPoints = expMax*numDataPointsPerPowerOf10;
		alphaSizes = new AlphaSizeAndRuntime[numDataPoints];
		//System.out.println("expMax "+expMax);
		int e = 1;
		int i = 0;
		int num;
		while(i < numDataPoints && e <= expMax){
			num = (int)(2*Math.pow(10,e));
			alphaSizes[i] = new AlphaSizeAndRuntime(num, numTrials);
			i++;
			if(i >= alphaSizes.length){
				break;
			}
			num = (int)(3*Math.pow(10,e));
			alphaSizes[i] = new AlphaSizeAndRuntime(num, numTrials);
			i++;
			if(i >= alphaSizes.length){
				break;
			}
			num = (int)(7*Math.pow(10,e));
			alphaSizes[i] = new AlphaSizeAndRuntime(num, numTrials);
			i++;
			e++;
		}
	}
	
	class AlphaSizeAndRuntime{
		int size;
		int runtimes[];
		public AlphaSizeAndRuntime(int s, int numTrials){
			if(size >= maxUnicodeChars){
				size = maxUnicodeChars-1;
			} else {
				size = s;
			}
			//System.out.println(size);
			runtimes = new int[numTrials];
		}
		public void setARuntime(int i, int r){
			runtimes[i] = r;
		}

		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append(size);
			for(int i : runtimes){
				sb.append("\t"+i+"\n");
			}
			return sb.toString();
		}
	}

	public void run(){
		String s;
		Huffman h;
		for(AlphaSizeAndRuntime a : alphaSizes){
			for(int t = 0; t < a.runtimes.length; t++){
				//System.out.println("SIZE "+a.size);
				s = generateString(a.size);
				h = new Huffman(0, a.size);
				h.encode(s);
				a.setARuntime(t, h.getNumOps());
			}
		}
		System.out.println("RESULTS: \n"+ toString());
	}

	public void runWithPremadeFreqTable(){
		int[] freqs;
		Huffman h;
		for(AlphaSizeAndRuntime a : alphaSizes){
			for(int t = 0; t < a.runtimes.length; t++){
				//System.out.println("SIZE "+a.size);
				freqs = generateFrequencyArray(a.size);
				h = new Huffman(0, a.size);
				h.encodeWithPremadeFreq(freqs);
				a.setARuntime(t, h.getNumOps());
			}
		}
		System.out.println("RESULTS: \n"+ toString());
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(AlphaSizeAndRuntime a : alphaSizes){
			sb.append(a.toString());
			sb.append("\n");
		}
		return sb.toString();
	}

	public int[] generateFrequencyArray(int alphabetSize){
		int[] freq = new int[alphabetSize];
		Random rand = new Random();
		int maxCharNum = alphabetSize/2;
		for(int i = 0; i<alphabetSize;i++){
			freq[i] = rand.nextInt(maxCharNum);
		}
		return freq;
	}

	public String generateString(int alphabetSize){
		int length = 10*alphabetSize;
		StringBuilder sb = new StringBuilder();
		char c;
		Random rand = new Random();
		for(int i = 0; i < length; i++){
			c = (char) rand.nextInt(alphabetSize);
			sb.append(c);
		}
		return sb.toString();
	}

	public static void main(String[] args){
		Experiment e = new Experiment(10, 3);
		// e.run();
		e.runWithPremadeFreqTable();
	}
	
}