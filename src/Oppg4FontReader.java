import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Oppg4FontReader {
	
	private String[] fontStrings;
	
	private boolean[][] readFont(String font) {
		while (font.charAt(0) != '-' && font.charAt(0) != '#') {
			font = font.substring(1);
		}
		String[] lines = font.split("\n");
		int letterHeight = lines.length;
		String[] testLine = lines[0].split(" ");
		int numLetters = testLine.length;
		int letterWidth = testLine[0].length();
		boolean[][] output = new boolean[numLetters][letterHeight*letterWidth];
		
		for (int i = 0; i < lines.length; ++i) {
			String line = lines[i];
			String[] letterLines = line.split(" ");
			for (int j = 0; j < letterLines.length; ++j) {
				String letterLine = letterLines[j];
				for (int k = 0; k < letterLine.length(); ++k) {
					char c = letterLine.charAt(k);
					boolean[] letter = output[j];
					if (c == '#')
						letter[i*letterWidth + k] = true;
				}
			}
		}
		
		return output;
	}
	
	public boolean[][] getFont(int num) {
		if (num < 0 || num >= fontStrings.length) {
			throw new ArrayIndexOutOfBoundsException("No such element.");
		}
		return readFont(fontStrings[num]);
	}
	
	public Oppg4FontReader(String filename) throws IOException {
		String contents = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
		fontStrings = contents.split("===");
	}
	
}
