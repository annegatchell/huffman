package src.main;

import java.util.Random;

public class Experiment{
	int numTrials;
	int numDataPoints;
	AlphaSizeAndRuntime[] alphaSizes;
	int maxUnicodeChars = 100;//0xFFFF; //65,535 chars
	
	public Experiment(int numTries, int numDataPts){
		numTrials = numTries;
		numDataPoints = numDataPts;
		alphaSizes = new AlphaSizeAndRuntime[numDataPoints];
		int sizeAlpha;
		int interval = maxUnicodeChars/numDataPoints;
		for(int i = 0; i < numDataPoints;i++){
			sizeAlpha = (i+1)*interval;
			System.out.println("sizeAlpha "+sizeAlpha);
			alphaSizes[i] = new AlphaSizeAndRuntime(sizeAlpha, numTrials);
		}

	}
	
	class AlphaSizeAndRuntime{
		int size;
		int runtimes[];
		public AlphaSizeAndRuntime(int s, int numTrials){
			size = s;
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
				System.out.println("SIZE "+a.size);
				s = generateString(a.size);
				h = new Huffman(0, a.size);
				h.encode(s);
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
		Experiment e = new Experiment(1, 10);
		e.run();
	}
	
}