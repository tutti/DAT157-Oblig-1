import java.util.Random;

import no.patternsolutions.javann.Adaline;
import no.patternsolutions.javann.Backpropagation;
import no.patternsolutions.javann.Perceptron;

public class Oppg1 {
	
	private static boolean[] T = {
		true,  true,  true,  true,  true,  true,  true,  true,  true,  true,
		true,  true,  true,  true,  true,  true,  true,  true,  true,  true,
		false, false, false, false, true,  true,  false, false, false, false,
		false, false, false, false, true,  true,  false, false, false, false,
		false, false, false, false, true,  true,  false, false, false, false,
		false, false, false, false, true,  true,  false, false, false, false,
		false, false, false, false, true,  true,  false, false, false, false,
		false, false, false, false, true,  true,  false, false, false, false,
		false, false, false, false, true,  true,  false, false, false, false,
		false, false, false, false, true,  true,  false, false, false, false
	};
	
	private static boolean[] O = {
		true,  true,  true,  true,  true,  true,  true,  true,  true,  true,
		true,  true,  true,  true,  true,  true,  true,  true,  true,  true,
		true,  true,  false, false, false, false, false, false, true,  true,
		true,  true,  false, false, false, false, false, false, true,  true,
		true,  true,  false, false, false, false, false, false, true,  true,
		true,  true,  false, false, false, false, false, false, true,  true,
		true,  true,  false, false, false, false, false, false, true,  true,
		true,  true,  false, false, false, false, false, false, true,  true,
		true,  true,  true,  true,  true,  true,  true,  true,  true,  true,
		true,  true,  true,  true,  true,  true,  true,  true,  true,  true
	};
	
	private static boolean[] N = {
		true,  true,  true,  false, false, false, false, false, true,  true,
		true,  true,  true,  false, false, false, false, false, true,  true,
		true,  true,  true,  true,  false, false, false, false, true,  true,
		true,  true,  true,  true,  true,  false, false, false, true,  true,
		true,  true,  false, true,  true,  true,  false, false, true,  true,
		true,  true,  false, false, true,  true,  true,  false, true,  true,
		true,  true,  false, false, false, true,  true,  true,  true,  true,
		true,  true,  false, false, false, false, true,  true,  true,  true,
		true,  true,  false, false, false, false, false, true,  true,  true,
		true,  true,  false, false, false, false, false, false, true,  true
	};
	
	private static boolean[] T_noise10 = {
		true,  true,  true,  true,  true,  true,  false, true,  true,  true,
		true,  true,  false, true,  true,  true,  true,  true,  true,  true,
		false, false, true,  false, true,  true,  false, false, false, false,
		false, false, false, false, true,  false, false, false, false, false,
		false, false, false, false, true,  true,  false, false, true,  false,
		false, true,  false, false, true,  true,  false, false, false, false,
		false, false, false, false, true,  true,  false, true,  false, false,
		false, false, false, false, false, true,  false, false, false, false,
		false, true,  false, false, true,  true,  false, false, false, false,
		false, false, false, false, true,  true,  false, false, false, true
	};
	
	private static boolean[] T_noise20 = {
		true,  true,  true,  false, false, true,  true,  true,  true,  true,
		true,  false, true,  true,  false, false, true,  true,  true,  true,
		false, false, true,  false, true,  true,  false, true,  false, false,
		false, true,  false, false, true,  true,  false, true,  false, false,
		false, false, false, false, true,  true,  false, false, false, false,
		false, true,  false, false, false, false, true,  false, false, true,
		false, false, true,  false, true,  false, false, false, true,  false,
		true,  false, false, false, true,  true,  false, true,  false, false,
		false, true,  false, false, false, true,  false, true,  true,  false,
		true,  false, false, false, true,  false, false, false, false, false
	};
	
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
	
	private static double[] toDoubles(boolean[] arr) {
		// Converts a true/false array to a 1.0/0.0 array because some methods require it
		double[] out = new double[arr.length];
		for (int i = 0; i < arr.length; ++i) {
			out[i] = arr[i] ? 1 : 0;
		}
		return out;
	}
	
	private static String resultstring(boolean[] conclusions, String[] names) {
		// Prints a list of letters corresponding to the true values in the boolean array
		StringBuilder out = new StringBuilder();
		boolean multiple = false;
		for (int i = 0; i < conclusions.length; ++i) {
			if (conclusions[i]) {
				if (multiple) out.append(", ");
				out.append(names[i]);
				multiple = true;
			}
		}
		return out.toString();
	}
	
	private static String resultstring(double[] conclusions, String[] names) {
		// Prints a list of each name along with its confidence level
		StringBuilder out = new StringBuilder();
		boolean first = true;
		for (int i = 0; i < conclusions.length; ++i) {
			if (!first) out.append(", ");
			out.append(String.format("%1s (%0$.2f%%)", names[i], conclusions[i]*100));
			first = false;
		}
		return out.toString();
	}
	
