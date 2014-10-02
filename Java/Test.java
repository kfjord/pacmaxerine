import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


class Test {

	public static int test(char[] g1, char[] g2) {

		PacGene pg1,pg2;
		DuelResults dr;

		try {
			pg1=new PacGene(new String(g1));
			pg2=new PacGene(new String(g2));
			dr=PacWarGuts.duel(pg1,pg2,500);
			System.out.println("Rounds: "+dr.rounds+" Gene1: "+dr.count1+" Gene2: "+dr.count2);
			return dr.count2;
		} catch(Exception e) {
			System.err.println("Error: "+e);
		}
		return 0;
	}

	public static void main(String[] args){
		char[] ones = new char[50];
		Arrays.fill(ones, '1');
		char[] threes = new char[50];
		Arrays.fill(threes, '3');
		
		ArrayList<char[]> genePool = new ArrayList<char[]>();
		//some okay genes
		genePool.add("33233333303333332332233331333333323302332330013333".toCharArray());
		genePool.add("13313332301101111111111211211111121131110311111201".toCharArray());
		genePool.add("33333333303333332333333331333333333333331231111111".toCharArray());
		genePool.add("11111120121113110101320103122032033233333133323203".toCharArray());
		genePool.add("32033333003133331333333331313333303131333022130133".toCharArray());

		
		for (int p=10; p<50; p+=10){
			char[] result = breed(ones, threes, p);
			System.out.println("result: " + new String(result));
			test(ones, result);
			test(threes, result);
			genePool.add(result);
		}
		for (int i=0; i<genePool.size(); i++){
			for (int j=0; j<genePool.size(); j++){
				if (i != j){
					System.out.println("Gene1: " + new String(genePool.get(i)));
					System.out.println("Gene2: " + new String(genePool.get(j)));
					char[] result = breed(genePool.get(i), genePool.get((i+1)%genePool.size()), 25);
					System.out.println("result: " + new String(result));
					int v1s = test(ones, result);
					int v3s = test(threes, result);
				}
			}


		}
	}
	
	public static char[] breed(char[] g1, char[] g2, int crossover){
		
		char[] result = new char[50];
		Random rng = new Random();
		for (int i=0; i<g1.length; i++){
			//mutation
			if (rng.nextInt(10) < 3) { //30% chance
				//uniform chance of 0, 1, 2, or 3
				result[i] = (char)(rng.nextInt(4) + 48);
			} else if (i<crossover){
				result[i] = g1[i];
			} else {
				result[i] = g2[i];
			}
		}
		return result;
	}
}
