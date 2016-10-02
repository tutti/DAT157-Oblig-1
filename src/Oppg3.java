import java.util.HashMap;
import java.util.Map;

import no.patternsolutions.javann.Backpropagation;

public class Oppg3 {
    
    private static double[] STOP =            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double[] Alanine =         {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double[] Arginine =        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double[] Asparagine =      {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double[] AsparticAcid =    {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double[] Cysteine =        {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double[] GlutamicAcid =    {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double[] Glutamine =       {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double[] Glycine =         {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double[] Histidine =       {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double[] Isoleucine =      {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double[] Leucine =         {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double[] Lysine =          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0};
    private static double[] Methionine =      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0};
    private static double[] Phenylalanine =   {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0};
    private static double[] Proline =         {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0};
    private static double[] Serine =          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
    private static double[] Threonine =       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0};
    private static double[] Tryptophan =      {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0};
    private static double[] Tyrosine =        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0};
    private static double[] Valine =          {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
    
    private static Map<String, double[]> solution;
    static {
        solution = new HashMap<String, double[]>();
        solution.put("UUU", Phenylalanine);     solution.put("UCU", Serine);            solution.put("UAU", Tyrosine);          solution.put("UGU", Cysteine);
        solution.put("UUC", Phenylalanine);     solution.put("UCC", Serine);            solution.put("UAC", Tyrosine);          solution.put("UGC", Cysteine);
        solution.put("UUA", Leucine);           solution.put("UCA", Serine);            solution.put("UAA", STOP);              solution.put("UGA", STOP);
        solution.put("UUG", Leucine);           solution.put("UCG", Serine);            solution.put("UAG", STOP);              solution.put("UGG", Tryptophan);
                                                                                                                                
        solution.put("CUU", Leucine);           solution.put("CCU", Proline);           solution.put("CAU", Histidine);         solution.put("CGU", Arginine);
        solution.put("CUC", Leucine);           solution.put("CCC", Proline);           solution.put("CAC", Histidine);         solution.put("CGC", Arginine);
        solution.put("CUA", Leucine);           solution.put("CCA", Proline);           solution.put("CAA", Glutamine);         solution.put("CGA", Arginine);
        solution.put("CUG", Leucine);           solution.put("CCG", Proline);           solution.put("CAG", Glutamine);         solution.put("CGG", Arginine);

        solution.put("AUU", Isoleucine);        solution.put("ACU", Threonine);         solution.put("AAU", Asparagine);        solution.put("AGU", Serine);
        solution.put("AUC", Isoleucine);        solution.put("ACC", Threonine);         solution.put("AAC", Asparagine);        solution.put("AGC", Serine);
        solution.put("AUA", Isoleucine);        solution.put("ACA", Threonine);         solution.put("AAA", Lysine);            solution.put("AGA", Arginine);
        solution.put("AUG", Methionine);        solution.put("ACG", Threonine);         solution.put("AAG", Lysine);            solution.put("AGG", Arginine);

        solution.put("GUU", Valine);            solution.put("GCU", Alanine);           solution.put("GAU", AsparticAcid);      solution.put("GGU", Glycine);
        solution.put("GUC", Valine);            solution.put("GCC", Alanine);           solution.put("GAC", AsparticAcid);      solution.put("GGC", Glycine);
        solution.put("GUA", Valine);            solution.put("GCA", Alanine);           solution.put("GAA", GlutamicAcid);      solution.put("GGA", Glycine);
        solution.put("GUG", Valine);            solution.put("GCG", Alanine);           solution.put("GAG", GlutamicAcid);      solution.put("GGG", Glycine);
    }
    
    private static double[] codon(String s) {
        double[] ret = new double[4*s.length()];
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if ("ACGU".indexOf(c) < 0) {
                throw new RuntimeException("Bad codon - only ACGU accepted.");
            }
            if (c == 'A') ret[4*i]     = 1;
            if (c == 'C') ret[4*i + 1] = 1;
            if (c == 'G') ret[4*i + 2] = 1;
            if (c == 'U') ret[4*i + 3] = 1;
        }
        return ret;
    }
    
    public static void main(String[] args) {
        // Create the network
        int[] hiddenLayerSizes = {32};
        Backpropagation mlp = new Backpropagation(12, hiddenLayerSizes, 20);
        //mlp.setWeightsInit(0.5);
        mlp.setIterations(10000);
        
        // Build the input data from the above
        double[][] input = new double[64][12];
        double[][] answers = new double[64][20];
        { // I want to use i as counter without leaking it, so here's a block
	        int i = 0;
	        for (Map.Entry<String, double[]> entry : solution.entrySet()) {
	            input[i] = codon(entry.getKey());
	            answers[i] = entry.getValue();
	            i++;
	        }
        }
        
        // Train the network
        mlp.trainPatterns(input, answers);
        
        // Test the network
        double[] test = mlp.run(codon("GCA"));
        for (int i = 0; i < test.length; ++i) {
        	System.out.println(test[i]*100);
        }
    }
}