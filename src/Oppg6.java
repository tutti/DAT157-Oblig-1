import java.io.IOException;
import java.util.Random;

import no.patternsolutions.javann.Bam;
import no.patternsolutions.javann.Hopfield;

public class Oppg6 {
	
	private static void printCell(boolean[] pattern, int width) {
		for (int i = 0; i < pattern.length; ++i) {
			if (i % width == 0) {
				System.out.println();
			}
			System.out.print(pattern[i] ? '#' : '-');
		}
	}
	
	private static boolean[] addNoise(boolean[] original, double noiseLevel) {
		// Randomly adds noise to a figure
		Random rnd = new Random();
		boolean[] output = new boolean[original.length];
		for (int i = 0; i < original.length; ++i) {
			float random = rnd.nextFloat();
			if (random <= noiseLevel)
				output[i] = !original[i];
			else
				output[i] = original[i];
		}
		return output;
	}
	
	public static void main(String[] args) throws IOException {
		// Leverage the font reader to get cell shapes from a text file
		Oppg4FontReader cellReader = new Oppg4FontReader("oppg6cancers.txt");
		
		boolean[][] cell1 = cellReader.getFont(0);
		boolean[][] cell2 = cellReader.getFont(1);
		boolean[][] cell3 = cellReader.getFont(2);
		boolean[][] cell4 = cellReader.getFont(3);
		
		boolean[][][] cells = {cell1, cell2, cell3, cell4};
		
		boolean[][] patterns = {cell1[0], cell2[0], cell3[0], cell4[0]};
		boolean[][] outpatterns = {cell1[1], cell2[1], cell3[1], cell4[1]};
		
		Hopfield hopfield = new Hopfield();
		hopfield.setIterations(100000);
		hopfield.setRandomUpdate(true);
		
		hopfield.trainPatterns(patterns);
		
		Bam bam = new Bam(144, 144);
		bam.trainPatterns(patterns, outpatterns);
		
		for (int i = 0; i < 4; ++i) {
			boolean[] hopfieldTest = hopfield.run(cells[i][0]);
			boolean[][] bamTest = bam.run(cells[i][0], null);
			System.out.print("Test cell input " + (i+1) + ":");
			/*for (int j = 0; j < cells[i][0].length; ++j) {
				if (j % 12 == 0) {
					System.out.println();
				}
				System.out.print(cells[i][0][j] ? '#' : '-');
			}*/
			printCell(cells[i][0], 12);
			System.out.println();
			System.out.println();
			System.out.print("Hopfield test cell output " + (i+1) + ":");
			/*for (int j = 0; j < hopfieldTest.length; ++j) {
				if (j % 12 == 0) {
					System.out.println();
				}
				System.out.print(hopfieldTest[j] ? '#' : '-');
			}*/
			printCell(hopfieldTest, 12);
			System.out.println();
			System.out.println();
			System.out.print("BAM test cell output " + (i+1) + " (in):");
			/*for (int j = 0; j < bamTest[0].length; ++j) {
				if (j % 12 == 0) {
					System.out.println();
				}
				System.out.print(bamTest[0][j] ? '#' : '-');
			}*/
			printCell(bamTest[0], 12);
			System.out.println();
			System.out.println();
			System.out.print("BAM test cell output " + (i+1) + " (out):");
			/*for (int j = 0; j < bamTest[1].length; ++j) {
				if (j % 12 == 0) {
					System.out.println();
				}
				System.out.print(bamTest[1][j] ? '#' : '-');
			}*/
			printCell(bamTest[1], 12);
			System.out.println();
			System.out.println();
		}
		
		boolean[] noiseInput = addNoise(cell3[0], 0.2);
		boolean[] hopfieldNoise = hopfield.run(noiseInput);
		boolean[][] bamNoise = bam.run(noiseInput, null);
		
		System.out.println("Test with 20% noise");
		System.out.print("Input pattern:");
		
		/*for (int i = 0; i < noiseInput.length; ++i) {
			if (i % 12 == 0) {
				System.out.println();
			}
			System.out.print(noiseInput[i] ? '#' : '-');
		}*/
		printCell(noiseInput, 12);
		
		System.out.println();
		System.out.println();
		System.out.print("Hopfield output:");
		printCell(hopfieldNoise, 12);
		System.out.println();
		System.out.println();
		System.out.print("BAM output (in):");
		printCell(bamNoise[0], 12);
		System.out.println();
		System.out.println();
		System.out.print("BAM output (out):");
		printCell(bamNoise[1], 12);
		
		
		
		//boolean[] test = hopfield.run(cell1[0]);
		
		/*Weight[][] weights = hopfield.getWeights();
		
		for (int i = 0; i < weights.length; ++i) {
			for (int j = 0; j < weights[i].length; ++j) {
				System.out.print(weights[i][j].getValue());
				System.out.print(" ");
			}
			System.out.println();
		}*/
		
		/*System.out.println();
		System.out.print("Output cell:");
		for (int i = 0; i < test.length; ++i) {
			if ((i % 12) == 0) {
				System.out.println();
			}
			boolean b = test[i];
			System.out.print(b ? '#' : '-');
		}*/
		
		/*System.out.println();
		System.out.println();
		
		boolean[][] out = bam.run(cell1[0], null);
		for (int i = 0; i < out.length; ++i) {
			for (int j = 0; j < out[i].length; ++j) {
				if (j % 12 == 0) System.out.println();
				System.out.print(out[i][j] ? '#' : '-');
			}
			System.out.println();
		}*/
	}
	
}