	public static void main(String[] args) {
		boolean[][] patterns = {T, O, N};
		boolean[][] output = {{true, false, false}, {false, true, false}, {false, false, true}};
		String[] names = {"T", "O", "N"};
		
		// Create the perceptron network
		Perceptron perceptron = new Perceptron(100, 3);
		perceptron.setIterations(10000);
		
		// Train the perceptron network
		perceptron.trainPatterns(patterns, output);
		
		// Test the perceptron network
		boolean[][] perceptronResults = new boolean[7][3];
		perceptronResults[0] = perceptron.run(T); // Perfect T
		perceptronResults[1] = perceptron.run(O); // Perfect O
		perceptronResults[2] = perceptron.run(N); // Perfect N
		perceptronResults[3] = perceptron.run(T_noise10); // T with 10% predetermined noise
		perceptronResults[4] = perceptron.run(T_noise20); // T with 20% predetermined noise
		perceptronResults[5] = perceptron.run(addNoise(T, 0.1)); // T with 10% random noise
		perceptronResults[6] = perceptron.run(addNoise(T, 0.2)); // T with 20% random noise
		
		System.out.println("Results from perceptron network:");
		System.out.println("Test of perfect T: " + resultstring(perceptronResults[0], names));
		System.out.println("Test of perfect O: " + resultstring(perceptronResults[1], names));
		System.out.println("Test of perfect N: " + resultstring(perceptronResults[2], names));
		System.out.println("Test of T with 10% predetermined noise: " + resultstring(perceptronResults[3], names));
		System.out.println("Test of T with 20% predetermined noise: " + resultstring(perceptronResults[4], names));
		System.out.println("Test of T with 10% random noise: " + resultstring(perceptronResults[5], names));
		System.out.println("Test of T with 20% random noise: " + resultstring(perceptronResults[6], names));
		
		// Create the adaline network
		Adaline adaline = new Adaline(100, 3);
		adaline.setIterations(10000);
		//adaline.setWeightsInit(0.5);
		
		// Train the adaline network
		adaline.trainPatterns(patterns, output);
		
		// Test the adaline network
		boolean[][] adalineResults = new boolean[7][3];
		adalineResults[0] = adaline.run(T); // Perfect T
		adalineResults[1] = adaline.run(O); // Perfect O
		adalineResults[2] = adaline.run(N); // Perfect N
		adalineResults[3] = adaline.run(T_noise10); // T with 10% predetermined noise
		adalineResults[4] = adaline.run(T_noise20); // T with 20% predetermined noise
		adalineResults[5] = adaline.run(addNoise(T, 0.1)); // T with 10% random noise
		adalineResults[6] = adaline.run(addNoise(T, 0.2)); // T with 20% random noise
		
		System.out.println();
		System.out.println("Results from adaline network:");
		System.out.println("Test of perfect T: " + resultstring(adalineResults[0], names));
		System.out.println("Test of perfect O: " + resultstring(adalineResults[1], names));
		System.out.println("Test of perfect N: " + resultstring(adalineResults[2], names));
		System.out.println("Test of T with 10% predetermined noise: " + resultstring(adalineResults[3], names));
		System.out.println("Test of T with 20% predetermined noise: " + resultstring(adalineResults[4], names));
		System.out.println("Test of T with 10% random noise: " + resultstring(adalineResults[5], names));
		System.out.println("Test of T with 20% random noise: " + resultstring(adalineResults[6], names));
		
		// Now try it with an MLP network
		int[] hiddenLayerSizes = {20};
		Backpropagation mlp = new Backpropagation(100, hiddenLayerSizes, 3);
		mlp.setIterations(10000);
		
		mlp.trainPatterns(patterns, output);
		
		double[][] mlpResults = new double[7][3];
		mlpResults[0] = mlp.run(toDoubles(T)); // Perfect T
		mlpResults[1] = mlp.run(toDoubles(O)); // Perfect O
		mlpResults[2] = mlp.run(toDoubles(N)); // Perfect N
		mlpResults[3] = mlp.run(toDoubles(T_noise10)); // T with 10% predetermined noise
		mlpResults[4] = mlp.run(toDoubles(T_noise20)); // T with 20% predetermined noise
		mlpResults[5] = mlp.run(toDoubles(addNoise(T, 0.1))); // T with 10% random noise
		mlpResults[6] = mlp.run(toDoubles(addNoise(T, 0.2))); // T with 20% random noise
		
		System.out.println();
		System.out.println("Results from MLP network:");
		System.out.println("Test of perfect T: " + resultstring(mlpResults[0], names));
		System.out.println("Test of perfect O: " + resultstring(mlpResults[1], names));
		System.out.println("Test of perfect N: " + resultstring(mlpResults[2], names));
		System.out.println("Test of T with 10% predetermined noise: " + resultstring(mlpResults[3], names));
		System.out.println("Test of T with 20% predetermined noise: " + resultstring(mlpResults[4], names));
		System.out.println("Test of T with 10% random noise: " + resultstring(mlpResults[5], names));
		System.out.println("Test of T with 20% random noise: " + resultstring(mlpResults[6], names));
	}
}
