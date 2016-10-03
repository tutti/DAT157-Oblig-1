import java.io.IOException;

import no.patternsolutions.javann.Perceptron;

public class Oppg4 {
	private String letters = "ABCDEJK";
	
//	private boolean[] A1 = {
//		false, false, true,  true,  false, false, false,
//		false, false, false, true,  false, false, false,
//		false, false, false, true,  false, false, false,
//		false, false, true,  false, true,  false, false,
//		false, false, true,  false, true,  false, false,
//		false, true,  true,  true,  true,  true,  false,
//		false, true,  false, false, false, true,  false,
//		false, true,  false, false, false, true,  false,
//		true,  true,  true,  false, true,  true,  true
//	};
	
//	private String A1 = 
//		"--##---"+
//		"---#---"+
//		"---#---"+
//		"--#-#--"+
//		"--#-#--"+
//		"-#####-"+
//		"-#---#-"+
//		"-#---#-"+
//		"###-###"
//	;
	
	public static void main(String[] args) throws IOException {
		Oppg4FontReader fontReader = new Oppg4FontReader("oppg4letters.txt");
		boolean[][] font1 = fontReader.getFont(0);
		boolean[][] font2 = fontReader.getFont(1);
		boolean[][] font3 = fontReader.getFont(2);
		
		boolean[][] outputs = new boolean[7][7];
		for (int i = 0; i < 7; ++i) {
			outputs[i][i] = true;
		}
		
		/*boolean[] A = font1[6];
		for (int i = 0; i < A.length; ++i) {
			if (i % 7 == 0)
				System.out.println();
			System.out.print(A[i] ? '#' : '-');
		}*/
		
		Perceptron perceptron = new Perceptron(font1[0].length, font1.length);
		perceptron.setIterations(10000);
		
		perceptron.trainPatterns(font1, outputs);
		perceptron.trainPatterns(font2, outputs);
		perceptron.trainPatterns(font3, outputs);
		
		boolean[][][] fonts = {font1, font2, font3};
		
		for (int f = 0; f < 3; ++f) {
			System.out.println("Font " + (f + 1));
			for (int i = 0; i < 7; ++i) {
				boolean[] out = perceptron.run(fonts[f][i]);
				for (boolean b : out) {
					System.out.print(b ? '#' : '-');
				}
				System.out.println();
			}
			System.out.println();
		}
	}
}
