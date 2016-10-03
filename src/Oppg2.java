import no.patternsolutions.javann.Adaline;

public class Oppg2 {
	
	/*
	 * in = {true, true}
	 * out = {false}
	 * 
	 * in = {{true, true}, {true, false}}
	 * out = {{false}, {true}}
	 */
	
	public static final boolean[][] IN = {{true, true}, {true, false}, {false, true}, {false, false}};
	//public static final boolean[][][] OUT = {{{false}, {true}, {true}, {true}}, {{{true}, {true}, {tru}, {false}}};
	public static final boolean[][][] OUT = {{{true}, {true}, {true}, {false}}, {{false}, {true}, {true}, {true}}};
	public static final boolean[][] M_OUT = {{true}, {false}, {false}, {false}};
	
	public static void main(String[] args) {
		
		// Make and train the adalines and madaline
		Adaline a1 = new Adaline(2, 1);
		Adaline a2 = new Adaline(2, 1);
		Adaline madaline = new Adaline(2, 1);
		a1.trainPatterns(IN, OUT[0]);
		a2.trainPatterns(IN, OUT[1]);
		madaline.trainPatterns(IN, M_OUT);
		
		boolean TT = test(a1, a2, madaline, IN[0]);
		boolean TF = test(a1, a2, madaline, IN[1]);
		boolean FT = test(a1, a2, madaline, IN[2]);
		boolean FF = test(a1, a2, madaline, IN[3]);
		
		System.out.println("True  XOR True:  " + TT);
		System.out.println("True  XOR False: " + TF);
		System.out.println("False XOR True:  " + FT);
		System.out.println("False XOR False: " + FF);
	}
	
	private static boolean test(Adaline a1, Adaline a2, Adaline madaline, boolean[] input) {
		boolean[] o1 = a1.run(input);
		boolean[] o2 = a2.run(input);
		
		System.out.println(o1[0] + " " + o2[0]);
		
		boolean[] temp = {o1[0], o2[0]};
		boolean[] out = madaline.run(temp);
		
		return out[0];
	}
}
