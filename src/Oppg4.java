import java.io.IOException;

import no.patternsolutions.javann.Backpropagation;
import no.patternsolutions.javann.Perceptron;

public class Oppg4 {
	private static String letters = "ABCDEJK";
	
	private static String findLetter(boolean[] output) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (int i = 0; i < output.length; ++i) {
			if (output[i]) {
				if (!first) sb.append(", ");
				sb.append(letters.charAt(i));
				first = false;
			}
		}
		return sb.toString();
	}
	
	private static String findLetter(double[] output) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (int i = 0; i < output.length; ++i) {
			if (output[i] >= 0.5) {
				if (!first) sb.append(", ");
				sb.append(letters.charAt(i));
				first = false;
			}
		}
		return sb.toString();
	}
	
	private static double[] toDoubles(boolean[] arr) {
		double[] out = new double[arr.length];
		for (int i = 0; i < arr.length; ++i) {
			out[i] = arr[i] ? 1 : 0;
		}
		return out;
	}
	
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

		System.out.println("Testing a perceptron network:");
		for (int f = 0; f < 3; ++f) {
			System.out.println("Font " + (f + 1));
			for (int i = 0; i < letters.length(); ++i) {
				boolean[] out = perceptron.run(fonts[f][i]);
				System.out.print("Testing with "+letters.charAt(i)+": ");
				System.out.println(findLetter(out));
			}
			System.out.println();
		}
		
		int[] hidden = {40};
		Backpropagation pony = new Backpropagation(63, hidden, 7);
		pony.setIterations(10000);
		
		pony.trainPatterns(font1, outputs);
		pony.trainPatterns(font2, outputs);
		pony.trainPatterns(font3, outputs);
		
		System.out.println("Testing an MLP network:");
		for (int f = 0; f < 3; ++f) {
			System.out.println("Font " + (f + 1));
			for (int i = 0; i < letters.length(); ++i) {
				double[] out = pony.run(toDoubles(fonts[f][i]));
				System.out.print("Testing with "+letters.charAt(i)+": ");
				System.out.println(findLetter(out));
			}
			System.out.println();
		}
	}
}
