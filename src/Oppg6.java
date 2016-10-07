import java.io.IOException;

import no.patternsolutions.javann.Bam;
import no.patternsolutions.javann.Hopfield;

public class Oppg6 {
	
	public static void main(String[] args) throws IOException {
		// Leverage the font reader to get cell shapes from a text file
		Oppg4FontReader cellReader = new Oppg4FontReader("oppg6newcancers.txt");
		
		boolean[][] cell1 = cellReader.getFont(0);
		boolean[][] cell2 = cellReader.getFont(1);
		boolean[][] cell3 = cellReader.getFont(2);
		boolean[][] cell4 = cellReader.getFont(3);
		
		boolean[][][] cells = {cell1, cell2, cell3, cell4};
		
		boolean[][] patterns = {cell1[0], cell2[0], cell3[0], cell4[0]};
		boolean[][] outpatterns = {cell1[1], cell2[1], cell3[1], cell4[1]};
		
		/*System.out.print("First input cell:");
		// Display cell 1
		for (int i = 0; i < cell1[0].length; ++i) {
			if ((i % 12) == 0) {
				System.out.println();
			}
			boolean b = cell1[0][i];
			System.out.print(b ? '#' : '-');
		}
		System.out.println();*/
		
		Hopfield hopfield = new Hopfield();
		hopfield.setIterations(100000);
		hopfield.setRandomUpdate(true);
		
		hopfield.trainPatterns(patterns);
		
		Bam bam = new Bam(144, 144);
		
		bam.trainPatterns(patterns, outpatterns);
		
		for (int i = 0; i < 4; ++i) {
			boolean[] hopfieldTest = hopfield.run(cells[i][0]);
			boolean[][] bamTest = bam.run(cells[i][0], null);
			System.out.print("Test cell input " + i + ":");
			for (int j = 0; j < cells[i][0].length; ++j) {
				if (j % 12 == 0) {
					System.out.println();
				}
				System.out.print(cells[i][0][j] ? '#' : '-');
			}
			System.out.println();
			System.out.println();
			System.out.print("Hopfield test cell output " + i + ":");
			for (int j = 0; j < hopfieldTest.length; ++j) {
				if (j % 12 == 0) {
					System.out.println();
				}
				System.out.print(hopfieldTest[j] ? '#' : '-');
			}
			System.out.println();
			System.out.println();
			System.out.println("BAM test cell output " + i + " (in):");
			for (int j = 0; j < bamTest[0].length; ++j) {
				if (j % 12 == 0) {
					System.out.println();
				}
				System.out.print(bamTest[0][j] ? '#' : '-');
			}
			System.out.println();
			System.out.println();
			System.out.println("BAM test cell output " + i + " (out):");
			for (int j = 0; j < bamTest[1].length; ++j) {
				if (j % 12 == 0) {
					System.out.println();
				}
				System.out.print(bamTest[1][j] ? '#' : '-');
			}
			System.out.println();
			System.out.println();
		}
		
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
