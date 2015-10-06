package util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public abstract class StringProcessing {
	final static Charset ENCODING = StandardCharsets.UTF_8;

	/**
	 * Reads the lines of a file and returns them as an ArrayList
	 * @param fileName
	 * @return An ArrayList<String> where each of the elements is a line.
	 * @throws IOException
	 */
	public static ArrayList<String> readFileLines(String fileName) throws IOException {
		Path path = Paths.get(fileName);
		ArrayList<String> fileLines = new ArrayList<String>();
		
		try (Scanner scanner = new Scanner(path, ENCODING.name())) {
			while (scanner.hasNextLine()) {
				fileLines.add(scanner.nextLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return fileLines;
	}
	
	/**
	 * For each string in stringList, only keep the left part of the leftmost '#' if there is any.
	 * @param stringList
	 * @return	stringList with all of its comments stripped.
	 */
	public static ArrayList<String> stripComments(ArrayList<String> stringList) {
		
		for (String str : stringList) {
			str = str.split("#")[0];
		}
		
		return stringList;
	}
	
	/**
	 * Removes all empty and whitespace lines from an ArrayList<String>.
	 * @param stringList
	 * @return A copy of stringList, with all empty (and whitespace) lines removed.
	 */
	public static ArrayList<String> removeAllEmptyLines(ArrayList<String> stringList) {
		final String EMPTY_LINE = "\\s*";

		ArrayList<String> emptyLines = new ArrayList<String>();
		
		for (String str : stringList) {
			if (Pattern.matches(EMPTY_LINE, str))
				emptyLines.add(str);
		}
		stringList.removeAll(emptyLines);
		
		return stringList;
	}
	
	/**
	 * Tokenizes each of the strings in stringList. Separator is whitespace.
	 * @param stringList
	 * @return
	 */
	public static TokenizedLines tokenizeLines(ArrayList<String> stringList) {
		TokenizedLines tokenizedLines = new TokenizedLines();
		
		for (String str : stringList) {
			ArrayList<String> tokens = new ArrayList<String>(Arrays.asList(str.split("\\s+")));
			tokenizedLines.add(tokens);
		}
		
		return tokenizedLines;
	}
}
